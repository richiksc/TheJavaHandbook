package com.richiksc.javadatatypes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DTGrid
    .OnFragmentInteractionListener {

  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private Drawer mDrawer;
  private MenuItem swapView;
  private int currentView = 0;
  private DTGrid gridFragment;


  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle(
          getResources().getStringArray(R.array.sections)[2]);
    }

    final DataType[] dataTypeArr =
        {
            new DataType(DefType.INTEGER, R.string.title_int,
                R.string.declare_int, R.drawable.avatar_int, this),
            new DataType(DefType.BOOLEAN, R.string.title_bool,
                R.string.declare_bool, R.drawable.avatar_boolean, R.drawable.boolean_square, this),
            new DataType(DefType.STRING, R.string.title_string,
                R.string.declare_string, R.drawable.avatar_string, this),
            new DataType(DefType.DOUBLE, R.string.title_double,
                R.string.declare_double, R.drawable.avatar_double, this)
        };

    String[] appSectionNames = getResources().getStringArray(R.array.sections);

    PrimaryDrawerItem[] appSections = new PrimaryDrawerItem[appSectionNames.length];
    for (int i = 0; i < appSectionNames.length; i++) {
      appSections[i] = new PrimaryDrawerItem()
          .withIdentifier(i)
          .withName(appSectionNames[i]);
    }

    mDrawer = new DrawerBuilder(this)
        .withToolbar(toolbar)
        .withHeader(R.layout.drawer_header)
        .addDrawerItems(appSections)
        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
          @Override
          public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            Log.d("tag", String.valueOf(position));
            if(position != 3) {
              mDrawer.setSelection(2);
              Log.d("tag", String.valueOf(view != null));
              showUnsupportedSnackbar();
            }
            return false;
          }
        })
        .build();
    mDrawer.setSelection(2, false);

    mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    mRecyclerView.setHasFixedSize(true);

    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    mAdapter = new DTListAdapter(
        getLayoutInflater(),
        Arrays.asList(dataTypeArr),
        new OnItemClickListener() {
          @Override
          public void onItemClick(DataType item, int position) {
            Log.d("MainActivity", "Item Clicked: " + item.getName() + " " + position);
            launchDetailScreen(item, mRecyclerView);
          }
        });

    mRecyclerView.setAdapter(mAdapter);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    gridFragment = new DTGrid();
    fragmentTransaction
        .add(R.id.gridviewcontainer, gridFragment)
        .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    swapView = menu.findItem(R.id.action_switch_view);
    swapView.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        swapDataTypeView();
        return false;
      }
    });
    return true;
  }

  public void swapDataTypeView() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.right_slide_in, R.anim.right_slide_out);
    if(gridFragment.isHidden()) {
      ft.show(gridFragment);
      swapView.setIcon(R.drawable.ic_view_list);
      Log.d("gridFragment", "Shown");
    } else {
      ft.hide(gridFragment);
      swapView.setIcon(R.drawable.ic_view_module);
      Log.d("gridFragment", "Hidden");
    }
    ft.commit();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.

    //noinspection SimplifiableIfStatement

    return super.onOptionsItemSelected(item);
  }

  public void launchDetailScreen(DataType type, View view) {
    for (int i : DefType.supportedTypes) {
      if(type.getId() == i) {
        Intent intent = new Intent(this, DefinitionActivity.class);
        intent.putExtra(DefinitionActivity.KEY, type.getId());
        startActivity(intent);
        return;
      }
    }
    Snackbar.make(view, "That data type not supported currently", Snackbar.LENGTH_LONG).show();
  }

  public void readMore(View v) {
    Uri webpage = Uri.parse("http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes" +
        ".html");
    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  private void showUnsupportedSnackbar() {
    Snackbar.make(findViewById(R.id.content_main),
        R.string.coming_soon,
        Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void onItemSelect(int position) {

    // Do nothing

  }
}

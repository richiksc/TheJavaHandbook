package com.richiksc.javadatatypes;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DefinitionActivity extends AppCompatActivity {

  public static final String KEY = "type";
  private int currType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_definition);
    currType = getIntent().getExtras().getInt(KEY, 0);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getStringId(currType));
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        shareDefinition(currType);
      }
    });

    if(getSupportActionBar() != null) {
      ActionBar supportBar = getSupportActionBar();
      supportBar.setDisplayHomeAsUpEnabled(true);
      supportBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

    }

    TextView textDeclare = (TextView) findViewById(R.id.text_declare);
    TextView textDef = (TextView) findViewById(R.id.text_def);
    TextView textValRange = (TextView) findViewById(R.id.text_val_range);

    textDeclare.setText(getTypeDeclarationText(currType));

    textDef.setText(getDefId(currType));
    textValRange.setText(getValRangeId(currType));

  }

  private void shareDefinition(int type) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, constructDefinitionText(type));

    sendIntent.setType("text/plain");
    startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
  }

  private String constructDefinitionText(int type) {
    Resources res = getResources();
    return String.format("Java Data Type: %s%n%s%n%s%n%s",
        res.getString(getStringId(type)),
        getTypeDeclarationText(type),
        res.getString(getDefId(type)),
        res.getString(getValRangeId(type)));
  }

  private int getValRangeId(int typeId) {
    switch (typeId) {
      case DefType.INTEGER:
        return R.string.value_range_int;
      case DefType.BOOLEAN:
        return R.string.value_range_bool;
      default:
        return R.string.value_range_default;
    }
  }

  private int getDefId(int typeId) {
    switch (typeId) {
      case DefType.INTEGER:
        return R.string.def_int;
      case DefType.BOOLEAN:
        return R.string.def_bool;
      default:
        return R.string.def_default;
    }
  }

  private int getStringId(int typeId) {
    switch (typeId) {
      case DefType.INTEGER:
        return R.string.title_int;
      case DefType.BOOLEAN:
        return R.string.title_bool;
      default:
        break;
    }
    return R.string.title_default;
  }

  private int getTypeDeclarationId(int typeId) {
    switch (typeId) {
      case DefType.INTEGER:
        return R.string.declare_int;
      case DefType.BOOLEAN:
        return R.string.declare_bool;
      default:
        break;
    }
    return R.string.title_default;
  }

  private String getTypeDeclarationText(int typeId) {
    return String.format(
        getString(R.string.type_declare),
        getString(getTypeDeclarationId(typeId))
    );
  }
}

package com.richiksc.javadatatypes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DTGrid.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DTGrid#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DTGrid extends Fragment {

  private OnFragmentInteractionListener mListener;

  public DTGrid() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment DTGrid.
   */
  // TODO: Rename and change types and number of parameters
  public static DTGrid newInstance() {
    DTGrid fragment = new DTGrid();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final DataType[] dataTypeArr =
        {
            new DataType(DefType.INTEGER, R.string.title_int,
                R.string.declare_int, R.drawable.avatar_int, getContext()),
            new DataType(DefType.BOOLEAN, R.string.title_bool,
                R.string.declare_bool, R.drawable.avatar_boolean, R.drawable.boolean_square, getContext()),
            new DataType(DefType.STRING, R.string.title_string,
                R.string.declare_string, R.drawable.avatar_string, getContext()),
            new DataType(DefType.DOUBLE, R.string.title_double,
                R.string.declare_double, R.drawable.avatar_double, R.drawable.double_square ,getContext())
        };

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_data_type_grid, container, false);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.grid_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(
        new DTGridAdapter(
            getLayoutInflater(savedInstanceState),
            Arrays.asList(dataTypeArr),
            new OnItemClickListener() {
              @Override
              public void onItemClick(DataType item, int position) {
                Log.d("DTGrid", "Item Clicked: " + item.getName() + " " + position);
                onItemSelected(position);
              }
            }
        ));
    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onItemSelected(int position) {
    if (mListener != null) {
      mListener.onItemSelect(position);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onItemSelect(int position);
  }
}

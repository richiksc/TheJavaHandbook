package com.richiksc.javadatatypes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Richik SC on 1/18/2017.
 */

public class DTGridAdapter extends RecyclerView.Adapter<DTGridAdapter
    .DTGridItemViewHolder> {
  private LayoutInflater inflater;
  private List<DataType> mDataset;
  private OnItemClickListener listener;

  public DTGridAdapter(LayoutInflater inflater, List<DataType> dataset, OnItemClickListener
      listener) {
    this.inflater = inflater;
    this.mDataset = dataset;
    this.listener = listener;
  }

  @Override
  public DTGridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = inflater.inflate(R.layout.grid_item_data_type, parent, false);
    DTGridItemViewHolder mViewHolder = new DTGridItemViewHolder(itemView);
    mViewHolder.view = itemView;
    mViewHolder.typeName = (TextView) itemView.findViewById(R.id.tvTypeNameGrid);
    mViewHolder.background = (ImageView) itemView.findViewById(R.id.imageViewBg);

    return mViewHolder;
  }

  @Override
  public void onBindViewHolder(DTGridItemViewHolder holder, int position) {
    DataType dataType = mDataset.get(position);
    holder.bind(dataType, position, this.listener);
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }

  public static class DTGridItemViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView typeName;
    ImageView background;

    public DTGridItemViewHolder(View itemView) {
      super(itemView);
      this.view = itemView;

    }

    public void bind(final DataType dataType,
                     final int position,
                     final OnItemClickListener listener)
    {
      typeName.setText(dataType.getName());
      background.setImageDrawable(dataType.getSquareImage());
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onItemClick(dataType, position);
        }
      });
    }

  }
}

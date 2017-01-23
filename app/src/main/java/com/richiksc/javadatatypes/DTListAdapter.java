package com.richiksc.javadatatypes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Richik SC on 7/14/2016.
 */
public class DTListAdapter extends RecyclerView.Adapter<DTListAdapter.DTListItemViewHolder> {
  private LayoutInflater inflater;
  private List<DataType> mDataset;
  private OnItemClickListener listener;

  public DTListAdapter(LayoutInflater inflater, List<DataType> dataset, OnItemClickListener
      listener) {
    this.inflater = inflater;
    this.mDataset = dataset;
    this.listener = listener;
  }

  @Override
  public DTListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = inflater.inflate(R.layout.list_item_data_type, parent, false);
    DTListItemViewHolder mViewHolder = new DTListItemViewHolder(itemView);
    mViewHolder.view = itemView;
    mViewHolder.typeName = (TextView) itemView.findViewById(R.id.tvTypeName);
    mViewHolder.typeDeclaration = (TextView) itemView.findViewById(R.id.tvTypeDeclaration);
    mViewHolder.imgAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);

    return mViewHolder;
  }

  @Override
  public void onBindViewHolder(DTListItemViewHolder holder, int position) {
    DataType dataType = mDataset.get(position);
    holder.bind(dataType, position, this.listener);
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }

  public static class DTListItemViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView typeName;
    TextView typeDeclaration;
    ImageView imgAvatar;

    public DTListItemViewHolder(View itemView) {
      super(itemView);
      this.view = itemView;
    }

    public void bind(final DataType dataType,
                     final int position,
                     final OnItemClickListener listener)
    {
      typeName.setText(dataType.getName());
      typeDeclaration.setText(dataType.getDeclaration());
      imgAvatar.setImageDrawable(dataType.getAvatar());
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onItemClick(dataType, position);
        }
      });
    }
  }
}
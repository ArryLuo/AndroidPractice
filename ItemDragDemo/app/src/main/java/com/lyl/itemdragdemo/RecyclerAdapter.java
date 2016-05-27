package com.lyl.itemdragdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyl.itemdragdemo.drag.ItemHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Wing_Li
 * 2016/5/27 0027.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> implements ItemHelper {

    private List<String> mItems = new ArrayList<>();

    public RecyclerAdapter(List<String> items) {
        mItems = items;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
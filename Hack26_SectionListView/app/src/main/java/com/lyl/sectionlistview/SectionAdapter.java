/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.lyl.sectionlistview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SectionAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strs;

    public SectionAdapter(Activity activity, String[] strs) {
        this.activity = activity;
        this.strs = strs;
    }

    @Override
    public int getCount() {
        return strs == null ? 0 : strs.length;
    }

    @Override
    public String getItem(int position) {
        return strs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);
            holder.header = (TextView) view.findViewById(R.id.header);
            holder.label = (TextView) view.findViewById(R.id.label);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //如果是首字母发生变化，就改变 header 标题
        String label = getItem(position);
        if (position == 0 || getItem(position - 1).charAt(0) != label.charAt(0)) {
            holder.header.setVisibility(View.VISIBLE);
            holder.header.setText(label.substring(0, 1));
        } else {
            holder.header.setVisibility(View.GONE);
        }
        holder.label.setText(label);

        return view;
    }

    class ViewHolder {
        TextView header;
        TextView label;
    }
}

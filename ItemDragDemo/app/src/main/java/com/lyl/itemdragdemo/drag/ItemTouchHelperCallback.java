package com.lyl.itemdragdemo.drag;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lyl.itemdragdemo.RecyclerAdapter;

/**
 * Wing_Li
 * 2016/5/27 0027.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private RecyclerAdapter mAdapter;

    public ItemTouchHelperCallback(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //上下拖动
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //左右滑动，可以设置只能往左 滑动
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);//dragFlags：拖动的方向；swipeFlags滑动的方向
    }

    /**
     * 上下移动时调用
     *
     * @param recyclerView
     * @param viewHolder   被拖着的 item。从哪
     * @param target       目标item。 到那
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    /**
     * 侧滑删除的时候调用
     *
     * @param viewHolder 滑动的item
     * @param direction 滑动方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}

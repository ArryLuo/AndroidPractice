package com.lyl.itemdragdemo.drag;

/**
 * Wing_Li
 * 2016/5/27 0027.
 */
public interface ItemHelper {

    /**
     * 移动
     * @param fromPosition 从哪
     * @param toPosition 到哪
     */
    void onItemMove(int fromPosition, int toPosition);

    /**
     * 删除
     * @param position 位置
     */
    void onItemDismiss(int position);
}

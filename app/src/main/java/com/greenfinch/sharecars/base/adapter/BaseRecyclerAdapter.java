package com.greenfinch.sharecars.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * 适用于RecyclerView的抽象Adapter，封装了数据集合，ViewHolder的创建于绑定过程，简化子类的操作
 * @param <D> 数据集合中的数据类型
 * @param <V> ViewHolder 的类型
 */
public abstract class BaseRecyclerAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    /**
     * RecycleView中的数据集合
     */
    protected ArrayList<D> mDataSet = new ArrayList();

    private ClickListener<D> clickListener;

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(V viewHolder, int position) {
        final D item = mDataSet.get(position);
        bindDataToItemView(viewHolder, item);
        setupItemViewClickListener(viewHolder, item);
    }

    public void setOnItemClickListener(ClickListener<D> mItemClickListener){
        this.clickListener = mItemClickListener;
    }

    protected void setupItemViewClickListener(V viewHolder, final D item){
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.click(item);
                }
            }
        });
    }

    protected D getItem(int position){
        return mDataSet.get(position);
    }

    public void addItem(ArrayList<D> items){
        items.removeAll(mDataSet);
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId){
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
    }

    /**
     * 将数据绑定到ItemView上
     * @param viewHolder
     * @param item
     */
    protected abstract void bindDataToItemView(V viewHolder, D item);

    public interface ClickListener<T>{
        void click(T item);
    }
}
package com.huier.listviewcategory;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作者：张玉辉
 * 时间：2017/8/4.
 */

public class CategoryAdapter extends BaseAdapter {
    /** 表示列表标题 **/
    private static final int TYPE_CATEGORY_ITEM = 0;
    /** 表示列表项 **/
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Category> mCategories;

    public CategoryAdapter(Context context,ArrayList<Category> categories){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCategories = categories;
    }

    @Override
    public int getCount() {
        int count = 0;
        if(null != mCategories){
            for(Category category:mCategories){
                count += category.getItemCount();
            }
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        // 异常情况处理
        if(null == mCategories || position<0 || position>getCount()){
            return null;
        }
        // 同一分类内，第一个元素的索引值
        int categoryFirstIndex = 0;
        for(Category category:mCategories){
            int size = category.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position - categoryFirstIndex;
            if(categoryIndex < size){
                return category.getItem(categoryIndex);
            }
            categoryFirstIndex += size;
        }
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if(null == mCategories || position<0 || position>getCount()){
            return TYPE_ITEM;
        }
        // 同一分类内，第一个元素的索引值
        int categoryFirstIndex = 0;
        for(Category category:mCategories){
            int size = category.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position-categoryFirstIndex;
            if(categoryIndex == 0){
                return TYPE_CATEGORY_ITEM;
            }
            categoryFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(position);
        ViewHolder viewHolder = null;
        if(null == convertView){
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1,null);
            TextView textView = (TextView)convertView.findViewById(android.R.id.text1);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = textView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        switch (itemViewType){
            case TYPE_ITEM:
                viewHolder.mTextView.setBackgroundColor(Color.WHITE);
                viewHolder.mTextView.setTextColor(Color.BLACK);
                viewHolder.mTextView.setTextSize(14);
                viewHolder.mTextView.setPadding(12,0,0,0);
                break;
            case TYPE_CATEGORY_ITEM:
                viewHolder.mTextView.setBackgroundColor(Color.BLUE);
                viewHolder.mTextView.setTextColor(Color.WHITE);
                viewHolder.mTextView.setTextSize(16);
                viewHolder.mTextView.setPadding(10,0,0,0);
                break;
        }
        viewHolder.mTextView.setText((String)getItem(position));
        return convertView;
    }

    /** 覆写BaseAdapter.areAllItemsEnabled和BaseAdapter.isEnabled两个方法，确保分类Item不可点击 **/
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == TYPE_ITEM;
    }

    private class ViewHolder{
        TextView mTextView;
    }
}

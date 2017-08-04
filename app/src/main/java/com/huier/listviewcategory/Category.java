package com.huier.listviewcategory;

import java.util.ArrayList;

/**
 * 作者：张玉辉
 * 时间：2017/8/4.
 */

public class Category {
    private String mCategoryName;
    private ArrayList<String> mCategoryList = new ArrayList<String>();

    public Category(String categoryName){
        this.mCategoryName = categoryName;
    }

    public void addItem(String categoryItem){
        mCategoryList.add(categoryItem);
    }

    public String getItem(int position){
        if(position == 0){
            return mCategoryName;
        }else{
            return mCategoryList.get(position-1);
        }
    }

    public int getItemCount(){
        return mCategoryList.size()+1;
    }
}

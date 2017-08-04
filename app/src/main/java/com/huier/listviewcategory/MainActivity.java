package com.huier.listviewcategory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private ListView mListView;
    private CategoryAdapter mCategoryAdapter;
    private ArrayList<Category> mListData = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mListView = (ListView)findViewById(R.id.lv_category);
        mCategoryAdapter = new CategoryAdapter(mContext,mListData);
        requestData();
        mListView.setAdapter(mCategoryAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(mContext,mCategoryAdapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestData(){
        Category categoryOne = new Category("路人甲");
        categoryOne.addItem("马三立");
        categoryOne.addItem("赵本山");
        categoryOne.addItem("郭德纲");
        categoryOne.addItem("周立波");
        Category categoryTwo = new Category("事件乙");
        categoryTwo.addItem("**贪污");
        categoryTwo.addItem("**照门");
        Category categoryThree = new Category("书籍丙");
        categoryThree.addItem("10天学会***");
        categoryThree.addItem("**大全");
        categoryThree.addItem("**秘籍");
        categoryThree.addItem("**宝典");
        categoryThree.addItem("10天学会***");
        categoryThree.addItem("10天学会***");
        categoryThree.addItem("10天学会***");
        categoryThree.addItem("10天学会***");
        Category categoryFour = new Category("书籍丙");
        categoryFour.addItem("河南");
        categoryFour.addItem("天津");
        categoryFour.addItem("北京");
        categoryFour.addItem("上海");
        categoryFour.addItem("广州");
        categoryFour.addItem("湖北");
        categoryFour.addItem("重庆");
        categoryFour.addItem("山东");
        categoryFour.addItem("陕西");

        mListData.add(categoryOne);
        mListData.add(categoryTwo);
        mListData.add(categoryThree);
        mListData.add(categoryFour);
        mCategoryAdapter.notifyDataSetChanged();
    }
}

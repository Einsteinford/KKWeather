package com.einsteinford.kkweather.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.einsteinford.kkweather.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KK on 2016-08-22.
 */
public class CityListViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<String> mHotList;     //热门城市列表
    private ArrayList<String> mSearchList;
    private ArrayList<String> mItemList;
    private ListView mMusicListView;
    private static String TAG = "CityListViewFragment";
    private ArrayAdapter<String> mCityListAdapter;
    private EditText mInputCity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_listview_fragment, container, false);
        //传入listView列表和EditText所存在的xml布局文件
        //实例化输入栏
        mInputCity = (EditText) view.findViewById(R.id.input_city);
        mMusicListView = (ListView) view.findViewById(R.id.city_list_view);
        //从布局文件中读取listView实例

        mInputCity.addTextChangedListener(new CityTextWatcher());

        mHotList = new ArrayList<>();      //一个存有数据库查询结果的容器
        mHotList.add("test");
        mHotList.add("test");
        mHotList.add("test");
        mHotList.add("test");

        mItemList = new ArrayList<>();  //绑定在Adapter上可动态变化的字符串容器
        mItemList.addAll(mHotList);
        mCityListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mItemList);

        mMusicListView.setAdapter(mCityListAdapter);
        //然后关联Adapter
        mMusicListView.setOnItemClickListener(this);
        return view;

    }


    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class CityTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (mSearchList == null){
                mSearchList = new ArrayList<>();  //实例化
            }
            mSearchList.clear();  //清空搜索结果
            if (mInputCity.getText() != null) {
                String inputText = mInputCity.getText().toString();
                mSearchList.add("我是KK");
                mSearchList.add("我是KK");
                //TODO 增加一个查询String，从数据库返回ArrayList的方法,建议返回一个Map<K,V>
                //TODO ，然后把K提取出来组成List，当点击后，得到K，再来Map中取出V（Id）
                mItemList.clear();
                mItemList.addAll(mSearchList);
                //绑定一个新的Adapter用于显示
                mCityListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }


}

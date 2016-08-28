package com.einsteinford.kkweather.fragment;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.einsteinford.kkweather.activity.MainActivity;
import com.einsteinford.kkweather.activity.SettingActivity;
import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.utils.HttpUtil;
import com.einsteinford.kkweather.utils.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by KK on 2016-08-22.
 */
public class SelectedCitiesFragment extends Fragment {
    private ArrayList<String> mItemList;
    private ListView mMusicListView;
    private static String TAG = "SelectedCitiesFragment";
    private ArrayAdapter<String> mCityListAdapter;
    private ArrayList<String> mKeyArrayList;
    private LocalBroadcastManager mLocalBroadcastManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mItemList = new ArrayList<>();  //绑定在Adapter上可动态变化的字符串容器
        mItemList = (ArrayList<String>) bundle.getSerializable("city_names");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_cities_listview_fragment, container, false);
        //传入listView列表和EditText所存在的xml布局文件
        mMusicListView = (ListView) view.findViewById(R.id.selected_cities_list_view);
        //从布局文件中读取listView实例
        ImageButton BtnGotoNext = (ImageButton) view.findViewById(R.id.goto_next);




        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mKeyArrayList = new ArrayList<>();


        mCityListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mItemList);

        mMusicListView.setAdapter(mCityListAdapter);  //然后关联Adapter
        mMusicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.SELECTED_CITY)
                        .putExtra(MainActivity.HANDLE_WEATHER,2)
                        .putExtra("position",i);
                mLocalBroadcastManager.sendBroadcast(intent);
                mItemList.remove(i);
                mCityListAdapter.notifyDataSetChanged();
                return true;
            }
        });



        BtnGotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListViewFragment cityListViewFragment = new CityListViewFragment();
                SettingActivity settingActivity = (SettingActivity) getActivity();
                settingActivity.replaceFragment(cityListViewFragment);
            }
        });
        return view;
    }



}

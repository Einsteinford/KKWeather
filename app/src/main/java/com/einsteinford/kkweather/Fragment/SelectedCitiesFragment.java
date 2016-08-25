package com.einsteinford.kkweather.Fragment;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.einsteinford.kkweather.Activity.MainActivity;
import com.einsteinford.kkweather.Activity.SettingActivity;
import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.Utils.HttpUtil;
import com.einsteinford.kkweather.Utils.JsonUtil;

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
    private AssetManager am;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.selected_cities_listview_fragment, container, false);
        //传入listView列表和EditText所存在的xml布局文件
        mMusicListView = (ListView) view.findViewById(R.id.selected_cities_list_view);
        //从布局文件中读取listView实例
        ImageButton BtnGotoNext = (ImageButton) view.findViewById(R.id.goto_next);
        Button addCityList = (Button) view.findViewById(R.id.add_city_list);

        am = getActivity().getAssets();

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mKeyArrayList = new ArrayList<>();
        mItemList = new ArrayList<>();  //绑定在Adapter上可动态变化的字符串容器
        mItemList.add("test");
        mItemList.add("test");
        mItemList.add("test");

        mCityListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mItemList);

        mMusicListView.setAdapter(mCityListAdapter);  //然后关联Adapter
        mMusicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                getActivity().finish();
            }
        });

        addCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                MakeSQL();
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

    private void MakeSQL() {
        try {
            InputStream in = am.open("allchina.json");
            HttpUtil.sendRequestWithLocalJson(in, new HttpUtil.HttpCallbackListener() {
                @Override
                public void onFinish(final String response) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            JsonUtil.parseJSONToCityList(getActivity(), response);
                        }
                    }).start();
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

}

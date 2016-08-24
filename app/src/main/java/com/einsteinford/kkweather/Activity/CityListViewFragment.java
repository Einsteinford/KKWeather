package com.einsteinford.kkweather.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.einsteinford.kkweather.R;

import java.util.ArrayList;


/**
 * Created by KK on 2016-08-22.
 */
public class CityListViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<String> mList;
    private ListView mMusicListView;
    private static String TAG = "CityListViewFragment";
    private ArrayAdapter<String> mCityListAdapter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_listview_fragment, container, false);
        //传入listView列表所存在的xml布局文件
        mList = new ArrayList<>();
        mList.add("test");
        mList.add("test");
        mList.add("test");
        mList.add("test");
        mCityListAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mList);
        mMusicListView = (ListView) view.findViewById(R.id.city_list_view);
        //从布局文件中读取listView实例
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
}

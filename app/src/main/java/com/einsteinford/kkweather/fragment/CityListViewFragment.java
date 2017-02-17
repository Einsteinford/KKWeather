package com.einsteinford.kkweather.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.einsteinford.kkweather.activity.MainActivity;
import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.utils.CityListDatabaseUtil;
import com.einsteinford.kkweather.utils.HttpUtils;
import com.einsteinford.kkweather.utils.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

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
    private ArrayList<String> mKeyArrayList;
    private LocalBroadcastManager mLocalBroadcastManager;
    private AssetManager am;
    private ProgressDialog pd;
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();// 关闭ProgressDialog
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_listview_fragment, container, false);
        //传入listView列表和EditText所存在的xml布局文件
        //实例化输入栏
        mInputCity = (EditText) view.findViewById(R.id.input_city);
        mMusicListView = (ListView) view.findViewById(R.id.city_list_view);
        //从布局文件中读取listView实例
        Button addCityList = (Button) view.findViewById(R.id.add_city_list);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        am = getActivity().getAssets();

        mInputCity.addTextChangedListener(new CityTextWatcher());
        mKeyArrayList = new ArrayList<>();      //存储Id的容器
        mHotList = new ArrayList<>();      //一个存有热门城市的容器
        mHotList.add("北京");mKeyArrayList.add("CN101010100");
        mHotList.add("上海");mKeyArrayList.add("CN101020100");
        mHotList.add("广州");mKeyArrayList.add("CN101280101");
        mHotList.add("深圳");mKeyArrayList.add("CN101280601");
        mHotList.add("杭州");mKeyArrayList.add("CN101210101");
        mHotList.add("重庆");mKeyArrayList.add("CN101040100");

        mItemList = new ArrayList<>();  //绑定在Adapter上可动态变化的字符串容器
        mItemList.addAll(mHotList);
        mCityListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, mItemList);

        mMusicListView.setAdapter(mCityListAdapter);
        //然后关联Adapter
        mMusicListView.setOnItemClickListener(this);
        addCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 显示ProgressDialog */
                pd = ProgressDialog.show(getActivity(), "城市搜索库", "初始化中，请稍后……");
                pd.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
                MakeSQL();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i(TAG, "onItemClick: "+mKeyArrayList.get(i));
        Intent intent = new Intent(MainActivity.SELECTED_CITY)
                .putExtra(MainActivity.HANDLE_WEATHER,1)
                .putExtra("ID",mKeyArrayList.get(i))
                .putExtra("name",deleteStr(mItemList.get(i)));
        mLocalBroadcastManager.sendBroadcast(intent);
        getActivity().finish();
    }

    public String deleteStr(String name){  //用来统一格式化城市名，一般来说自己选的城市不太会有两个相同的地名
        String str[] = name.split("-");
        return str[0];
    }

    private void MakeSQL() {
        try {
            InputStream in = am.open("allchina.json");
            HttpUtils.sendRequestWithLocalJson(in, new HttpUtils.HttpCallbackListener() {
                @Override
                public void onFinish(final String response) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            JsonUtil.parseJSONToCityList(getActivity(), response, new JsonUtil.JsonCallbackListener() {
                                @Override
                                public void onFinish() {
                                    mHandler.sendEmptyMessage(0);
                                }
                                @Override
                                public void onError(Exception e) {
                                    e.printStackTrace();
                                }
                            });
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


    class CityTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mSearchList == null){
                mSearchList = new ArrayList<>();  //实例化
            }
            mSearchList.clear();  //清空搜索结果
            if (!mInputCity.getText().toString().equals("")) {
                Map<String,String> response;
                String inputText = mInputCity.getText().toString();
                response = CityListDatabaseUtil.queryCity(inputText,getActivity());
                mKeyArrayList.clear();
                mKeyArrayList.addAll(response.keySet());    //将所有id组成list，编号即将跟listview的item相同
                Log.i(TAG, "onTextChanged: "+ mKeyArrayList);
                for (int s = 0; s < mKeyArrayList.size(); s++) {
                    mSearchList.add(response.get(mKeyArrayList.get(s)));
                }
                mItemList.clear();
                mItemList.addAll(mSearchList);
                //绑定一个新的Adapter用于显示
                mCityListAdapter.notifyDataSetChanged();
            }
        }

    }

}

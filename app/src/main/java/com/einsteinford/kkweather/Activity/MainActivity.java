package com.einsteinford.kkweather.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.ViewGroup;
import android.widget.TextView;

import com.einsteinford.kkweather.Fragment.WeatherFragment;
import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.UI.ZoomOutPageTransformer;
import com.einsteinford.kkweather.Utils.HttpUtil;
import com.einsteinford.kkweather.Utils.SaveDataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KK on 2016-08-10.
 */
public class MainActivity extends BaseActivity {
    public static final String HANDLE_MAP = "handleMap";
    public static int PAGE_NUM = 2;
    private WeatherPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private String TAG = getClass().getSimpleName();
    private Map<String,String> MyCities;
    private ArrayList<String> mCityIdArrayList;
    public static final int SHOW_RESPONSE = 0;
    public static final String SELECTED_CITY = "com.einsteinford.kkweather.selected_city";
    private static final String HEWEATHER_API_URL = "https://api.heweather.com/";
    private static final String PATH_WEATHER = "x3/weather";

    private static final String CITY_ID = "cityid=CN101210101";

    private static final String CITY_NAME = "city=chongqing";
    private static final String APP_SECRET = "key=03d784e8221542f68271c249076f4d7b";
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter mIntentFilter;
    private StringBuilder ID  = new StringBuilder();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    Log.i(TAG, "handleMessage: 已接收");
                    String response = (String) msg.obj;
//                    responseText.setText(response);
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCities = new HashMap<>();
        mCityIdArrayList = new ArrayList<String>();

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter(SELECTED_CITY);
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());  //添加动画效果
        mAdapter = new WeatherPagerAdapter(getSupportFragmentManager());  //通过子类实例化
        mViewPager.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_origin);

        setSupportActionBar(toolbar);  //将toolbar设置为ActionBar,确保toolbar已经布置在layout中，切不为空
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                mCityIdArrayList.clear();
                ArrayList<String> mCityNameArrayList = new ArrayList<String>();
                mCityIdArrayList.addAll(MyCities.keySet());
                for (int s = 0; s < mCityIdArrayList.size(); s++) {
                    mCityNameArrayList.add(MyCities.get(mCityIdArrayList.get(s)));
                }
                intent.putExtra("city_names", mCityNameArrayList);
                startActivity(intent);
                return true;
            }
        });

//        responseText = (TextView) findViewById(R.id.response_text);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //关联菜单项
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.send_request:
//                HttpUtil.sendHttpRequest(HEWEATHER_API_URL + PATH_WEATHER + "?" + CITY_ID + "&" + APP_SECRET, new HttpUtil.HttpCallbackListener() {
//                    @Override
//                    public void onFinish(String response) {
//                        Log.i(TAG, "onFinish: !!!!!!!!!");
//                        SaveDataUtil.write2SDFromString(MainActivity.this, response, "new.json");
//                        SendMessage(response);
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//                break;
//            case R.id.local_request:
//                try {
//                    InputStream in = am.open("hz.json");
//                    HttpUtil.sendRequestWithLocalJson(in, new HttpUtil.HttpCallbackListener() {
//                        @Override
//                        public void onFinish(String response) {
//                            Log.i(TAG, "onFinish: !!!!!!!!!");
//                            SaveDataUtil.write2SDFromString(MainActivity.this, response, "hz.json");
//
//                            SendMessage(response);
//                            JsonUtil.parseJSONToWeather(response);
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            e.printStackTrace();
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//
//            case R.id.button:
////                String str = IpAddressUtil.getLocalIpAddress(MainActivity.this);
//                String str = IpAddressUtil.getIpAddress();
//                Log.d(TAG, "onClick: " + str);
//        }
//    }

    private void SendMessage(String response) {
        Message message = new Message();
        message.what = SHOW_RESPONSE;
        message.obj = response;
        mHandler.sendMessage(message);
    }

//    public List<Fragment> fragments = new ArrayList<>();
//    public void addFragment(Fragment fragment){
//        fragments.add(fragment);
//    }
//    public void removeFragment(Fragment fragment){
//        fragments.add(fragment);
//    }
//    public void RemoveAllFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        for (Fragment fragment : fragments) {
//            if (fragment!=null) {
//                transaction.remove(fragment);
//            }
//        }
//        transaction.commit();
//    }



    private void sendHttpUri(String City_id) {
        HttpUtil.sendHttpRequest(HEWEATHER_API_URL + PATH_WEATHER + "?" + City_id + "&" + APP_SECRET, new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Log.i(TAG, "onFinish: !!!!!!!!!");
                SaveDataUtil.write2SDFromString(MainActivity.this, response, "new.json");
                SendMessage(response);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra(HANDLE_MAP, 0)){
                case 1:
                    String id = intent.getStringExtra("ID");
                    String name = intent.getStringExtra("name");
                    MyCities.put(id,name);      //key肯定唯一
                    ID.delete(0,ID.length());
                    mCityIdArrayList.clear();
                    mCityIdArrayList.addAll(MyCities.keySet());
                    ID.append(mCityIdArrayList.indexOf(id));
                    Log.i(TAG, "add: " + ID +"  "+ mCityIdArrayList);
                    mAdapter.notifyDataSetChanged();
                    mViewPager.setCurrentItem(Integer.parseInt(ID.toString()));
                    break;
                case 2:
                    int position = intent.getIntExtra("position",0);
                    ID.delete(0,ID.length());
                    ID.append(mCityIdArrayList.get(position));
                    MyCities.remove(mCityIdArrayList.get(position));
                    mCityIdArrayList.clear();
                    mCityIdArrayList.addAll(MyCities.keySet());
                    Log.i(TAG, "remove: ");
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    private class WeatherPagerAdapter extends FragmentStatePagerAdapter {

        public WeatherPagerAdapter(FragmentManager fm) {
            super(fm);
            Log.i(TAG, "WeatherPagerAdapter: ");
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return  POSITION_NONE;
        }

        @Override
        public int getCount() {
            return MyCities.size();
        }

        @Override
        public Fragment getItem(int position) {
            WeatherFragment fragment = new WeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ID",mCityIdArrayList.get(position));
            Log.i(TAG, "getItem: "+mCityIdArrayList.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }
    }



}

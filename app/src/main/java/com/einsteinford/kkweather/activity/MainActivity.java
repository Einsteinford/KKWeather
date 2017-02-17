package com.einsteinford.kkweather.activity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.ViewGroup;

import com.einsteinford.kkweather.bean.WeatherBean;
import com.einsteinford.kkweather.fragment.WeatherFragment;
import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.ui.DepthPageTransformer;
import com.einsteinford.kkweather.utils.CityListDatabaseUtil;
import com.einsteinford.kkweather.utils.HttpUtils;
import com.einsteinford.kkweather.utils.JsonUtil;
import com.einsteinford.kkweather.utils.SaveDataUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by KK on 2016-08-10.
 */
public class MainActivity extends BaseActivity {
    public static final String HANDLE_WEATHER = "handleWeather";
    public static final String CUSTOM_CITIES = "custom_cities";
    public static final int INSERT_SUCCEED = 0;
    public static final int UPDATE_SUCCEED = 1;
    public static final String SELECTED_CITY = "com.einsteinford.kkweather.selected_city";
    private static final String HEWEATHER_API_URL = "https://api.heweather.com/";
    private static final String PATH_WEATHER = "x3/weather";
    private static final String APP_SECRET = "key=03d784e8221542f68271c249076f4d7b";
    private WeatherPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private String TAG = getClass().getSimpleName();
    private HashMap<String, String> MyCities;
    private ArrayList<String> mCityIdArrayList;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter mIntentFilter;
    private StringBuilder ID = new StringBuilder();
    //    private ContentValues mValues;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INSERT_SUCCEED:
                    Log.i(TAG, "handleMessage:INSERT_SUCCEED 已接收");
//                    String[] strings = (String[]) msg.obj;
//                    final String id = strings[0];
//                    String NAME = strings[1];
//                    CityListDatabaseUtil.insertCityWeather(id, mValues, NAME, MainActivity.this, new CityListDatabaseUtil.SQLCallbackListener() {
//                        @Override
//                        public void onFinish(String name) {
//                            MyCities.put(id, name);      //key肯定唯一
//                            ID.delete(0, ID.length());    //清空以前的位置
//                            mCityIdArrayList.clear();
//                            mCityIdArrayList.addAll(MyCities.keySet());     //这是城市ID集合
//                            ID.append(mCityIdArrayList.indexOf(id));    //这次的城市添加到了第几页
//                            Log.i("sendHttpUri", "add: " + ID + "  " + mCityIdArrayList);
//                            mAdapter.notifyDataSetChanged();        //提醒Adapter，页面信息已经变化,getCount会执行很多次
//                            mViewPager.setCurrentItem(Integer.parseInt(ID.toString()));     //将页面设置为刚才添加的那一页
//                            SaveDataUtil.save2SharedPreferences(MainActivity.this, MyCities, CUSTOM_CITIES);    //最后把Map保存到SP中
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            e.printStackTrace();
//                        }
//                    });
                    break;
                case UPDATE_SUCCEED:
                    Log.i(TAG, "handleMessage:UPDATE_SUCCEED 已接收");
//                    ContentValues values = (ContentValues) msg.obj;
//                    String updateId = "CN" + String.valueOf(msg.arg1);
//                    CityListDatabaseUtil.updateCityWeather(updateId, values, MainActivity.this, new CityListDatabaseUtil.SQLCallbackListener() {
//                        @Override
//                        public void onFinish(String name) {
//                            mAdapter.notifyDataSetChanged();        //提醒Adapter，页面信息已经变化,getCount会执行很多次
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            e.printStackTrace();
//                        }
//                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCities = new HashMap<>();
        MyCities = SaveDataUtil.getFromSharedPreferences(MainActivity.this, CUSTOM_CITIES);
        mCityIdArrayList = new ArrayList<String>();
        mCityIdArrayList.clear();
        mCityIdArrayList.addAll(MyCities.keySet());
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter(SELECTED_CITY);
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());  //添加动画效果
        mAdapter = new WeatherPagerAdapter(getSupportFragmentManager());  //通过子类实例化
        mViewPager.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_origin);

        setSupportActionBar(toolbar);  //将toolbar设置为ActionBar,确保toolbar已经布置在layout中，切不为空
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.miProfile:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        mCityIdArrayList.clear();
                        ArrayList<String> mCityNameArrayList = new ArrayList<String>();
                        mCityIdArrayList.addAll(MyCities.keySet());
                        for (int s = 0; s < mCityIdArrayList.size(); s++) {
                            mCityNameArrayList.add(MyCities.get(mCityIdArrayList.get(s)));
                        }
                        intent.putExtra("city_names", mCityNameArrayList);
                        startActivity(intent);
                        break;
                    case R.id.miCompose:
                        sendUpdateHttpUri();     //全部更新的方法,想要更新所有天气按下去就对了！
                        break;
                }

                return true;
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {     //关联菜单项
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void sendHttpUri(Intent intent) {
        String id = intent.getStringExtra("ID");//发送网络请求，得到需解析的json
        //TODO 将以下方法替换掉
//        HttpUtils.sendHttpRequest(HEWEATHER_API_URL + PATH_WEATHER + "?cityid=" + id + "&" + APP_SECRET, new HttpUtils.HttpCallbackListener() {
//            @Override
//            public void onFinish(String response) {
//                SaveDataUtil.write2SDFromString(MainActivity.this, response, "new.json");
//                //每次都记录到本地,手动缓存，太低效
//                mValues = JsonUtil.parseJSONToWeather(response);

//            }
//
//            @Override
//            public void onError(Exception e) {
//                e.printStackTrace();
//            }
//        });
        Observable<WeatherBean> observable = HttpUtils
                .getInstance()
                .sendRetrofitRequest(id);   //得到observable对象

        observable
                .doOnNext(new Action1<WeatherBean>() {      //在onNext前进行的操作
                    @Override
                    public void call(WeatherBean WeatherBean) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())    //在主线程中观察
                .subscribe(new Subscriber<WeatherBean>() {      //订阅
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherBean WeatherBean) {
                        //进行数据库操作
                        Logger.i(WeatherBean.getHeWeather5().get(0).getBasic().getCity());
                    }
                });

        String NAME = intent.getStringExtra("name");
        SendInsertMessage(id, NAME);

    }


    private void sendUpdateHttpUri() {
        mCityIdArrayList.clear();
        mCityIdArrayList.addAll(MyCities.keySet());
        for (int i = 0; i < mCityIdArrayList.size(); i++) {
            final String id = mCityIdArrayList.get(i);
            //发送网络请求，得到需解析的json
//            HttpUtils.sendHttpRequest(HEWEATHER_API_URL + PATH_WEATHER + "?cityid=" + id + "&" + APP_SECRET, new HttpUtils.HttpCallbackListener() {
//                @Override
//                public void onFinish(String response) {
//                    ContentValues values = JsonUtil.parseJSONToWeather(response);   //返回其中一个城市的json
//                    SendUpdateMessage(id, values);
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    e.printStackTrace();
//                }
//            });
        }
    }

    private class LocalReceiver extends BroadcastReceiver {     //接收城市的增删请求
        //虽然可接收全局广播，但是此应用中的广播发送方式都是本地发送
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra(HANDLE_WEATHER, 0)) {
                case 1:     //往用户的列表里新增一个城市
                    sendHttpUri(intent);
                    break;
                case 2:     //直接从用户列表里删除此城市
                    int position = intent.getIntExtra("position", 0);
                    ID.delete(0, ID.length());
                    ID.append(mCityIdArrayList.get(position));
                    MyCities.remove(mCityIdArrayList.get(position));
                    mCityIdArrayList.clear();
                    mCityIdArrayList.addAll(MyCities.keySet());
                    Log.i(TAG, "remove: ");
                    mAdapter.notifyDataSetChanged();
                    SaveDataUtil.save2SharedPreferences(MainActivity.this, MyCities, CUSTOM_CITIES);
                    break;
                default:
                    break;
            }
        }
    }

    public class AutoUpdateReceiver extends BroadcastReceiver {      //仅用于接收系统日期改变的广播
        @Override
        public void onReceive(Context context, Intent intent) {
            sendUpdateHttpUri();
        }
    }

    private class WeatherPagerAdapter extends FragmentStatePagerAdapter {   //用于ViewPager

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
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return MyCities.size();
        }

        @Override
        public Fragment getItem(int position) {
            WeatherFragment fragment = new WeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ID", mCityIdArrayList.get(position));     //从Map中得到自己的城市ID

            Log.i(TAG, "getItem: " + mCityIdArrayList.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    private void SendInsertMessage(String CITY_ID, String CITY_NAME) {
        Message message = new Message();
        message.what = INSERT_SUCCEED;
        message.obj = new String[]{CITY_ID, CITY_NAME};
        mHandler.sendMessage(message);
    }

    private void SendUpdateMessage(String id, ContentValues values) {
        Message message = new Message();
        message.what = UPDATE_SUCCEED;
        //String没法携带，把id的前2个字符CN删掉，转换为int
        String[] strings = id.split("CN");
        int ID = Integer.parseInt(strings[1]);
        Log.i(TAG, "SendUpdateMessage:" + ID);
        message.arg1 = ID;
        message.obj = values;
        mHandler.sendMessage(message);
    }
}

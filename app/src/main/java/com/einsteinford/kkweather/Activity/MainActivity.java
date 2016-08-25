package com.einsteinford.kkweather.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.UI.WeatherPagerAdapter;
import com.einsteinford.kkweather.UI.ZoomOutPageTransformer;
import com.einsteinford.kkweather.Utils.HttpUtil;
import com.einsteinford.kkweather.Utils.SaveDataUtil;

/**
 * Created by KK on 2016-08-10.
 */
public class MainActivity extends BaseActivity {
    public static int PAGE_NUM = 2;
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private String TAG = getClass().getSimpleName();

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

    private TextView responseText;

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
            String id = intent.getStringExtra("ID");
            String name = intent.getStringExtra("name");
            Log.i(TAG, "onReceive: " + id + name);
        }
    }


}

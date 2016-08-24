package com.einsteinford.kkweather.UI;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KK on 2016-08-22.
 */
public class ItemCityList implements Parcelable {
    //存储了每一首歌的信息,后期实现了Parcelable接口，方便与利用Intent传递此类
    private String mMusicTitle;    //需要显示的歌曲名称
    private String mSinger;       //需要显示的歌手名字
    private int ImageId; //需要显示的图片资源编号
    private String mMusicFileName;     //保存文件名(xxx.mp3)，利于寻找

    public ItemCityList(String musicTitle, String singer, int imageId, String musicFileName) {
        mMusicTitle = musicTitle;
        mSinger = singer;
        ImageId = imageId;
        mMusicFileName = musicFileName;
    }

    protected ItemCityList(Parcel in) {
        mMusicTitle = in.readString();   //1
        mSinger = in.readString();       //2
        ImageId = in.readInt();          //3
        mMusicFileName = in.readString();//4
    }

    public static final Creator<ItemCityList> CREATOR = new Creator<ItemCityList>() {
        @Override
        public ItemCityList createFromParcel(Parcel in) {
            return new ItemCityList(in);
        }

        @Override
        public ItemCityList[] newArray(int size) {
            return new ItemCityList[size];
        }
    };

    public String getMusicTitle() {
        return mMusicTitle;
    }

    public String getSinger() {
        return mSinger;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getMusicFileName() {
        return mMusicFileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mMusicTitle);        //1
        parcel.writeString(mSinger);            //2
        parcel.writeInt(ImageId);               //3
        parcel.writeString(mMusicFileName);     //4
    }
}

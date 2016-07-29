package com.bignerdranch.android.criminalintent0728;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-28.
 */
public class Crime {

    private UUID mId;
    private  String mTitle;
    private Date mDate; //범죄가 발생한 날짜
    private Date mTime;//범죄가 발생한 시간
    private boolean mSolved;


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Crime(){
        //고유한 식별자를 생성한다.
        mId = UUID.randomUUID();
        mDate =  new Date();
        mTime = new Date(); //이거 때문에 한참 ...ㅜ 기본에 충실!!



    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {

        mDate = date;


    }


    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}

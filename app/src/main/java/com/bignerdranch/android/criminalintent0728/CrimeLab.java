package com.bignerdranch.android.criminalintent0728;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent0728.database.CrimeBaseHelper;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-28i.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List <Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){//Context 객체는 Activity 또는 Service와 같은 객체를 나타냄.
        if( sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private  CrimeLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        //다른 클래스에서 CrimeLab의 인스턴스를 생성할 때는 이 생성자를 호출할 수 없다. 반드시 get()메소드를 호출해야 함

        mCrimes = new ArrayList<>();
     /*   for(int i=0;i<100;i++){
            Crime crime = new Crime();
            crime.setTitle("범죄 #"+i);
            crime.setSolved(i%2 == 0);//짝수 번째 요소에는 true를 임의 설정한다.
            mCrimes.add(crime);
        }*/
    }


    public void addCrime(Crime c){  //새로운 범죄 추가하기
        mCrimes.add(c);
    }
    public List<Crime>getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for( Crime crime: mCrimes){
            if( crime.getId().equals(id)){
                return crime;
            }
        }
        return  null;
    }



}

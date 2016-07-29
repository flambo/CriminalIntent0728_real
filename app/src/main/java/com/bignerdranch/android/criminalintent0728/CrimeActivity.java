package com.bignerdranch.android.criminalintent0728;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalIntent0728.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId ){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        //createFragment()라는 추상 메소드는 프래그먼트 인스턴스 생성에 사용되는 메소드이다.
        //SingleFragmentActivity의 서브 클래스에서는 이 메서드를 구현하여 액티비티가 호스팅하는 프래그먼트 인스턴스를 반환해야 한다.

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);



    }
}

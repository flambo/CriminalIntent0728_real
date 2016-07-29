package com.bignerdranch.android.criminalintent0728;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-28.
 */
public class CrimeFragment extends Fragment {

    ///CrimeFragement는 모델 및 뷰 객체와 상호 동작하는 컨트롤러이다. 따라서 특정 범죄의 상세 내역을 보여주고
    // 사용자가 수정한 상세 내역을 변경하는 것이 CrimeFragment의 역할이다!

   private static final String ARG_CRIME_ID = "crime_id";
    private static  final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME="DialogTime";
    private  static final int REQUEST_DATE = 0;
    private  static  final int REQUEST_TIME = 1;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;


    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*   UUID crimeId = (UUID) getActivity().getIntent()
               .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);*/
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID) ;
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v  = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mCrime.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mDateButton = (Button)v.findViewById(R.id.crime_date);
       // Date nowDate = new Date();

        updateDate();
        // mDateButton.setText(mCrime.getDate().toString());
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
             //   DatePickerFragment dialog = new DatePickerFragment();


                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());

                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mTimeButton = (Button)v.findViewById(R.id.crime_time);
       mTimeButton.setText(mCrime.getTime().toString());
     updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog =  TimePickerFragment.newInstance(mCrime.getTime());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                dialog.show(manager,DIALOG_TIME);
            }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //범죄 해결 여부 속성 값 설정.
                mCrime.setSolved(isChecked);
            }
        });
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode!= Activity.RESULT_OK){
            return;
        }

        if( requestCode == REQUEST_DATE){
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }

      if(requestCode == REQUEST_TIME){
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
           mCrime.setTime(date);
          updateTime();
        }
    }

   private void updateTime() {
       Date now = mCrime.getTime();
       SimpleDateFormat sdf = new SimpleDateFormat("h:mm aa");
       mTimeButton.setText(sdf.format(now));

    }

    private void updateDate() {


        Date now = mCrime.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("E요일,  MM dd,yyyy");
        mDateButton.setText(sdf.format(now));
    }
}

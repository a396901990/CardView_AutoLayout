package com.demo.activity;


import java.util.ArrayList;
import java.util.List;

import com.dean.autolayout.CardManager;
import com.dean.fragment.CardFragment;
import com.demo.R;
import com.demo.fragment.*;

import android.os.Bundle;
import android.app.Activity;

/**
 * Demo Activity to show how to use CardView_AutoLayout library.
 * Create your fragment which extended from CardFragment and added into CardManager
 * 
 * Author: Dean Guo
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCrads();
		setContentView(R.layout.main_layout);
	}
	
    public void initCrads()
    {
        List<CardFragment> mCardFragments =  new ArrayList<CardFragment>(); 
        mCardFragments.add(new IDFragment());
        mCardFragments.add(new CalcFragment());
        mCardFragments.add(new PicFragment());
        mCardFragments.add(new ClockFragment());
        
        CardManager.getInstance().setCardFragments(mCardFragments);
    }

}

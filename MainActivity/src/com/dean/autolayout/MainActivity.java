package com.dean.autolayout;

import java.util.List;

import com.dean.fragment.CalcFragment;
import com.dean.fragment.CardFragment;
import com.dean.fragment.ClockFragment;
import com.dean.fragment.IDFragment;
import com.dean.fragment.PicFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_layout);
	}

}

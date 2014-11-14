package com.dean.autolayout;

import com.dean.fragment.CalcFragment;
import com.dean.fragment.ClockFragment;
import com.dean.fragment.IDFragment;
import com.dean.fragment.PicFragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity
    extends Activity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main_layout);
    }
    
   

}

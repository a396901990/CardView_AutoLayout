package com.demo.fragment;

import com.dean.fragment.CardFragment;
import com.demo.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The fragment to show clock
 * 
 * Author: Dean Guo
 */
public class ClockFragment
    extends CardFragment
{

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        isShowTitle(false);
        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.clock_card, null, false));
        
    }

}

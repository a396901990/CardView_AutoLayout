package com.demo.fragment;

import com.dean.fragment.CardFragment;
import com.demo.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * The fragment to show identity information
 * 
 * @author Dean Guo
 */
public class IDFragment
    extends CardFragment
{
    ImageView arrow;

    LinearLayout expandedView;

    boolean isShow = false;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("InflateParams") @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        getView().setOnTouchListener(this);
        
        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.id_card, null, false));
        setTitle("RESUME");
        arrow = (ImageView) getView().findViewById(R.id.arrow);
        expandedView = (LinearLayout) getView().findViewById(R.id.expandedView);

        arrow.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick( View arg0 )
                {
                    expandedView.setVisibility(isShow ? View.VISIBLE : View.GONE);
                    arrow.setImageDrawable(isShow ? getResources().getDrawable(android.R.drawable.arrow_up_float) : getResources().getDrawable(android.R.drawable.arrow_down_float));
                    isShow = !isShow;
                }
            });
    }

}

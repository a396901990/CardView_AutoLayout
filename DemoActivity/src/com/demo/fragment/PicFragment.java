package com.demo.fragment;

import com.dean.fragment.CardFragment;
import com.demo.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

/**
 * The fragment to show picture
 * 
 * @author Dean Guo
 */
public class PicFragment
    extends CardFragment
{
    ViewAnimator flipper;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        getView().setOnTouchListener(this);
        
        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.pic_card, null, false));

        flipper = (ViewAnimator) getView().findViewById(R.id.flipper);
        flipper.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick( View v )
                {
                    flipper.showNext();
                }
            });
    }

}

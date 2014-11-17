package com.demo.fragment;

import com.dean.fragment.CardFragment;
import com.demo.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

/**
 * The fragment to show picture
 * 
 * Author: Dean Guo
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

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        isShowTitle(false);
        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.pic_card, null, false));

        flipper = (ViewAnimator) getView().findViewById(R.id.flipper);
        getView().setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick( View v )
                {
                    flipper.showNext();

                }
            });
    }

}

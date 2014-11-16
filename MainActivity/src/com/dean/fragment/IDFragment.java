package com.dean.fragment;

import com.dean.autolayout.CardManager;
import com.dean.autolayout.MyScrollView;
import com.dean.autolayout.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * 
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

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.id_card, null, false));
        setTitle("∏ˆ»ÀΩÈ…‹");
        Button delBtn = (Button) getView().findViewById(R.id.card_del_btn);
        delBtn.setOnClickListener(new View.OnClickListener()
            {
                
                @Override
                public void onClick( View arg0 )
                {
                    deleteSelf();
                }
            });
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

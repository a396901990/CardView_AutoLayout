package com.dean.fragment;

import com.dean.autolayout.CardManager;
import com.dean.autolayout.R;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardFragment
    extends Fragment
{

    private boolean isShowTitle = false;

    private PagerAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        FrameLayout root = new FrameLayout(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(layoutParams);
        root.setPadding(10, 10, 10, 10);
        return root;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView()
    {

        ViewGroup root = (ViewGroup) getView();
        if (root == null)
        {
            return;
        }

        root.removeAllViewsInLayout();
        View.inflate(getActivity(), R.layout.cardview_container, root);

        View titleView = root.findViewById(R.id.card_title);
        titleView.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_in);
        animation.setDuration(500);
        root.clearAnimation();
        root.startAnimation(animation);
    }

    public void setCardView( View v )
    {
        ViewGroup root = (ViewGroup) getView();
        if (root != null)
        {
            ViewGroup container = (ViewGroup) root.findViewById(R.id.card_container);
            if (v.getParent() != null)
            {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            container.addView(v);
        }
    }

    public void setTitle( String title )
    {
        ViewGroup root = (ViewGroup) getView();
        if (root == null)
        {
            return;
        }
        isShowTitle = true;
        TextView titleView = (TextView) root.findViewById(R.id.card_title_text);
        titleView.setText(title);
    }

    public void isShowTitle( boolean isShowTitle )
    {
        this.isShowTitle = isShowTitle;
    }

    public void deleteSelf()
    {
        startActivity(new Intent(getActivity(), getActivity().getClass()));
        getActivity().finish();
    }

    public PagerAdapter getAdapter()
    {
        return mAdapter;
    }

    public void setAdapter( PagerAdapter mAdapter )
    {
        this.mAdapter = mAdapter;
    }

}

package com.dean.autolayout;

import java.util.List;

import com.dean.fragment.CardFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Custom ScrollView as a parent view
 * 
 * Author: Dean Guo
 */
public class MyScrollView
    extends ScrollView
{
    int columns = 0;

    Context mContext;

    PagerAdapter mAdapter;

    public MyScrollView( Context context )
    {

        super(context);

    }

    private void initAdapter()
    {
        FragmentManager fm = ((Activity) mContext).getFragmentManager();
        mAdapter = new Adapter(fm, CardManager.getInstance().getCardFragments());
    }

    public MyScrollView( Context context, AttributeSet attrs )
    {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyScrollView);
        columns = typedArray.getInteger(R.styleable.MyScrollView_columns, 0);
        typedArray.recycle();
        initView(columns);
        initCrads();
        initAdapter();
    }

    public void initCrads()
    {
//        List<CardFragment> mCardFragments =  new ArrayList<CardFragment>(); 
//        mCardFragments.add(new IDFragment());
//        mCardFragments.add(new CalcFragment());
//        mCardFragments.add(new PicFragment());
//        mCardFragments.add(new ClockFragment());
//        
//        CardManager.getInstance().setCardFragments(mCardFragments);
    }
    
    private void initView( int columns )
    {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        AutoCardLayout autoCardLayout = new AutoCardLayout(getContext(), columns);
        autoCardLayout.setId(R.id.auto_fragment);
        linearLayout.addView(autoCardLayout);
        addView(linearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        populate();
    }

    public void populate()
    {
        post(new Runnable()
            {

                @Override
                public void run()
                {
                    ViewGroup container = getContainer();
                    mAdapter.startUpdate(container);
                    for (int i = 0; i < mAdapter.getCount(); i++)
                    {
                        createNewItem(container, i);
                    }
                    mAdapter.finishUpdate(container);
                }
            });
    }

    public Object createNewItem( final ViewGroup container, final int index )
    {
        Object item = mAdapter.instantiateItem(container, index);
        return item;
    }

    public AutoCardLayout getContainer()
    {
        return (AutoCardLayout) ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    public class Adapter
        extends FragmentPagerAdapter
    {
        public Adapter( FragmentManager fm, List<CardFragment> mCards )
        {
            super(fm);
            this.mCards = mCards;
        }

        private List<CardFragment> mCards;

        @Override
        public Fragment getItem( final int position )
        {
            Fragment frag = mCards.get(position);
            return frag;
        }

        @Override
        public int getCount()
        {
            return mCards.size();
        }

    }

}

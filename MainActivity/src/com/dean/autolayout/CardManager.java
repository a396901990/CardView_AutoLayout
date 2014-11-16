package com.dean.autolayout;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;

import com.dean.fragment.CardFragment;

/**
 * Created by temp on 8/29/14.
 */
public final class CardManager
{
    private static CardManager sInstance;

    public static CardManager getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new CardManager();
        }
        return sInstance;
    }

    private PagerAdapter mAdapter;

    private List<CardFragment> mCardFragments = new ArrayList<CardFragment>();

    public void addFragment( CardFragment fragment )
    {
        mCardFragments.add((CardFragment) fragment);
    }

    public void removeFragment( CardFragment fragment )
    {
        if (mCardFragments.contains(fragment))
        {
            mCardFragments.remove(fragment);
        }
        updateFragment();
    }

    public List<CardFragment> getCardFragments()
    {
        return mCardFragments;
    }

    public void setCardFragments( List<CardFragment> mCardFragments )
    {
        this.mCardFragments = mCardFragments;
    }

    public void updateFragment()
    {
    }
}

package com.dean.adapter;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.dean.fragment.CardFragment;

/**
 * The adapter for Card Fragment
 * 
 * @author Dean Guo
 */
public class CardFragmentAdapter
    extends FragmentPagerAdapter
{
    public CardFragmentAdapter( FragmentManager fm, List<CardFragment> mCards )
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

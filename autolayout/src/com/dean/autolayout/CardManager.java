package com.dean.autolayout;

import java.util.ArrayList;
import java.util.List;

import com.dean.fragment.CardFragment;

/**
 * Management the card fragment
 * 
 * Author: Dean Guo
 */
public final class CardManager {
	private static CardManager sInstance;

	public static CardManager getInstance() {
		if (sInstance == null) {
			sInstance = new CardManager();
		}
		return sInstance;

	}

	private List<CardFragment> mCardFragments = new ArrayList<CardFragment>();

	public void addFragment(CardFragment fragment) {
		mCardFragments.add((CardFragment) fragment);
	}

	public void removeFragment(CardFragment fragment) {
		if (mCardFragments.contains(fragment)) {
			mCardFragments.remove(fragment);
		}
		updateFragment();
	}

	public List<CardFragment> getCardFragments() {
		return mCardFragments;
	}

	public void setCardFragments(List<CardFragment> mCardFragments) {
		this.mCardFragments = mCardFragments;
	}

	public void updateFragment() {
	}
}

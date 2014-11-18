package com.dean.autolayout;

import com.dean.adapter.CardFragmentAdapter;
import com.dean.fragment.CardFragment;
import com.dean.fragment.CardFragment.CardActionListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Custom ScrollView as a parent view
 * 
 * @author Dean Guo
 */
public class CardScrollView
    extends ScrollView
    implements CardActionListener
{
    private int columns = 0;

    private Context mContext;

    private PagerAdapter mAdapter;

    public CardScrollView( Context context )
    {
        super(context);
    }

    private void initAdapter()
    {
        FragmentManager fm = ((Activity) mContext).getFragmentManager();
        mAdapter = new CardFragmentAdapter(fm, CardManager.getInstance().getCardFragments());
    }

    public CardScrollView( Context context, AttributeSet attrs )
    {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardScrollView);
        columns = typedArray.getInteger(R.styleable.CardScrollView_columns, 0);
        typedArray.recycle();
        initView(columns);
        initAdapter();
    }

    private void initView( int columns )
    {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        AutoCardLayout autoCardLayout = new AutoCardLayout(getContext(), columns);
        autoCardLayout.setId(R.id.auto_fragment);
        linearLayout.addView(autoCardLayout);
        addView(linearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        instantiateItem();
    }

    public void instantiateItem()
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

    public void destoryItem( final CardFragment f, final int position )
    {
        post(new Runnable()
            {

                @Override
                public void run()
                {
                    ViewGroup container = getContainer();
                    mAdapter.startUpdate(container);
                    mAdapter.destroyItem(container, position, f);
                    mAdapter.finishUpdate(container);
                }
            });
    }

    public Object createNewItem( final ViewGroup container, final int index )
    {
        CardFragment cardFragment = (CardFragment) mAdapter.instantiateItem(container, index);
        cardFragment.setCardActionListener(this);
        return cardFragment;
    }

    public AutoCardLayout getContainer()
    {
        return (AutoCardLayout) ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    @Override
    public void addCard( CardFragment fragment )
    {

    }

    @Override
    public void removeCard( CardFragment fragment )
    {
        int pos = CardManager.getInstance().getCardPosition(fragment);
        CardManager.getInstance().removeFragment(fragment);
        destoryItem(fragment, pos);
    }

}

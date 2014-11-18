package com.dean.fragment;

import java.util.Timer;
import java.util.TimerTask;

import com.dean.autolayout.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Parent Fragment of card
 * 
 * @author Dean Guo
 */
@SuppressLint(
    { "HandlerLeak", "ClickableViewAccessibility" })
public class CardFragment
    extends Fragment
    implements OnTouchListener
{

    private boolean isShowTitle = false;

    private boolean isLongClick = false;

    private Timer timer = null;

    private CardActionListener cardActionListener;

    private CardFragment mFragment;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        mFragment = this;
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
        TextView titleTextView = (TextView) root.findViewById(R.id.card_title_text);

        View titleView = root.findViewById(R.id.card_title);
        titleView.setVisibility(View.VISIBLE);
        titleTextView.setText(title);
    }

    public void isShowTitle( boolean isShowTitle )
    {
        this.isShowTitle = isShowTitle;
    }

    protected static final int LONG_CLICK = 0;

    protected static final int UP_CLICK = 1;

    private Handler mHandler = new Handler()
        {
            public void handleMessage( Message msg )
            {
                switch (msg.what)
                {
                case LONG_CLICK:
                    getView().findViewById(R.id.delete_view).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.container_view).setAlpha(0.2f);
                    isLongClick = true;
                    this.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if (isLongClick)
                                {
                                    cardActionListener.removeCard(mFragment);
                                }
                            }
                        }, 3000);
                    break;
                case UP_CLICK:
                    getView().findViewById(R.id.delete_view).setVisibility(View.GONE);
                    getView().findViewById(R.id.container_view).setAlpha(1f);
                    isLongClick = false;
                    break;
                }
            }

        };

    @Override
    public boolean onTouch( View v, MotionEvent ev )
    {
        switch (ev.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            timer = new Timer();
            timer.schedule(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        mHandler.sendEmptyMessage(LONG_CLICK);
                    }
                }, 1500);

            break;
        case MotionEvent.ACTION_UP:
            timer.cancel();
            timer = null;
            mHandler.sendEmptyMessage(UP_CLICK);
            break;
        }

        return true;
    }

    public CardActionListener getCardActionListener()
    {
        return cardActionListener;
    }

    public void setCardActionListener( CardActionListener cardActionListener )
    {
        this.cardActionListener = cardActionListener;
    }

    /**
     * Card action Listener to listening adding & removing action
     */
    public static interface CardActionListener
    {
        public void addCard( CardFragment fragment );

        public void removeCard( CardFragment fragment );
    }

}

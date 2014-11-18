package com.dean.autolayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Auto layout view by columns like waterfall layout
 * 
 * @author Dean Guo
 */
public class AutoCardLayout
    extends ViewGroup
{
    int column = 0;

    int margin = 20;

    public AutoCardLayout( Context context, int columns )
    {
        super(context);
        this.column = columns;
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {

        int width = MeasureSpec.getSize(widthMeasureSpec);

        int colWidthSpec = MeasureSpec.makeMeasureSpec((width - (column - 1) * margin) / column, MeasureSpec.EXACTLY);
        int colHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int[] colPosition = new int[column];

        for (int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            child.measure(colWidthSpec, colHeightSpec);
            colPosition[(i + 1 + column) % column] += child.getMeasuredHeight();
        }

        int height = 0;
        for (int j = 0; j < column; j++)
        {
            height = colPosition[j] > height ? colPosition[j] : height;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout( boolean changed, int l, int t, int r, int b )
    {
        int[] colPosition = new int[column];

        for (int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            int index = (i + 1 + column) % column == 0 ? column - 1 : (i + 1 + column) % column - 1;
            colPosition[index] += height;

            int left = l + index * (width + margin);
            int right = column == index + 1 ? r : left + width;
            int top = t + colPosition[index] - height;
            int bottom = top + height;
            child.layout(left, top, right, bottom);
        }
    }
}

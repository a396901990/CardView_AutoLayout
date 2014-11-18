package com.demo.fragment;

import com.dean.fragment.CardFragment;
import com.demo.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * The fragment of a calculation
 * 
 * @author Dean Guo
 */
public class CalcFragment
    extends CardFragment
{

    EditText displayText;

    double num1 = 0;

    double num2 = 0;

    double result = 0;

    int CalType = 0;

    boolean EquBtnDownFlag = false;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Override
    public void onActivityCreated( Bundle savedInstanceState )
    {
        getView().setOnTouchListener(this);

        super.onActivityCreated(savedInstanceState);
        setCardView(getActivity().getLayoutInflater().inflate(R.layout.calc_card, null, false));

        initCalc();
    }

    private void initCalc()
    {
        displayText = (EditText) getView().findViewById(R.id.result);
        displayText.setText(null);

        getView().findViewById(R.id.num0).setOnClickListener(handler);
        getView().findViewById(R.id.num1).setOnClickListener(handler);
        getView().findViewById(R.id.num2).setOnClickListener(handler);
        getView().findViewById(R.id.num3).setOnClickListener(handler);
        getView().findViewById(R.id.num4).setOnClickListener(handler);
        getView().findViewById(R.id.num5).setOnClickListener(handler);
        getView().findViewById(R.id.num6).setOnClickListener(handler);
        getView().findViewById(R.id.num7).setOnClickListener(handler);
        getView().findViewById(R.id.num8).setOnClickListener(handler);
        getView().findViewById(R.id.num9).setOnClickListener(handler);
        getView().findViewById(R.id.add).setOnClickListener(handler);
        getView().findViewById(R.id.subtract).setOnClickListener(handler);
        getView().findViewById(R.id.multiply).setOnClickListener(handler);
        getView().findViewById(R.id.divide).setOnClickListener(handler);
        getView().findViewById(R.id.point).setOnClickListener(handler);
        getView().findViewById(R.id.equal).setOnClickListener(handler);
        getView().findViewById(R.id.clear).setOnClickListener(handler);

    }

    public void pressNumButton( String buttonName )
    {
        if (EquBtnDownFlag)
        {
            displayText.setText(null);
            EquBtnDownFlag = false;
        }
        CharSequence temp = displayText.getText();
        String myString = temp.toString();
        myString += buttonName;
        displayText.setText(myString);
    }

    public void pressButton( int calcType )
    {
        CharSequence temp = displayText.getText();
        if (TextUtils.isEmpty(temp))
        {
            return;
        }
        String myString = temp.toString();
        num1 = Double.parseDouble(myString);
        CalType = calcType;
        displayText.setText(null);
    }

    public void pressEquButton()
    {
        CharSequence temp = displayText.getText();
        if (TextUtils.isEmpty(temp))
        {
            return;
        }
        String myString = temp.toString();
        num2 = Double.parseDouble(myString);
        switch (CalType)
        {
        case 0:
            result = num2;
            break;
        case 1:
            result = num1 + num2;
            break;
        case 2:
            result = num1 - num2;
            break;
        case 3:
            result = num1 * num2;
            break;
        case 4:
            result = num1 / num2;
            break;
        default:
            result = 0;
            break;
        }
        String temp_result = String.valueOf(result);
        displayText.setText(temp_result);
        EquBtnDownFlag = true;
    }

    private void pressClearButton()
    {
        num1 = 0;
        num2 = 0;
        displayText.setText(null);
        EquBtnDownFlag = true;
    }

    public View.OnClickListener handler = new View.OnClickListener()
        {
            public void onClick( View v )
            {
                switch (v.getId())
                {
                case R.id.num0:
                    pressNumButton("0");
                    break;
                case R.id.num1:
                    pressNumButton("1");
                    break;
                case R.id.num2:
                    pressNumButton("2");
                    break;
                case R.id.num3:
                    pressNumButton("3");
                    break;
                case R.id.num4:
                    pressNumButton("4");
                    break;
                case R.id.num5:
                    pressNumButton("5");
                    break;
                case R.id.num6:
                    pressNumButton("6");
                    break;
                case R.id.num7:
                    pressNumButton("7");
                    break;
                case R.id.num8:
                    pressNumButton("8");
                    break;
                case R.id.num9:
                    pressNumButton("9");
                    break;
                case R.id.point:
                    pressNumButton(".");
                    break;
                case R.id.add:
                    pressButton(1);
                    break;
                case R.id.subtract:
                    pressButton(2);
                    break;
                case R.id.multiply:
                    pressButton(3);
                    break;
                case R.id.divide:
                    pressButton(4);
                    break;
                case R.id.equal:
                    pressEquButton();
                    break;
                case R.id.clear:
                    pressClearButton();
                    break;
                default:
                    break;
                }
            }
        };

}
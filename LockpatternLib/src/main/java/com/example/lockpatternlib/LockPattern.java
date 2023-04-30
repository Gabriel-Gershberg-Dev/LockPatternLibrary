package com.example.lockpatternlib;

import static android.widget.GridLayout.ALIGN_MARGINS;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class LockPattern extends LinearLayout {

    private int dotCount;
    private int rows;
    private int columns;
    private ArrayList<DotData> dotsList;
    private ArrayList<Dot> dotsListView;
    private int dotRadius;
    private LottieAnimationView unlockColorBtu;
    private LottieAnimationView unlockPressedNumBtu;
    private PatternOperations patternOperations;
    private GridLayout gridLayout;

    public LockPattern(Context context) {
        super(context);
        init();
    }

    public LockPattern(Context context, AttributeSet attrs) {
        super(context, attrs);
        dotCount = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "dotCount", 9);
        rows = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "rows", 3);
        columns = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "columns", 3);
        dotRadius = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "dotRadius", 200);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        gridLayout = new GridLayout(getContext());
        gridLayout. setColumnCount(columns);
        gridLayout.setRowCount(rows);
        gridLayout.setAlignmentMode(ALIGN_MARGINS);
        gridLayout.setColumnOrderPreserved(false);
        this.addView(gridLayout);
        setDotsGridView();
        patternOperations= new PatternOperations(getContext());
        setButtons(patternOperations.loadDots()!=null);




    }

    private void setButtons(boolean patternIsStored){
    LinearLayout buttonsLayout=new LinearLayout(getContext());
    buttonsLayout.setOrientation(HORIZONTAL);

    if(!patternIsStored) {

        LottieAnimationView button1 = new LottieAnimationView(getContext());
        button1.setImageResource(R.drawable.ic_unlock);
        button1.setBackgroundColor(Color.WHITE);
        buttonsLayout.addView(button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDotsData();
                patternOperations.saveDots(dotsList);
                setInitialState();
                Toast.makeText(getContext(), "Lock was set successfully", Toast.LENGTH_SHORT).show();
                buttonsLayout.removeView(view);
                setButtons(patternOperations.loadDots()!=null);

            }
        });
    }
    else {

        Toast.makeText(getContext(), "Please choose a method to unlock the pattern", Toast.LENGTH_LONG).show();
        unlockPressedNumBtu = new LottieAnimationView(getContext());
        unlockPressedNumBtu.setImageResource(R.drawable.ic_lock);
        unlockPressedNumBtu.setBackgroundColor(Color.WHITE);
        buttonsLayout.addView(unlockPressedNumBtu);
        unlockPressedNumBtu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDotsData();
                    if (patternOperations.checkPatternPressCountMatch(dotsList, patternOperations.loadDots())) {
                        unlockPressedNumBtu.setImageResource(R.drawable.ic_unlock);
                        Toast.makeText(getContext(), "Unlocked successfully with current presses number", Toast.LENGTH_SHORT).show();

                    }

                    else {
                        Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();
                        setInitialState();
                    }


            }
        });
        unlockColorBtu = new LottieAnimationView(getContext());
        buttonsLayout.addView(unlockColorBtu);
        unlockColorBtu.setImageResource(R.drawable.ic_lock_color);
        unlockColorBtu.setBackgroundColor(Color.WHITE);
        unlockColorBtu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDotsData();
                    if (patternOperations.checkPatternColorMatch(dotsList, patternOperations.loadDots())) {
                        unlockColorBtu.setImageResource(R.drawable.ic_unlock_color);
                        Toast.makeText(getContext(), "Unlocked successfully with current color combination", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();


            }
        });
    }
        addView(buttonsLayout);
    }
    private void setDotsGridView(){
        dotsListView=new ArrayList<>();
        for (int i = 0; i < dotCount; i++) {
            Dot dot = new Dot(getContext(),dotRadius);
            gridLayout.addView(dot.getDot());
            dotsListView.add(dot);


        }

    }
    private void setDotsData(){
         dotsList = new ArrayList<>();
        for (int i = 0; i < dotCount; i++) {
            dotsList.add(new DotData(dotsListView.get(i).getDotRadius(),dotsListView.get(i).getPressCount(),dotsListView.get(i).getDotColor()));

        }

    }
    private void setInitialState(){
        gridLayout.removeAllViews();
        setDotsGridView();
    }
}
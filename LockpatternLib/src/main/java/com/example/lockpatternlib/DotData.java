package com.example.lockpatternlib;

import android.widget.ImageButton;

import java.util.ArrayList;

public class DotData {
    private int dotRadius;
    private int pressCount =0;
    private int dotColor;


    public DotData(int dotRadius, int pressCount, int dotColor){
        this.dotColor=dotColor;
        this.dotRadius=dotRadius;
        this.pressCount=pressCount;
    }



    public int getDotRadius() {
        return dotRadius;
    }

    public DotData setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
        return this;
    }

    public int getPressCount() {
        return pressCount;
    }

    public DotData setPressCount(int pressCount) {
        this.pressCount = pressCount;
        return this;
    }

    public int getDotColor() {
        return dotColor;
    }

    public DotData setDotColor(int dotColor) {
        this.dotColor = dotColor;
        return this;
    }
    public boolean comparePressedCount(DotData other){
        return other.getPressCount()-this.pressCount==0;
    }
    public boolean compareDotColor(DotData other){
        return other.getDotColor()== this.dotColor;
    }

}

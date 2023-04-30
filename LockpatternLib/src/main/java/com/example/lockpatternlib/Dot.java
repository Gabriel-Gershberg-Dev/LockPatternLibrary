package com.example.lockpatternlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Dot {
    private Context context;
    private int dotRadius;
    private int pressCount =0;
    private ImageButton dot;
    private ArrayList<Integer> colors = new ArrayList<>();
    private int dotColor;
    private Animation dotAnimation;


    public Dot(){}
    public Dot(Context context, int dotRadius){
        this.context=context;
        this.dotRadius=dotRadius;
        init();


    }
    private void init(){
        dot=new ImageButton(context);
       dotAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_animator);
        setColors();
        setDotView();
        dot.setOnClickListener(view -> {
            updateDotStatus();
            view.startAnimation(dotAnimation);


        });
    }

    private void setDotView(){
        int colorIndex= pressCount%colors.size();
        dotColor=colors.get(colorIndex);
        dot.setImageBitmap(createDotBitmap(dotColor,dotRadius));
        dot.setBackgroundColor(Color.WHITE);
        dot.setPadding(20,20,20,20);

    }
    private void updateDotStatus(){
        pressCount++;
        setDotView();
    }
    public int getDotRadius() {
        return dotRadius;
    }
    private Bitmap createDotBitmap(int color, int size) {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint);
        return bitmap;
    }
    private void setColors(){
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.rgb(128, 0, 128)); // purple
        colors.add(Color.rgb(255, 165, 0)); // orange
    }
    public int getPressCount() {
        return pressCount;
    }
    public ImageButton getDot() {
        return dot;
    }

    public Dot setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
        return this;
    }

    public Dot setPressCount(int pressCount) {
        this.pressCount = pressCount;
        return this;
    }

    public int getDotColor() {
        return dotColor;
    }

    public Dot setDot(ImageButton dot) {
        this.dot = dot;
        return this;
    }


}

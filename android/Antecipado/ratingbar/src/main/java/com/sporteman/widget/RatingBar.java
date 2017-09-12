package com.sporteman.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class RatingBar extends RelativeLayout {

    public RatingBar(Context context) {
        super(context);
    }

    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        clipPath.addPath(drawStar());
        canvas.clipPath(clipPath);

        canvas.drawColor(Color.YELLOW);
        super.onDraw(canvas);
    }

    Path drawStar(){
        Path path = new Path();
        float midX = getWidth()/2;
        float midY = getHeight()/2;
        path.moveTo(midX, midY);
        path.lineTo(midX+190, midY+300);
        path.lineTo(midX, midY+210);
        path.lineTo(midX-190, midY+300);
        path.lineTo(midX-160, midY+90);
        path.lineTo(midX-300, midY-70);
        path.lineTo(midX-100 ,midY-110);
        path.lineTo(midX, midY-300);
        path.lineTo(midX+100, midY-110);
        path.lineTo(midX+300, midY-70);
        path.lineTo(midX+160, midY+90);
        path.lineTo(midX+190, midY+300);
        return path;
    }
}

package com.example.bsw123.shapedroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bsw123 on 07/06/18.
 */

public class MakeShape extends View {

    float x, y;
    Bitmap bmp;
    Paint mPaint;
    ArrayList<Dimensions> pointsList=new ArrayList<>();
    ArrayList<RectAngleStore> Store=new ArrayList<>();
    float width = 200.0f;
    float height = 100.0f;

    boolean touched = false;

    public MakeShape(Context context) {
        super(context);
        x = y = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setDimensions(float width,float height){
        this.width=width;
        this.height=height;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Iterator<Dimensions> iter = pointsList.iterator();
        while(iter.hasNext()) {

            Dimensions touch = iter.next();
            float x = touch.getX();
            float y = touch.getY();
            float width=touch.getWidth();
            float height=touch.getHeight();
            //  if (touched) {
            canvas.drawRect(x - width / 2, y - height / 2, x + width / 2, y + height / 2, mPaint);

        }




    }

    public String print(){
        Iterator<RectAngleStore> iter2 = Store.iterator();
        String message = "";
        while(iter2.hasNext())
        {
            RectAngleStore yp = iter2.next();
            message+=yp.printCoordinates();
        }
        return  message;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touched = true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //getting the touched x and y position
            x = event.getX();
            y = event.getY();
            pointsList.add(new Dimensions(x, y,width,height));
            RectAngleStore object = new RectAngleStore();
            System.out.println("count");

            object.setX1coordinate(x - width / 2);
            object.setX2coordinate(x + width / 2);
            object.setX3coordinate(x + width / 2);
            object.setX4coordinate(x - width / 2);
            object.setY1coordinate(y - height / 2);
            object.setY2coordinate(y - height / 2);
            object.setY3coordinate(y + height / 2);
            object.setY4coordinate(y + height / 2);
            Store.add(object);
        }
        invalidate();
        return true;
    }
}
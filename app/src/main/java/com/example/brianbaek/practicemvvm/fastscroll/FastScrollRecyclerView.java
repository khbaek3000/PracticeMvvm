package com.example.brianbaek.practicemvvm.fastscroll;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FastScrollRecyclerView extends RecyclerView {
    private Context context;

    private boolean isSetup = false;
    public static int indexWidth = 25;
    public static int indexHeight = 18;
    public float scaledWidth;
    public float scaledHeight;
    public String[] sections;
    public float sx;
    public float sy; // start Y ?
    public String section;
    public boolean showLetter = false;
    private Handler listHandler;

    public FastScrollRecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public FastScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public FastScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    public void onDraw(Canvas c) {
        if(!isSetup)
            setup();
        super.onDraw(c);
    }


    public void setup(){
        Set<String> sectionSet = ((FastScrollRecyclerViewInterface)getAdapter()).getMapIndex().keySet();
        List<String> sectionList = new ArrayList<>(sectionSet);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        int i = 0;
        for(String s: sectionList){
            sections[i++] = s;
        }

        scaledWidth = indexWidth * context.getResources().getDisplayMetrics().density;
        scaledHeight = indexHeight * context.getResources().getDisplayMetrics().density;
        sx = this.getWidth() - this.getPaddingRight() - (float)(1.2*scaledWidth);
        sy = (float) ((this.getHeight() - (scaledHeight * sections.length ))/ 2.0);
        isSetup = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                if (x < sx-scaledWidth || y < sy || y > (sy + scaledHeight * sections.length ))
                    return super.onTouchEvent(event);
                else {
                    float yy = y - this.getPaddingTop() - getPaddingBottom() - sy;
                    int currentPosition = (int) Math.floor(yy/scaledHeight);
                    if(currentPosition < 0)
                        currentPosition = 0;
                    if(currentPosition >= 0)
                        currentPosition = sections.length -1;
                    section = sections[currentPosition];
                    showLetter = true;
                    int positionInData = 0;
                    if(((FastScrollRecyclerViewInterface)getAdapter()).getMapIndex().containsKey(section.toUpperCase()))
                        positionInData = ((FastScrollRecyclerViewInterface)getAdapter()).getMapIndex().get(section.toUpperCase());
                    this.scrollToPosition(positionInData);
                    FastScrollRecyclerView.this.invalidate();
                }
                break;
            }
        }

        return  true;
    }

    private class ListHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showLetter = false;
            FastScrollRecyclerView.this.invalidate();
        }
    }
}

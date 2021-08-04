package com.alan.lib.corner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WViewHelper {
    private int backgroundColor, backgroundColorRes;
    private float radius, radius_l_u, radius_l_d, radius_r_u, radius_r_d;
    private int ripple_color, ripple_color_res;
    private float stroke_width;
    private int stroke_color;
    private View mView;
    private boolean mNeedEffect = true;
    private Orientation orientation;
    private int start_color, end_color;

    public void init(Context context, AttributeSet attrs, View view, boolean needEffect) {
        mNeedEffect = needEffect;
        init(context, attrs, view);
    }

    public void init(Context context, AttributeSet attrs, View view) {
        mView = view;
        if(attrs == null) return;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeiCiView);
        radius = a.getDimension(R.styleable.WeiCiView_radius, 0);
        ripple_color = a.getColor(R.styleable.WeiCiView_ripple_color, 0);
        ripple_color_res = a.getResourceId(R.styleable.WeiCiView_ripple_color, 0);
        backgroundColorRes = a.getResourceId(R.styleable.WeiCiView_background, 0);
        backgroundColor = a.getColor(R.styleable.WeiCiView_background_color, 0);
        stroke_width = a.getDimension(R.styleable.WeiCiView_stroke_width, 0);
        stroke_color = a.getColor(R.styleable.WeiCiView_stroke_color, 0);

        radius_l_u = a.getDimension(R.styleable.WeiCiView_radius_l_u, 0);
        radius_l_d = a.getDimension(R.styleable.WeiCiView_radius_l_d, 0);
        radius_r_u = a.getDimension(R.styleable.WeiCiView_radius_r_u, 0);
        radius_r_d =  a.getDimension(R.styleable.WeiCiView_radius_r_d, 0);

        start_color = a.getColor(R.styleable.WeiCiView_wv_start_color, 0);
        end_color = a.getColor(R.styleable.WeiCiView_wv_end_color, 0);
        int ori = a.getInt(R.styleable.WeiCiView_wv_color_orientation, 0);
        for (Orientation o : Orientation.values()) {
            if (o.ordinal() == ori) {
                orientation = o;
                break;
            }
        }
        update();
        a.recycle();
    }

    private void update(){
        GradientDrawable gd;// 形状-圆角矩形

        if(orientation != null && start_color != 0 && end_color != 0) {
            gd = new GradientDrawable(orientation, new int[]{start_color, end_color});
            if(radius != 0)
                gd.setCornerRadius(radius);
            else{
                gd.setCornerRadii(new float[]{radius_l_u, radius_l_u, radius_r_u, radius_r_u, radius_r_d, radius_r_d, radius_l_d, radius_l_d});
            }
            //gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,new int[]{Color.RED,Color.YELLOW});
        }else{
            gd = new GradientDrawable();
            if(radius != 0)
                gd.setCornerRadius(radius);
            else{
                gd.setCornerRadii(new float[]{radius_l_u, radius_l_u, radius_r_u, radius_r_u, radius_r_d, radius_r_d, radius_l_d, radius_l_d});
            }
            int tempBackground = backgroundColor;
            if(tempBackground == 0 && backgroundColorRes != 0) {
                tempBackground = ContextCompat.getColor(mView.getContext(), backgroundColorRes);
                gd.setColor(tempBackground);// 2
            }else if(tempBackground != 0){
                gd.setColor(tempBackground);
            }
        }

        gd.setShape(GradientDrawable.RECTANGLE);// 圆角


        if(stroke_width != 0) {
            if(stroke_width<1)stroke_width = 1;
            gd.setStroke((int) stroke_width, stroke_color);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && (ripple_color != 0 || ripple_color_res != 0)) {
            //if(ripple_color == 0) ripple_color = background;
            int rippleColor = ripple_color;
            if(rippleColor == 0)
                rippleColor = ContextCompat.getColor(mView.getContext(), ripple_color_res);
            RippleDrawable drawable =new RippleDrawable(
                    ColorStateList.valueOf(rippleColor),//灰色波纹
                    gd,//一个圆角5dp,白色填充,灰色边框的shape图形
                    null//mask
            );
            mView.setBackground(drawable);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mView.setBackground(gd);
            }else{
                mView.setBackgroundDrawable(gd);
            }
            if(mView.isClickable())
                setClickEffect(mView);
        }
    }

    private void setClickEffect(View view) {
        if(!mNeedEffect) return;
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(0.6f);
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_SCROLL:
                        v.setAlpha(1.0f);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    public boolean rippleEnable(){
        return (ripple_color != 0 ||  ripple_color_res != 0) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public void setRippleColor(int rippleColor){
        this.ripple_color = rippleColor;
        update();
    }
    public void setRippleColorRes(int rippleColorRes){
        this.ripple_color = 0;
        this.ripple_color_res = rippleColorRes;
        update();
    }

    public void setBackgroundColor(int color){
        this.backgroundColor = color;
        update();
    }

    public void setBackgroundColorRes(int color){
        backgroundColor = 0;
        this.backgroundColorRes = color;
        update();
    }

    public void setRadius(float radius) {
        this.radius = radius;
        update();
    }

    public void setStrokeWidth(int stroke_width) {
        this.stroke_width = stroke_width;
        update();
    }

    public void setStrokeColor(int stroke_color) {
        this.stroke_color = stroke_color;
        update();
    }

    public void setRadius(int left_top, int left_bottom, int right_top, int right_bottom){
        radius_l_u = left_top;
        radius_l_d = left_bottom;
        radius_r_u = right_top;
        radius_r_d = right_bottom;
        radius = 0;
        update();
    }
}

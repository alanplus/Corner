package com.alan.lib.corner;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;



public class WConstraintLayout extends ConstraintLayout {
    public WViewHelper WViewHelper;

    public WConstraintLayout(Context context) {
        super(context);
        init(context, null);
    }

    public WConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        WViewHelper = new WViewHelper();
        WViewHelper.init(context, attrs, this);
    }
}

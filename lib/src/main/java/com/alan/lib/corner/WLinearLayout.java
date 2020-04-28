package com.alan.lib.corner;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;




/***
 * 设置圆角
 * @author wxw
 */
public class WLinearLayout extends LinearLayout {
	public WViewHelper WViewHelper;

	public WLinearLayout(Context context) {
		super(context);
		init(context, null);
	}

	public WLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		WViewHelper = new WViewHelper();
		WViewHelper.init(context, attrs, this);
	}
}

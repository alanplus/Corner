package com.alan.lib.corner;


import android.content.Context;
import android.util.AttributeSet;

/***
 * 设置圆角
 * @author wxw
 */
public class WTextView extends android.support.v7.widget.AppCompatTextView {

	public WViewHelper WViewHelper;
	public WTextView(Context context) {
		super(context);
		init(context, null);
	}

	public WTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		WViewHelper = new WViewHelper();
		WViewHelper.init(context, attrs, this);
	}
}

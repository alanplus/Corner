package com.alan.lib.corner;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;




/***
 * 设置圆角
 * @author wxw
 */
public class WEditText extends android.support.v7.widget.AppCompatEditText {

	public WViewHelper WViewHelper;
	public WEditText(Context context) {
		super(context);
		init(context, null);
	}

	public WEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public WEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		WViewHelper = new WViewHelper();
		WViewHelper.init(context, attrs, this, false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private float startY, startX;
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startY = event.getRawY();
				startX = event.getRawX();
				break;
			case MotionEvent.ACTION_UP:
				float endY = event.getRawY();
				float endX = event.getRawX();

				if (Math.abs(endY - startY) > 10 || Math.abs(endX - startX) > 10) {
					return true;
				}
				break;
		}

		return super.dispatchTouchEvent(event);
	}
}

package com.example.testeventbus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}
	
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/*@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
//		if(isIntercept)
			return true;
//			else return false;
	}*/
	/*@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(isIntercept)
		return super.onInterceptTouchEvent(ev);
		else return false;
	}*/
	
	private boolean isIntercept = true;

	public boolean isIntercept() {
		return isIntercept;
	}

	public void setIntercept(boolean isIntercept) {
		this.isIntercept = isIntercept;
	}

	
	
}

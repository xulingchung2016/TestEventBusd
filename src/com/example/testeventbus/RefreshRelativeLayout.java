package com.example.testeventbus;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class RefreshRelativeLayout extends RelativeLayout {
	View mView; 
	 boolean mNeedLayout = false; 
	 int mLeft = 0; 
	 int mTop = 0; 
	 int mRight = 0; 
	 int mBottom = 0;
	public RefreshRelativeLayout(Context context) {
	    this(context, null);
	}

	public RefreshRelativeLayout(Context context, AttributeSet attrs) {
	    this(context, attrs, 0);
	}

	public RefreshRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
	    super(context, attrs, defStyleAttr);
	}

	public void setTargetViewLayout(View view,float xFromDeltaDistance, float xToDeltaDistance, float yFromDeltaDistance, float yToDeltaDistance, int duration, int delay, final boolean isBack) {


	// mNeedLayout = false; 
//	 mView = getChildAt(2); 
		mView = view;
	 moveViewWithAnimation(mView, xFromDeltaDistance, xToDeltaDistance, yFromDeltaDistance, yToDeltaDistance, duration, delay, isBack); 
	 }
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	    super.onLayout(changed, l, t, r, b);
	    Log.i("test>>>>>>>>>>>>>>>>>>>", "来了》》》》》》》》》》》》》》");
	    if (mNeedLayout == false) {
	    	return;
	    }
	    Log.i("test>>>>>>>>>>>>>>>>>>>", "来刷了》》》》》》》》》》》》》》");
	    mView.layout(mLeft, mTop, mRight, mBottom);
	}

	/**
	 * 动画移动view并摆放至相应的位置
	 *
	 * @param view               控件
	 * @param xFromDeltaDistance x起始位置的偏移量
	 * @param xToDeltaDistance   x终止位置的偏移量
	 * @param yFromDeltaDistance y起始位置的偏移量
	 * @param yToDeltaDistance   y终止位置的偏移量
	 * @param duration           动画的播放时间
	 * @param delay              延迟播放时间
	 * @param isBack             是否需要返回到开始位置
	 */
	public void moveViewWithAnimation(final View view, final float xFromDeltaDistance, final float xToDeltaDistance, final float yFromDeltaDistance, final float yToDeltaDistance, int duration, int delay, final boolean isBack) {
	    //创建位移动画
	    TranslateAnimation ani = new TranslateAnimation(xFromDeltaDistance, xToDeltaDistance, yFromDeltaDistance, yToDeltaDistance);
	    ani.setInterpolator(new AccelerateDecelerateInterpolator());//设置加速器
	    ani.setDuration(duration);//设置动画时间
	    ani.setStartOffset(delay);//设置动画延迟时间
	    //监听动画播放状态
	    ani.setAnimationListener(new Animation.AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {
	        }

	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        }

	        @Override
	        public void onAnimationEnd(Animation animation) {
	            int deltaX = (int) (xToDeltaDistance - xFromDeltaDistance);
	            int deltaY = (int) (yToDeltaDistance - yFromDeltaDistance);
	            int layoutX = view.getLeft();
	            int layoutY = view.getTop();
	            int tempWidth = view.getWidth();
	            int tempHeight = view.getHeight();
	            view.clearAnimation();
	            if (isBack == false) {
	                layoutX += deltaX;
	                layoutY += deltaY;
	                view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
	            } else {
	                view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
	            }
	            
	            mLeft = layoutX;
	            mTop = layoutY;
	            mRight = layoutX + tempWidth;
	            mBottom = layoutY + tempHeight;
	            mNeedLayout = true;
	        }
	    });
	    view.startAnimation(ani);

	}
	
	public void moveViewWithFinger(final View view, final float xFromDeltaDistance, final float xToDeltaDistance, final float yFromDeltaDistance, final float yToDeltaDistance, final boolean isBack){
		 int deltaX = (int) (xToDeltaDistance - xFromDeltaDistance);
         int deltaY = (int) (yToDeltaDistance - yFromDeltaDistance);
         int layoutX = view.getLeft();
         int layoutY = view.getTop();
         int tempWidth = view.getWidth();
         int tempHeight = view.getHeight();
         view.clearAnimation();
         if (isBack == false) {
             layoutX += deltaX;
             layoutY += deltaY;
             view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
         } else {
             view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
         }
         
         mLeft = layoutX;
         mTop = layoutY;
         mRight = layoutX + tempWidth;
         mBottom = layoutY + tempHeight;
         mNeedLayout = true;
	}
	

}

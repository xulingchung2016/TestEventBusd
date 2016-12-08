package com.example.testeventbus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView1 extends TextView {
	private LinearGradient mLinearGradient;
	private Matrix mGradientMatrix;
	private Paint mPaint;
	private int mViewWidth = 0;
	private int mTranslate = 0;
	 private float mScale = 0.1f; //从中间往两边
	private boolean mAnimating = true;
	private int mMode =0;//0为平移1为从中间往两边
	private String mBcolor = "#49c538";//字体颜色
	private String mUcolor = "#5049c538";//光的颜色颜色
	private int During = 50;//光扫速度
	public MyTextView1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mViewWidth == 0) {
			mViewWidth = getMeasuredWidth();
			if (mViewWidth > 0) {
				mPaint = getPaint();
				if(mMode == 0){
				mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0,
						new int[] { Color.parseColor(mBcolor),Color.parseColor(mUcolor) ,Color.parseColor(mBcolor)  },
						new float[] { 0, 0.5f, 1 }, TileMode.CLAMP);
				}else{
					
					mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
							new int[] { Color.parseColor(mBcolor),Color.parseColor(mUcolor) ,Color.parseColor(mBcolor)  },
							new float[] { 0, 0.5f, 1 }, TileMode.CLAMP);
				}
				mPaint.setShader(mLinearGradient);
				mGradientMatrix = new Matrix();
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mAnimating && mGradientMatrix != null) {
			if(mMode == 0){
			mTranslate += mViewWidth / 10;
			if (mTranslate > 2 * mViewWidth) {
				mTranslate = -mViewWidth;
			}
			mGradientMatrix.setTranslate(mTranslate, 0);
			}else{
				 mScale += 0.1f;  
			            if (mScale > 1.2f) {  
				              mScale = 0.1f;  
				            }  
				           mGradientMatrix.setScale(mScale, mScale, mViewWidth / 2, 0);  

			}
			mLinearGradient.setLocalMatrix(mGradientMatrix);
			postInvalidateDelayed(During);
		}
	}

	public int getmMode() {
		return mMode;
	}

	public void setmMode(int mMode) {
		this.mMode = mMode;
	}

	public String getmBcolor() {
		return mBcolor;
	}

	public void setmBcolor(String mBcolor) {
		this.mBcolor = mBcolor;
	}

	public String getmUcolor() {
		return mUcolor;
	}

	public void setmUcolor(String mUcolor) {
		this.mUcolor = mUcolor;
	}

	public int getDuring() {
		return During;
	}

	public void setDuring(int during) {
		During = during;
	}
	
	
	


}

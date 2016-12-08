package com.example.testeventbus;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class TestPathAnimator extends AppCompatActivity implements OnClickListener{
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_path_animator);
		testPathAnimator();
	}
	
	public void testPathAnimator(){
        final FrameLayout l = (FrameLayout) findViewById(R.id.root_view);

        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher);
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l.addView(imageView, param);

        Path path = new Path();
        path.moveTo(200, 200);

        path.quadTo(800, 200, 800, 800);
        path.quadTo(800, 800, 200, 1600);

        PathInterpolator pathInterpolator = new PathInterpolator(0.33f,0f,0.12f,1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                l.removeView(imageView);
//                l.addView(new MyAnimView(TestPathAnimator.this, null));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                l.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        ObjectAnimator scalex = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0.1f, 1f);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0.1f, 1f);
        ObjectAnimator alphe = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0.1f, 1f);
        ObjectAnimator roatex = ObjectAnimator.ofFloat(imageView, View.ROTATION_X, 0.0f, 180f,0f);
        ObjectAnimator roatey = ObjectAnimator.ofFloat(imageView, View.ROTATION_Y, 0.0f, 90f,0f);
        ObjectAnimator traslateAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", path);

        		ObjectAnimator translationUp = ObjectAnimator.ofInt(imageView,
        				"backgroundColor", Color.RED, Color.BLUE, Color.GRAY,
        				Color.GREEN);
        		translationUp.setInterpolator(new DecelerateInterpolator());
        		translationUp.setDuration(1500);
        		translationUp.setRepeatCount(-1);
        		translationUp.setRepeatMode(Animation.REVERSE);
        		
        		 * ArgbEvaluator：这种评估者可以用来执行类型之间的插值整数值代表ARGB颜色。
        		 * FloatEvaluator：这种评估者可以用来执行浮点值之间的插值。
        		 * IntEvaluator：这种评估者可以用来执行类型int值之间的插值。
        		 * RectEvaluator：这种评估者可以用来执行类型之间的插值矩形值。
        		 * 
        		 * 由于本例是改变View的backgroundColor属性的背景颜色所以此处使用ArgbEvaluator
        		 

        		translationUp.setEvaluator(new ArgbEvaluator());
//        		translationUp.start();
        		
        animSet.playTogether(scalex, scaley,alphe,traslateAnimator,translationUp,roatex,roatey);

        animSet.setInterpolator(pathInterpolator);
        animSet.setDuration(3500);
        animSet.start();
    }*/
	
	   int flag=0;
	    int width,height;
	    private  int[] pic ={R.id.a,R.id.b,R.id.c,R.id.d,R.id.e};
	    private List<ImageView> imageViewList = new ArrayList<>();

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.test_path_animator);
	        getWindow().setFormat(PixelFormat.RGBA_8888);

	        DisplayMetrics metric = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metric);


	         width = metric.widthPixels;  // 宽度（PX）
	         height = metric.heightPixels;  // 高度（PX）



	        for(int i =0;i<pic.length;i++){

	            ImageView imageView = (ImageView) findViewById(pic[i]);
	            imageView.setOnClickListener(this);
	            imageViewList.add(imageView);

	        }

	    }


	    @Override
	    public void onClick(View v) {

	        switch (v.getId()){

	            case R.id.a:
	                if(flag==0)
	                startAni();
	                else if(flag==1)
	                    closeAni();
	                    else if(flag==2)
	                startAni2();
	                else if(flag==3)
	                    closeAni2();
	              else if (flag==4)
	                 startAni3();
	                else if (flag==5)
	                    startAni4();
	            break;

	            default:
	                Toast.makeText(TestPathAnimator.this,"Click"+v.getId(),Toast.LENGTH_SHORT).show();
	break;
	        }

	    }

	    private void startAni4() {
	        ObjectAnimator oe =
	                ObjectAnimator.ofFloat(imageViewList.get(0),"rotationX", 0.0F, 360.0F).setDuration(500);
	        oe.start();
	        for(int i =1;i<pic.length;i++) {
	            float x0 = width/3;
	            float y0 = height/5;
	            float x = (float) (width/3 * Math.cos(Math.PI *2/ 4 *i ));
	            float y = (float) (height/6* Math.sin(Math.PI *2/4 *i));
	            ObjectAnimator oa = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", y0 + y, y0);
	            ObjectAnimator ob = ObjectAnimator.ofFloat(imageViewList.get(i), "translationX", x0+x, x0 );
	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(oa, ob);
	            set.setInterpolator(new BounceInterpolator());
	            set.setDuration(500);

	            set.start();
	        }
	    }

	    private void startAni3() {



	        for(int i=1;i<pic.length;i++) {

	            ObjectAnimator of =
	                    ObjectAnimator.ofFloat(imageViewList.get(i),"rotationX", 0.0F, 360.0F).setDuration(500);

	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(of);

	            set.setDuration(1000);

	            set.start();
	        }

	        flag = 5;

	    }

	    private  void startAni(){

	        for(int i =1;i<pic.length;i++)
	        {
	            float x =(float) (width/2 * Math.cos(Math.PI / 2 / (pic.length - 2) * (i - 1)));
	            float y =(float) (height/5* Math.sin(Math.PI / 2 / (pic.length - 2)* (i-1)));
	            ObjectAnimator oa = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",0F,y+i*12);
	            ObjectAnimator ob = ObjectAnimator.ofFloat(imageViewList.get(i),"translationX",0F,x);
	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(oa,ob);
	            set.setInterpolator(new BounceInterpolator());
	            set.setDuration(500);

	            set.start();




	        }
	         flag=1;

	    }

	    private  void closeAni(){

	        for(int i =1;i<pic.length;i++)
	        {
	            float x =(float) (width/2 * Math.cos(Math.PI / 2 / (pic.length -2)* (i-1)));
	            float y =(float) (height/5 * Math.sin(Math.PI / 2 / (pic.length - 2)* (i-1)));
	            ObjectAnimator oa = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",y+i*12,0F);
	            ObjectAnimator ob = ObjectAnimator.ofFloat(imageViewList.get(i),"translationX",x,0F);
	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(oa,ob);

	            set.setDuration(500);
	            set.setInterpolator(new BounceInterpolator());
	            set.start();


	        }
	        flag=2;

	    }

	    private  void startAni2(){

	        for(int i =1;i<pic.length;i++)
	        {
	             float x = width/3;
	            float y = height/5;

	            ObjectAnimator oa = ObjectAnimator.ofFloat(imageViewList.get(0),"translationY",0F,y);
	            ObjectAnimator ob = ObjectAnimator.ofFloat(imageViewList.get(0),"translationX",0F,x);
	            ObjectAnimator oc = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",0F,y);
	            ObjectAnimator od = ObjectAnimator.ofFloat(imageViewList.get(i),"translationX",0F,x);

	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(oa,ob,oc,od);
	            set.setDuration(500);
	            set.setInterpolator(new BounceInterpolator());
	            set.start();



	        }

	        flag=3;

	    }

	    private  void closeAni2(){

	        ObjectAnimator oe =
	                ObjectAnimator.ofFloat(imageViewList.get(0),"rotationX", 0.0F, 360.0F).setDuration(500);
	        oe.start();

	        for(int i=1;i<pic.length;i++) {

	            float x0 = width/3;
	            float y0 = height/5;
	            float x = (float) (width/3 * Math.cos(Math.PI *2/ 4 *i ));
	            float y = (float) (height/6 * Math.sin(Math.PI *2/4 *i));
	            ObjectAnimator oa = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", y0, y0 + y);
	            ObjectAnimator ob = ObjectAnimator.ofFloat(imageViewList.get(i), "translationX", x0, x0 + x);
	            AnimatorSet set = new AnimatorSet();
	            set.playTogether(oa, ob);
	            set.setInterpolator(new BounceInterpolator());
	            set.setDuration(500);

	            set.start();
	        }


	        flag=4;

	    }

	 
	
 }



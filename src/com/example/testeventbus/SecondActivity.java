package com.example.testeventbus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.net.wifi.WifiConfiguration.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
/**
 * �ؼ��ƶ� �����ƶ� �����ƶ� test �¼��ַ�����
 * @author Ī����
 *
 */
@SuppressWarnings("deprecation")
public class SecondActivity extends Activity implements OnClickListener,OnTouchListener,OnScrollListener{
	private RelativeLayout rl_b,root;
	private RefreshRelativeLayout root_1;
	private LinearLayout ll_handle;
	private com.example.testeventbus.MyListView ldata;
	private int height,handleHeight,toptitleHeight;
	private int top = 0,firstItem = 0,c;
	private TextView buttontest;
	private int mStatus =1;//0Ϊ�ײ�1Ϊ�м�2Ϊ����,Ĭ��Ϊ�м���ʾ�� 
	private int count =100;
	private ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		toptitleHeight = getTopHeight(this);
		height = getHeight(this) - toptitleHeight;
		Log.i("denisty", "height:"+getHeight(this));
		buttontest = (TextView) findViewById(R.id.buttontest);
//		slideview(0, height/2,  buttontest);
		buttontest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//			slideview(0, height/4,  v);
				
			}
		});
		
		rl_b = (RelativeLayout) findViewById(R.id.rl_b);
		root_1 = (com.example.testeventbus.RefreshRelativeLayout) findViewById(R.id.root_1);
		root = (RelativeLayout) findViewById(R.id.root);
		ll_handle = (LinearLayout) findViewById(R.id.ll_handle);
		handleHeight = ll_handle.getLayoutParams().height;
//		ll_handle.setOnClickListener(this);
		ldata = (com.example.testeventbus.MyListView) findViewById(R.id.list);
//		ldata.setIntercept(false);//����listview��ontouch�¼�����root
		ldata.setOnScrollListener(this);
		ldata.setOnTouchListener(this);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
		ldata.setAdapter(adapter);
		ldata.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				ldata.setAdapter(adapter);
//				slideview(0, height/2, ll_handle,null);
//				slideview(0, height/2, rl_b,ldata);
			}
		});
		
//		slideview(0, height/2, ll_handle,null);
//		slideview(0, height/2, rl_b,ldata);
		root_1.setTargetViewLayout(root, 0, 0, 0,925, 500, 10, false);
//		 root.layout(0, 925, root.getWidth(), root.getHeight());
		
//		root.setTargetViewLayout(rl_b, 0, 0, 0,height/2, 500, 10, false);
//		root.getLayoutParams().height =  height/2-handleHeight;
//		ldata.getLayoutParams().height = height/2-handleHeight;
		root.setOnTouchListener(this);
		
//		buttontest.setOnTouchListener(this);
	/*	ldata.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				Log.i("singc", "scrollState:>"+scrollState);
				if(firstItem == 0 &&scrollState== 0){
					c+=1;
					//�ֱ�ȫ����ʾ
					if(c>1){
					moveViewWithFinger(ll_handle,0,height - handleHeight ,null);
					//����ȫ����ʾ
					moveViewWithFinger(rl_b,height,height,ldata);
					mStatus = 0;
					c = 0;
					}
				}else if(firstItem >0)c = 0;
				Log.i("singcvalue", "c:>>>"+c);
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				Log.i("sing", "visivable:>"+firstVisibleItem);
				firstItem = firstVisibleItem;
				
			}
		});*/
		
	}
	
	
	/**
	 * ��ȡ��Ļ���
	 * **/
	public  int getTopHeight(Activity context) {
		/*Rect frame = new Rect();

		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

		int statusBarHeight = frame.top;
		return statusBarHeight;*/
		Class<?> c =  null; 

		Object obj =  null; 

		Field field =  null; 

		int  x = 0, sbar =  0; 

		try  { 

		    c = Class.forName("com.android.internal.R$dimen");

		    obj = c.newInstance();

		    field = c.getField("status_bar_height");

		    x = Integer.parseInt(field.get(obj).toString());

		    sbar = this.getResources().getDimensionPixelSize(x);

		} catch(Exception e1) {

//		    log.e("get status bar height fail");

//		    e1.printStackTrace();

		}
		return sbar;
	}
	
	private List<String> getData() {
		   List<String> data = new ArrayList<String>();

	        data.add("��������1");

	        data.add("��������2");

	        data.add("��������3");

	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");
	        data.add("��������4");

	         

	        return data;
	}

	/**
	   * ����View�Ĳ������ԣ�ʹ��view������ָ�ƶ� ע�⣺view���ڵĲ��ֱ���ʹ��RelativeLayout ���Ҳ������þ��е���ʽ
	   * 
	   * @param view
	   * @param rawX
	   * @param rawY
	   */
	  private void moveViewWithFinger(final View view, final int height,final int top,final View childView) {
		  int left = 0;
			 int width = left + view.getWidth();
			 int height1 = top +view.getHeight();
			 if(height>0)height1 = height;
			    view.layout(left, top, width, height1);
			    if(null != childView)
			    childView.layout(left, 0, width, height);
		    
	  }
	  
	  /** 
	  02.     * ͨ��layout�������ƶ�view  
	  03.     * �ŵ㣺��view���ڵĲ��֣�Ҫ�󲻿��̣���Ҫ��RelativeLayout�����ҿ����޸�view�Ĵ�С 
	  04.     *  
	  05.     * @param view 
	  06.     * @param rawX 
	  07.     * @param rawY 
	  08.     */  
	  
	   private void moveViewByLayout(View view, int rawX, int rawY,int stauts,View childView) {  
	         int left = 0;  
	       int top = rawY -view.getHeight()-getTopHeight(this);  
	       if(stauts == 1)top = rawY -handleHeight/2;
	        int width = left + view.getWidth();  
	         int height = top + view.getHeight();  
	         if(stauts == 1)height = getHeight(this);
	          view.layout(left, top, width, height); 
	          if(null != childView)
				    childView.layout(left, 0, width, height);
	          Log.i("moveresult:","rawY:>"+rawY+" view.getHeight/2:>"+view.getHeight()/2+"topHeight:>"+getTopHeight(this)+" top:>"+top);
	    }  
	  

	  
	  private void TranslateAnim(final View view,int anima){
		  
		  Animation anim = AnimationUtils.loadAnimation(SecondActivity.this,anima);  
		  view.startAnimation(anim); 
		  anim.setFillAfter(true);
		  anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
//			view.setVisibility(View.GONE);
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				animation.setFillAfter(true);
//				view.setVisibility(View.VISIBLE);
				
			}
		});
	  }
	  
	  
	  
	  /**
		 * ��ȡ��Ļ���
		 * **/
		public static int getHeight(Context context) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);

			@SuppressWarnings("deprecation")
			int width = wm.getDefaultDisplay().getHeight();
			return width;
		}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Log.i("clickTag", "��Ӧclick");
		switch (id) {
		case R.id.ll_handle:
			if(!isClick)break;
			isClick = false;
			if(mStatus == 1){//�״ν�������ȫ����ʾ����
				/*//�ֱ�ȫ����ʾ
				moveViewWithFinger(ll_handle, 0,0,null);
				//����ȫ����ʾ
				moveViewWithFinger(rl_b,height,handleHeight,ldata);
				TranslateAnim(root, R.anim.push_bottom_in);*/
//				slideview(0,-height/2, ll_handle,null);
//				slideview(0,-height/2, rl_b,ldata);
				root_1.setTargetViewLayout(root, 0, 0, 0,-height/2, 500, 10, false);
				mStatus = 2;
			}else if(mStatus == 2||mStatus == 0){//ȫ����ʾʱ�����Ϊ�в�������״̬�����Զ��������Եײ���
			/*	//�ֱ�ȫ����ʾ
				moveViewWithFinger(ll_handle,0,height/2,null);
				//����ȫ����ʾ
				moveViewWithFinger(rl_b,height,(height/2)+handleHeight,ldata);
				if(mStatus == 2)
				TranslateAnim(root, R.anim.push_bottom_out);
				else {
					TranslateAnim(root, R.anim.push_bottom_in2);
				}*/
				if(mStatus == 2){
//				slideview(0, height/2, ll_handle,null);
//				slideview(0, height/2, rl_b,ldata);
					root_1.setTargetViewLayout(root, 0, 0, 0,height/2, 500, 10, false);
				}
				else {
					root_1.setTargetViewLayout(root, 0, 0, 0,-height/2+handleHeight, 500, 10, false);
//					slideview(0, -height/2+handleHeight, ll_handle,null);
//					slideview(0, -height/2+handleHeight, rl_b,ldata);
				}
				mStatus = 1;
				
			}else {//�ײ�
				
				/*//�ֱ�ȫ����ʾ
				moveViewWithFinger(ll_handle,0,height - handleHeight ,null);
				//����ȫ����ʾ
				moveViewWithFinger(rl_b,height,height,ldata);*/
//				slideview(0, height/2-handleHeight, ll_handle,null);
//				slideview(0, height/2-handleHeight, rl_b,ldata);
				root_1.setTargetViewLayout(root, 0, 0, 0,height/2-handleHeight, 500, 10, false);
				mStatus = 0;
			}
			
			/*TranslateAnim(ll_handle);
			TranslateAnim(rl_b);*/
			break;

		default:
			break;
		}
	}
	private boolean isClick = false;
/*	private float y1,y2,posy1,posy2;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) { 
			case MotionEvent.ACTION_DOWN: 
	             y1 = event.getY(); 
	             posy1 = event.getRawY()-handleHeight;
				Log.i("clickTag", "��Ӧdown");
//	 			int[] locations = new int[2]; 
//	 			tvTips.getLocationInWindow(locations); 
//					topTitleHeight = locations[1]; 
		 			break; 
				case MotionEvent.ACTION_MOVE: 
		             y2 = event.getY(); 
		             Log.i("clickTag", "��Ӧmove+:y2:>"+y2);
		             if (Math.abs(y1 - y2) > 5&&v.getId() == R.id.ll_handle){
		 			moveViewByLayout(ll_handle, (int) event.getRawX(), 
						(int) event.getRawY(),0,null); 
		 			moveViewByLayout(rl_b, (int) event.getRawX(), 
		 					(int) event.getRawY(),1,ldata); 
		             }
		             else{
		            	 moveViewByLayout(buttontest, (int) event.getRawX(), 
		 						(int) event.getRawY(),0,null); 
		             }
		            
					break; 
			case MotionEvent.ACTION_UP: 
		             y2 = event.getY(); 
		            posy2 = event.getRawY()-handleHeight;
		            if(v.getId() == R.id.buttontest){
		            	 
		            	 slideview(0, -event.getRawY()+v.getHeight()+toptitleHeight, buttontest);
		             }
		            float disPos = Math.abs(posy2 - posy1);
		             Log.i("clickTag", "��ӦposY:>"+posy2+"��������"+disPos);
		             if (Math.abs(y1 - y2) <= 5) { 
		            	 Log.i("clickTag", "��Ӧupxclick:>");
		            	 isClick = true;
		            	 ll_handle.performClick();
		             } 
		             else if(Math.abs(y1 - y2) >5){// ������onTouch�¼� 
		            	 isClick = false;
		            	 Message msg = handler.obtainMessage();
		            	 msg.obj = disPos;
		            	 msg.what = 2;
		            	 handler.sendMessageAtTime(msg, 50);
		             }  
		             
		             
				break; 
			 case MotionEvent.ACTION_CANCEL:
				    //��ť�����߼�
				 Log.i("clickTag", "��Ӧcancel:>");
		             y2 = event.getY(); 
		            posy2 = event.getRawY()-handleHeight;
		            float disPos1 = Math.abs(posy2 - posy1);
		             Log.i("clickTag", "��ӦposY:>"+posy2);
		             if (Math.abs(y1 - y2) <= 5) { 
		            	 Log.i("clickTag", "��Ӧupxclick:>");
		            	 isClick = true;
		            	 ll_handle.performClick();
		             } 
		             else if(Math.abs(y1 - y2) >5){  // ������onTouch�¼� 
		            	 isClick = false;
		            	 Message msg = handler.obtainMessage();
		            	 msg.obj = disPos1;
		            	 msg.what = 2;
		            	 handler.sendMessageAtTime(msg, 50);
		            
		             }  
				    break;

		 		} 
		 Log.i("clickTag", "��ӦisClick:>"+isClick);
		
		 return true;
		
		 
	}*/
	
/*	private final int mMoveDis = 20;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if(what == 2){
				 Log.i("clickTag", "��Ӧ�ƶ��¼���������������������");
				float disPos = (Float) msg.obj;
				 //������ָ̧��ʱ��ǰλ������Ļ1/4������ֱ�ӵ�����
           	 if(mStatus == 0){//���ڵײ�״̬
           		 if(disPos > mMoveDis&& disPos < height*2/3){
        				moveViewWithFinger(ll_handle,0,height/2,null);
        				moveViewWithFinger(rl_b,height,(height/2)+handleHeight,ldata);
        				mStatus = 1;
           		 }else if(disPos > height*2/3){
           			 moveViewWithFinger(ll_handle, 0,0,null);
	         				//����ȫ����ʾ
	         				moveViewWithFinger(rl_b,height,handleHeight,ldata);
	         				mStatus = 2;
           		 }else {//�ص��ײ�
           				//�ֱ�ȫ����ʾ
	         				moveViewWithFinger(ll_handle,0,height - handleHeight ,null);
	         				//����ȫ����ʾ
	         				moveViewWithFinger(rl_b,height,height,ldata);
	         				mStatus = 0;
           		 }
           	 }else if(mStatus == 1){//�����в���״̬
           		 float dis = posy2 - posy1;
           		 if(dis>0&&disPos>mMoveDis){//�򶥲�����
           			 //�ֱ�ȫ����ʾ
           			 moveViewWithFinger(ll_handle,0,height - handleHeight ,null);
           			 //����ȫ����ʾ
           			 moveViewWithFinger(rl_b,height,height,ldata);
           			 mStatus = 0;
           		 }else if(dis<0&&disPos>mMoveDis){//��ײ�����
           			 //�ֱ�ȫ����ʾ
           			 moveViewWithFinger(ll_handle, 0,0,null);
           			 //����ȫ����ʾ
           			 moveViewWithFinger(rl_b,height,handleHeight,ldata);
           			 mStatus = 2;
           		 }else {
           			 moveViewWithFinger(ll_handle,0,height/2,null);
	         				moveViewWithFinger(rl_b,height,(height/2)+handleHeight,ldata);
	         				mStatus = 1;
           		 }
           	 }else if(mStatus == 2){//���ڶ���
           		 if(posy2>mMoveDis&&posy2<height*2/3){
           				moveViewWithFinger(ll_handle,0,height/2,null);
	         				moveViewWithFinger(rl_b,height,(height/2)+handleHeight,ldata);
	         				mStatus = 1;
           		 }else if(posy2 >height*2/3){
           				//�ֱ�ȫ����ʾ
	         				moveViewWithFinger(ll_handle,0,height - handleHeight ,null);
	         				//����ȫ����ʾ
	         				moveViewWithFinger(rl_b,height,height,ldata);
	         				mStatus = 0;
           		 }else {//�ص�����
           				moveViewWithFinger(ll_handle, 0,0,null);
	         				//����ȫ����ʾ
	         				moveViewWithFinger(rl_b,height,handleHeight,ldata);
	         				mStatus = 2;
           		 }
           	 }
			}
			
		};
		
	};*/
	
	private long durationMillis = 1000,delayMillis = 100;
	public void slideview(final float p1, final float p2,final View view,final View childView) {
	     TranslateAnimation animation = new TranslateAnimation(0, 0, p1, p2);
	     animation.setInterpolator(new AccelerateDecelerateInterpolator());
	     animation.setDuration(durationMillis);
	     animation.setStartOffset(delayMillis);
	     animation.setAnimationListener(new Animation.AnimationListener() {
	         @Override
	         public void onAnimationStart(Animation animation) {
	         }
	         
	         @Override
	         public void onAnimationRepeat(Animation animation) {
	         }
	         
	         @Override
	         public void onAnimationEnd(Animation animation) {
	             int left = view.getLeft();
	             int top = view.getTop()+(int)(p2-p1);
	             int width = view.getWidth();
	             int height = view.getHeight();
	             view.clearAnimation();
	             if(null != childView){
	            	 top = 0;
	            	 height = SecondActivity.this.height;
//	            	 view.layout(left, top, left+width, SecondActivity.this.height);
//	 			    childView.layout(left, 0, width,SecondActivity.this.height);
	             }else  top = view.getTop()+(int)(p2-p1);
//	            	 view.layout(left, top, left+width, top+height);
	           /*  MarginLayoutParams mlp =   (MarginLayoutParams)view.getLayoutParams();
	     		mlp.topMargin = top ;*/
	     		LayoutParams params = (LayoutParams) view.getLayoutParams();
	     		params.height = height;
	     		params.topMargin = top;
	     		view.setLayoutParams(params);
	         }
	     });
	     
	     view.startAnimation(animation);
	 }

	   private int  lastY,lastY2,lastY3;
	   private final int moveUp = 150,moveDown = 100;//����,�����ƶ���ֵ
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if(view.getId() == R.id.root){
		 final int X = (int) event.getRawX();  
         final int Y = (int) event.getRawY();  
         switch (event.getAction() & MotionEvent.ACTION_MASK) {  
         case MotionEvent.ACTION_DOWN:  
             lastY = (int) event.getRawY();
             lastY2 = (int) event.getRawY();
             Log.i("ontouch", "down��������������������������");
             break;  
         case MotionEvent.ACTION_UP:  
        	 int dy1 = (int) event.getRawY() - lastY2;//�����ƶ��˶��ٸ���λ
        	 Log.i("ontouch", "up��������������������������"+dy1+" absMath:>"+Math.abs(dy1));
        	 if (Math.abs(dy1) <= 1) { //����¼�
            	 Log.i("clickTag", "��Ӧupxclick:>");
            	 if(mStatus == 1){//�״ν�������ȫ����ʾ����
     				root_1.setTargetViewLayout(root, 0, 0, 0,-height/2, 500, 10, false);
     				mStatus = 2;
     			}else if(mStatus == 2||mStatus == 0){//ȫ����ʾʱ�����Ϊ�в�������״̬�����Զ��������Եײ���
     				if(mStatus == 2){
     					root_1.setTargetViewLayout(root, 0, 0, 0,height/2, 500, 10, false);
     				}
     				else {
     					root_1.setTargetViewLayout(root, 0, 0, 0,-height/2+handleHeight, 500, 10, false);
     				}
     				mStatus = 1;
     				
     			}else {//�ײ�
     				
     				root_1.setTargetViewLayout(root, 0, 0, 0,height/2-handleHeight, 500, 10, false);
     				mStatus = 0;
     			}
             } 
             else if(Math.abs(dy1) >5){// ������onTouch�¼� 
            	//����������ƶ���һ��λ��ʱʱ����ԭ��״̬���ǵ���һ��״̬ͨ��������ʽ�ƶ�
            	 if(mStatus == 1){//��ǰ�����в�״̬���Ϊ�����ƶ��������ƶ�
            		 if(dy1<0&&Math.abs(dy1)>moveUp){//���򶥲��ƶ������ƶ���100�ĵ�λ������ӵ�ǰͣ��λ��ֱ�������ƶ�
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-height/2-dy1, 200, 10, false);
          				mStatus = 2;
            		 }else if(dy1>0&&Math.abs(dy1)>moveDown){//����ײ��ƶ�
            			 root_1.setTargetViewLayout(root, 0, 0, 0,height/2-handleHeight-dy1, 200, 10, false);
          				mStatus = 0;
            		 }else {//�ص���ʼλ��
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-dy1, 100, 10, false);
           				mStatus = 1;
            		 }
            		 
            	 }else if(mStatus == 0){//��ǰ���ڵײ�״̬��Ϊ���в��ƶ��Ͷ����ƶ�
            		 if(dy1<0){
            		 int moveDis = Math.abs(dy1);
            		 if(moveDis>moveDown&&moveDis<=height/2){//�ӵ�ǰλ�ö����ƶ����в�
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-height/2+handleHeight+moveDis, 200, 10, false);
            			 mStatus = 1;
            		 }else if(moveDis>height/2&&moveDis<=height- handleHeight){
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-height+handleHeight+moveDis, 200, 10, false);
            			 mStatus = 2;
            		 }else{
            			 if(event.getRawY()>height)dy1 = 0;
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-dy1, 100, 10, false);
            				mStatus = 0;
            		 }
            		 }
            		 
            	 }else if(mStatus == 2){//��ǰ���ڶ���״̬��Ϊ���в��ƶ�����ײ��ƶ�
            		 if(dy1>moveDown){
            			 if(dy1<=height/2){//�򻬶����в�
            				 root_1.setTargetViewLayout(root, 0, 0, 0,height/2-dy1, 200, 10, false);
            				 mStatus = 1;
            			 }else{//�������ײ�
            				 root_1.setTargetViewLayout(root, 0, 0, 0,height-dy1-handleHeight, 200, 10, false);
            				 mStatus = 1;
            			 }
            			 
            		 }else {//�ص���ʼλ��
            			 if(dy1<0)dy1 =0;
            			 root_1.setTargetViewLayout(root, 0, 0, 0,-dy1, 100, 10, false);
         				mStatus = 2;
            		 }
            	 }
            	 
             }  
             break;  
         case MotionEvent.ACTION_POINTER_DOWN:  
        	
             break;  
         case MotionEvent.ACTION_POINTER_UP:  
        	
             break;  
         case MotionEvent.ACTION_MOVE:  
        	 int dy = (int) event.getRawY() - lastY;
        	 int top = view.getTop() + dy;
        	 Log.i("ontouch", "move��������������������������"+top+"dy:"+dy+"lastY"+lastY+"now:"+event.getRawY());
          /*
        	 int dx = (int) event.getRawX() - lastX;
             int left = view.getLeft() + dx;
             RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view 
                     .getLayoutParams();  
             layoutParams.height=height;
             layoutParams.width = view.getWidth();
             layoutParams.leftMargin =0;  
             layoutParams.topMargin =top;  
             root.setLayoutParams(layoutParams);
             root.layout(0, top,0, 0);
             lastX = (int) event.getRawX();
             lastY = (int) event.getRawY();*/
//             moveViewWithFinger(root, 0, top, null);
//        	 root.layout(0, top, root.getWidth(), root.getHeight());
        	 if(top >0&&top<height-handleHeight)
        	 root_1.moveViewWithFinger(root, 0, 0, 0,dy,false);
        	 
        	 
             lastY = (int) event.getRawY();
             break;  
             
         }  
//         root_1.postInvalidate();
         return false; 
		}else {
			 switch (event.getAction() & MotionEvent.ACTION_MASK) { 
		 case MotionEvent.ACTION_DOWN:  
			 lastY3 = (int) event.getRawY();
			 break;
		 case MotionEvent.ACTION_MOVE:  
			 if(ldata.getFirstVisiblePosition() == 0){
				 int dy = (int) event.getRawY() - lastY3;
	        	 int top = view.getTop() + dy;
	        	 if(top >0&&top<height-handleHeight)
	            	 root_1.moveViewWithFinger(root, 0, 0, 0,dy,false);
	            	 
	            	 
	        	 lastY3  = (int) event.getRawY();
			 }
			 break;
		 case MotionEvent.ACTION_UP:  
			 
			 break;
			 }
			return false;
		}
	}
	
	
	
	private int pos;
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		Log.i("listView", "onscroll>>>>>>"+firstVisibleItem+" <> "+visibleItemCount+" <> "+totalItemCount);
		pos = firstVisibleItem;
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(pos == 0)ldata.setIntercept(false);
		else ldata.setIntercept(true);
		
		
	}

}

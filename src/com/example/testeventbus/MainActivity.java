package com.example.testeventbus;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.example.testeventbus.CustomSufaceView.SelectChangeListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  implements SelectChangeListener{
	private MyEventBus eventBus;
	private ShimmerFrameLayout container;
//	22.664989<>114.04102499999999上次：22.664989<>114.04102499999999
	private Double elat,elon,lastla,lastlon;
	private SlidingDrawer sliderDrawa;
	AlarmManager aManager;
	private int mJobId = 0;
//	private CustomChatView chartView;
	private CustomSufaceView chartView;
	private HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	private int height;
	private BaseFundChartView baseFundChartView;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		setStatusBarColorOther();
		initSystemBarTint();
		height = getWindow().getWindowManager().getDefaultDisplay().getHeight()-150;
//		startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
		/**
		 * 注册定于
		 */
		eventBus = MyEventBus.getInstance();
		eventBus.register(this);
		CustomToast.init(MainActivity.this);
		container = (ShimmerFrameLayout) findViewById(R.id.tv_container);
		sliderDrawa = (SlidingDrawer) findViewById(R.id.slidingDrawer);
//		container.setAutoStart(true);
//		container.setAlpha(0.5f);
		container.setDuration(2000);//
//		container.setRepeatMode(1);
		
		/*  if (ContextCompat.checkSelfPermission(this,
                  Manifest.permission.READ_CONTACTS)
        	        != PackageManager.PERMISSION_GRANTED)*/
		container.setMaskShape(ShimmerFrameLayout.MaskShape.LINEAR);
		container.setAngle(ShimmerFrameLayout.MaskAngle.CW_0);//从左到右扫
		
		/**
		 * jobservice 5.0后
		 */
//		JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        ComponentName componentName = new ComponentName(MainActivity.this, TestJobService.class);
//        JobInfo.Builder builder = new JobInfo.Builder(++mJobId, componentName);
        //设置JobService执行的最小延时时间
//        builder.setMinimumLatency(Long.valueOf(3) * 1000);
      //设置JobService执行的最晚时间
//        builder.setOverrideDeadline(Long.valueOf(5) * 1000);
        //设置执行的网络条件
//            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        builder.setRequiresDeviceIdle(false);//是否要求设备为idle状态
//        builder.setRequiresCharging(false);//是否要设备为充电状态
        
//        scheduler.schedule(builder.build());

		
		  /*aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);  
		      // 指定启动ChangeService组件  
		       Intent intent = new Intent(MainActivity.this,  
		    		   ScreenOnService.class);  
		       // 创建PendingIntent对象  
	        final PendingIntent pi = PendingIntent.getService(  
	        		MainActivity.this, 0, intent, 0);  
	        aManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pi);  */

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 // 设置每５秒执行pi代表的组件一次  

			startActivity(new Intent(MainActivity.this, SecondActivity.class));
//			CustomToast.show("testLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			}
		});
		elat = 22.664989;
		elon =114.04102499999999;
		lastla = 22.664989;
		lastlon = 114.04102499999999;
		if(lastla.equals(elat)){
			CustomToast.show("true");
		}else {
			
			CustomToast.show("false");
		}
		
		String str1 = new String("str");
        String str2 = new String("str");
        System.out.println("==比较 ："+ (str1 == str2));
        System.out.println("equal比较："+ str1.equals(str2));
        String str3 = "str1";
        String str4 = "str1";
        System.out.println("==比较 ："+ (str3 == str4));
        System.out.println("equal比较："+ str3.equals(str4));
        
        sliderDrawa.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			
			@Override
			public void onScrollStarted() {
				Log.i("scroll", "start");
				
			}
			
			@Override
			public void onScrollEnded() {
				Log.i("scroll", "end");
				
			}
			
		});
        
        chartView =(CustomSufaceView) findViewById(R.id.chat);
        Snackbar.make(chartView, "welcom snackbar", Snackbar.LENGTH_LONG).setAction("点击", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Toast.makeText(
                         MainActivity.this,
                         "Toast comes out",
                         Toast.LENGTH_SHORT).show();
			}
		}).show();
        
        
        
        List<Integer> datas=new ArrayList<Integer>();   //海拔
        List<Integer> datasMile=new ArrayList<Integer>();   //距离
        
        for(int i = 0;i<20;i++){
        	int mile = (i*40);
        	datasMile.add(mile);
        	int radom = new Random().nextInt(300);
        	datas.add(radom);
        	map.put(mile, radom);
        }
      
//        map.put(810, -100);
        chartView.setData(datas,datasMile,map,height);
        chartView.setSelectChangeListener(this);
        final List<List<Float>> datams = new ArrayList<>();

        List<Float> datam = new ArrayList<>();
//        List<String> texts = new ArrayList<>();
        List<String> dateX = new ArrayList<>();
        for(int i = 0;i<200;i++){
        	float mile = (i*40);
        	dateX.add(mile+"m");
        	float radom = new Random().nextInt(100);
        	datam.add(radom);
//        	texts.add(mile+"m");
        }
        datams.add(datam);
        baseFundChartView = (BaseFundChartView) findViewById(R.id.chat2);
      //这个是刷新缓存的标志，如果你只有一个图的话可以不用设置。而我的项目里是点击切换多个表，需要设置清除缓存
//        baseFundChartView.setRefData(true);
//        设置图标识，可以在这里设置，也可以在回调里设置，我的需求是要每次触摸要重新写标识，我就在回调里写了
         baseFundChartView.setText(null);
        //设置回调，每次触摸成功回调（这里有触摸重复判断）
         baseFundChartView.setOnFundChat(new BaseFundChartView.OnFundChat() {
                            @Override
                            public void onRefChatText(BaseFundChartView view, int x) {
                            	Log.i("x:", "Values:"+datams.get(0).get(x)+"pos:"+x);
                               /* if(x<tongleiMapData.get(spaceEnum).size()){
                                    List<String> list = new ArrayList<String>();
                                    list.add("时间:"+tongleiMapXTime.get(spaceEnum).get(x));
                                    list.add("同类排名:"+(int)((float)tongleiMapData.get(spaceEnum).get(x)));
                                    view.setText(list);
                                }*/
                            }
                        });
       
         
       
        //设置纵轴的数字保留几位
//         baseFundChartView.setFormat(TNum.moneyFormat(point,keep));
        //设置有几条虚线
         baseFundChartView.setyCount(5);
        //设置起始（纵轴左下角的起始点）
         baseFundChartView.setyStart(0f);
        //设置结束（纵轴左上角的结束点）
         baseFundChartView.setyStop(160f);
        //设置x轴（必须与数据的数量一样）
         baseFundChartView.setDateX(dateX);
        //设置数据（List<List<Float>> 这种格式，List<Float>是数据，外面表示多组）
         baseFundChartView.setData(datams);
        //设置颜色的数组（按照数据的顺序给颜色）
         List<Integer> colors = new ArrayList<>();
         colors.add(Color.rgb(55,161,236));
         colors.add(Color.rgb(255,149,0));
         colors.add(Color.rgb(255,27,26));
         baseFundChartView.setColors(colors);
//         baseFundChartView.setText(texts);
        //刷新当前图表
         baseFundChartView.invalidate();
         
        
		
	}
	private SystemBarTintManager tintManager;
	/**
	 * 沉浸式状态栏
	 */
	private void setStatusBarColorOther(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(Color.RED);
            tintManager.setStatusBarTintEnabled(true);
        }

	}
	
	 /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.RED);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }
    
    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }

    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * 
	 */
	@Subscribe (threadMode = ThreadMode.MAIN)
	public void handle(TestEventBusModle what){
		((com.example.testeventbus.MyTextView1)findViewById(R.id.tv_1)).setText("kkkkk");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		/**
		 * 解除定
		 * 
		 */
		
		CustomToast.eixt();
		eventBus.unregister(this);
		MyApp.getRefWatcher(this).watch(this);
	}

	@Override
	public void setChartChangeListener(int pos) {
		Log.i("changeList", "changeListerXOFF:"+pos);
		
	}
}

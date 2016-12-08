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
//	22.664989<>114.04102499999999�ϴΣ�22.664989<>114.04102499999999
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
		 * ע�ᶨ��
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
		container.setAngle(ShimmerFrameLayout.MaskAngle.CW_0);//������ɨ
		
		/**
		 * jobservice 5.0��
		 */
//		JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        ComponentName componentName = new ComponentName(MainActivity.this, TestJobService.class);
//        JobInfo.Builder builder = new JobInfo.Builder(++mJobId, componentName);
        //����JobServiceִ�е���С��ʱʱ��
//        builder.setMinimumLatency(Long.valueOf(3) * 1000);
      //����JobServiceִ�е�����ʱ��
//        builder.setOverrideDeadline(Long.valueOf(5) * 1000);
        //����ִ�е���������
//            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        builder.setRequiresDeviceIdle(false);//�Ƿ�Ҫ���豸Ϊidle״̬
//        builder.setRequiresCharging(false);//�Ƿ�Ҫ�豸Ϊ���״̬
        
//        scheduler.schedule(builder.build());

		
		  /*aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);  
		      // ָ������ChangeService���  
		       Intent intent = new Intent(MainActivity.this,  
		    		   ScreenOnService.class);  
		       // ����PendingIntent����  
	        final PendingIntent pi = PendingIntent.getService(  
	        		MainActivity.this, 0, intent, 0);  
	        aManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pi);  */

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 // ����ÿ����ִ��pi��������һ��  

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
        System.out.println("==�Ƚ� ��"+ (str1 == str2));
        System.out.println("equal�Ƚϣ�"+ str1.equals(str2));
        String str3 = "str1";
        String str4 = "str1";
        System.out.println("==�Ƚ� ��"+ (str3 == str4));
        System.out.println("equal�Ƚϣ�"+ str3.equals(str4));
        
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
        Snackbar.make(chartView, "welcom snackbar", Snackbar.LENGTH_LONG).setAction("���", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Toast.makeText(
                         MainActivity.this,
                         "Toast comes out",
                         Toast.LENGTH_SHORT).show();
			}
		}).show();
        
        
        
        List<Integer> datas=new ArrayList<Integer>();   //����
        List<Integer> datasMile=new ArrayList<Integer>();   //����
        
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
      //�����ˢ�»���ı�־�������ֻ��һ��ͼ�Ļ����Բ������á����ҵ���Ŀ���ǵ���л��������Ҫ�����������
//        baseFundChartView.setRefData(true);
//        ����ͼ��ʶ���������������ã�Ҳ�����ڻص������ã��ҵ�������Ҫÿ�δ���Ҫ����д��ʶ���Ҿ��ڻص���д��
         baseFundChartView.setText(null);
        //���ûص���ÿ�δ����ɹ��ص��������д����ظ��жϣ�
         baseFundChartView.setOnFundChat(new BaseFundChartView.OnFundChat() {
                            @Override
                            public void onRefChatText(BaseFundChartView view, int x) {
                            	Log.i("x:", "Values:"+datams.get(0).get(x)+"pos:"+x);
                               /* if(x<tongleiMapData.get(spaceEnum).size()){
                                    List<String> list = new ArrayList<String>();
                                    list.add("ʱ��:"+tongleiMapXTime.get(spaceEnum).get(x));
                                    list.add("ͬ������:"+(int)((float)tongleiMapData.get(spaceEnum).get(x)));
                                    view.setText(list);
                                }*/
                            }
                        });
       
         
       
        //������������ֱ�����λ
//         baseFundChartView.setFormat(TNum.moneyFormat(point,keep));
        //�����м�������
         baseFundChartView.setyCount(5);
        //������ʼ���������½ǵ���ʼ�㣩
         baseFundChartView.setyStart(0f);
        //���ý������������ϽǵĽ����㣩
         baseFundChartView.setyStop(160f);
        //����x�ᣨ���������ݵ�����һ����
         baseFundChartView.setDateX(dateX);
        //�������ݣ�List<List<Float>> ���ָ�ʽ��List<Float>�����ݣ������ʾ���飩
         baseFundChartView.setData(datams);
        //������ɫ�����飨�������ݵ�˳�����ɫ��
         List<Integer> colors = new ArrayList<>();
         colors.add(Color.rgb(55,161,236));
         colors.add(Color.rgb(255,149,0));
         colors.add(Color.rgb(255,27,26));
         baseFundChartView.setColors(colors);
//         baseFundChartView.setText(texts);
        //ˢ�µ�ǰͼ��
         baseFundChartView.invalidate();
         
        
		
	}
	private SystemBarTintManager tintManager;
	/**
	 * ����ʽ״̬��
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
	
	 /** ����״̬����ɫ */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // ����״̬��ȫ͸��
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
        // ����ʽ״̬��
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0����ʹ��ԭ������
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.RED);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0ʹ������������
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }
    
    /** ���������д�ı�״̬����ɫ */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /** ���������д�����Ƿ�ʹ��͸��״̬�� */
    protected boolean translucentStatusBar() {
        return false;
    }

    /** ��ȡ����ɫ */
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
		 * �����
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

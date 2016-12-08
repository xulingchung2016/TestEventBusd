package com.example.testeventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CustomChatView extends View {
	 //坐标轴原点的位置
    private int xPoint=60;  
    private int yPoint=720;
    //刻度长度
    private int xScale=80;  //100个单位构成一个刻度
    private int yScale=50;
    //x与y坐标轴的长度
    private int xLength=800;
    private int yLength=500;
    
    private int MaxDataSize=xLength/xScale;   //横坐标  最多可绘制的点
    
    private List<Integer> data=new ArrayList<Integer>();   //存放 纵坐标 所描绘的点
    private List<Integer> data_miles=new ArrayList<Integer>();   //存放 横坐标 所描绘的点
    private  HashMap<Integer, Integer> map;
    private String[] yLabel=new String[yLength/yScale];  //Y轴的刻度上显示字的集合
    private String[] xLabel=new String[xLength/xScale];  //x轴的刻度上显示字的集合
    
    
    private Handler mh=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0){                //判断接受消息类型
            	CustomChatView.this.invalidate();  //刷新View
            }
        };
    };
    
    public CustomChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int i = 0; i <yLabel.length; i++) {
            yLabel[i]=(i)*yScale+"M";
        }
        
        for (int j = 0; j <xLabel.length; j++) {
        	xLabel[j]=(j)*xScale+"M";
        }
        
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){     //在线程中不断往集合中增加数据
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(data.size()>MaxDataSize){  //判断集合的长度是否大于最大绘制长度
                        data.remove(0);  //删除头数据
                    }
                    data.add(new Random().nextInt(5)+1);  //生成1-6的随机数
                    mh.sendEmptyMessage(0);   //发送空消息通知刷新
                }
            }
        }).start();*/
    }
    
    public CustomChatView(Context context) {
    	super(context);
    	  for (int i = 0; i <yLabel.length; i++) {
              yLabel[i]=(i)*yScale+"M";
          }
          
          for (int j = 0; j <xLabel.length; j++) {
          	xLabel[j]=(j)*xScale+"M";
          }
    	
    	
    }
    
    private Paint paint;
    
    
    public List<Integer> getData() {
		return data;
	}

	public void setData(final List<Integer> datas,final List<Integer> datasMiles, HashMap<Integer, Integer> map) {
		this.map = map;
		 paint=new Paint();
         paint.setStyle(Paint.Style.STROKE);
         paint.setAntiAlias(true);  
         paint.setColor(Color.GRAY);
         paint.setTextSize(16f);
		new Thread(new Runnable() {
    		@Override
    		public void run() {
//    			while(true){     //在线程中不断往集合中增加数据
    				/*try {
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}*/
    				if(data.size()>MaxDataSize){  //判断集合的长度是否大于最大绘制长度
    					data.remove(0);  //删除头数据
    				}
    				data = datas;
    				data_miles = datasMiles;
    				mh.sendEmptyMessage(0);   //发送空消息通知刷新
//    			}
    		}
    	}).start();
	}
	private int xOff= yLength/2,yOff= xLength/2;
	private int value;
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//这里是绘制背景
        //绘制Y轴
        canvas.drawLine(xPoint, yPoint-yLength, xPoint, yPoint, paint); 
       
//        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
//        canvas.drawPaint(paint);  
//        paint.setXfermode(new PorterDuffXfermode(Mode.SRC));  
       
//        canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
//        paint.setColor(Color.WHITE);
//        canvas.drawLine(xLength/2+xPoint, yPoint-yLength, xLength/2+xPoint, yPoint, paint);// 画线
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1f);
        //绘制Y轴左右两边的箭头
        canvas.drawLine(xPoint, yPoint-yLength, xPoint-3,yPoint-yLength+6, paint);
        canvas.drawLine(xPoint, yPoint-yLength, xPoint+3,yPoint-yLength+6, paint);
        //Y轴上的刻度与文字
        for (int i = 0; i * yScale< yLength; i++) {
            canvas.drawLine(xPoint, yPoint-i*yScale, xPoint+5, yPoint-i*yScale, paint);  //刻度
            canvas.drawText(yLabel[i], xPoint-50, yPoint-i*yScale, paint);//文字
        }
        
        //x轴上的刻度与文字
        for (int i = 0; i * yScale< yLength; i++) {
        	canvas.drawLine(xPoint+i*xScale, yPoint, xPoint+i*xScale, yPoint+5, paint);  //刻度
        	canvas.drawText(xLabel[i],xPoint+i*xScale, yPoint+50, paint);//文字
        }
        
        //X轴
        canvas.drawLine(xPoint, yPoint, xPoint+xLength, yPoint, paint);
      
        //实现填充
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1f);
        paint.setStyle(Paint.Style.FILL);
        if(data.size()>1){
            Path path=new Path();
            
            path.moveTo(xPoint, yPoint);
            for (int i = 0; i < data.size()-1; i++) {
            	int x1 = xPoint+data_miles.get(i);
            	int y1 = yPoint-data.get(i);
            	 
          /*  float x2 = xPoint+data_miles.get(i+1);
            float y2 = yPoint-data.get(i+1);*/
            
//                path.quadTo(x1,y1 ,x2, y2);
            	path.lineTo(x1, y1);
            }
            
            path.lineTo(xPoint+xLength, yPoint);
            canvas.drawPath(path, paint);
        }
        
        
        yOff = mov_x - xPoint;
        try {
        	if(map.containsKey(yOff)){
				xOff =  map.get(yOff);
				value = yOff;
        	}
			else {
				if(value != 0)
				xOff = map.get(value);;
			} 
      if(xOff<=0)xOff = 0;
      else if(xOff>=yLength)xOff = yLength;
      if(yOff>=xLength)yOff = yLength;
      else if(yOff<=0)yOff = 0;
      
			Log.i("ondraw?????????","来了:xOff:>"+xOff+" yOff:>"+yOff);
			//绘制指示线
			drawTipLines(paint, canvas,xOff,yOff);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	/**
	 * 
	 * @param paint //画笔
	 * @param canvas //画布
	 * @param xOff //x轴的移动量
	 * @param yOff //y轴的移动量
	 */
	private void drawTipLines(Paint paint,Canvas canvas,int xOff,int yOff){
		
		  //清除之前画的线
       /* paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3f);
        canvas.drawLine(xPoint, yPoint- xOff, xPoint+xLength, yPoint- xOff, paint);
        //绘制一条y轴的指示线
        canvas.drawLine(yOff+xPoint, yPoint-yLength, yOff+xPoint, yPoint, paint); 
        */
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3f);
        canvas.drawLine(xPoint, yPoint- xOff, xPoint+xLength, yPoint- xOff, paint);
        //绘制一条y轴的指示线
        canvas.drawLine(yOff+xPoint, yPoint-yLength, yOff+xPoint, yPoint, paint); 
	}
	private int mov_x;//声明起点坐标
	 private int mov_y;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_MOVE) {//如果拖动
			Log.i("value:>", "mov_x:>"+mov_x +" mov_y:>"+mov_y);
//			   canvas.drawLine(mov_x, mov_y, event.getX(), event.getY(), paint);//画线
			  //绘制指示线
//	        drawTipLines(paint, canvas,yLength/2,xLength/2);
			   invalidate();
			  }
			  if (event.getAction()==MotionEvent.ACTION_DOWN) {//如果点击
			   mov_x=(int) event.getX();
			   mov_y=(int) event.getY();
//			   canvas.drawPoint(mov_x, mov_y, paint);//画点
//			   invalidate();

			  }
			  mov_x=(int) event.getX();
			  mov_y=(int) event.getY();
			  return true;
			 }

			 


}

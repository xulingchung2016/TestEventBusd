package com.example.testeventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomSufaceView extends SurfaceView implements SurfaceHolder.Callback{
	
	private SurfaceHolder holder=null; //控制对象
//	private Vector<Float> xs=new Vector<Float>();
//	private Vector<Float> ys=new Vector<Float>();
	 //坐标轴原点的位置
    private int xPoint=60;  
    private int yPoint=720;
    //刻度长度
    private int xScale=80;  //100个单位构成一个刻度
    private int yScale=50;
    private final int radius = 30;
    //x与y坐标轴的长度
    private int xLength=800;
    private int yLength=500;
    
    private int MaxDataSize=xLength/xScale;   //横坐标  最多可绘制的点
    private Path path;
    private List<Integer> data=new ArrayList<Integer>();   //存放 纵坐标 所描绘的点
    private List<Integer> data_miles=new ArrayList<Integer>();   //存放 横坐标 所描绘的点
    private  HashMap<Integer, Integer> map;
    private String[] yLabel=new String[yLength/yScale];  //Y轴的刻度上显示字的集合
    private String[] xLabel=new String[xLength/xScale];  //x轴的刻度上显示字的集合
	public CustomSufaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 holder=getHolder();
		   holder.addCallback(this);
		   paint=new Paint();
	         paint.setStyle(Paint.Style.STROKE);
	         paint.setAntiAlias(true);  
	         paint.setColor(Color.GRAY);
	         paint.setTextSize(16f);
	         for (int i = 0; i <yLabel.length; i++) {
	             yLabel[i]=(i)*yScale+"M";
	         }
	         
	         for (int j = 0; j <xLabel.length; j++) {
	         	xLabel[j]=(j)*xScale+"M";
	         }
	         
	}
	private Paint paint;
	public void setData(final List<Integer> datas,final List<Integer> datasMiles, HashMap<Integer, Integer> map,int yPoint) {
		this.map = map;
		data = datas;
		data_miles = datasMiles;
		this.yPoint = yPoint;
		 if(data.size()>1){
	             path=new Path();
	            
	            path.moveTo(xPoint, yPoint);
	            for (int i = 0; i < data.size(); i++) {
	            	int x1 = xPoint+data_miles.get(i);
	            	int y1 = yPoint-data.get(i);
	            	 
//	            int x2 = xPoint+data_miles.get(i+1);
//	            int y2 = yPoint-data.get(i+1);
//	            Log.i("data:>>","x1:>"+x1+"y1:>"+y1+"x2:>"+x2+"y2:>"+y2);
	            Log.i("data:>>","x1:>"+x1+"y1:>"+y1);
//	                path.quadTo(x1,y1 ,x2, y2);
	            	
	            	path.lineTo(x1, y1);
	            }
	            
	            path.lineTo(xPoint+xLength, yPoint+radius);
	           
	        }
		
	}
	
	private int xOff= yLength/2,yOff= xLength/2;
	private int value;

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		doMainDraw();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	private int count= 0;
	void doDraw( Canvas canvas){
//		if(mov_x == 0){
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);//这里是绘制背景
	        //绘制Y轴
		 paint.setColor(Color.GRAY);
		 paint.setStrokeWidth(1f);
	        canvas.drawLine(xPoint, yPoint-yLength, xPoint, yPoint, paint); 
	       
//	        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
//	        canvas.drawPaint(paint);  
//	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC));  
	       
//	        canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
//	        paint.setColor(Color.WHITE);
//	        canvas.drawLine(xLength/2+xPoint, yPoint-yLength, xLength/2+xPoint, yPoint, paint);// 画线
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
	        paint.setPathEffect(new CornerPathEffect(radius));  
	        canvas.drawPath(path, paint);
	        
	       
		 if(mov_x != 0){
	        yOff = mov_x - xPoint;
	        int temp = Math.round(yOff);
//	        if(num<1){
//	        	num++;
	        for(int i = 0;i<data_miles.size();i++){
	        	int miles0  = data_miles.get(0);
	        	int miles = data_miles.get(i);
	        	int miles1 = 0;
	        	count = i+1;
	        	if(i<data_miles.size()-1)miles1 = data_miles.get(i+1);
	        	else miles1 = 800;
	        	if(temp>=0&&temp<=miles0+1){
	        		temp = miles0;
	        		break;
	        	}
	        	
	        	else if(temp> miles+1&&temp<=miles1){
	        		temp = miles1;
	        		break;
	        	}
	        }
	        
//	        lastTemp = temp;
//	        }else{
	        
	      /*  if(temp>lastTemp&&temp<lastTempQ){//向前移动
	        	if(count<data.size()-1)
	         count +=1;	
	         
	        }else if(temp<lastTemp&&temp>lastTempH){//向后移动
	        	if(count>0)
	        	count-=1;
	        }else if(temp>lastTempQ)
	        
	        temp = data_miles.get(count);
	        if(count<data.size()-1)
	        lastTempQ = data_miles.get(count+1);
	        if(count>0)
	        lastTempH = data_miles.get(count-1);
	        lastTemp = temp;
	        }*/
	        
	        yOff = temp;
	        Log.i("round:>", "round:>"+temp);
	        try {
	        	if(map.containsKey(temp)){
					xOff =  map.get(temp);
					value = temp;
					if(null != mSelectChangeListener)
					mSelectChangeListener.setChartChangeListener(count);
	        	}
				else {
					if(value != 0)
					xOff = map.get(value);;
				} 
	      if(xOff<=0)xOff = 0;
	      else if(xOff>=yLength)xOff = yLength;
	      if(yOff>=xLength)yOff = xLength;
	      else if(yOff<=0)yOff = 0;
	      
//				Log.i("ondraw?????????","来了:xOff:>"+xOff+" yOff:>"+yOff);
				//绘制指示线
				drawTipLines(paint, canvas,xOff,yOff);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        }
	}
	
/*	public void doDraw(Canvas canvas) {
		   // TODO Auto-generated method stub
		   super.onDraw(canvas);
		   canvas.drawColor(Color.WHITE);//这里是绘制背景
		   Paint p=new Paint(); //笔触
		   p.setAntiAlias(true); //反锯齿
		   p.setColor(Color.BLACK);
		   p.setStyle(Style.STROKE);
		   for(int i=0;i<xs.size();i++)
		    canvas.drawCircle(xs.elementAt(i),ys.elementAt(i),10, p);
		}
	*/
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
		 paint.setStyle(Paint.Style.STROKE);
		 paint.setPathEffect(null);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3f);
        canvas.drawLine(xPoint, yPoint- xOff, xPoint+xLength, yPoint- xOff, paint);
        //绘制一条y轴的指示线
        canvas.drawLine(yOff+xPoint, yPoint-yLength, yOff+xPoint, yPoint, paint); 
	}
	
	public interface SelectChangeListener{
		 void setChartChangeListener(int pos);
	}
	private SelectChangeListener mSelectChangeListener;
	public void setSelectChangeListener(SelectChangeListener l) {
		mSelectChangeListener = l;
	}
	private int mov_x;//声明起点坐标
	 private int mov_y;
	 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_MOVE) {//如果拖动
//			Log.i("value:>", "mov_x:>"+mov_x +" mov_y:>"+mov_y);
//			   canvas.drawLine(mov_x, mov_y, event.getX(), event.getY(), paint);//画线
			  //绘制指示线
//	        drawTipLines(paint, canvas,yLength/2,xLength/2);
//			   invalidate();c  
			mov_x=(int) event.getX();
			   mov_y=(int) event.getY();
			doMainDraw();
			  }
			  if (event.getAction()==MotionEvent.ACTION_DOWN) {//如果点击
			   mov_x=(int) event.getX();
			   mov_y=(int) event.getY();
//			   canvas.drawPoint(mov_x, mov_y, paint);//画点
//			   invalidate();
//			   num = 0;
			   doMainDraw();
			  }else if(event.getAction()==MotionEvent.ACTION_UP){
				  
				  mov_x=0;
				  mov_y=0;
//				  num = 0;
//				  doMainDraw();
			  }
			  return true;
			 }

	/*	@Override
		public boolean onTouchEvent(MotionEvent event) {
		   // TODO Auto-generated method stub
		   if(event.getAction()==MotionEvent.ACTION_DOWN){
		    xs.add(event.getX());
		    ys.add(event.getY());
		   }
		   return true;
		}*/
		void doMainDraw(){
			 synchronized (MyLoop.class) {
					
				   Canvas c=holder.lockCanvas();
				   doDraw(c);
				   holder.unlockCanvasAndPost(c); 
			}
			     
		}

		class MyLoop implements Runnable{
		//熟悉游戏编程的应该很面熟吧，主循环
		   @Override
		   public void run() {
		  /*  // TODO Auto-generated method stub
		    while(true){
		     try{
		      Canvas c=holder.lockCanvas();
		      doDraw(c);
		      holder.unlockCanvasAndPost(c);
		      Thread.sleep(20);
		     }catch(Exception e){
		     
		     }
		    }*/
			   synchronized (MyLoop.class) {
				
				   Canvas c=holder.lockCanvas();
				   doDraw(c);
				   holder.unlockCanvasAndPost(c); 
			}
			     
		   }
		  
		}

}

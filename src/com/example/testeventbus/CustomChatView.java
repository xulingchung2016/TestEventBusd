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
	 //������ԭ���λ��
    private int xPoint=60;  
    private int yPoint=720;
    //�̶ȳ���
    private int xScale=80;  //100����λ����һ���̶�
    private int yScale=50;
    //x��y������ĳ���
    private int xLength=800;
    private int yLength=500;
    
    private int MaxDataSize=xLength/xScale;   //������  ���ɻ��Ƶĵ�
    
    private List<Integer> data=new ArrayList<Integer>();   //��� ������ �����ĵ�
    private List<Integer> data_miles=new ArrayList<Integer>();   //��� ������ �����ĵ�
    private  HashMap<Integer, Integer> map;
    private String[] yLabel=new String[yLength/yScale];  //Y��Ŀ̶�����ʾ�ֵļ���
    private String[] xLabel=new String[xLength/xScale];  //x��Ŀ̶�����ʾ�ֵļ���
    
    
    private Handler mh=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0){                //�жϽ�����Ϣ����
            	CustomChatView.this.invalidate();  //ˢ��View
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
                while(true){     //���߳��в�������������������
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(data.size()>MaxDataSize){  //�жϼ��ϵĳ����Ƿ���������Ƴ���
                        data.remove(0);  //ɾ��ͷ����
                    }
                    data.add(new Random().nextInt(5)+1);  //����1-6�������
                    mh.sendEmptyMessage(0);   //���Ϳ���Ϣ֪ͨˢ��
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
//    			while(true){     //���߳��в�������������������
    				/*try {
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}*/
    				if(data.size()>MaxDataSize){  //�жϼ��ϵĳ����Ƿ���������Ƴ���
    					data.remove(0);  //ɾ��ͷ����
    				}
    				data = datas;
    				data_miles = datasMiles;
    				mh.sendEmptyMessage(0);   //���Ϳ���Ϣ֪ͨˢ��
//    			}
    		}
    	}).start();
	}
	private int xOff= yLength/2,yOff= xLength/2;
	private int value;
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//�����ǻ��Ʊ���
        //����Y��
        canvas.drawLine(xPoint, yPoint-yLength, xPoint, yPoint, paint); 
       
//        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
//        canvas.drawPaint(paint);  
//        paint.setXfermode(new PorterDuffXfermode(Mode.SRC));  
       
//        canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
//        paint.setColor(Color.WHITE);
//        canvas.drawLine(xLength/2+xPoint, yPoint-yLength, xLength/2+xPoint, yPoint, paint);// ����
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1f);
        //����Y���������ߵļ�ͷ
        canvas.drawLine(xPoint, yPoint-yLength, xPoint-3,yPoint-yLength+6, paint);
        canvas.drawLine(xPoint, yPoint-yLength, xPoint+3,yPoint-yLength+6, paint);
        //Y���ϵĿ̶�������
        for (int i = 0; i * yScale< yLength; i++) {
            canvas.drawLine(xPoint, yPoint-i*yScale, xPoint+5, yPoint-i*yScale, paint);  //�̶�
            canvas.drawText(yLabel[i], xPoint-50, yPoint-i*yScale, paint);//����
        }
        
        //x���ϵĿ̶�������
        for (int i = 0; i * yScale< yLength; i++) {
        	canvas.drawLine(xPoint+i*xScale, yPoint, xPoint+i*xScale, yPoint+5, paint);  //�̶�
        	canvas.drawText(xLabel[i],xPoint+i*xScale, yPoint+50, paint);//����
        }
        
        //X��
        canvas.drawLine(xPoint, yPoint, xPoint+xLength, yPoint, paint);
      
        //ʵ�����
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
      
			Log.i("ondraw?????????","����:xOff:>"+xOff+" yOff:>"+yOff);
			//����ָʾ��
			drawTipLines(paint, canvas,xOff,yOff);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	/**
	 * 
	 * @param paint //����
	 * @param canvas //����
	 * @param xOff //x����ƶ���
	 * @param yOff //y����ƶ���
	 */
	private void drawTipLines(Paint paint,Canvas canvas,int xOff,int yOff){
		
		  //���֮ǰ������
       /* paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3f);
        canvas.drawLine(xPoint, yPoint- xOff, xPoint+xLength, yPoint- xOff, paint);
        //����һ��y���ָʾ��
        canvas.drawLine(yOff+xPoint, yPoint-yLength, yOff+xPoint, yPoint, paint); 
        */
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3f);
        canvas.drawLine(xPoint, yPoint- xOff, xPoint+xLength, yPoint- xOff, paint);
        //����һ��y���ָʾ��
        canvas.drawLine(yOff+xPoint, yPoint-yLength, yOff+xPoint, yPoint, paint); 
	}
	private int mov_x;//�����������
	 private int mov_y;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_MOVE) {//����϶�
			Log.i("value:>", "mov_x:>"+mov_x +" mov_y:>"+mov_y);
//			   canvas.drawLine(mov_x, mov_y, event.getX(), event.getY(), paint);//����
			  //����ָʾ��
//	        drawTipLines(paint, canvas,yLength/2,xLength/2);
			   invalidate();
			  }
			  if (event.getAction()==MotionEvent.ACTION_DOWN) {//������
			   mov_x=(int) event.getX();
			   mov_y=(int) event.getY();
//			   canvas.drawPoint(mov_x, mov_y, paint);//����
//			   invalidate();

			  }
			  mov_x=(int) event.getX();
			  mov_y=(int) event.getY();
			  return true;
			 }

			 


}

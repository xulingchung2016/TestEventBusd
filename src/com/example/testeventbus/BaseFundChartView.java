package com.example.testeventbus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BaseFundChartView extends View implements View.OnTouchListener{
	 Paint linePaint;
	    Paint textPaint;
	    Paint xyChartPaint;
	    Paint chartLinePaint;
	    Paint chartJianbianPaint;
	    Paint huodongPaint;
	    Paint huodongPaintText;
	    List<Point> points;
	    
	    private int TextSize = 15;
	    private int YPaddingTop = 30;
	    private int YPaddingBottom = 40;
	    private int XPaddingLeft = 40;
	    private int XTextPadding = 20;
	    
	    private Context context;
	    public BaseFundChartView(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	        this.context = context;
	        init();
	    }

	    public BaseFundChartView(Context context) {
	        this(context, null);
	    }

	    public BaseFundChartView(Context context, AttributeSet attrs) {
	        this(context, attrs, 0);
	    }

	    PathEffect effect;
	    Path path;

	    private float getWidthYMax(){
	        return getWidth()-15;
	    }
	    public static int dip2px(Context context, int dipValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dipValue * scale + 0.5f);
		}
		
	    private void init() {
	    	TextSize = dip2px(context, 10);
	    	YPaddingTop = dip2px(context, 20);
	    	YPaddingBottom = dip2px(context, 10);
	    	XPaddingLeft = dip2px(context, 30);
	    	XTextPadding = dip2px(context, 12);
	        linePaint = new Paint();
	        textPaint = new Paint();
	        xyChartPaint = new Paint();
	        chartLinePaint = new Paint();
	        chartJianbianPaint = new Paint();
	        huodongPaint = new Paint();
	        huodongPaintText = new Paint();
	        //���û���ģʽΪ-������Ϊ�����ߡ�
	        effect = new DashPathEffect(new float[] { 6, 6, 6, 6, 6}, 2);
	        //��������·��.
	        path = new Path();
	        //ֻ�ǻ��Ƶ�XY��
	        linePaint.setStyle(Paint.Style.STROKE);
//	        linePaint.setStrokeWidth((float) 0.7);
	        linePaint.setStrokeWidth((float) 1.0);             //�����߿�

	        linePaint.setColor(Color.BLACK);
	        linePaint.setAntiAlias(true);// ��ݲ���ʾ

	        //XY�̶��ϵ���
	        textPaint.setStyle(Paint.Style.FILL);// ���÷����
	        textPaint.setStrokeWidth(1);// �ʿ�5����
	        textPaint.setColor(Color.BLACK);// ����Ϊ����
	        textPaint.setAntiAlias(true);// ��ݲ���ʾ
	        textPaint.setTextAlign(Paint.Align.CENTER);
	        textPaint.setTextSize(TextSize);

	        //����XY���ϵ��֣�Y����״̬��Xʱ��
	        xyChartPaint.setStyle(Paint.Style.FILL);
	        xyChartPaint.setStrokeWidth(1);
	        xyChartPaint.setColor(Color.BLUE);
	        xyChartPaint.setAntiAlias(true);
	        xyChartPaint.setTextAlign(Paint.Align.CENTER);
	        xyChartPaint.setTextSize(18);
	        //���Ƶ�����
	        chartLinePaint.setStyle(Paint.Style.STROKE);
	        chartLinePaint.setStrokeWidth(2);
	        chartLinePaint.setColor(Color.BLUE);
	        chartLinePaint.setAntiAlias(true);

	        //���Ƶ�����
	        chartJianbianPaint.setStyle(Paint.Style.FILL);
	        chartJianbianPaint.setStrokeWidth(2);
	        //chartJianbianPaint.setColor(Color.YELLOW);

	        chartJianbianPaint.setAntiAlias(true);


	        huodongPaint.setStyle(Paint.Style.STROKE);
	        huodongPaint.setStrokeWidth((float) 3.0);             //�����߿�

	        huodongPaint.setColor(Color.RED);
	        huodongPaint.setAntiAlias(true);// ��ݲ���ʾ

	        huodongPaintText.setStyle(Paint.Style.STROKE);
	        huodongPaintText.setStrokeWidth((float) 2.0);             //�����߿�
	        huodongPaintText.setTextSize(40);
	        huodongPaintText.setColor(Color.GRAY);
	        huodongPaintText.setAntiAlias(true);// ��ݲ���ʾ

	        setOnTouchListener(this);
	    }

	    /**
	     * ��Ҫ����������֮���Ϊ�����軭����������ֶ�Խ�࣬�軭�����߾�Խ��ϸ.
	     */
	    private static final int STEPS = 12;

	    float gridX,gridY,xSpace = 0,ySpace = 0,spaceYT = 0;
	    Float yStart=null,yStop = null;
	    Integer yCount = null;

	    List<String> text = null;

	    public void setText(List<String> text) {
	        this.text = text;
	    }

	    public void setyCount(Integer yCount) {
	        this.yCount = yCount;
	    }

	    public void setyStart(Float yStart) {
	        this.yStart = yStart;
	    }

	    public void setyStop(Float yStop) {
	        this.yStop = yStop;
	    }

	    String yFormat=null;

	    public void setFormat(String yFormat){
	        this.yFormat = yFormat;
	    }

	    List<String> dateX = null;
	    List<Float> dateY = null;

	    List<List<Float>> data = null;

	    List<Integer> colors = null;

	    public void setColors(List<Integer> colors) {
	        this.colors = colors;
	    }

	    public List<Float> getDateY() {
	        return dateY;
	    }

	    public void setDateY(List<Float> dateY) {
	        this.dateY = dateY;
	    }

	    public List<List<Float>> getData() {
	        return data;
	    }

	    public void setData(List<List<Float>> data) {
	        this.data = data;
	    }

	    public List<String> getDateX() {
	        return dateX;
	    }

	    public void setDateX(List<String> dateX) {
	        this.dateX = dateX;
	    }

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);

	        //��׼�㡣
	        gridX = XPaddingLeft;
	        gridY = getHeight() - YPaddingTop;
	        //XY�����
	        if(dateX!=null&&dateX.size()>0){
	            xSpace = (getWidthYMax() - gridX)/dateX.size();
	        }
	        /**
	         * ������ÿ�ͷ�ͽ�β�Ļ�����ֱ������y��
	         */
	        if(yStart!=null&&yStop!=null&&yCount!=null){
	            dateY = new ArrayList<>();
	            ySpace = (gridY - YPaddingBottom)/yCount;
	            float ze = (yStop-yStart)/yCount;
	            for(int i=0;i<yCount;i++){
	                dateY.add(yStart+ze*i);
	            }
	            dateY.add(yStop);
	            if(dateY.size()>2){
	                spaceYT = dateY.get(1)-dateY.get(0);
	            }
	        }else{
	            if(dateY!=null&&dateY.size()>0){
	                ySpace = (gridY - YPaddingBottom)/dateY.size();
	                yStart = dateY.get(0);
	                if(dateY.size()>2){
	                    spaceYT = dateY.get(1)-dateY.get(0);
	                }
	            }
	        }



//	        UIUtils.log("rewqfdesa",gridY,"fdsafdsa");

	        //��Y��(����ͷ)��
	        canvas.drawLine(gridX, gridY, gridX,  10, linePaint);
	        canvas.drawLine(gridX,  10, gridX - 6, 14 + 10, linePaint);//Y���ͷ��
	        canvas.drawLine(gridX,  10, gridX + 6, 14 + 10, linePaint);



	        //��Y�����֡�
	        //��������ֱ��ʾ�ģ�����ԭ��˳ʱ����ת90�Ⱥ�Ϊ�µ�����ϵ
	        //canvas.rotate(-90);
	        //��xyChartPaint��setTextAlign��������Ϊcenterʱ�ڶ������������������ĸ����е����ڵ�xy����

	        //canvas.drawText("����״̬", -((float) (getHeight() - 60) - 15 - 5 - 1 / ((float) 1.6 * 1) * (getHeight() - 60) / 2), gridX - 15, xyChartPaint);
	        //����Y������


	        //canvas.rotate(90); //�ı�������ϵ��Ҫ�ٸĹ���
	        float y = 0;
	        //��X�ᡣ
	        y = gridY;
	        canvas.drawLine(gridX, y , getWidthYMax(), y, linePaint);//X��.
	        canvas.drawLine(getWidthYMax(), y , getWidthYMax() - 14, y - 6 , linePaint);//X���ͷ��
	        canvas.drawLine(getWidthYMax(), y , getWidthYMax() - 14, y + 6 , linePaint);

	        //����X�̶����ꡣ
	        float x = 0;
	        if(dateX!=null){
	        	int size = dateX.size();
	        	int step = size/5;
	            for (int n = 0; n < size; n+=step) {
	                //ȡX�̶�����.
	                x = gridX + (n) * xSpace;//��ԭ��(0,0)��Ҳ���̶ȣ������Ļ�����n+1��,�����ƶ�һ����ȡ�
	                //��X�����̶�ֵ��
	                if (dateX.get(n) != null) {
	                    //canvas.drawLine(x, gridY - 30, x, gridY - 18, linePaint);//��X�̶ȡ�
	                    canvas.drawText(dateX.get(n), x, gridY + XTextPadding, textPaint);//X����̶�ֵ��
	                }
	            }
	        }

	        float my = 0;

	        if(dateY!=null){

	            for(int n=0;n<dateY.size();n++){
	                //ȡY�̶�����.
	                my = gridY - (n)*ySpace;
//	                UIUtils.log(my,"fdsafss",ySpace);
	                //��y�����̶�ֵ��
	                float day = dateY.get(n);
	                String dayForm = String.valueOf(day);
//	                if(yFormat!=null){
//	                    dayForm = TNum.getMoney(day,yFormat);
//	                }
	                canvas.drawText(dayForm,gridX-15,my,textPaint);

	                if(my != gridY){
	                    linePaint.setPathEffect(effect);//�跨���߼����ʽ��
	                    //����X��֮���------��������һ��-------
	                    path.moveTo(gridX, my);//������������㡿��
	                    path.lineTo(getWidthYMax(), my);//�����������յ㡿��
	                    canvas.drawPath(path, linePaint);
	                }

	            }
	        }

	        if(data!=null&&data.size()>0){
	            float lastPointX = 0; //ǰһ����
	            float lastPointY = 0;
	            float currentPointX = 0;//��ǰ��
	            float currentPointY = 0;
	            for(int n=0;n<data.size();n++){
	                List<Float> da = data.get(n);
	                List<Float> da_x = new ArrayList<>();
	                List<Float> da_y = new ArrayList<>();
	                /**
	                 * ����·��
	                 */
	                Path curvePath = new Path();
	                /**
	                 * ����ɫ·��
	                 */
	                Path jianBianPath = new Path();

	                for(int m=0;m<da.size();m++){
	                    currentPointX = m * xSpace + gridX;
	                    currentPointY = gridY - ((da.get(m)-yStart)*(ySpace/spaceYT));
	                    da_x.add(currentPointX);
	                    da_y.add(currentPointY);
//	                    if(m>0){
//	                        canvas.drawLine(lastPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);
//	                    }
//	                    lastPointX = currentPointX;
//	                    lastPointY = currentPointY;
	                }
	                List<Cubic> calculate_y = calculate(da_y);
	                List<Cubic> calculate_x = calculate(da_x);
	                curvePath.moveTo(calculate_x.get(0).eval(0), calculate_y.get(0).eval(0));
	                jianBianPath.moveTo(gridX,gridY );
	                jianBianPath.lineTo(calculate_x.get(0).eval(0), calculate_y.get(0).eval(0));
	                chartLinePaint.setColor(colors.get(n));
	                float lastx = 0;
	                for (int i = 0; i < calculate_x.size(); i++) {
	                    for (int j = 1; j <= STEPS; j++) {
	                        float u = j / (float) STEPS;
	                        curvePath.lineTo(calculate_x.get(i).eval(u), calculate_y.get(i)
	                                .eval(u));
	                        jianBianPath.lineTo(calculate_x.get(i).eval(u), calculate_y.get(i)
	                                .eval(u));
	                        lastx = calculate_x.get(i).eval(u);
	                    }
	                }
	                jianBianPath.lineTo(lastx,gridY );
	                canvas.drawPath(curvePath, chartLinePaint);
	                Shader mShader = new LinearGradient(0, 10,0,gridY,new int[] {colors.get(n),Color.TRANSPARENT},null,Shader.TileMode.REPEAT);
	//�½�һ�����Խ��䣬ǰ���������ǽ��俪ʼ�ĵ����꣬�����ĸ������ǽ�������ĵ�����ꡣ������2���������һ���������ˣ����PS�Ķ�����Ȼ���Ǹ������ǽ������ɫ����һ�������ǽ�����ɫ�ķֲ������Ϊ�գ�ÿ����ɫ���Ǿ��ȷֲ��ġ������ģʽ���������õ���ѭ������

	                chartJianbianPaint.setShader(mShader);
	                canvas.drawPath(jianBianPath, chartJianbianPaint);
	            }
	        }

	        if(lineX!=null){
	            /*if(text!=null&&text.size()>0){
	                Paint.FontMetrics metrics = huodongPaintText.getFontMetrics();
	                float top = metrics.top;
	                float bootom = metrics.bottom;
	                float h = bootom-top;
	                float th = h + h*text.size();
	                float w=0;
	                float fx = 1;
	                if(lineX>getWidth()/2){
	                    fx = -1;
	                }else{
	                    fx = 1;
	                }
	                for(int i=0;i<text.size();i++){
	                    Rect rect = new Rect();
	                    huodongPaintText.getTextBounds(text.get(i), 0, text.get(i).length(), rect);
	                    int width = rect.width();//�ı��Ŀ��
	                    if(width>w){
	                        w = width;
	                    }
	                    canvas.drawText(text.get(i),fx==1?lineX+10:lineX-10-width,40+ h*(i+1),huodongPaintText);
	                }
	                canvas.drawLine(lineX, gridY - 20 - 10, lineX, 30 + 10, huodongPaint);
	                //canvas.drawRect(lineX,40,lineX+w*fx+20*fx,40+th,huodongPaint);
	            }*/
	        	
	        	 canvas.drawLine(lineX, gridY , lineX, 10, huodongPaint);
	  	        //��X�ᡣ
	  	        canvas.drawLine(gridX, lineY, getWidthYMax(), lineY, huodongPaint);//X��.
	        }





//	        //��ʼ�㡣
//	        float lastPointX = 0; //ǰһ����
//	        float lastPointY = 0;
//	        float currentPointX = 0;//��ǰ��
//	        float currentPointY = 0;
//	        if (dateY != null) {
//	            //1.�������ߡ�
//	            for (int n = 0; n < dateY.length; n++) {
//	                //get current point
//	                currentPointX = n * xSpace + gridX;
//	                currentPointY = (float) (getHeight() - 60) - 15 - 5 - (float) dateY[n] / ((float) 1.6 * 1) * (getHeight() - 60);
//	                if (dateX[n] != null) {//��X���жϣ�������������տ�ʼ�ĵ�������7��������ҵ������Ӷ�û���˸տ�ʼ�ļ�����㣻����ΪX��Y�������ʼ��ʱ��û��ֵ�����Ըտ�ʼ��ʱ��������Ϳ����ж������е��׼����㣩
//	                    if (n > 0) {//�ӵڶ����㿪ʼ�ж�
//	                        if (dateY[n - 1] == dateY[n]) {//����������������һֱ��ֱ��
//	                            //draw line
//	                            canvas.drawLine(lastPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);//��һ����[��ɫ]
//	                        } else {//������ڼ�ĵ㲻���֤���Ǵӿ����ػ��߹ص�����״̬��Ҫ������
//	                            //draw line  ����
//	                            canvas.drawLine(lastPointX, lastPointY, currentPointX, lastPointY, chartLinePaint);//��һ����[��ɫ]
//	                            //draw line  ����
//	                            canvas.drawLine(currentPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);//��һ����[��ɫ]
//	                        }
//	                    }
//	                }
	//
//	                lastPointX = currentPointX;
//	                lastPointY = currentPointY;
//	            }
//	        }




	        //���������ߣ�һ��(��Ϊ��ȥ��X��)����Y��̶�
	 //       y = (float) (getHeight() - 60) - 15 - 5 - 1 / ((float) 1.6 * 1) * (getHeight() - 60);//���ߵ�Y�������ǿ���ʱ���Y��
//	        linePaint.setPathEffect(effect);//�跨���߼����ʽ��
//	        //����X��֮���------��������һ��-------
//	        path.moveTo(gridX, y);//������������㡿��
//	        path.lineTo(getWidth(), y);//�����������յ㡿��
//	        canvas.drawPath(path, linePaint);
//	        //��Y��̶ȡ�
//	        canvas.drawText("��", gridX - 6 - 7, gridY - 20, textPaint);
//	        canvas.drawText("��", gridX - 6 - 7, y + 10, textPaint);
	//
//	        //����X�̶����ꡣ
//	        float x = 0;
//	        if (dateX[0] != null) { //��X���жϣ�������������տ�ʼ�ĵ�������7��������ҵ������Ӷ�û���˸տ�ʼ�ļ�����㣻����ΪX��Y�������ʼ��ʱ��û��ֵ�����Ըտ�ʼ��ʱ��������Ϳ����ж������е��׼����㣩
//	            for (int n = 0; n < dateX.length; n++) {
//	                //ȡX�̶�����.
//	                x = gridX + (n) * xSpace;//��ԭ��(0,0)��Ҳ���̶ȣ������Ļ�����n+1��,�����ƶ�һ����ȡ�
//	                //��X�����̶�ֵ��
//	                if (dateX[n] != null) {
//	                    canvas.drawLine(x, gridY - 30, x, gridY - 18, linePaint);//��X�̶ȡ�
//	                    canvas.drawText(dateX[n], x, gridY + 5, textPaint);//X����̶�ֵ��
//	                }
//	            }
//	        }
	//
//	        //��ʼ�㡣
//	        float lastPointX = 0; //ǰһ����
//	        float lastPointY = 0;
//	        float currentPointX = 0;//��ǰ��
//	        float currentPointY = 0;
//	        if (dateY != null) {
//	            //1.�������ߡ�
//	            for (int n = 0; n < dateY.length; n++) {
//	                //get current point
//	                currentPointX = n * xSpace + gridX;
//	                currentPointY = (float) (getHeight() - 60) - 15 - 5 - (float) dateY[n] / ((float) 1.6 * 1) * (getHeight() - 60);
//	                if (dateX[n] != null) {//��X���жϣ�������������տ�ʼ�ĵ�������7��������ҵ������Ӷ�û���˸տ�ʼ�ļ�����㣻����ΪX��Y�������ʼ��ʱ��û��ֵ�����Ըտ�ʼ��ʱ��������Ϳ����ж������е��׼����㣩
//	                    if (n > 0) {//�ӵڶ����㿪ʼ�ж�
//	                        if (dateY[n - 1] == dateY[n]) {//����������������һֱ��ֱ��
//	                            //draw line
//	                            canvas.drawLine(lastPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);//��һ����[��ɫ]
//	                        } else {//������ڼ�ĵ㲻���֤���Ǵӿ����ػ��߹ص�����״̬��Ҫ������
//	                            //draw line  ����
//	                            canvas.drawLine(lastPointX, lastPointY, currentPointX, lastPointY, chartLinePaint);//��һ����[��ɫ]
//	                            //draw line  ����
//	                            canvas.drawLine(currentPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);//��һ����[��ɫ]
//	                        }
//	                    }
//	                }
	//
//	                lastPointX = currentPointX;
//	                lastPointY = currentPointY;
//	            }
//	        }
//	        //��X�����֡�
//	        canvas.drawText("ʱ��", getWidth() / 2 - 10, getHeight() - 18, xyChartPaint);
	    }

//	    /**
//	     * ����.
//	     *
//	     * @param canvas
//	     */
//	    private void drawPoints(Canvas canvas) {
//	        for (int i = 0; i < points.size(); i++) {
//	            Point p = points.get(i);
//	            canvas.drawCircle(p.x, p.y, 5, paint);
//	        }
//	    }


	    /**
	     * ��������.
	     *
	     * @param x
	     * @return
	     */
	    private List<Cubic> calculate(List<Float> x) {
	        int n = x.size() - 1;
	        float[] gamma = new float[n + 1];
	        float[] delta = new float[n + 1];
	        float[] D = new float[n + 1];
	        int i;
	        
	        /*
	         * We solve the equation [2 1 ] [D[0]] [3(x[1] - x[0]) ] |1 4 1 | |D[1]|
	         * |3(x[2] - x[0]) | | 1 4 1 | | . | = | . | | ..... | | . | | . | | 1 4
	         * 1| | . | |3(x[n] - x[n-2])| [ 1 2] [D[n]] [3(x[n] - x[n-1])]
	         *
	         * by using row operations to convert the matrix to upper triangular and
	         * then back sustitution. The D[i] are the derivatives at the knots.
	         */

	        gamma[0] = 1.0f / 2.0f;
	        for (i = 1; i < n; i++) {
	            gamma[i] = 1 / (4 - gamma[i - 1]);
	        }
	        gamma[n] = 1 / (2 - gamma[n - 1]);

	        delta[0] = 3 * (x.get(1) - x.get(0)) * gamma[0];
	        for (i = 1; i < n; i++) {
	            delta[i] = (3 * (x.get(i + 1) - x.get(i - 1)) - delta[i - 1])
	                    * gamma[i];
	        }
	        delta[n] = (3 * (x.get(n) - x.get(n - 1)) - delta[n - 1]) * gamma[n];

	        D[n] = delta[n];
	        for (i = n - 1; i >= 0; i--) {
	            D[i] = delta[i] - gamma[i] * D[i + 1];
	        }

	        /* now compute the coefficients of the cubics */
	        List<Cubic> cubics = new LinkedList<Cubic>();
	        for (i = 0; i < n; i++) {
	            Cubic c = new Cubic(x.get(i), D[i], 3 * (x.get(i + 1) - x.get(i))
	                    - 2 * D[i] - D[i + 1], 2 * (x.get(i) - x.get(i + 1)) + D[i]
	                    + D[i + 1]);
	            cubics.add(c);
	        }
	        return cubics;
	    }


	    Float lineX = null;
	    Float lineY = null;
	    Integer temp = null;

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
	        getParent().requestDisallowInterceptTouchEvent(true);
	        switch(event.getAction()){
	            case MotionEvent.ACTION_DOWN:
	            case MotionEvent.ACTION_MOVE:
	                float zhi = (event.getX()-gridX)%xSpace;
	                boolean fanwei = zhi>0&&zhi<xSpace;
	                if(xSpace!=0&&(event.getX() - gridX )>0&&fanwei&&event.getX()<getWidthYMax()){
//	                    UIUtils.log("��������");
	                    int x = (int)((event.getX() - gridX )/xSpace);
//	                    int y = (int)((event.getY() - gridY )/ySpace);
	                    lineX = gridX + x*xSpace;
//	                    lineY = gridY-(data.get(0).get(x)*(gridY-30)/160.0f);
	                    lineY = gridY - ((data.get(0).get(x)-yStart)*(ySpace/spaceYT));
	                    if(temp!=null&&temp.equals(x)){
//	                        UIUtils.log("��ˢ��");
	                        return true;
	                    }
//	                    UIUtils.log("ˢ��");
	                    temp = x;
	                    if(onFundChat!=null){
	                        onFundChat.onRefChatText(this,x);
	                    }
	                    invalidate();

	                }
	                break;
	        }
	        return true;
	    }

	    OnFundChat onFundChat = null;

	    public interface OnFundChat{
	        void onRefChatText(BaseFundChartView view,int x);
	    }

	    public void setOnFundChat(OnFundChat onFundChat) {
	        this.onFundChat = onFundChat;
	    }

	    class Cubic{
	        float a,b,c,d;         /* a + b*u + c*u^2 +d*u^3 */

	        public Cubic(float a, float b, float c, float d){
	            this.a = a;
	            this.b = b;
	            this.c = c;
	            this.d = d;
	        }


	        /** evaluate cubic */
	        public float eval(float u) {
	            return (((d*u) + c)*u + b)*u + a;
	        }
	    }

	


}

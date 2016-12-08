package com.example.testeventbus;

import java.util.ArrayList;

import com.example.testeventbus.TestRecyclerAdapter.RecycleItemClickListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class TestRecleView extends Activity implements RecycleItemClickListener,OnChartGestureListener, OnChartValueSelectedListener {
	private RecyclerView recyclerView_one;  
    @SuppressWarnings("rawtypes")
	private RecyclerView.Adapter mAdapter;  
    private LinearLayoutManager mLayoutManager; 
    private com.github.mikephil.charting.charts.LineChart mChart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_recleview);
		mChart = (LineChart) findViewById(R.id.linechart);
		initChart();
		/* lineChart.setScaleEnabled(false);
	        lineChart.setDrawBorders(true);
	        lineChart.setBorderWidth(1);
	        lineChart.setBorderColor(getResources().getColor(Color.RED));
//	        lineChart.setDescription("");
	        Legend lineChartLegend = lineChart.getLegend();
	        lineChartLegend.setEnabled(false);
	      //x轴
	        XAxis   xAxis = lineChart.getXAxis();
	        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//	        xAxis.setLabelsToSkip(59);
*/		
		recyclerView_one = (RecyclerView) findViewById(R.id.recyclerView_one);
		//设置固定大小  
        recyclerView_one.setHasFixedSize(true);  
        //创建线性布局  
        mLayoutManager = new LinearLayoutManager(this);  
         //垂直方向  
       mLayoutManager.setOrientation(OrientationHelper.VERTICAL);  
        //给RecyclerView设置布局管理器  
       recyclerView_one.setLayoutManager(mLayoutManager);  
//       recyclerView_one.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
     //设置Item增加、移除动画
       recyclerView_one.setItemAnimator(new DefaultItemAnimator());
       //添加分割线
       recyclerView_one.addItemDecoration(new DividerItemDecoration(
                       this, DividerItemDecoration.VERTICAL_LIST));
       
        //创建适配器，并且设置  
        mAdapter = new TestRecyclerAdapter(this);  
        ((TestRecyclerAdapter) mAdapter).setRecycleItemClickListener(this);
        recyclerView_one.setAdapter(mAdapter); 
		
	}
	
	private void initChart() {
		mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(true);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setBorderWidth(2f);
        mChart.setBorderColor(Color.BLUE);
        mChart.setBackgroundColor(Color.LTGRAY);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
       /* MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
*/
        // x-axis limit line
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line


        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTypeface(tf);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(150f);
        leftAxis.setAxisMinimum(0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data
        setData(15, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(2500);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(LegendForm.LINE);
		
	}
	private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setHighLightColor(Color.RED);
            set1.setHighlightLineWidth(3f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }
	
	public class DividerItemDecoration extends RecyclerView.ItemDecoration {

	    private  final int[] ATTRS = new int[]{
	            android.R.attr.listDivider
	    };

	    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

	    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

	    private Drawable mDivider;

	    private int mOrientation;

	    public DividerItemDecoration(Context context, int orientation) {
	        final TypedArray a = context.obtainStyledAttributes(ATTRS);
	        mDivider = a.getDrawable(0);
	        a.recycle();
	        setOrientation(orientation);
	    }

	    public void setOrientation(int orientation) {
	        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
	            throw new IllegalArgumentException("invalid orientation");
	        }
	        mOrientation = orientation;
	    }

	    @Override
	    public void onDraw(Canvas c, RecyclerView parent) {
	        Log.v("recyclerview - itemdecoration", "onDraw()");

	        if (mOrientation == VERTICAL_LIST) {
	            drawVertical(c, parent);
	        } else {
	            drawHorizontal(c, parent);
	        }

	    }

	    public void drawVertical(Canvas c, RecyclerView parent) {
	        final int left = parent.getPaddingLeft();
	        final int right = parent.getWidth() - parent.getPaddingRight();

	        final int childCount = parent.getChildCount();
	        for (int i = 0; i < childCount; i++) {
	            final View child = parent.getChildAt(i);
	            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
	            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
	                    .getLayoutParams();
	            final int top = child.getBottom() + params.bottomMargin;
	            final int bottom = top + mDivider.getIntrinsicHeight();
	            mDivider.setBounds(left, top, right, bottom);
	            mDivider.draw(c);
	        }
	    }

	    public void drawHorizontal(Canvas c, RecyclerView parent) {
	        final int top = parent.getPaddingTop();
	        final int bottom = parent.getHeight() - parent.getPaddingBottom();

	        final int childCount = parent.getChildCount();
	        for (int i = 0; i < childCount; i++) {
	            final View child = parent.getChildAt(i);
	            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
	                    .getLayoutParams();
	            final int left = child.getRight() + params.rightMargin;
	            final int right = left + mDivider.getIntrinsicHeight();
	            mDivider.setBounds(left, top, right, bottom);
	            mDivider.draw(c);
	        }
	    }

	    @Override
	    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
	        if (mOrientation == VERTICAL_LIST) {
	            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
	        } else {
	            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
	        }
	    }
	}

	@Override
	public void onItemClickListener(int pos) {
		Toast.makeText(this, "点击了我"+pos, 1).show();
		
	}

	@Override
	public void onItemLongClickListener(int poss) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onValueSelected(Entry e, Highlight h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartGestureStart(MotionEvent me, ChartGesture lastPerformedGesture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartGestureEnd(MotionEvent me, ChartGesture lastPerformedGesture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartLongPressed(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartDoubleTapped(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartSingleTapped(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChartTranslate(MotionEvent me, float dX, float dY) {
		// TODO Auto-generated method stub
		
	}
	
}

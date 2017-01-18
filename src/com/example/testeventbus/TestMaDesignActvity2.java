package com.example.testeventbus;

import java.util.ArrayList;

import com.example.testeventbus.TestRecleView.DividerItemDecoration;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
/**
 * 材料设计
 * @author 莫筱晴
 *
 */
public class TestMaDesignActvity2 extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
	private TextInputLayout textInputpwd,textInputAcc;
//	TabLayout tabLayout;
	CollapsingToolbarLayout collapsingToolbarLayout;

	private RecyclerView recyclerView_one;
	 private LinearLayoutManager mLayoutManager; 
	 FloatingActionButton fabBtn;
	 CoordinatorLayout rootLayout;
	 Toolbar toolbar;
	 private TabLayout tabs;
	 private TwinklingRefreshLayout  refreshLayout;
	 private AppBarLayout appBarLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_disgn2);
		appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
		rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
	    fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
	    fabBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
	                    .setAction("Undo", new View.OnClickListener() {
	                        @Override
	                        public void onClick(View v) {
	                        	
	                        }
	                    })
	                    .show();
	        }
	    });
	    
	    toolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);
	    final ActionBar ab = getSupportActionBar(); 
	          ab.setHomeAsUpIndicator(R.drawable.ic_launcher); 
	           ab.setDisplayHomeAsUpEnabled(true);
	           
	  /*  tabLayout = (TabLayout) findViewById(R.id.tabLayout);
	    tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
	    tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
	    tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));*/

	    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
	    collapsingToolbarLayout.setTitle("Design Library");
	    
	    tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setBackgroundColor(Color.GREEN);
        tabs.setTabTextColors(Color.WHITE, Color.RED);//文本选中和未选中时字体颜色
        tabs.addTab(tabs.newTab().setText("tab1").setIcon(R.drawable.fade_red));
        tabs.addTab(tabs.newTab().setText("tab2"));
        tabs.addTab(tabs.newTab().setText("tab3"));
	    
	    recyclerView_one = (RecyclerView) findViewById(R.id.listview);
        
	      //设置固定大小  
	        recyclerView_one.setHasFixedSize(true);  
	        //创建线性布局  
	        mLayoutManager = new LinearLayoutManager(this);  
	         //垂直方向  
	       mLayoutManager.setOrientation(OrientationHelper.VERTICAL);  
	        //给RecyclerView设置布局管理器  
	       recyclerView_one.setLayoutManager(mLayoutManager);  
//	       recyclerView_one.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
	     //设置Item增加、移除动画
	       recyclerView_one.setItemAnimator(new DefaultItemAnimator());
	       //添加分割线
	       
	        //创建适配器，并且设置  
//	        ((TestRecyclerAdapter) mAdapter).setRecycleItemClickListener(this);
	        recyclerView_one.setAdapter(new TestRecyclerAdapter(this)); 
	        
	        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
	        refreshLayout.setEnableLoadmore(true);
	        refreshLayout.setEnableRefresh(false);
//	        refreshLayout.setPureScrollModeOn(true);
	        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
	            @Override
	            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
	                new Handler().postDelayed(new Runnable() {
	                    @Override
	                    public void run() {
	                        refreshLayout.finishRefreshing();
	                    }
	                },2000);
	            }

	            @Override
	            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
	            	Log.i("tag", "on load more>>>>>>>>>>");
	                new Handler().postDelayed(new Runnable() {
	                    @Override
	                    public void run() {
	                        refreshLayout.finishLoadmore();
	                    }
	                },2000);
	            }
	        });
	    

	}
	
	 @Override 
	     public boolean onCreateOptionsMenu(Menu menu) { 
	       return true; 
	    } 
	
	  
	    @Override 
	    public boolean onOptionsItemSelected(MenuItem item) { 
	        switch (item.getItemId()) { 
	            case android.R.id.home: 
	                return true; 
	        } 
	        return super.onOptionsItemSelected(item); 
	     }

		@Override
		public void onOffsetChanged(AppBarLayout arg0, int arg1) {
			refreshLayout.setEnableRefresh(arg1 >= 0);
			Log.i("arg1", "values:"+arg1+"recyclerView_one:"+isSlideToBottom(recyclerView_one));
			
		} 
		protected boolean isSlideToBottom(RecyclerView recyclerView) {  
		    if (recyclerView == null) return false;  
		    if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())  
		        return true;  
		    return false;  
		}  
		@Override  
        protected void onResume() {  
       super.onResume();  
       appBarLayout.addOnOffsetChangedListener(this);  
   }  
 
   @Override  
   protected void onPause() {  
       super.onPause();  
       appBarLayout.removeOnOffsetChangedListener(this);  
   }  
 


}

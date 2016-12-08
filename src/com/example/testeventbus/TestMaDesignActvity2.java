package com.example.testeventbus;

import java.util.ArrayList;

import com.example.testeventbus.TestRecleView.DividerItemDecoration;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
/**
 * ≤ƒ¡œ…Ëº∆
 * @author ƒ™Û„«Á
 *
 */
public class TestMaDesignActvity2 extends AppCompatActivity  {
	private TextInputLayout textInputpwd,textInputAcc;
//	TabLayout tabLayout;
	CollapsingToolbarLayout collapsingToolbarLayout;

	private RecyclerView recyclerView_one;
	 private LinearLayoutManager mLayoutManager; 
	 FloatingActionButton fabBtn;
	 CoordinatorLayout rootLayout;
	 Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_disgn2);
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


}

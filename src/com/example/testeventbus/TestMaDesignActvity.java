package com.example.testeventbus;

import java.util.ArrayList;

import com.example.testeventbus.TestRecleView.DividerItemDecoration;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.support.design.widget.TabLayout;
/**
 * 材料设计
 * @author 莫筱晴
 *
 */
public class TestMaDesignActvity extends Activity {
	private TextInputLayout textInputpwd,textInputAcc;
	private TabLayout tabs;
	private RecyclerView recyclerView_one;
	 private LinearLayoutManager mLayoutManager; 
	ArrayList<String> list1 = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_disgn);
//		TextInputLayout作为一个父容器控件，包装了新的EditText。通常，单独的EditText会在用户输入第一个字母之后隐藏hint提示信息，但是现在你可以使用TextInputLayout 来将EditText封装起来，提示信息会变成一个显示在EditText之上的floating label，这样用户就始终知道他们现在输入的是什么。同时，如果给EditText增加监听，还可以给它增加更多的floating label。
		textInputpwd = (TextInputLayout) findViewById(R.id.til_pwd);
		EditText editText = textInputpwd.getEditText();
		textInputpwd.setHint("Password");
		
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 4) {
                	textInputpwd.setError("Password error");
                	textInputpwd.setErrorEnabled(true);
                } else {
                	textInputpwd.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        textInputAcc = (TextInputLayout) findViewById(R.id.til_acc);
        EditText editText1 = textInputAcc.getEditText();
        textInputAcc.setHint("account");
        textInputAcc.setBackgroundColor(Color.BLUE);
        editText1.addTextChangedListener(new TextWatcher() {
        	@Override
        	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        		if (s.length() > 6) {
        			textInputAcc.setError("account error");
        			textInputAcc.setErrorEnabled(true);
        		} else {
        			textInputAcc.setErrorEnabled(false);
        		}
        	}
        	
        	@Override
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        	}
        	
        	@Override
        	public void afterTextChanged(Editable s) {
        	}
        });
//        Tab滑动切换View并不是一个新的概念，但是Google却是第一次在support库中提供了完整的支持，而且，Design library的TabLayout 既实现了固定的选项卡 - view的宽度平均分配，也实现了可滚动的选项卡 - view宽度不固定同时可以横向滚动。选项卡可以在程序中动态添加：
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(Color.GREEN);
        tabs.setTabTextColors(Color.WHITE, Color.RED);//文本选中和未选中时字体颜色
        tabs.addTab(tabs.newTab().setText("tab1").setIcon(R.drawable.fade_red));
        tabs.addTab(tabs.newTab().setText("tab2"));
        tabs.addTab(tabs.newTab().setText("tab3"));
//        但大部分时间我们都不会这样用，通常滑动布局都会和ViewPager配合起来使用，所以，我们需要ViewPager来帮忙：
       /* mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // 设置ViewPager的数据等
        setupViewPager();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/
       /* for (int i = 0; i < 100; i++) {
			list1.add(i+1+"");
		}*/
        recyclerView_one = (RecyclerView) findViewById(R.id.listview);
        
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
       
        //创建适配器，并且设置  
//        ((TestRecyclerAdapter) mAdapter).setRecycleItemClickListener(this);
        recyclerView_one.setAdapter(new TestRecyclerAdapter(this)); 
		
	}

}

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
 * �������
 * @author Ī����
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
//		TextInputLayout��Ϊһ���������ؼ�����װ���µ�EditText��ͨ����������EditText�����û������һ����ĸ֮������hint��ʾ��Ϣ���������������ʹ��TextInputLayout ����EditText��װ��������ʾ��Ϣ����һ����ʾ��EditText֮�ϵ�floating label�������û���ʼ��֪�����������������ʲô��ͬʱ�������EditText���Ӽ����������Ը������Ӹ����floating label��
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
//        Tab�����л�View������һ���µĸ������Googleȴ�ǵ�һ����support�����ṩ��������֧�֣����ң�Design library��TabLayout ��ʵ���˹̶���ѡ� - view�Ŀ��ƽ�����䣬Ҳʵ���˿ɹ�����ѡ� - view��Ȳ��̶�ͬʱ���Ժ��������ѡ������ڳ����ж�̬��ӣ�
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(Color.GREEN);
        tabs.setTabTextColors(Color.WHITE, Color.RED);//�ı�ѡ�к�δѡ��ʱ������ɫ
        tabs.addTab(tabs.newTab().setText("tab1").setIcon(R.drawable.fade_red));
        tabs.addTab(tabs.newTab().setText("tab2"));
        tabs.addTab(tabs.newTab().setText("tab3"));
//        ���󲿷�ʱ�����Ƕ����������ã�ͨ���������ֶ����ViewPager�������ʹ�ã����ԣ�������ҪViewPager����æ��
       /* mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // ����ViewPager�����ݵ�
        setupViewPager();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/
       /* for (int i = 0; i < 100; i++) {
			list1.add(i+1+"");
		}*/
        recyclerView_one = (RecyclerView) findViewById(R.id.listview);
        
      //���ù̶���С  
        recyclerView_one.setHasFixedSize(true);  
        //�������Բ���  
        mLayoutManager = new LinearLayoutManager(this);  
         //��ֱ����  
       mLayoutManager.setOrientation(OrientationHelper.VERTICAL);  
        //��RecyclerView���ò��ֹ�����  
       recyclerView_one.setLayoutManager(mLayoutManager);  
//       recyclerView_one.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
     //����Item���ӡ��Ƴ�����
       recyclerView_one.setItemAnimator(new DefaultItemAnimator());
       //��ӷָ���
       
        //��������������������  
//        ((TestRecyclerAdapter) mAdapter).setRecycleItemClickListener(this);
        recyclerView_one.setAdapter(new TestRecyclerAdapter(this)); 
		
	}

}

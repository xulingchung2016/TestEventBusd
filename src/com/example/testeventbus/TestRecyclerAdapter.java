package com.example.testeventbus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder> {

	private LayoutInflater mInflater;  
    private String[] mTitles=null;  
    private  RecycleItemClickListener mItemCLickListener;
    public void setRecycleItemClickListener(RecycleItemClickListener mItemCLickListener){
    	this.mItemCLickListener = mItemCLickListener;
    }
    public interface RecycleItemClickListener{
    	void onItemClickListener(int pos);
    	void onItemLongClickListener(int pos);
    }
    public TestRecyclerAdapter(Context context){  
       this.mInflater=LayoutInflater.from(context);  
        this.mTitles=new String[20];  
        for (int i=0;i<20;i++){  
            int index=i+1;  
            mTitles[i]="item"+index;  
        }  
    }  
    /** 
     * item��ʾ���� 
     * @param parent 
     * @param viewType 
     * @return 
     */  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        View view=mInflater.inflate(R.layout.item_recycler_layout,parent,false);  
        //view.setBackgroundColor(Color.RED);  
        ViewHolder viewHolder=new ViewHolder(view);  
        return viewHolder;  
    }  
   
    @Override  
    public int getItemCount() {  
        return mTitles.length;  
    }  
   
    //�Զ����ViewHolder������ÿ��Item�ĵ����н���Ԫ��  
    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public TextView item_tv;  
        public ViewHolder(View view){  
            super(view);  
            item_tv = (TextView)view.findViewById(R.id.item_tv);  
           
        }  
    }

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		 holder.item_tv.setText(mTitles[position]);
		 holder. item_tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(mItemCLickListener != null){
						mItemCLickListener.onItemClickListener(position);
					}
					
				}
			});
	}  

}

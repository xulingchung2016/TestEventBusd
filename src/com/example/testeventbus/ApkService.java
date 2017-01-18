package com.example.testeventbus;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class ApkService extends AccessibilityService {
	 String[] PACKAGES = { "com.android.settings" };  
	 
	 public static int INVOKE_TYPE = 1;              // Event默认标志  
	    public static final int TYPE_INSTALL_APP = 1;   // 安装应用事件标  
	    public static final int TYPE_UNINSTALL_APP = 2; // 卸载应用事件标志  
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		// TODO Auto-generated method stub
//		   int eventType = event.getEventType();  
//		   if(eventType == AccessibilityEvent.TYPE_VIEW_CLICKED){
			   
			 /*  Log.i("来了", "来了啊》》》》》》》》》》》》》》》》》》》");
			   AccessibilityNodeInfo noteInfo = event.getSource();
			   Log.i("来了", "来了"+noteInfo);
			   if(noteInfo == null)return;
			   int lenth =  noteInfo.getChildCount();
			   String name = noteInfo.getClassName().toString();
			   Log.i("来了", "来了啊》》》》》》》》》》》》》》》》》》》");
			   if(name.equals("android.widget.Button")){
				   Log.i("text","："+ noteInfo.getText());
				   if(noteInfo.getText().equals("安装")){
					   Log.i("安装", "来了啊》》》》》》》》》》》》》》》》》》》");
					   noteInfo.performAction(AccessibilityEvent.TYPE_VIEW_CLICKED);//模拟点击安装
					   return;
				   }
			   }
			   
			   for (int i = 0; i < lenth; i++) {
				   AccessibilityNodeInfo noteInfo1 =  noteInfo.getChild(i);
				   String name1 =noteInfo1.getClassName().toString();
				   if(name1.equals("android.widget.Button")){
					   Log.i("text","："+ noteInfo1.getText());
					   if(noteInfo1.getText().equals("安装")){
						   Log.i("安装", "来了啊》》》》》》》》》》》》》》》》》》》");
						   noteInfo1.performAction(AccessibilityNodeInfo.ACTION_CLICK);//模拟点击安装
						   return;
					   }
				   }
			   }*/
//		   }
		processAccessibilityEnvent(event);
		
	}
	
	  /** 
     * 功能 ：事件处理方法 
     *  
     * @param event  事件类型 
     */  
    private void processAccessibilityEnvent(AccessibilityEvent event) {  
  
  
        if (event.getSource() != null) {  
            switch (INVOKE_TYPE) {  
            case TYPE_UNINSTALL_APP:  
                uninstallApplication(event); // 静默卸载  
                break;  
            case TYPE_INSTALL_APP:  
                installApplication(event); // 静默安装  
            default:  
                break;  
            }  
        }  
    }  
    
    /** 
     * 功能：实现静默卸载 
     *  
     * @param event 
     *            卸载事件 
     */  
    private void uninstallApplication(AccessibilityEvent event) {  
        findAndPerformActions(event, "确定");  
        findAndPerformActions(event, "确认");  
        findAndPerformActions(event, "卸载");  
    }  
  
  
    /** 
     * 功能 ：实现静默安装 
     *  
     * @param event 
     *            事件，如ACTION_INSTALL 
     */  
    private void installApplication(AccessibilityEvent event) {  
        findAndPerformActions(event, "安装");  
        findAndPerformActions(event, "下一步");  
        findAndPerformActions(event, "完成");  
        findAndPerformActions(event, "打开");  
       
    }  
   
    /** 
     * 功能：模拟用户点击操作 
     *  
     * @param text 
     */  
    private void findAndPerformActions(AccessibilityEvent event, String text) {  
    	Log.i("info:", "文本信息"+text+">>>>>>>>>>>>>");
        if (event.getSource() != null) {  
            Log.d("debug", "source不为空"+event.getPackageName());  
            // 判断当前界面为安装界面  
            boolean isInstallPage = event.getPackageName().equals(  
                    "com.android.packageinstaller");  
            if (isInstallPage) {  
                Log.d("debug", "isInstallPage不为false");  
                List<AccessibilityNodeInfo> action_nodes = event.getSource()  
                        .findAccessibilityNodeInfosByText(text);  
                if (action_nodes != null && !action_nodes.isEmpty()) {  
                    AccessibilityNodeInfo node = null;  
                    for (int i = 0; i < action_nodes.size(); i++) {  
                        node = action_nodes.get(i);  
                        // 执行按钮点击行为  
                        if (node.getClassName().equals("android.widget.Button")  
                                && node.isEnabled()) {  
                            Log.d("debug", "就是Button");  
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);  
                        }  
                    }  
                }  
            }  
        }  else {
        	  Log.d("debug", "source为空");  
        }
    }  
  

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub

	}
	
	   
}

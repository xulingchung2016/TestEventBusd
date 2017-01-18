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
	 
	 public static int INVOKE_TYPE = 1;              // EventĬ�ϱ�־  
	    public static final int TYPE_INSTALL_APP = 1;   // ��װӦ���¼���  
	    public static final int TYPE_UNINSTALL_APP = 2; // ж��Ӧ���¼���־  
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		// TODO Auto-generated method stub
//		   int eventType = event.getEventType();  
//		   if(eventType == AccessibilityEvent.TYPE_VIEW_CLICKED){
			   
			 /*  Log.i("����", "���˰���������������������������������������");
			   AccessibilityNodeInfo noteInfo = event.getSource();
			   Log.i("����", "����"+noteInfo);
			   if(noteInfo == null)return;
			   int lenth =  noteInfo.getChildCount();
			   String name = noteInfo.getClassName().toString();
			   Log.i("����", "���˰���������������������������������������");
			   if(name.equals("android.widget.Button")){
				   Log.i("text","��"+ noteInfo.getText());
				   if(noteInfo.getText().equals("��װ")){
					   Log.i("��װ", "���˰���������������������������������������");
					   noteInfo.performAction(AccessibilityEvent.TYPE_VIEW_CLICKED);//ģ������װ
					   return;
				   }
			   }
			   
			   for (int i = 0; i < lenth; i++) {
				   AccessibilityNodeInfo noteInfo1 =  noteInfo.getChild(i);
				   String name1 =noteInfo1.getClassName().toString();
				   if(name1.equals("android.widget.Button")){
					   Log.i("text","��"+ noteInfo1.getText());
					   if(noteInfo1.getText().equals("��װ")){
						   Log.i("��װ", "���˰���������������������������������������");
						   noteInfo1.performAction(AccessibilityNodeInfo.ACTION_CLICK);//ģ������װ
						   return;
					   }
				   }
			   }*/
//		   }
		processAccessibilityEnvent(event);
		
	}
	
	  /** 
     * ���� ���¼������� 
     *  
     * @param event  �¼����� 
     */  
    private void processAccessibilityEnvent(AccessibilityEvent event) {  
  
  
        if (event.getSource() != null) {  
            switch (INVOKE_TYPE) {  
            case TYPE_UNINSTALL_APP:  
                uninstallApplication(event); // ��Ĭж��  
                break;  
            case TYPE_INSTALL_APP:  
                installApplication(event); // ��Ĭ��װ  
            default:  
                break;  
            }  
        }  
    }  
    
    /** 
     * ���ܣ�ʵ�־�Ĭж�� 
     *  
     * @param event 
     *            ж���¼� 
     */  
    private void uninstallApplication(AccessibilityEvent event) {  
        findAndPerformActions(event, "ȷ��");  
        findAndPerformActions(event, "ȷ��");  
        findAndPerformActions(event, "ж��");  
    }  
  
  
    /** 
     * ���� ��ʵ�־�Ĭ��װ 
     *  
     * @param event 
     *            �¼�����ACTION_INSTALL 
     */  
    private void installApplication(AccessibilityEvent event) {  
        findAndPerformActions(event, "��װ");  
        findAndPerformActions(event, "��һ��");  
        findAndPerformActions(event, "���");  
        findAndPerformActions(event, "��");  
       
    }  
   
    /** 
     * ���ܣ�ģ���û�������� 
     *  
     * @param text 
     */  
    private void findAndPerformActions(AccessibilityEvent event, String text) {  
    	Log.i("info:", "�ı���Ϣ"+text+">>>>>>>>>>>>>");
        if (event.getSource() != null) {  
            Log.d("debug", "source��Ϊ��"+event.getPackageName());  
            // �жϵ�ǰ����Ϊ��װ����  
            boolean isInstallPage = event.getPackageName().equals(  
                    "com.android.packageinstaller");  
            if (isInstallPage) {  
                Log.d("debug", "isInstallPage��Ϊfalse");  
                List<AccessibilityNodeInfo> action_nodes = event.getSource()  
                        .findAccessibilityNodeInfosByText(text);  
                if (action_nodes != null && !action_nodes.isEmpty()) {  
                    AccessibilityNodeInfo node = null;  
                    for (int i = 0; i < action_nodes.size(); i++) {  
                        node = action_nodes.get(i);  
                        // ִ�а�ť�����Ϊ  
                        if (node.getClassName().equals("android.widget.Button")  
                                && node.isEnabled()) {  
                            Log.d("debug", "����Button");  
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);  
                        }  
                    }  
                }  
            }  
        }  else {
        	  Log.d("debug", "sourceΪ��");  
        }
    }  
  

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub

	}
	
	   
}

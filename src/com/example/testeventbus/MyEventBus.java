package com.example.testeventbus;

import org.greenrobot.eventbus.EventBus;

public class MyEventBus extends EventBus {
	public MyEventBus (){
		
	}
	public static MyEventBus getInstance(){
	
		return SigleHolder.eventBus;
	}

	public static class SigleHolder{
		static MyEventBus eventBus = new MyEventBus();
	}
	
}

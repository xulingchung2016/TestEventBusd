package com.example.testeventbus;

public class BaseThread extends Thread {
	
	
	private boolean isgo = true;
	public void execute() {
		
	}
	
	@Override
	public void run() {
		while(isgo){
		execute();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(">>>>>isgoing"+isgo);
		}
	}
	 
	public void quit(){
		isgo = false;
	
	}
	
	
	
	

}

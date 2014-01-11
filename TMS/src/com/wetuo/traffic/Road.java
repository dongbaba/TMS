package com.wetuo.traffic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	List<String> vechicles = new ArrayList<String>();
	
	private String name = null;
	public Road(String name) {
		this.name = name;
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable(){
			@Override
			public void run() {
				for(int i=1;i<1000;i++) {
					try {
						Thread.sleep((new Random().nextInt(10)+1) * 1000);//1~10秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					vechicles.add(Road.this.name+"_"+i);
				}
			}
			
		});
		//定时器
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable(){
					public void run() {
						//检查有没有车
						if(vechicles.size()>0) {
								boolean lighted = 
										Lamp.valueOf(Road.this.name).isLighted();
								if(lighted) {
									System.out.println(vechicles.remove(0)+" is traversing !");
								}
						}
					}
				}, 
				1, 
				1,
				TimeUnit.SECONDS);
		

		
		
	}
}

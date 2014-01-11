package com.wetuo.traffic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LampController {
	private Lamp currentLamp;
	public LampController() {
		currentLamp = Lamp.S2N;//由南向北
		currentLamp.light();
		
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable(){
					public void run() {
						System.out.println("来啊");
						currentLamp = currentLamp.blackOut();
					}
				}, 
				10, 
				10, 
				TimeUnit.SECONDS);
	}
}

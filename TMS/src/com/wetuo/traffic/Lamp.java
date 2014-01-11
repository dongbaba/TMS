package com.wetuo.traffic;

public enum Lamp {
   S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),
   N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
   S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
   private boolean lighted; //是否是绿灯
   private String opposite; //相反方向的灯
   private String next; //下一个灯
   
   private Lamp(String opposite,String next,boolean lighted) {
	   this.opposite = opposite;
	   this.next = next;
	   this.lighted = lighted;
   }
   private Lamp() {
   }
   
   public boolean isLighted() {//是否是绿灯
	   return lighted;
   }
   public void light() {//灯变绿
	   this.lighted = true;
	   if(opposite != null) {
		   Lamp.valueOf(opposite).light();
	   }
	   System.out.println(name()+" lamp is green,下面总共应该能看到6个方向的汽车穿过");
	  
   }
   
   public Lamp blackOut() {  //变红灯
	   this.lighted = false;
	   if(opposite != null) {
		   Lamp.valueOf(opposite).blackOut();
	   }
	   Lamp  nextLamp = null;
	   if(next != null) {
		   nextLamp = Lamp.valueOf(next);
		   System.out.println("绿灯从"+name()+"------>切换为"+next);
		   nextLamp.light();
	   }
	   return nextLamp;
   }
}

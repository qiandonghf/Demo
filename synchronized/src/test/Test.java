package test;

import test.Test.Thread3.Inner;

/**
 *  һ�������������̷߳���ͬһ������object�е����synchronized(this)ͬ�������ʱ��һ��ʱ����ֻ����һ���̵߳õ�ִ�С���һ���̱߳���ȴ���ǰ�߳�ִ�������������Ժ����ִ�иô���顣

     ����Ȼ������һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ����һ���߳���Ȼ���Է��ʸ�object�еķ�synchronized(this)ͬ������顣

     ��������ؼ����ǣ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�������̶߳�object����������synchronized(this)ͬ�������ķ��ʽ���������

     �ġ�����������ͬ����������ͬ������顣Ҳ����˵����һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�����ͻ�������object�Ķ�����������������̶߳Ը�object��������ͬ�����벿�ֵķ��ʶ�����ʱ������

     �塢���Ϲ��������������ͬ������.
 * @author Administrator
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*TestThread tt = new TestThread();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();*/
		
        /*Test2 test2 = new Test2();
        Thread ta = new Thread(test2, "A");  
        Thread tb = new Thread(test2, "B");  
        ta.start();
        tb.start();*/
        
        
        /*final Thread2 myt2 = new Thread2();  
        Thread t1 = new Thread(  new Runnable() {  public void run() {  myt2.m4t1();  }  }, "t1"  );  
        Thread t2 = new Thread(  new Runnable() {  public void run() { myt2.m4t2();   }  }, "t2"  );  
        t1.start();  
        t2.start();*/  
        
        final Thread3 myt3 = new Thread3(); 
        final Inner inner = myt3.new Inner(); 
        Thread t1 = new Thread( new Runnable() {public void run() { myt3.m4t1(inner);} }, "t1"); 
        Thread t2 = new Thread( new Runnable() {public void run() { myt3.m4t2(inner);} }, "t2"); 
        t1.start(); 
        t2.start(); 
	}
	
	public static class TestThread implements Runnable{
		int num = 100;
		String str = new String();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				if(num>0){
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.getMessage();
					}
					System.out.println(Thread.currentThread().getName()+ "this is "+ num--);
				}
			}
		}
		
	}
	
	public static class Test2 implements Runnable{
		@Override
		public void run() {
			synchronized (this) {
				for (int i=0;i<5;i++) {
					System.out.println(Thread.currentThread().getName()+" synchronized loop " + i);
				}
			}
			
		}
		
	}

	public static class Thread2 {  
	     public void m4t1() {  
	          synchronized(this) {  
	               int i = 5;  
	               while( i-- > 0) {  
	                    System.out.println(Thread.currentThread().getName() + " : " + i);  
	                    try {  
	                         Thread.sleep(500);  
	                    } catch (InterruptedException ie) {  
	                    }  
	               }  
	          }  
	     }  
	     /**
	      * ��������ؼ����ǣ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�������̶߳�object����������synchronized(this)ͬ�������ķ��ʽ���������
	      */
	    /* public void m4t2() {  
	          int i = 5;  
	          while( i-- > 0) {  
	               System.out.println(Thread.currentThread().getName() + " : " + i);  
	               try {  
	                    Thread.sleep(500);  
	               } catch (InterruptedException ie) {  
	               }  
	          }  
	     }*/
	     /***
	      * �ġ�����������ͬ����������ͬ������顣Ҳ����˵����һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�����ͻ�������object�Ķ�����������������̶߳Ը�object��������ͬ�����벿�ֵķ��ʶ�����ʱ������
	      */
	     public synchronized void m4t2() {  
	          int i = 5;  
	          while( i-- > 0) {  
	               System.out.println(Thread.currentThread().getName() + " : " + i);  
	               try {  
	                    Thread.sleep(500);  
	               } catch (InterruptedException ie) {  
	               }  
	          }  
	     }
	}
	/**
	 * �塢���Ϲ��������������ͬ������:
	 * @author Administrator
	 *
	 */
	public static class Thread3 { 
	     class Inner { 
	          private void m4t1() { 
	               int i = 5; 
	               while(i-- > 0) { 
	                    System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i); 
	                    try { 
	                         Thread.sleep(500); 
	                    } catch(InterruptedException ie) { 
	                    } 
	               } 
	          } 
	         /* private void m4t2() { 
	               int i = 5; 
	               while(i-- > 0) { 
	                    System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i); 
	                    try { 
	                         Thread.sleep(500); 
	                    } catch(InterruptedException ie) { 
	                    } 
	               } 
	          } */
	          
              /**
               * ������Inner.m4t2()ǰ�����synchronized��
               * �����߳�t1��t2������ͬһ��Inner����������������صĲ���,����Ϊt1�Ȼ���˶�Inner�Ķ�����������t2��Inner.m4t2()�ķ���Ҳ����������Ϊm4t2()��Inner�е�һ��ͬ��������
               */
	          private synchronized void m4t2() {  
	               int i = 5;  
	               while(i-- > 0) {  
	                    System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);  
	                    try {  
	                         Thread.sleep(500);  
	                    } catch(InterruptedException ie) {  
	                    }  
	               }  
	          }
	     } 
	     private void m4t1(Inner inner) { 
	          synchronized(inner) { //ʹ�ö����� 
	        	  inner.m4t1();
	          }
	     } 
	     private void m4t2(Inner inner) { 
	          inner.m4t2(); 
	     } 
	}
}

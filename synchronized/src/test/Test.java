package test;

import test.Test.Thread3.Inner;

/**
 *  一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。

     二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。

     三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。

     四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。

     五、以上规则对其它对象锁同样适用.
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
	      * 三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
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
	      * 四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
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
	 * 五、以上规则对其它对象锁同样适用:
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
               * 现在在Inner.m4t2()前面加上synchronized：
               * 尽管线程t1与t2访问了同一个Inner对象中两个毫不相关的部分,但因为t1先获得了对Inner的对象锁，所以t2对Inner.m4t2()的访问也被阻塞，因为m4t2()是Inner中的一个同步方法。
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
	          synchronized(inner) { //使用对象锁 
	        	  inner.m4t1();
	          }
	     } 
	     private void m4t2(Inner inner) { 
	          inner.m4t2(); 
	     } 
	}
}

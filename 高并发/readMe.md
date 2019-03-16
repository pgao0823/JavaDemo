#java高并发编程

##synchronize关键字：对某个对象加锁。

如下demo，synchronize(o)表示进入m()方法以后，要执行synchronize下面的语句，必须先申请o对象的锁，必须说明的是，申请o这个对象的锁，是指申请这个对象的堆内存，而不是o这个引用；如果o这个引用指向了其他的对象，那么锁的对象就变了。如果第一个线程在运行的时候，这个时候o这个对象的锁没有被其他对象持有，那么第一个线程就可以执行count--了，假设此时第二个线程来了，同样执行这段代码，当它走到synchronize的时候，也要去申请o这个对象的锁，此时由于第一个线程已经将o对象给锁住了，所以第二个线程执行不了，只能在这里等第一个线程执行完把这个锁被释放。

    package demo01;
    
    public class T {
    	
    	private int count = 10;
    	
    	public Object o = new Object();
    	
    	public void m() {
    		synchronized (o) {
    			count --;
    			System.out.println(Thread.currentThread().getName() + count);
    		}
    	}
    
    	public static void main(String[] args) {
    		T t = new T();
    		t.m();
    	}
    }


也可以将上述new出来的对象o用换成this，表示锁定当前对象。
也可以将synchronize关键字放在方法前，用public synchronized void m(){}等同于在执行m方法的时候要锁定当前对象synchronized(this)，方法结束时才会释放这把锁。

public synchronized static void m(){} 等同于synchronized(demo01.T.class)	，因为静态的成员变量和方法是不需要new出对象的，这样就不存在this，这时就不是等同于synchronized(this)。


在看如下例子：

    package demo02;
    
    public class T implements Runnable{
    	
    	private int count = 10;
    	
    	public void run() {
    		count --;
    		System.out.println(Thread.currentThread().getName() + ", count = " + count);
    	}
    
    	public static void main(String[] args) {
    		T t = new T();
    		for(int i =0;i<5;i++) {
    			new Thread(t, "thread" + i).start();
    		}
    	}
    
    }
    
运行两次结果如下：
    
    thread0, count = 8
    thread1, count = 8
    thread2, count = 7
    thread3, count = 6
    thread4, count = 5
    

    thread0, count = 9
    thread1, count = 8
    thread2, count = 7
    thread3, count = 6
    thread4, count = 5

多运行几次，发现并不是程序预期的每个线程count值减1，这是为什么呢？
这里分析下为什么第一个线程和第二个线程都打印8，而不是第一个线程打印9，第二个线程打印8，假设第一个线程启动，此时count--变成了9了，接下来准备走打印语句的时候，第二个线程来了，再执行count--此时count的值变成8了，然后再执行打印语句，就出现了这种情况，其他的不是预期的输出都是这种原因。

为了解决这种一个线程执行的时候被另一个线程打断的情况，就可以用synchronized方法来解决。

    package demo02;
    
    public class T implements Runnable{
    	
    	private int count = 10;
    	
    	public synchronized void run() {
    		count --;
    		System.out.println(Thread.currentThread().getName() + ", count = " + count);
    	}
    
    	public static void main(String[] args) {
    		T t = new T();
    		for(int i =0;i<5;i++) {
    			new Thread(t, "thread" + i).start();
    		}
    	}
    
    }
    
此时无论执行多少遍，输出都是
    
    thread0, count = 9
    thread4, count = 8
    thread3, count = 7
    thread2, count = 6
    thread1, count = 5

###这就是通常所说的原子操作。

###同步非同步方式可以同时调用
通过打印结果可以看出，第一个线程在运行m1方法的时候，虽然锁定了当前对象，但是第二个线程依然可以运行m2方法。这个很好解释，因为只有m1方法执行的时候是需要锁定当前对象的，而m2执行的时候是不需要申请这把锁的。


    package demo03;
    
    import java.util.concurrent.TimeUnit;
    
    public class T {
    	
    	public synchronized void m1() {
    		System.out.println("m1 start ...");
    		try {
    			Thread.sleep(10000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("m1 end ...");
    	}
    	
    	public void m2() {
    		try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("m2");
    	}
    
    	public static void main(String[] args) {
    		T t = new T();
    		new Thread(new Runnable() {
    			@Override
    			public void run() {
    				t.m1();
    			}	
    		}).start();
    		//java8 lambda表达式
    		new Thread(()->t.m2(),"t2").start();
		    //更简洁的写法
		    //new Thread(t::m2,"t2").start();
    	}
    
    }

输出结果

    m1 start ...
    m2
    m1 end ...


###对业务写的方法加锁，读的方法不加锁，容易产生脏读（dirtyRead）问题

    package demo04;
    
    public class Account {
    	
    	private String name;
    	private double money;
    	
    	public synchronized void set(String name, double money) {
    		this.name = name;
    		/*模拟业务代码在执行，在同步方法执行的时候，其他非同步方法比如getMoney方法也可以执行，
    		 * 一个线程在执行set方法的this.name=name和this.money=mone之间，
    		 * 另一个线程的getMoney方法执行了，来读取账户上的钱数，这个时候读取到的money值是初始值0，这就产生了脏读*/
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		this.money = money;
    	}
    
    	public double getMoney(String name) {
    		return this.money;
    	}
    	
    	public static void main(String[] args) {
    		Account a = new Account();
    		new Thread(()->a.set("gaopan",100.0),"t1").start();
    		System.out.println(a.getMoney("gaopan"));
    		
    		try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		System.out.println(a.getMoney("gaopan"));
    	}
    
    }


输出结果：

    0.0
    100.0

解决办法就是在读的方法上也加上synchronized同步锁

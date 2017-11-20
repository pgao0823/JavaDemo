# 多线程 #

## 创建和启动线程 ##

有两种方法：

第一种

    1、定义线程类实现Runnable接口
    2、Thread t = new Thread(r) //r为Runnable接口类型
    3、Runnable中只有一个方法：public void run(); 用以定义线程运行体
    4、使用Runnable接口可以为多个线程提供共享数据
    5、在实现Runnable接口的类的run方法定义中可以使用Thread的静态方法：public static Thread currentThread()获取当前线程的引用
范例一：

    public class TestThread1 {
    	public static void main(String[] args) {
    		Runner1 r1 = new Runner1();
    		Thread t = new Thread(r1); //起一个线程，先new一个Thread对象出来
    		t.start(); //再调用start方法，开启这个线程
    		
    		for (int i = 0; i < 100; i++) {
    			System.out.println("main thread: "+i);
    		}
    	}
    }
    class Runner1 implements Runnable{ //方法一：实现Runnable接口
    	public void run() {
    		for (int i = 0; i < 100; i++) {
    			System.out.println("Runner1: "+i);
    		}
    	}
    }

运行结果：可以看出，main线程和r1线程是在并行执行的。但是CPU分配给两个线程的时间片不是相等的。

    main thread: 0
    main thread: 1
    main thread: 2
    main thread: 3
    main thread: 4
    main thread: 5
    main thread: 6
    main thread: 7
    main thread: 8
    main thread: 9
    main thread: 10
    main thread: 11
    main thread: 12
    main thread: 13
    main thread: 14
    main thread: 15
    main thread: 16
    main thread: 17
    main thread: 18
    main thread: 19
    main thread: 20
    main thread: 21
    main thread: 22
    main thread: 23
    main thread: 24
    main thread: 25
    main thread: 26
    main thread: 27
    main thread: 28
    main thread: 29
    main thread: 30
    main thread: 31
    main thread: 32
    main thread: 33
    main thread: 34
    Runner1: 0
    Runner1: 1
    Runner1: 2
    Runner1: 3
    Runner1: 4
    Runner1: 5
    Runner1: 6
    Runner1: 7
    Runner1: 8
    Runner1: 9
    Runner1: 10
    Runner1: 11
    Runner1: 12
    Runner1: 13
    Runner1: 14
    Runner1: 15
    Runner1: 16
    Runner1: 17
    Runner1: 18
    Runner1: 19
    Runner1: 20
    Runner1: 21
    Runner1: 22
    Runner1: 23
    Runner1: 24
    Runner1: 25
    Runner1: 26
    Runner1: 27
    Runner1: 28
    Runner1: 29
    Runner1: 30
    Runner1: 31
    Runner1: 32
    Runner1: 33
    Runner1: 34
    Runner1: 35
    Runner1: 36
    Runner1: 37
    Runner1: 38
    Runner1: 39
    Runner1: 40
    Runner1: 41
    Runner1: 42
    Runner1: 43
    Runner1: 44
    Runner1: 45
    Runner1: 46
    Runner1: 47
    Runner1: 48
    Runner1: 49
    Runner1: 50
    Runner1: 51
    Runner1: 52
    Runner1: 53
    Runner1: 54
    Runner1: 55
    Runner1: 56
    main thread: 35
    Runner1: 57
    main thread: 36
    main thread: 37
    main thread: 38
    main thread: 39
    main thread: 40
    main thread: 41
    main thread: 42
    main thread: 43
    main thread: 44
    main thread: 45
    main thread: 46
    main thread: 47
    main thread: 48
    main thread: 49
    main thread: 50
    main thread: 51
    main thread: 52
    main thread: 53
    main thread: 54
    main thread: 55
    main thread: 56
    main thread: 57
    main thread: 58
    main thread: 59
    main thread: 60
    main thread: 61
    main thread: 62
    main thread: 63
    main thread: 64
    main thread: 65
    main thread: 66
    main thread: 67
    main thread: 68
    main thread: 69
    main thread: 70
    main thread: 71
    main thread: 72
    main thread: 73
    main thread: 74
    main thread: 75
    main thread: 76
    main thread: 77
    main thread: 78
    main thread: 79
    main thread: 80
    main thread: 81
    main thread: 82
    main thread: 83
    main thread: 84
    main thread: 85
    main thread: 86
    main thread: 87
    main thread: 88
    main thread: 89
    main thread: 90
    main thread: 91
    main thread: 92
    main thread: 93
    main thread: 94
    main thread: 95
    main thread: 96
    main thread: 97
    main thread: 98
    main thread: 99
    Runner1: 58
    Runner1: 59
    Runner1: 60
    Runner1: 61
    Runner1: 62
    Runner1: 63
    Runner1: 64
    Runner1: 65
    Runner1: 66
    Runner1: 67
    Runner1: 68
    Runner1: 69
    Runner1: 70
    Runner1: 71
    Runner1: 72
    Runner1: 73
    Runner1: 74
    Runner1: 75
    Runner1: 76
    Runner1: 77
    Runner1: 78
    Runner1: 79
    Runner1: 80
    Runner1: 81
    Runner1: 82
    Runner1: 83
    Runner1: 84
    Runner1: 85
    Runner1: 86
    Runner1: 87
    Runner1: 88
    Runner1: 89
    Runner1: 90
    Runner1: 91
    Runner1: 92
    Runner1: 93
    Runner1: 94
    Runner1: 95
    Runner1: 96
    Runner1: 97
    Runner1: 98
    Runner1: 99

如果将上述代码修改为：

    public class TestThread1 {
    	public static void main(String[] args) {
    		Runner1 r1 = new Runner1();
    		r1.run();
    		
    		for (int i = 0; i < 100; i++) {
    			System.out.println("main thread: "+i);
    		}
    	}
    }
    class Runner1 implements Runnable{ //方法一：实现Runnable接口
    	public void run() {
    		for (int i = 0; i < 100; i++) {
    			System.out.println("Runner1: "+i);
    		}
    	}
    }

则并没有开启新的线程，而只是在main方法中调用了r1的run()方法，不难看出，程序是先执行r1的run()方法循环打印“"Runner1: "+i”，执行完以后才会接着执行main方法中的循环打印语句。

第二种：

    1、定义一个Thread的子类并重写其run方法如：
    class mThread extends Thread{
      public void run(){...}
    }
    2、然后生成该类的对象：
    mThread t = new mThread()

范例二：

    public class TestThread2 {
    	public static void main(String[] args) {
    		Runner2 r2 = new Runner2();
    		r2.start();
    		
    		for (int i = 0; i < 100; i++) {
    			System.out.println("main thread: "+i);
    		}
    	}
    }
    class Runner2 extends Thread{ //方法二：继承Thread类，此时Runner2本身就是一个Thread了，所以可以在main方法中直接调r2.start()开启新线程
    	public void run() {
    		for (int i = 0; i < 100; i++) {
    			System.out.println("Runner1: "+i);
    		}
    	}
    }
运行结果和范例一是一样的，也是main和r2是并行执行的

## 线程状态转换 ##

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E7%BA%BF%E7%A8%8B1.png)

线程类new出来的时候，只是在内存中创建了一个对象，接下来调用它的start()方法，并不是说调用了start()方法后线程就立马执行了，而只是处于就绪状态，为什么呢？因为此时CPU可能正在执行其他线程，你新建的线程准备好了排队去，等着CPU分配时间片段出来，如果在CPU分配的时间片内（运行状态）事情没有处理完，就又回到就绪状态等着去


## 线程控制基本方法 ##

1. isAlive(): 判断线程是否还“活着”，即线程是否还未终止。（什么叫活着？就绪/运行/阻塞、这几个状态都表示活着，终止了就是死了，new出来没有start也是死的）
2. getPriority(): 获得线程的优先级数值
3. setPriority(): 设置线程的优先级数值
4. Thread.sleep(): 将当前线程睡眠指定毫秒数
5. join(): 调用某线程的该方法，将当前线程与该线程“合并”，即等待该线程结束，再恢复当前线程的运行
6. yield(): 让出CPU，当前线程进入就绪队列等待调用
7. wait(): 当前线程进入对象的wait pool
8. notify()/notifyAll(): 唤醒对象的wait pool中的一个/所有等待的线程

范例：Interrupt方法

    public class TestInterrupt {
    	public static void main(String[] args) {
    		MyThread t = new MyThread();
    		t.start();
    		try {
    			Thread.sleep(10000); //在哪个线程里调用sleep方法就让哪个线程睡眠，这里只主线程
    		} catch (InterruptedException e) {}
    //		t.interrupted(); //在sleep过程中，调用Thread类中的interrupt方法以后，就会抛出InterruptedException异常
    		t.flag = false; //flag置为false以后，while循环终止
    	}
    }
    class MyThread extends Thread{
    	boolean flag = true;
    	public void run(){
    		while(flag){
    			System.out.println("-->"+new Date()+"<--");
    			try {
    				sleep(1000);
    			} catch (InterruptedException e) {
    				return; //子线程结束
    			}
    		}
    	}
    }

运行结果：

    D:\EclipseWorkspace\TestThread\src\com\gaopan\testthread>java TestInterrupt
    -->Wed Nov 15 21:19:40 CST 2017<--
    -->Wed Nov 15 21:19:41 CST 2017<--
    -->Wed Nov 15 21:19:42 CST 2017<--
    -->Wed Nov 15 21:19:43 CST 2017<--
    -->Wed Nov 15 21:19:44 CST 2017<--
    -->Wed Nov 15 21:19:45 CST 2017<--
    -->Wed Nov 15 21:19:46 CST 2017<--
    -->Wed Nov 15 21:19:47 CST 2017<--
    -->Wed Nov 15 21:19:48 CST 2017<--
    -->Wed Nov 15 21:19:49 CST 2017<--
    
    D:\EclipseWorkspace\TestThread\src\com\gaopan\testthread>

范例：Join方法

    public class TestJoin {
    	public static void main(String[] args) {
    		MyThread1 t1 = new MyThread1("thread-->1");
    		t1.start();
    		try {
    			t1.join();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		for (int i = 0; i < 10; i++) {
    			System.out.println("this is main thread");
    		}
    	}
    }
    class MyThread1 extends Thread{
    	public MyThread1(String name) { //有一个入参的构造
    		super(name);
    	}
    	public void run(){
    		for (int i = 0; i < 10; i++) {
    			System.out.println("this is "+getName());
    			try {
    				sleep(1000);
    			} catch (InterruptedException e) {
    				return;
    			}
    		}
    	}
    }
运行结果：

    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is thread-->1
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread
    this is main thread

范例：Yield方法

    public class TestYield {
    	public static void main(String[] args) {
    		MyThread2 t1 = new MyThread2("t1");
    		MyThread2 t2 = new MyThread2("t22222");
    		t1.start();
    		t2.start();
    	}
    }
    
    class MyThread2 extends Thread {
    	public MyThread2(String name) {
    		super(name);
    	}
    	public void run() {
    		for (int i = 0; i < 20; i++) {
    			System.out.println(getName() + "-->"+i);
    			if (i % 10 == 0) {
    				yield();
    			}
    		}
    	}
    }
运行结果：

    t1-->0
    t22222-->0
    t1-->1
    t22222-->1
    t22222-->2
    t22222-->3
    t1-->2
    t1-->3
    t22222-->4
    t22222-->5
    t22222-->6
    t22222-->7
    t22222-->8
    t22222-->9
    t22222-->10
    t1-->4
    t1-->5
    t1-->6
    t1-->7
    t1-->8
    t1-->9
    t1-->10
    t22222-->11
    t22222-->12
    t22222-->13
    t22222-->14
    t22222-->15
    t22222-->16
    t22222-->17
    t22222-->18
    t22222-->19
    t1-->11
    t1-->12
    t1-->13
    t1-->14
    t1-->15
    t1-->16
    t1-->17
    t1-->18
    t1-->19

## 线程的优先级 ##

Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程，线程调度器按照线程的优先级决定应该调度哪个线程来执行。

线程的优先级用数字表示，范围从1到10，一个线程的缺省优先级是5

    Thread.MIN_PRIORITY=1
    Thread.MAX_PRIORITY=10
    Thread.NORM_PRIORITY=5

使用下述方法获得或设置线程对象的优先级

    int getPriority();
    void setPriority(int newPriority);


## 线程同步 ##

范例：

    public class TestSync implements Runnable{
    	Timer timer = new Timer();
    	public static void main(String[] args) {
    		TestSync test = new TestSync();
    		Thread t1 = new Thread(test);
    		Thread t2 = new Thread(test);
    		t1.setName("t1");
    		t2.setName("t2");
    		t1.start();
    		t2.start();
    	}
    	public void run() {
    		timer.add(Thread.currentThread().getName());
    	}
    }
    class Timer{
    	public static int num = 0;
    	public void add(String name){
    		num ++;
    		try {
    			Thread.sleep(1);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		System.out.println(name + ": 你是第"+num+"个使用Timer的线程");
    	}
    }
运行结果：

    t2: 你是第2个使用Timer的线程
    t1: 你是第2个使用Timer的线程

为什么都是第2个使用Timer线程呢？因为当t1启动以后，执行timer的add方法时，num的值由0增加到1，然后t1线程睡眠1ms（t1被打断了），此时t2执行到了timer的add方法，将num的值由1增加到了2，然后执行打印语句，然厚t1执行接着执行打印语句。

将上述例子中做如下修改：

    package com.gaopan.testthread;
    
    public class TestSync implements Runnable{
    	Timer timer = new Timer();
    	public static void main(String[] args) {
    		TestSync test = new TestSync();
    		Thread t1 = new Thread(test);
    		Thread t2 = new Thread(test);
    		t1.setName("t1");
    		t2.setName("t2");
    		t1.start();
    		t2.start();
    	}
    	public void run() {
    		timer.add(Thread.currentThread().getName());
    	}
    }
    class Timer{
    	public static int num = 0;
    	public void add(String name){
    		synchronized (this) {
    			num ++;
    			try {
    				Thread.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.println(name + ": 你是第"+num+"个使用Timer的线程");
    		}
    	}
    }
运行结果：

    t1: 你是第1个使用Timer的线程
    t2: 你是第2个使用Timer的线程

关键字synchronized表示锁定当前对象，即锁定的这个线程执行的过程中不会被打断，即使在执行过程中调用了sleep()也没关系，别的线程必须等着代码执行完才能执行该段代码

还有一种简便的写法，相当于在执行add方法的过程中锁定当前对象

    class Timer{
    	public static int num = 0;
    	public synchronized void add(String name){
    		num ++;
    		try {
    			Thread.sleep(1);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		System.out.println(name + ": 你是第"+num+"个使用Timer的线程");
    	}
    }

### 死锁 ###

一个线程同步块持有a对象的同时需要再持有b对象就可以执行，但是另一个线程同步块持有b对象的同时等待持有a对象，这样就导致了死锁

范例：

    public class TT implements Runnable{
    	int b = 100;
    	public synchronized void m1() throws Exception{
    		b = 1000;
    		Thread.sleep(5000);
    		System.out.println("m1-->"+b);
    	}
    	public synchronized void m2() throws Exception{
    		Thread.sleep(2500);
    		b = 2000;
    		System.out.println("m2-->"+b);
    	}
    	public void run() {
    		try {
    			m1();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	public static void main(String[] args) throws Exception {
    		TT tt = new TT();
    		Thread t = new Thread(tt);
    		t.start();
    		tt.m2();
    		System.out.println("main-->"+tt.b);
    	}
    }
运行结果：

    m2-->2000
    main-->2000
    m1-->1000

如果将m2()方法的synchronized关键字去掉，运行结果是：

    m2-->2000
    main-->2000
    m1-->2000

可以看出，如果两个方法都改了b的值，为了避免相互影响，应该都加同步

wait()的过程中，不再持有锁对象

wait()、notify()一一对应，notify()表示叫醒当前正在wait的线程，继续往下执行。
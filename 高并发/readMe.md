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


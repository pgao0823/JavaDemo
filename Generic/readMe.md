# 泛型 #

泛型是指在定义类的时候不会设置类中属性或者方法中的参数的具体类型，而是在类使用的时候再进行定义。
如果想要进行泛型的操作，就必须做一个类型标记的声明。

    package com.gaopan.reflection;
    
    import java.util.Date;
    
    class Point<T>{
    	private T x;
    	private T y;
    	public T getX() {
    		return x;
    	}
    	public void setX(T x) {
    		this.x = x;
    	}
    	public T getY() {
    		return y;
    	}
    	public void setY(T y) {
    		this.y = y;
    	}
    	
    }
    
    public class TestDemo {
    	public static void main(String[] args){
    		Point<String> p = new Point<String>();
    		p.setX("东经10度");
    		Point<Integer> p1 = new Point<Integer>();
    		p1.setY(10);
    		String x = p.getX();
    		Integer y = p1.getY();
    		System.out.println("x: "+x+", y: "+y);
    	}
    }


# 通配符 #

先看一段程序

    package com.gaopan.reflection;
    
    import java.util.Date;
    
    class Message<T>{
    	private T note;
    	public T getNote() {
    		return note;
    	}
    	public void setNote(T note) {
    		this.note = note;
    	}
    }
    
    public class TestDemo {
    	public static void main(String[] args){
    		Message<String> msg = new Message<String>();
    		msg.setNote("通配符是什么鬼？");
    		fun(msg);
    	}
    	public static void fun(Message<String> temp){
    		System.out.println(temp.getNote());
    	}
    }

上述代码运行很正常，但是如果
> Message<Integer> msg = new Message<Integer>();
> 
> msg.setNote(2);

在new message对象的时候，泛型的数据类型设置的不是String，而是Integer，由于此时fun()接收的类型只能是String，那么在主方法中调用fun()方法的时候就会报错。

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E6%B3%9B%E5%9E%8B1.png)

此时就需要用通配符“?”了：

    package com.gaopan.reflection;
    
    import java.util.Date;
    
    class Message<T>{
    	private T note;
    	public T getNote() {
    		return note;
    	}
    	public void setNote(T note) {
    		this.note = note;
    	}
    }
    
    public class TestDemo {
    	public static void main(String[] args){
    		Message<Integer> msg = new Message<Integer>();
    		msg.setNote(21);
    		fun(msg);
    	}
    	//此时使用的通配符“?”描述的是它可以接受任意类型，但是由于不确定类型，所以无法修改
    	public static void fun(Message<?> temp){
    		System.out.println(temp.getNote());
    	}
    }


# 泛型接口 #

定义一个泛型接口：

    interface IMessage<T>{  //在接口上定义了泛型
    	public void print(T t);
    }

这个接口的实现子类有两种方法：

1、在子类定义的时候继续使用泛型：

    interface IMessage<T>{  //在接口上定义了泛型
    	public void print(T t);
    }
    class MessageImpl<T> implements IMessage<T>{
    	public void print(T t) {
    		System.out.println(t);
    	}
    }
    public class TestDemo {
    	public static void main(String[] args){
    		IMessage<String> msg= new MessageImpl<String>();
    		msg.print("Hello World!");
    	}
    }
2、在子类实现接口的时候明确给出具体类型：

    interface IMessage<T>{  //在接口上定义了泛型
    	public void print(T t);
    }
    class MessageImpl implements IMessage<String>{
    	public void print(String t) {
    		System.out.println(t);
    	}
    }
    public class TestDemo {
    	public static void main(String[] args){
    		IMessage<String> msg= new MessageImpl();
    		msg.print("Hello World!");
    	}
    }
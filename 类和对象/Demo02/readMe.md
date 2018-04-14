# 引用传递初次分析 #

对于初学者最难的就是引用传递，以后开发中用的都是引用传递

引用传递的本质是别名，这个别名只不过放在了栈内存中：**一块堆内存可以被多个栈内存所指向**。

范例：观察引用传递

	class Person {//定义一个类，类名称的首字母要大写
		String name;
		int age;
	public void info(){
			System.out.println("name: " + name + ", age: " + age);
		}
	}
	
	public class Demo02 {
		public static void main(String[] args) {
			Person per1 = new Person();  //声明并实例化对象
			per1.name = "小于子";  //设置对象的属性
			per1.age = 30;  //设置对象的属性
			Person per2 = per1;  //采用相同的类型接收  类似于int num = 10;   int x = num;
			per2.name = "狗剩";
			per1.info();  //调用对象中的方法
		}
	}
运行结果：
name: 狗剩, age: 30

看内存分析图：

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%95%E7%94%A8%E4%BC%A0%E9%80%92%E5%86%85%E5%AD%98%E5%88%86%E6%9E%901.png)

范例：观察引用传递

	class Person {//定义一个类，类名称的首字母要大写
		String name;
		int age;
		public void info(){
			System.out.println("name: " + name + ", age: " + age);
		}
	}
	
	public class Demo02 {
		public static void main(String[] args) {
			Person per1 = new Person();  //声明并实例化对象
			Person per2 = new Person();
			per1.name = "小于子";  //设置对象的属性
			per1.age = 30;  //设置对象的属性
			per2.name = "张三";
			per2.age = 20;
			//此步骤就是引用传递
			per2 = per1;  //采用相同的类型接收  类似于int num = 10;   int x = num;
			per2.name = "狗剩";
			per1.info();  //调用对象中的方法
		}
	}

内存分析图：
![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%95%E7%94%A8%E4%BC%A0%E9%80%92%E5%86%85%E5%AD%98%E5%88%86%E6%9E%902.png)
代码的关键：per2 = per1;

此时per1和per2都有自己的指向了，由于栈内存存放的是地址，是数值，当将per1赋值给per2的时候，per2的指向就是per1指向的堆内存空间，如红箭头所示，由此原来per2指向的堆内存就没有任何引用了，就变成了垃圾内存。

垃圾内存空间是指没有任何栈内存指向的堆内存空间，所有的垃圾内存空间将不定期被java中的垃圾收集器（GC：Garbage Collector）进行回收以释放内存空间。

从实际的开发来讲，虽然java提供了GC，但是GC也会造成程序性能的下降，所以开发过程中要控制好对象的产生数量，即：无用的对象尽可能少产生。
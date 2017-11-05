# 类的定义和对象的使用 #

类的定义使用class关键字完成，定义的语法如下：

	class 类名称 {     //所有的程序都要求以“{}”为分界符
	    属性;
	    属性;
	    方法(){}
	}
此时的方法不再由主类调用，而是要通过对象进行调用

范例：

	class Person {   //定义一个类，类名称的首字母要大写
		String name;
		int age;
	    public void info(){
			System.out.println("name: " + name + ", age: " + age);
		}
	}

类定义完成以后是不能直接调用的，如果想使用类就必须要产生对象，对象的定义分为以下两种语法形式：
>     1.声明并实例化对象：类名称 对象名称 = new 类名称();
>     2.分步进行对象实例化
>         2.1.声明对象：类名称 对象名称 = null;
>         2.1.实例化对象：对象名称 = new 类名称();
关键字new表示开辟新内存

范例：

	public class Demo01 {
		public static void main(String[] args) {
			Person per = new Person();
			per.name = "张三";
			per.age = 18;
			per.info();
		}
	}
对象只有实例化了以后才可以真正使用，而对象的使用都是围绕类进行的，那么此时就有两种形式：
> 1.调用类中的属性：对象.属性 = 内容;
> 
> 2.调用类中的方法：对象.方法();


# 对象的产生分析 #

如果要想进行对象的产生分析，那么首先就必须清楚引用类型，引用类型指的是内存空间的操作。对于内存空间，我们需要会使用两块内存空间：
> 1.堆内存空间：保存真正的数据，堆内存保存的是对象的属性信息；
> 
> 2.栈内存空间：保存的堆内存的地址，堆内存的操作权；

那么按照之前的程序，可以给出如下内存图：
![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E6%A0%88%E5%86%85%E5%AD%98%E5%92%8C%E5%A0%86%E5%86%85%E5%AD%98%E5%9B%BE1.png)

以上产生对象的形式是声明并实例化，也可以使用另外一种形式，即分步进行对象实例化：

	class Person {    //定义一个类，类名称的首字母要大写
		String name;
		int age;
	    public void info(){
			System.out.println("name: " + name + ", age: " + age);
		}
	}
	
	public class Demo01 {
		public static void main(String[] args) {
			Person per = null;  //声明对象
			per = new Person();  //实例化对象
			per.name = "张三";  //设置对象的属性
			per.age = 18;  //设置对象的属性
			per.info();  //调用对象中的方法
		}
	}
运行结果也是 name: 张三, age: 18
![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E6%A0%88%E5%86%85%E5%AD%98%E5%92%8C%E5%A0%86%E5%86%85%E5%AD%98%E5%9B%BE2.png)

对象（所有的引用数据类型）都必须实例化（开辟堆内存空间）后才能使用，如果使用了还没有开辟内存空间的引用数据类型，则将出现NullPointerException异常。例如将per = new Person();注释掉即只声明对象而没有实例化：

	class Person {    //定义一个类，类名称的首字母要大写
		String name;
		int age;
	    public void info(){
			System.out.println("name: " + name + ", age: " + age);
		}
	}
	
	public class Demo01 {
		public static void main(String[] args) {
			Person per = null;  //声明对象
			//per = new Person();  //实例化对象
			per.name = "张三";  //设置对象的属性
			per.age = 18;  //设置对象的属性
			per.info();  //调用对象中的方法
		}
	}
运行结果为：

>Exception in thread "main" java.lang.NullPointerException
        at Demo01.main(Demo01.java:13)

上述例子只是声明了对象，但是没有为其开辟对内存空间，此时编译时没有语法错误的，但是运行会报NullPointerException。

NullPointerException是开发中最常见的异常，只有引用数据类型（数组，类，接口）才会产生此异常，所以以后出现了此异常，就根据错误位置观察对象是否被实例化。
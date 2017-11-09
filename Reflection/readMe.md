# 反射 #

反射：
　　可以动态的获取指定类中的成员，以及建立类对象。


1、任何实例化对象都可以通过Object类中的getClass()方法取得Class对象；

    public class TestDemo {
    	public static void main(String[] args) {
    		Class<?> clazz = new Date().getClass();
    		System.out.println(clazz.getName());
    	}
    }

2、“类.class”：直接根据某一个具体的类来取得Class类的实例化对象；

    public class TestDemo {
    	public static void main(String[] args) {
    		Class<?> clazz = Date.class; //不需要实例化Date类
    		System.out.println(clazz.getName());
    	}
    }

3、使用Class类提供的方法forName()方法；

    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("java.util.Date"); //直接使用字符串描述的类
    		System.out.println(clazz.getName());
    	}
    }

只有第一种会产生Date类的实例化对象，第二种和第三种都不会产生实例化对象。于是取得Class类对象可以直接通过反射实例化需要取得的对象，在Class类中有一个方法：newInstance().

反射实例化对象：

    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("java.util.Date"); 
    		Object object = clazz.newInstance(); //实例化对象，等价于new java.util.Date
    		System.out.println(object);
    	}
    }
运行结果：Tue Nov 07 17:49:09 CST 2017

**那么现在除了关键字new之外，对于对象的实例化模式有了第二种方法，通过反射进行。**

**取得了Class类就意味着取得了一个指定类的操作权**


# 反射取得父类信息 #

Class类取得父类的方法：

1、取得包名称：getPackage()

2、取得父类的Class对象：getSuperclass()

3、取得父接口：getInterfaces()

# 反射调用构造 #

一个类中可以存在多个构造方法，那么如果想取得类中构造的调用，就可以使用Class类提供的两个方法：

1、取得指定参数类型的构造getConstructor(Class<?>...paramterTypes)

2、取得类中所有的构造getConstructors()

上述两个方法返回的类型都是java.lang.reflect.Constructor类的一个实例化对象，这个类里面关注一个方法：

实例化对象：public T newInstance(Object...initargs)

    class Person{
    	public Person(){}
    	public Person(String name){}
    	public Person(String name,int age){}
    }
    public class TestDemo {
    	public static void main(String[] args){
    		Class<?> clazz = Person.class;
    		Constructor<?> constructor[] = clazz.getConstructors();
    		for (int i = 0; i < constructor.length; i++) {
    			System.out.println(constructor[i]);
    		}
    	}
    }

运行结果：

    public com.gaopan.reflection.Person()
    public com.gaopan.reflection.Person(java.lang.String)
    public com.gaopan.reflection.Person(java.lang.String,int)

将Person类中的无参构造注去掉，然后再主方法中通过Class类去实例化对象：

    package com.gaopan.reflection;
    
    import java.lang.reflect.Constructor;
    import java.util.Date;
    
	class Person{
		private String name;
		private int age;
		public Person(String name,int age){
			this.name = name;
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
		
	}
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Person.class;
    		System.out.println(clazz.newInstance());
    	}
    }

出现如下NoSuchMethodException异常

    Exception in thread "main" java.lang.InstantiationException: com.gaopan.reflection.Person
     	at java.lang.Class.newInstance(Class.java:427)
     	at com.gaopan.reflection.TestDemo.main(TestDemo.java:14)
    Caused by: java.lang.NoSuchMethodException: com.gaopan.reflection.Person.<init>()
     	at java.lang.Class.getConstructor0(Class.java:3082)
     	at java.lang.Class.newInstance(Class.java:412)
     	... 1 more

Class类通过反射实例化对象的时候，只能调用类中的无参构造，如果类中没有无参构造，就无法使用Class类操作，只能通过明确的构造调用执行实例化处理。

范例：通过Constructor类实例化对象

    class Person{
    	private String name;
    	private int age;
    	public Person(String name,int age){
    		this.name = name;
    		this.age = age;
    	}
    	@Override
    	public String toString() {
    		return "Person [name=" + name + ", age=" + age + "]";
    	}
    	
    }
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Person.class;
    		Constructor<?> cons = clazz.getConstructor(String.class,int.class);
    		System.out.println(cons.newInstance("张三",10));
    	}
    }

运行结果：
> Person [name=张三, age=10]

# 反射调用方法（重点） #

Class类中定义有如下两个取得类中普通方法的定义：

1、取得全部方法：public Method[] getMethods()

2、取得指定方法:public Method getMethod(String name,Class<?>...parameterTypes)

以上两个方法返回的类型是java.lang.reflect.Method类的对象，在此类中提供有一个调用的方法：

**调用：public Object invoke(Object obj,Object...args)**

范例：取得全部方法

    class Person{
    	private String name;
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}	
    }
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Person.class;
    		Method[] methods = clazz.getMethods(); //取得全部方法
    		for (int i = 0; i < methods.length; i++) {
    			System.out.println(methods[i]);
    		}
    	}
    }

运行结果：

    public java.lang.String com.gaopan.reflection.Person.getName()
    public void com.gaopan.reflection.Person.setName(java.lang.String)
    public final void java.lang.Object.wait() throws java.lang.InterruptedException
    public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
    public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
    public boolean java.lang.Object.equals(java.lang.Object)
    public java.lang.String java.lang.Object.toString()
    public native int java.lang.Object.hashCode()
    public final native java.lang.Class java.lang.Object.getClass()
    public final native void java.lang.Object.notify()
    public final native void java.lang.Object.notifyAll()


在之前编写的程序对于类中的setter和getter方法采用的都是明确的对象调用

    Person p = new Person();
    p.setName("abc");
    System.out.println(p.getName());

而现在有了反射机制处理以后，这个时候程序即使没有明确的Person类型的对象（依然需要实例化对象，只不过通过Object描述，所有的普通方法必须在有实例化对象之后才可以进行调用），就可以通过反射来完成。

范例：通过反射调用setter、getter

    class Person{
    	private String name;
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}	
    }
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		String attribute = "name";
    		String value = "反射";
    		Class<?> clazz = Class.forName("com.gaopan.reflection.Person");
    		Object obj = clazz.newInstance(); //任何情况下调用类中普通方法都必须要实例化对象
    		//取得setName这个方法的实例化对象
    		//setName是方法名称，但是这个方法名称是根据给定的属性信息拼凑得来的，同时该方法需要接收一个String类型的参数
    		Method set = clazz.getMethod("set" + initcap(attribute), String.class);
    		//通过Method类对象调用指定的方法，调用方法必须有实例化对象，同时需要传入一个参数
    		set.invoke(obj, value); //相当于:Person对象.setName(value);
    		Method get = clazz.getMethod("get" + initcap(attribute));
    		Object ret = get.invoke(obj); //相当于:Person对象.getName(value);
    		System.out.println(ret);
    	}
    	public static String initcap(String str){
    		return str.substring(0, 1).toUpperCase()+str.substring(1);
    	}
    }

此类操作的好处是：不再局限于某一类型的对象，而是可以通过Object类型进行所有类的方法调用。


# 反射调用成员 #

之前已经实现了类的构造调用，方法调用，除了这两种之外还有成员的调用。类中所有属性都需要在类对象实例化之后才会进行空间的分配，所以此时如果想要调用类中的属性，就必须保证有实例化对象，而通过反射newInstance()方法可以直接取得实例化对象（Object类型）。

在Class类里面提供有两组取得属性的操作方法：

1、取得**父类**属性

  取得类中的全部属性：public Field[] getFields()

  取得类中指定名称的属性：public Field getField(String name)

2、取得**本类**属性

  取得类中的全部属性：public Field[] getDeclaredFields()

  取得类中指定名称的属性：public Field getDeclaredField(String name)

范例：取得类中的全部属性

    class Person{
    	private String name;
    }
    class Student extends Person{
    	private String school;
    }
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("com.gaopan.reflection.Student");
    		{
    			Field[] fields = clazz.getFields();
    			for (int i = 0; i < fields.length; i++) {
    				System.out.println(fields[i]);
    			}
    		}
    		System.out.println("================================");
    		{
    			Field[] fields = clazz.getDeclaredFields();
    			for (int i = 0; i < fields.length; i++) {
    				System.out.println(fields[i]);
    			}
    		}
    	}
    }

运行结果：

    ================================
    private java.lang.String com.gaopan.reflection.Student.school

把父类Person中的属性private改为public，再运行代码结果是：

    public java.lang.String com.gaopan.reflection.Person.name
    ================================
    private java.lang.String com.gaopan.reflection.Student.school

关注属性的核心类java.lang.reflect.Field，在这个类里面有两个重要的方法

1、设置属性的内容：public void set(Object obj,Object value)

2、取得属性的内容：public Object get(Object obj)

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%8F%8D%E5%B0%84.png)

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%8F%8D%E5%B0%842.png)
在AccessibleObject类中提供有一个方法：
	动态设置封装：public void setAccessible(boolean flag)

范例：通过反射操作属性

    class Person{
    	private String name;
    }
    
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("com.gaopan.reflection.Person");
    		Object obj = clazz.newInstance();
    		Field field = clazz.getDeclaredField("name");  //操作name属性
    		field.set(obj, "张三"); //等价于对象.name="张三";
    		System.out.println(field.get(obj));
    	}
    }
运行报错：

    Exception in thread "main" java.lang.IllegalAccessException: Class com.gaopan.reflection.TestDemo can not access a member of class com.gaopan.reflection.Person with modifiers "private"
    	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
    	at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(AccessibleObject.java:296)
    	at java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:288)
    	at java.lang.reflect.Field.set(Field.java:761)
    	at com.gaopan.reflection.TestDemo.main(TestDemo.java:17)

加一句话field.setAccessible(true);以后运行正常

    class Person{
    	private String name;
    }
    
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("com.gaopan.reflection.Person");
    		Object obj = clazz.newInstance();
    		Field field = clazz.getDeclaredField("name");  //操作name属性
    		field.setAccessible(true); //取消private封装了
    		field.set(obj, "张三"); //等价于对象.name="张三";
    		System.out.println(field.get(obj));
    	}
    }
上述属性操作在实际开发中不会使用，而是要使用getter，setter方法，这样给用户操作的机会。

在Field类里面有一个特别有用的方法

取得属性类型：public Class<?> getType()

    class Person{
    	private String name;
    }
    
    public class TestDemo {
    	public static void main(String[] args) throws Exception{
    		Class<?> clazz = Class.forName("com.gaopan.reflection.Person");
    		Object obj = clazz.newInstance();
    		Field field = clazz.getDeclaredField("name");  //操作name属性
    		System.out.println(field.getType().getName()); //包.类
    		System.out.println(field.getType().getSimpleName()); //类
    	}
    }
运行结果：

    java.lang.String
    String

将Field取得属性与Method类中的invoke()结合在一起，就可以编写非常灵活的程序了。



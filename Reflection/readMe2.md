# 反射机制 #

先了解一下程序的运行过程：

1、编译生成的xxx.class文件被ClassLoader（类加载器）加载到内存中

2、运行环境找到main方法开始执行

3、由于java的动态加载机制，运行过程中有很多其他的.class被加载到内存

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E7%A8%8B%E5%BA%8F%E8%BF%90%E8%A1%8C%E8%BF%87%E7%A8%8B.png)

参考文章：

[java程序运行机制及运行过程](https://www.cnblogs.com/xiaoQcn/archive/2010/01/26/1656394.html)

[Java程序编译和运行的过程](https://www.cnblogs.com/qiumingcheng/p/5398610.html)

## 1、ClassLoader的类加载机制 ##

1、并非一次性加载

2、需要时加载（运行期间动态加载）

    观察方法：在eclipse代码中，右键选择“Run As”，选择“Open Run Dialog”打开对话框，在Arguments的VM arguments中填写“-verbose:class”

通过观察可以看出，static语句块在加载后执行一次，dynamic（动态）语句块每次new新的对象都会执行

JDK内置的ClassLoader很多：

![](https://raw.githubusercontent.com/pgao0823/Picture/master/ClassLoader.png)

##2、反射机制 ##

在程序运行期间，动态的加载类和对象

    class Person {
    	private String name;
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    		System.out.println(this.name);
    	}
    }
    
    public class TestDemo {
    	public static void main(String[] args) throws Exception {
    		Class<?> c = Class.forName("com.gaopan.reflection.Person"); //取得Class类
    		Object obj = c.newInstance(); //实例化对象
    		Method[] methods = c.getMethods(); //取得类中的所有方法
    		for (Method m : methods) {
    			if(m.getName().equals("setName")){
    				m.invoke(obj, "张三"); //调用setName方法，并传入参数“张三”
    			}
    			if(m.getName().equals("getName")){
    				Class<?> returnType = m.getReturnType(); //取得getName方法的返回值类型
    				System.out.println(returnType.getName());
    			}
    		}
    	}
    }


# 工厂模式 #

先看一下单例模式：

    class Person {
    	private Person p;
    	public Person getInstance(){
    		if(p == null){
    			p = new Person();
    		}
    		return p;
    	}
    }

getInstance()这个方法其实就像一个工厂，这个工厂就是用来产生Person对象的，我们不去new一个对象，而是通过一个方法去创建对象，在通过这个方法去new对象的时候，可以添加一些限制条件，这种方法称之为工厂方法。
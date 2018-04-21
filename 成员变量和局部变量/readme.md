# 成员变量和成员方法 #

成员变量是类中的变量，用来描述类对象的属性。

成员方法是类中的方法，用来描述类对象的行为。

# 局部变量 #

局部变量定义在成员方法内，局部变量在方法被执行时创建，在方法执行结束时被销毁，局部变量在使用时**必须被初始化**，否则会出现编译错误。

正因为局部变量有次特性，所以在互不嵌套的区域可以定义同名、同类型的局部变量。如下例中的局部变量i：

    package com.gaopan.test;
    
    public class Test {
    	public static void main(String[] args) {
            T t = new T();
            t.doString("JR");
    	}
    }
    
    class T {
    	public void doString(String name) {
    		int id = 0;
    		for (int i = 0; i < 5; i++) {
    			System.out.println(name+String.valueOf(i));
    		}
    		for (int i = 0; i < 3; i++) {
    			System.out.println(i);
    		}
    	}
    }
输出：

    JR0
    JR1
    JR2
    JR3
    JR4
    0
    1
    2
但是在互相嵌套的区域，就不行了，因为嵌套了的话就相当于一个方法还没有运行完，此时其成员变量还没有销毁，再定义相同名称、相同类型的成员变量编译会报错，如下例：

    package com.gaopan.test;
    
    public class Test {
    	public static void main(String[] args) {
            T t = new T();
            t.doString("JR");
    	}
    }
    
    class T {
    	public void doString(String name) {
    		int id = 0;
    		for (int i = 0; i < 5; i++) {
    			System.out.println(name+String.valueOf(i));
    		}
    		for (int i = 0; i < 3; i++) {
    			int id = 2;
    			System.out.println(i);
    		}
    	}
    }
编译报错：

    Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
    	Duplicate local variable id
    
    	at com.gaopan.test.T.doString(Test.java:17)
    	at com.gaopan.test.Test.main(Test.java:6)
duplicate  [ˈduplɪkeɪt] v.复制； 复印； 重复； 
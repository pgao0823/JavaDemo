# overwrite/override重写 #

在子类中可以根据需要对从父类中继承来的方法进行重写；

重写方法必须和被重写方法具有相同方法名称、参数列表和返回类型；

重写方法不能使用比被重写方法更严格的访问权限。

    package com.gaopan.test;
    
    public class Test {
    	public static void main(String[] args) {
    		Dog d = new Dog();
    		System.out.println("d = " + d);		
    	}
    }
    
    class Dog {
    	
    }

输出：

    d = com.gaopan.test.Dog@3c679bde

为什么输出的d会是com.gaopan.test.Dog@3c679bde呢？看API中Object类的toString()方法：

    toString
    public String toString()
    Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method. 
    The toString method for class Object returns a string consisting of the name of the class of which the object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of: 
     getClass().getName() + '@' + Integer.toHexString(hashCode())
     
    Returns:
    a string representation of the object.
    
此时默认继承了Object类中的toString方法，相当于System.out.println("d = " + d.toString());如果觉得带一个hashCode码不是想要得到的输出，也就是说**当对父类的方法不满意时，可以重写父类的方法来实现自己的目的：**

    package com.gaopan.test;
    
    public class Test {
    	public static void main(String[] args) {
    		Dog d = new Dog();
    		System.out.println("d = " + d);		
    	}
    }
    
    class Dog {
    	public String toString() {
    		return "I'm a cool dog !";
    	}
    }

输出：
    
    d = I'm a cool dog !
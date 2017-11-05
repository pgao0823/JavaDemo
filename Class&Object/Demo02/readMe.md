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
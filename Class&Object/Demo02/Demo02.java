class Person {    //定义一个类，类名称的首字母要大写
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

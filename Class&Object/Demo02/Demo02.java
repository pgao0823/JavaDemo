class Person {    //����һ���࣬�����Ƶ�����ĸҪ��д
	String name;
	int age;
    public void info(){
		System.out.println("name: " + name + ", age: " + age);
	}
}

public class Demo02 {
	public static void main(String[] args) {
		Person per1 = new Person();  //������ʵ��������
		per1.name = "С����";  //���ö��������
		per1.age = 30;  //���ö��������
		Person per2 = per1;  //������ͬ�����ͽ���  ������int num = 10;   int x = num;
		per2.name = "��ʣ";
		per1.info();  //���ö����еķ���
	}
}

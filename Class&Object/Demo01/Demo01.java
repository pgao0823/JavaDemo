class Person {    //����һ���࣬�����Ƶ�����ĸҪ��д
	String name;
	int age;
    public void info(){
		System.out.println("name: " + name + ", age: " + age);
	}
}

public class Demo01 {
	public static void main(String[] args) {
		Person per = null;  //��������
		//per = new Person();  //ʵ��������
		per.name = "����";  //���ö��������
		per.age = 18;  //���ö��������
		per.info();  //���ö����еķ���
	}
}
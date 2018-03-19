# Java 中方法的重载 #
## 问： 什么是方法的重载呢？ ##
答： 如果同一个类中包含了两个或两个以上方法名相同、方法参数的个数、顺序或类型不同的方法，则称为方法的重载，也可称该方法被重载了。如下所示 4 个方法名称都为 show ，但方法的参数有所不同，因此都属于方法的重载：
![](https://raw.githubusercontent.com/pgao0823/Picture/master/重载1.png)

## 问： 如何区分调用的是哪个重载方法呢？ ##
答：当调用被重载的方法时， Java 会根据参数的个数和类型来判断应该调用哪个重载方法，参数完全匹配的方法将被执行。如：
![](https://raw.githubusercontent.com/pgao0823/Picture/master/重载2.png)

运行结果：

![](https://raw.githubusercontent.com/pgao0823/Picture/master/重载3.png)

判断方法重载的依据：

    1、 必须是在同一个类中
    2、 方法名相同
    3、 方法参数的个数、顺序或类型不同
    4、 与方法的修饰符或返回值没有关系


# java异常处理 #


## 1、异常的分类 ##

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%82%E5%B8%B8%E6%8D%95%E8%8E%B71.png)

从图中可以看出所有异常类型都是内置类**Throwable**的子类，因而Throwable在异常类的层次结构的顶层。

接下来Throwable分成了两个不同的分支，一个分支是Error，它表示不希望被程序捕获或者是程序无法处理的错误。另一个分支是Exception，它表示用户程序可能捕捉的异常情况或者说是程序可以处理的异常。其中异常类Exception又分为运行时异常(RuntimeException)和非运行时异常。

**Error**：Error类对象由 Java 虚拟机生成并抛出，大多数错误与代码编写者所执行的操作无关。例如，Java虚拟机运行错误（Virtual MachineError），当JVM不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止；还有发生在虚拟机试图执行应用时，如类定义错误（NoClassDefFoundError）、链接错误（LinkageError）。这些错误是不可查的，因为它们在应用程序的控制和处理能力之 外，而且绝大多数是程序运行时不允许出现的状况。对于设计合理的应用程序来说，即使确实发生了错误，本质上也不应该试图去处理它所引起的异常状况。在Java中，错误通常是使用Error的子类描述。

**Exception**：

在Exception分支中有一个重要的子类RuntimeException（运行时异常），该类型的异常自动为你所编写的程序定义ArrayIndexOutOfBoundsException（数组下标越界）、NullPointerException（空指针异常）、ArithmeticException（算术异常）、MissingResourceException（丢失资源）、ClassNotFoundException（找不到类）等异常，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生；

而RuntimeException**之外**的异常我们统称为非运行时异常，类型上属于Exception类及其子类，从程序语法角度讲是**必须进行处理的异常**，如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。

## 2、Java 异常的处理机制 ##

Java的异常处理本质上是**抛出异常**和**捕获异常**。

抛出异常

    if(stu == null){
    throw new NullPointerException();
    }

捕获异常

    try{
    //code that might generate exceptions
    }catch(Exception e){
    //the code of handling exception1
    }catch(Exception e){
    //the code of handling exception2
    }

## 3、异常捕获处理流程 ##

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%82%E5%B8%B8%E6%8D%95%E8%8E%B72.png)

**try语句**

try{...}语句的代码段就是一次捕获并处理异常的范围，在执行过程中，该段代码可能会产生并抛出一种或几种类型的异常对象，它后面的catch语句要分别对这些异常做出相应的处理，如果没有异常产生，所有的catch代码段都略过不执行。

**catch语句**

在catch语句块中是对异常进行处理的代码，每个try语句块可以伴随一个或者多个catch语句，用于处理可能产生的不同类型的异常对象，在catch中声明的异常对象（catch(SomeException e)）封装了异常事件发生的信息。

例如：

getMessage()方法，用来得到有关异常事件的信息；

printStackTrace()方法，用来跟踪异常事件发生时执行堆栈的内容；


**finally语句**

finally语句为异常处理提供了一个统一的出口，使得在控制流程转到程序的其他部分以前，能够对程序的状态作统一的管理，无论try所指定的程序块中是否抛出异常，finally所指定的代码都要被执行，通常在finally语句中可以进行资源的清除工作，如：关闭打开的文件，删除临时文件...

## 自定义异常 ##

使用自定义异常一般有如下步骤：

1、通过继承java.lang.Exception类声明自己的异常类

2、在方法适当的位置生成自定义异常的实例，并用throw语句抛出异常

3、在方法的声明部分用throws语句声明该方法可能抛出的异常


![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%82%E5%B8%B8%E6%8D%95%E8%8E%B73.png)


## 总结 ##

![](https://raw.githubusercontent.com/pgao0823/Picture/master/%E5%BC%82%E5%B8%B8%E6%8D%95%E8%8E%B74.png)




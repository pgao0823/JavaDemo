# IO #

![](https://raw.githubusercontent.com/pgao0823/Picture/master/IO%E6%B5%812.png)

输入输出流的分类：

java.io包中定义了多个流类型（类或抽象类）来实现输入/输出功能，可以从不同的角度对其尽心分类：

    按数据流的方向不同可以分为输入流和输出流；
    按处理数据单位不同可以分为字节流和字符流；
    按功能不同可以分为字节流和处理流

![](https://raw.githubusercontent.com/pgao0823/Picture/master/IO%E6%B5%811.png)

输入输出流是站在程序的角度说的。

### InputStream ###

继承自InputSteam的流都是用于向程序中输入数据（从别的地方读取数据），且数据的单位为字节（8bit）。

InputStream的基本方法：

    int read() throws IOException  //读取一个字节并以整数的形式返回（0~255），如果返回-1表示已读到输入流的末尾；
    int read(byte[] buffer) throws IOException  //读取一系列字节并存储到一个数组buffer，返回实际读取的字节数，如果读取前已到输入流的末尾返回-1；
    int read(byte[] buffer, int offset, int length) throws IOException  //从offset位置开始读取，读取length个字节，并存储到一个字节数组buffer，从length位置开始，返回实际读取的字节数，如果读取前已到输入流的末尾返回-1；
    void close() throws IOException  //关闭流，释放内存资源
    long skip(long n) throws IOException  //跳过n个字节不读，返回实际跳过的字节数；


### OutputStream ###

继承自OutputSteam的流是用于程序中输出数据（往别的地方写数据），且数据的单位为字节（8bit）

	void write(int b) throws IOException  //向输出流中写入一个字节数据，该字节数据为参数b的低8位；
    void write(byte[] b) throws IOException  //将一个字节类型的数组中的数据写入输出流；
    void write(byte[] b, int off, int len) throws IOException  //将一个字节类型的数组中的从指定位置（off）开始的len个字节写入到流输出；
    void close() throws IOException  //关闭流，释放内存资源
    void flush() throws IOException  //将输出流中缓冲的数据全部写出到目的地；

### Reader ###

继承自Reader的流都是用于向程序中输入数据，且数据的单位为字符（16bit）

    int read() throws IOException  //读取一个字符并以整数的形式返回，如果返回-1表示已读到输入流的末尾；
    int read(char[] cbuf) throws IOException  //读取一系列字节并存储到一个数组buffer，返回实际读取的字节数，如果读取前已到输入流的末尾返回-1；
    int read(char[] cbuf, int offset, int length) throws IOException  //从offset位置开始读取，读取length个字节，并存储到一个字节数组buffer，返回实际读取的字节数，如果读取前已到输入流的末尾返回-1；
    void close() throws IOException  //关闭流，释放内存资源
    long skip(long n) throws IOException  //跳过n个字节不读，返回实际跳过的字节数；


### Writer ###

继承自Writer的流都是用于程序中输出数据，且数据的单位为字符（16bit）

	void write(int c) throws IOException  //向输出流中写入一个字符数据，该字符数据为参数c的低16位；
    void write(char[] cbuf) throws IOException  //将一个字符类型的数组中的数据写入输出流；
    void write(char[] cbuf, int offset, int length) throws IOException  //将一个字符类型的数组中的从指定位置（offset）开始的length个字符写入到流输出；
	void write(String str) throws IOException  //将一个字符串中的字符写入到输出流；
	void write(String str，int offset, int length) throws IOException  //将一个字符串从offset开始的length个字符写入到输出流；
    void close() throws IOException  //关闭流，释放内存资源
    void flush() throws IOException  //将输出流中缓冲的数据全部写出到目的地；

以上最基本的方法简单的认识了一下，下面通过具体的流来看方法怎么使用：

## 节点流 ##

用形象思维去理解节点流，就是说，一根管道直接怼到数据源上，比如说可以怼到文件上，内存里面的数组上，内存里面的String上，也可以怼到管道(Pipe)上。

![](https://raw.githubusercontent.com/pgao0823/Picture/master/IO-%E8%8A%82%E7%82%B9%E6%B5%81.png)


##### 范例：FileInputStream ######

    public class TestFileInputStream {
    	public static void main(String[] args) {
    		FileInputStream in = null;
    		int b = 0;
    		try {
    			in = new FileInputStream("D:\\EclipseWorkspace\\TestIO\\src\\com\\gaopan\\testio\\TestFileInputStream.java");
    		} catch (FileNotFoundException e) {
    			System.out.println("找不到指定文件");
    			System.exit(-1);
    		}
    		try {
    			long num = 0;
    			while ((b = in.read()) != -1) { //返回值不等于-1就说明文件还没有读取完
    				System.out.print((char)b); // 强制转换成char
    				num ++;
    			}
    			in.close(); //关闭流
    			System.out.println();
    			System.out.println("读取了"+num+"个字节");
    		} catch (IOException e) {
    			System.out.println("文件读取错误");
    			System.exit(-1);
    		}
    	}
    }

运行结果是：

    package com.gaopan.testio;
    
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    
    public class TestFileInputStream {
    	public static void main(String[] args) {
    		FileInputStream in = null;
    		int b = 0;
    		try {
    			in = new FileInputStream("D:\\EclipseWorkspace\\TestIO\\src\\com\\gaopan\\testio\\TestFileInputStream.java");
    		} catch (FileNotFoundException e) {
    			System.out.println("?????????¨????");
    			System.exit(-1);
    		}
    		try {
    			long num = 0;
    			while ((b = in.read()) != -1) { //·???????????-1?????÷???????????????ê
    				System.out.print((char)b); // ????×?????char
    				num ++;
    			}
    			in.close(); //??±??÷
    			System.out.println();
    			System.out.println("??????"+num+"??×???");
    		} catch (IOException e) {
    			System.out.println("?????????í?ó");
    			System.exit(-1);
    		}
    	}
    }
    
    读取了857个字节

运行的结果中的中文字之所以显示乱码，是因为InputStream是一个字节一个字节的去读取，而一个中文字是占一个字符等于两个字节，也就是这里读取了半个字就显示，当然显示为乱码了。

##### 范例：FileOutputStream #####

    public class TestFileOutputStream {
    	public static void main(String[] args) {
    		FileInputStream in = null;
    		FileOutputStream out = null;
    		int b = 0;
    		try {
    			in = new FileInputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestIn.txt"); //读取TextIn.txt文件中的字节数据
    			out = new FileOutputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestOut.txt"); //将字节数据写入TextOut.txt文件中，如果文件不存在，则先创建文件再写入字节数据
    			while((b = in.read())!= -1){
    				out.write(b);
    			}
    			in.close();
    			out.close();
    		} catch (FileNotFoundException e1) {
    			System.out.println("找不到指定文件！");
    			System.exit(-1);
    		} catch (IOException e) {
    			System.out.println("文件复制错误！");
    		}
    		System.out.println("文件复制成功！");
    	}
    }

运行结果：

    文件复制成功！

查看D:\EclipseWorkspace\TestIO\src\com\gaopan\testio目录，确实已经有了TestOut.txt这个文件，而且TestOut.txt文件中的内容和TestIn.txt中的一样，即完成了复制。


##### 范例：FileReader #####

    public class TestFileReader {
    	public static void main(String[] args) {
    		FileReader fr = null;
    		int c = 0;
    		try {
    			fr = new FileReader("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestFileReader.java");
    			while((c = fr.read())!= -1){
    				System.out.print((char)c);
    			}
    			fr.close();
    		} catch (FileNotFoundException e) {
    			System.out.println("找不到指定文件！");
    		} catch (IOException e) {
    			System.out.println("文件读取失败！");
    		}
    	}
    }

运行结果：可以看到，中文字符能够正常显示了，是因为Reader是以一个字符一个字符的去读取数据的，刚好一个中文字就是一个字符，所以能否正常显示

    package com.gaopan.testio;
    
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.IOException;
    
    public class TestFileReader {
    	public static void main(String[] args) {
    		FileReader fr = null;
    		int c = 0;
    		try {
    			fr = new FileReader("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestFileReader.java");
    			while((c = fr.read())!= -1){
    				System.out.print((char)c);
    			}
    			fr.close();
    		} catch (FileNotFoundException e) {
    			System.out.println("找不到指定文件！");
    		} catch (IOException e) {
    			System.out.println("文件读取失败！");
    		}
    	}
    
    }



##### 范例：FileWriter #####

    public class TestFileWriter {
    	public static void main(String[] args) {
    		FileWriter fw = null;
    		try {
    			fw = new FileWriter("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestWriter.txt");
    			for(int i=0;i<2000;i++){
    				fw.write(i);
    			}
    			fw.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("文件写入错误！");
    			System.exit(-1);
    		}
    	}
    }
运行以后，在D:/EclipseWorkspace/TestIO/src/com/gaopan/testio目录下看到生成了TestWriter.txt这个文件，里面的内容是unicode字符的一部分

## 处理流 ##

形象思维理解处理流，就是报道别的流外面的流

![](https://raw.githubusercontent.com/pgao0823/Picture/master/IO-%E5%A4%84%E7%90%86%E6%B5%81.png)

### 缓冲流 ###

缓冲流要“套接”在相应的节点流之上，对读写数据提供了缓冲功能，提高了读写的效率，同事增加了一些新的方法。

	常用的构造方法：
    BufferedReader(Reader in)
    BufferedReader(Reader in,int size) //size是自定义缓存区的大小（缓存区可以显著的减少IO的读写次数，保护硬盘）
    BufferedWriter(Writer out)
    BufferedWriter(Writer out,int size)
    BufferedInputStream(InputStream in)
    BufferedInputStream(InputStream in,int size)
    BufferedOutputStream(OutputStream out)
    BufferedOutputStream(OutputStream out,int size)

其中

    1. 缓冲输入流支持其父类的mark和reset方法；
    2. BufferedReader提供了readLine方法用于读取一行字符串（以\r或\n分隔）；
    3. BufferedWriter提供了newLine用于写入一个行分隔符；
    4. 对于输出的缓冲流，写出的数据会先在内存中缓存，使用flush方法将会使内存中的数据立刻写出；
    
##### 范例：TestBufferedInputStream #####

    public class TestBufferedInputStream {
    	public static void main(String[] args) {
    		try {
    			FileInputStream fis = new FileInputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestIn1.txt");
    			BufferedInputStream bis = new BufferedInputStream(fis);//带缓冲区的功能
    			System.out.println((char)bis.read()); //从缓冲区里面读数据
    			System.out.println(bis.read());
    			int c =0;
    			bis.mark(20); //标记位，从20这个位置开始往外读
    			for(int i=0;i<10 && (c = bis.read()) != -1;i++){
    				System.out.print((char)c);
    			}
    			System.out.println();
    			bis.reset(); //回到标记位，再读数据
    			for(int i=0;i<10 && (c = bis.read()) != -1;i++){
    				System.out.print(c);
    			}
    			bis.close();
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

运行结果：

    a
    98
    cdefghijkl
    99100101102103104105106107108

默认读取的是数据对应的ASCALL码的值，强制转换成char类型之后，变成对应的char值

##### 范例：TestBufferedOutputStream #####

    public class TestBufferedOutputStream {
    	public static void main(String[] args) {
    		try {
    			FileInputStream fis = new FileInputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestIn1.txt"); //以字节为单位读取文件TextIn.txt中的数据
    			FileOutputStream fos = new FileOutputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestOut1.txt"); //以字节为单位把数据写入到文件TextOut.txt中
    			BufferedOutputStream bos = new BufferedOutputStream(fos);
    			int b = 0;
    			while((b = fis.read()) != -1){
    				bos.write(b);
    			}
    			fis.close();
    			bos.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

##### 范例：TestBufferedReader&Writer #####

    public class TestBufferedReaderWriter {
    	public static void main(String[] args) {
    		try {
    			BufferedWriter bw = new BufferedWriter(new FileWriter("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestWriter1.txt"));
    			BufferedReader br = new BufferedReader(new FileReader("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestWriter1.txt"));
    			String s = null ;
    			for (int i = 0; i < 20; i++) {
    				s = String.valueOf(Math.random()); //产生一个随机数并把它转换成字符串
    				bw.write(s); //把字符串从缓冲区写到文件中
    				bw.newLine(); //往文件中写一个换行符
    			}
    			bw.flush(); //把缓存中的剩下的数据全部写到文件中
    			while ((s=br.readLine())!=null) { //一次读取一行，一行一行的读取
    				System.out.println(s);
    			}
    			bw.close();
    			br.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
运行结果： 20个随机数

    0.8267406312728267
    0.4241767185619467
    0.854223933033159
    0.28685927638202635
    0.16525459685172705
    0.6455325594470135
    0.939492548719613
    0.38752116266427017
    0.3952182254348918
    0.6674528776459768
    0.8819500389268712
    0.26199838787599417
    0.8382554012972729
    0.6345182145525571
    0.3704940710450326
    0.2527103486436073
    0.29778979526229354
    0.3734841572856836
    0.07299844567011537
    0.8276940104702191

### 转换流 ###

##### 范例：TestOutputStreamWriter #####
OutputStreamWriter：将OutputStream转换成Writer

    public class TestOutputStreamWriter {
    	public static void main(String[] args) {
    		try {
    			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestOut2.txt"));
    			osw.write("this is a String."); // 直接写字符串到文件
    			osw.close();
    
    			osw = new OutputStreamWriter(new FileOutputStream("D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestOut2.txt",true), "ISO8859_1"); //true表示追加，false表示覆盖；ISO8859_1编码表示所有西欧语言
    			osw.write("this is a String ^_^");
    			osw.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
运行以后，在D:/EclipseWorkspace/TestIO/src/com/gaopan/testio/TestOut2.txt中成功写入“this is a String.this is a String ^_^”

##### 范例：InputStreamReader #####
InputStreamReader：将InputStream转换成Reader

    public class TestInputStreamReader {
    	public static void main(String[] args) {
    		InputStreamReader isr = new InputStreamReader(System.in); //键盘输入
    		BufferedReader br = new BufferedReader(isr);
    		String s = null;
    		try {
    			s = br.readLine();//方便调用readLine一行一行的读取
    			while(s!=null){
    				if(s.equalsIgnoreCase("exit"))break;
    				System.out.println(s.toUpperCase()); //转换成大写
    				s = br.readLine();
    			}
    			br.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

### 数据流 ###

DataInputStream和DataOutputStream分别继承自InputStream和OutputStream，它属于处理流，需要分别“套接”在InputStream和OutputStream类型的节点流上

DataInputStream和DataOutputStream提供了可以存取与机器无关的Java原始类型数据（如：int,double等）的方法

DataInputStream和DataOutputStream的构造方法为：

    DataInputStream(InputStream in)
    DataOutputStream(OutputStream out)

##### 范例：TestDataStream #####

    public class TestDataStream {
    	public static void main(String[] args) {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节数组
    		DataOutputStream dos = new DataOutputStream(baos);
    		try {
    			dos.writeDouble(Math.random()); //写入随机浮点数（8字节）
    			dos.writeBoolean(true); //写入布尔值（1字节）
    			
    			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    			System.out.println(bais.available()); //字节数组输入流中的字节数
    			DataInputStream dis = new DataInputStream(bais);
    			System.out.println(dis.readDouble());
    			System.out.println(dis.readBoolean());
    			dos.close();
    			dis.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }

运行结果为：

    9
    0.259024365303006
    true

### Print流 ###
### Object流 ###

![](https://raw.githubusercontent.com/pgao0823/Picture/master/IO%E6%B5%813.png)



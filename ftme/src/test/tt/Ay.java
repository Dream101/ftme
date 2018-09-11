package test.tt;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Ay {

	/**
	 * @param args
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		/*
		 * // TODO Auto-generated method stub JavaCompiler complier =
		 * ToolProvider.getSystemJavaCompiler(); StandardJavaFileManager sjf =
		 * complier.getStandardFileManager(null, null, null); Iterable it =
		 * sjf.getJavaFileObjects("D:\\HelloWorld.java"); CompilationTask task =
		 * complier.getTask(null, sjf, null, null, null, it); task.call();
		 * //调用创建 sjf.close();
		 * 
		 * URL urls[] = new URL[]{ new URL("file:/D:/")}; //储存文件目录的地址
		 * URLClassLoader uLoad = new URLClassLoader(urls); //classloader从哪个目录找？
		 * Class c = uLoad.loadClass("HelloWorld"); //找哪个class文件 注意不带后缀名
		 * c.newInstance(); //创建一个实例
		 */
		// 获得系统的java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 编译文件，编译成功返回 0 否则 返回 1
		int flag = compiler.run(null, null, null, "D:\\HelloWorld.java");
		System.out.println(flag == 0 ? "编译成功" : "编译失败");
		// 指定class路径，默认和源代码路径一致，加载class
		URLClassLoader classLoader = new URLClassLoader(new URL[] { new URL( "file:/d:/") });
//		Class<?> printer = (Class<?>) classLoader.loadClass("HelloWorld").newInstance();
//		printer.getMethods();

		
		Class c = classLoader.loadClass("HelloWorld");
		Object o = c.newInstance();
		c.getResource("HelloWorld");
		System.out.println(o.toString());
	}
}

/*
Author: Rohit Kulkarni
Description: A java program that compiles and runs another java program.
*/

/*package rohit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


public class Compile_and_Run 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
		
		
		File javaFile = new File("/home/rohit/workspace/Compile_Runtime/src/Compile_This_Code.java");
		Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
		String[] options = new String[]{"-d", "/home/rohit/workspace/Compile_Runtime/bin"}; 
		
		
		jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call(); 
		sjfm.close();
		System.out.println("Class has been successfully compiled");
		
		
		URL[] urls = new URL[]{ new URL("file:///home/rohit/workspace/Compile_Runtime/bin/") }; 
		URLClassLoader ucl = new URLClassLoader(urls);
		Class clazz = ucl.loadClass("rohit.Compile_This_Code");
		System.out.println("Class has been successfully loaded");
		
		Method method = clazz.getDeclaredMethod("call_this_method", null);
		
		Object object = clazz.newInstance();
		method.invoke(object, null);

	}

}*/

/*******************************************************BETTER CODE*******************************************
*/

import java.io.*;

public class test 
{
	private static void runProcess(String command) throws Exception 
	{
	    Process pro = Runtime.getRuntime().exec(command);
	    printLines(command + " Output:", pro.getInputStream());
	    printLines(command + " Error:", pro.getErrorStream());
	    pro.waitFor();
	    //System.out.println(command + " exitValue() " + pro.exitValue());
	}
	
	private static void printLines(String name, InputStream ins) throws Exception 
	{
	    String line = null;
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(ins));
	    while ((line = in.readLine()) != null) 
	    {
	        System.out.println(name + " " + line);
	    }
	}
	
	public static void main(String[] args)
	{
		try 
		{
	      runProcess("javac hw_Complex.java");
	      runProcess("java hw_Complex");
		} 
	catch (Exception e) 
		{
	      e.printStackTrace();
		}
	}
	
}

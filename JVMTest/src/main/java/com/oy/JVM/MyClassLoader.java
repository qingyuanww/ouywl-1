package com.oy.JVM;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-14 12:08
 */

public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 通过文件名读取磁盘，返回二进制流
     *
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    /**
     * 加载
     *
     * @param NAME
     * @return
     * @throws ClassNotFoundException
     */
    protected Class<?> findClass(String NAME) throws ClassNotFoundException {
        try {
            byte[] DATA = loadByte(NAME);
            //把获取到的二进制流加载到内存中（方法区）
            return defineClass(NAME, DATA, 0, DATA.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }



    public static void main(String[] args) throws Exception {
        MyClassLoader myCladdLoader = new MyClassLoader("D:/java/testJVM");

        Class<?> aClass = myCladdLoader.loadClass("com.oy.JVM.User1");
        Object instance = aClass.newInstance();
        Method methods = aClass.getDeclaredMethod("sout", null);//方法名和参数列表
        methods.invoke(instance, null);//执行
        //java.class.path中有，就会用appclassLoader加载
        System.out.println(instance.getClass().getClassLoader());

    }
}

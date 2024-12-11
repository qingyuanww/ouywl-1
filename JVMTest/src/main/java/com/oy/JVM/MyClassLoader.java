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
            /**
             *             这里有沙箱安全机制。ClassLoader.preDefineClass 禁止加载类
             *             if ((name != null) && name.startsWith("java.")) {
             *                 throw new SecurityException
             *                         ("Prohibited package name: " +
             *                                 name.substring(0, name.lastIndexOf('.')));
             *             }
             */
            return defineClass(NAME, DATA, 0, DATA.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    /**
     * 打破双亲委派机制,通过重写loadeClass方法，因为机制实现是在这个方法里面，重写它即可
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> loadClass(String name)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
//                try {
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    if(name.startsWith("com.oy.JVM.User")){
                        //这里是走自己的加载逻辑
                        c = findClass(name);
                    }else {
                        //用父类的loadClass方法。走双亲机制，最后由引导类加载器加载Object。记得要赋值哦
                        c=this.getParent().loadClass(name);
                    }
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }

            return c;
        }
    }


    public static void main(String[] args) throws Exception {
        MyClassLoader myCladdLoader = new MyClassLoader("D:/java/testJVM");

        Class<?> aClass = myCladdLoader.loadClass("com.oy.JVM.User");
//        Class<?> aClass = myCladdLoader.loadClass("com.oy.JVM.User1");
        Object instance = aClass.newInstance();
        Method methods = aClass.getDeclaredMethod("sout", null);//方法名和参数列表
        methods.invoke(instance, null);//执行
        //java.class.path中有，就会用appclassLoader加载
        System.out.println(instance.getClass().getClassLoader());

    }
}

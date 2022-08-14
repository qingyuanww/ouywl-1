package com.oy;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @description: 引导类加载器(bootstrapClassLoader):加载jre lib目录下的核心类库
 * 扩展类加载器(extLoader):加载 jre lib 下 ext扩展目录下的jar包
 * 应用程序类加载器(appLoader):加载ClassPath路径下的类包，主要加载你自己写的那些类
 * 自己的类加载器(myLoader):负责加载用户自定义路径下的类包
 * @author: oywl
 * @time: 2022-8-11 15:31
 */

public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(TestClassLoader.class.getClassLoader().getClass().getName());
        System.out.println();


        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);
        System.out.println();
        System.out.println("bootstrapLoader加载以下文件(核心类库)："); //target目录下的
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }
        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        for (String s : System.getProperty("java.class.path").split(";")) {
            System.out.println(s);
        }
        //问题：为什么appclassloader也加载 lib和lib\ext下的核心类库
        //原因：虽然它打印了，但是并没有加载（双亲委派模式）
    }
}

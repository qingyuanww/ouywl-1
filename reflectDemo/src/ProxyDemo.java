import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-15 17:42
 */

public class ProxyDemo {
    public static void main(String[] args) throws Exception {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        MyInvocationHandler handler = new MyInvocationHandler();
        Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
        Foo foo = (Foo) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
        foo.foo();
        foo.foo2();
    }
    private static class MyInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            return null;
        }
    }

    private interface Foo{
        void foo();
        void foo2();
    }
}

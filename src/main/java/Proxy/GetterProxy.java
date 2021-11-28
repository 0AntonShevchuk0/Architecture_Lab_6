package Proxy;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GetterProxy implements InvocationHandler {
    private Object ref;

    public GetterProxy(Object ref) {
        this.ref = ref;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Getter.class)) {
            return method.invoke(ref, args);
        }
        throw new IllegalAccessException("Not Allowed");
    }

    public static Object newProxyInstance(Object obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new GetterProxy(obj)
        );
    }
}

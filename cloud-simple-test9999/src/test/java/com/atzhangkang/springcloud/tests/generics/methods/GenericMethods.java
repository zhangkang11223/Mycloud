package com.atzhangkang.springcloud.tests.generics.methods;

/**
 * 如果方法是 static 的，则无法访问该类的泛型类型参数，
 * 因此，如果使用了泛型类型参数，则它必须是泛型方法。
 *
 * 对于泛型类，必须在实例化该类时指定类型参数。
 * 使用泛型方法时，通常不需要指定参数类型，因为编译器会找出这些类型,这称为: 类型参数推断
 *
 *
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}

package com.atzhangkang.springcloud.tests.generics.tuple;

/**
 * 导入Tuple的静态方法： tuple
 * 可以直接调用tuple();
 */
import static com.atzhangkang.springcloud.tests.generics.tuple.Tuple.tuple;

public class TupleTest {
    static Tuple2<String, Integer> f() {
        return tuple("hi", 47);
    }

    static Tuple2 f2() {
        return tuple("hi", 47);
    }

    static Tuple3<Amphibian, String, Integer> g() {
        return tuple(new Amphibian(), "hi", 47);
    }

    static Tuple4<Vehicle, Amphibian, String, Integer> h() {
        return tuple( new Vehicle(), new Amphibian(), "hi", 47);
    }

    static Tuple5<Vehicle, Amphibian, String, Integer, Double> k() {
        return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        Tuple2<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}

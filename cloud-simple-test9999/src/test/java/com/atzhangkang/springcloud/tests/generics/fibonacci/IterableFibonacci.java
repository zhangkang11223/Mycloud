package com.atzhangkang.springcloud.tests.generics.fibonacci;

import java.util.Iterator;

/**
 *
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
    // 定义这个Iterable的长度
    private int n;
    public IterableFibonacci(int count) { n = count; }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() { return n > 0; }
            @Override
            public Integer next() {
                n--;
                return IterableFibonacci.this.get();
            }
            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        /*for(int i : new IterableFibonacci(18))
            System.out.print(i + " ");*/

        Iterator<Integer> iterator = new IterableFibonacci(18).iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}

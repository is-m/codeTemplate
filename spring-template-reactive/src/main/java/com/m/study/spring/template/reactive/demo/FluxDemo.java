package com.m.study.spring.template.reactive.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {

    public static void main(String[] args) {

        println("运行...");

        Flux.just("A", "B", "C") // A,B,C
                //.publishOn(Schedulers.elastic()) // 线程池切换
                .map(value -> "+" + value)
                .subscribe(FluxDemo::println, // 消费 onNext
                        FluxDemo::println,    // 异常 onException
                        () -> println("完成操作!"),  // 完成回调 onComplete
                        subscription -> { // 背压操作 onSubscribe  设置Integer.MAX_VALUE 可以保证批量数据消费
                            subscription.request(Integer.MAX_VALUE); // 请求元素的数量
                             // subscription.cancel(); // 取消请求后，不会触发消费行为
                        }
                )
        ;

        println("结束");
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程 " + threadName + "] " + object);
    }

}

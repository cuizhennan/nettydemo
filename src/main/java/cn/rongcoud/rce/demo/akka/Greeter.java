package cn.rongcoud.rce.demo.akka;

import akka.actor.UntypedActor;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/14 14:33
 * Use:
 */
public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET, DONE
    }

    public void onReceive(Object message) {
        if (message == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}

package cn.rongcoud.rce.demo.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/14 14:41
 * Use:
 */
public class HelloWorld extends UntypedActor {
    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path: " + greeter.path());
        greeter.tell(Greeter.Msg.GREET, getSelf());
        getContext().stop(getSelf());
    }

    public void onReceive(Object message) {
        if (message == Greeter.Msg.DONE) {
            System.out.println("Done!");
            greeter.tell(Greeter.Msg.GREET, getSelf());
        } else {
            unhandled(message);
        }
    }
}

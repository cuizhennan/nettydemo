package cn.rongcoud.rce.demo.akka;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/15 14:38
 * Use:
 */
public class Watcher extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    public Watcher(ActorRef ref) {
        getContext().watch(ref);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Terminated) {
            System.out.println(String.format("%s has terminated, shutting down system", ((Terminated) message).getActor().path()));
        } else
            unhandled(message);
    }
}

package cn.rongcoud.rce.demo.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/15 14:41
 * Use:
 */
public class DeadMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("deadwatch", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "workder");
        system.actorOf(Props.create(Watcher.class, worker), "watcher");
        worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
//        worker.tell(MyWorker.Msg.CLOSE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}

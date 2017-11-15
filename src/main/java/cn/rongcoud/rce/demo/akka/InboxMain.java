package cn.rongcoud.rce.demo.akka;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;

import javax.xml.datatype.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/15 15:31
 * Use:
 */
public class InboxMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("inboxdemo", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "workder");

        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);

        inbox.send(worker, MyWorker.Msg.WORKING);
        inbox.send(worker, MyWorker.Msg.DONE);
        inbox.send(worker, MyWorker.Msg.CLOSE);

        while (true) {
            Object msg = inbox.receive(scala.concurrent.duration.Duration.create(1, TimeUnit.MINUTES));
            if (msg == MyWorker.Msg.CLOSE) {
                System.out.println("MyWorker is Closing");
            } else if (msg instanceof Terminated) {
                System.out.println("My worker is dead");
                system.shutdown();
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}

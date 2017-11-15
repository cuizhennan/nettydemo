
package cn.rongcoud.rce.demo.akka;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.akka
 * User: @CZN 2017/11/15 15:09
 * Use:
 */
public class Supervisor extends UntypedActor {

    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES), new Function<Throwable, SupervisorStrategy.Directive>() {
        public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
            if (t instanceof ArithmeticException) {
                System.out.println("meet arithmeticException, just resume");
                return SupervisorStrategy.resume();
            } else if (t instanceof NullPointerException) {
                System.out.println("meet NullPointExcepiton, restart");
                return SupervisorStrategy.restart();
            } else if (t instanceof IllegalArgumentException) {
                return SupervisorStrategy.stop();
            } else {
                return SupervisorStrategy.escalate();
            }
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof Props) {
            getContext().actorOf((Props) message, "restartActor");
        } else
            unhandled(message);
    }
}

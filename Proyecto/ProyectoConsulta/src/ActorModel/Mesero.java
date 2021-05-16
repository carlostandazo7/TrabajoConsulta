package ActorModel;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Mesero extends UntypedActor {
    public enum Mensaje {
        ESCRIBRIR_ORDEN,
        PEDIDO
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private static final long TIEMPO_ESCRIBIR_PEDIDO = 2000;
    private ActorRef comensal;
    private ActorRef chef;


    @Override
    public void preStart() {
        chef = getContext().actorOf(Props.create(Chef.class), "chef");
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Mesero] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.ESCRIBRIR_ORDEN) {
            comensal = getSender();
            chef.tell(Chef.Mensaje.RECIBIR_PEDIDO, getSelf());
        } else if (o == Mensaje.PEDIDO) {
            log.info("[Mesero] está tomando el pedido...");
            tomarPedido();
            log.info("[Mesero] ha tomado el pedido.");
            comensal.tell(Comensal.Mensaje.NUEVO_PEDIDO, getSelf());
        } else {
            unhandled(o);
        }
    }

    private void tomarPedido() throws InterruptedException {
        Thread.sleep(TIEMPO_ESCRIBIR_PEDIDO);
    }

    @Override
    public void unhandled(Object message) {
        log.info("[Mesero] no sabe qué hacer ante el mensaje: \"{}\".", message);
    }
}

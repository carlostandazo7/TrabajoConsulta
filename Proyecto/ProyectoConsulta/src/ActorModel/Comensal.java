package ActorModel;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Comensal extends UntypedActor {
    public enum Mensaje {
        NUEVO_PEDIDO,
        SALUDO
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void preStart() {
        final ActorRef mesero = getContext().actorOf(Props.create(Mesero.class), "mesero");
        mesero.tell(Mesero.Mensaje.ESCRIBRIR_ORDEN, getSelf());
        mesero.tell(Mensaje.SALUDO, getSelf());
    }

    @Override
    public void onReceive(Object o) {
        log.info("[Comensal] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.NUEVO_PEDIDO) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
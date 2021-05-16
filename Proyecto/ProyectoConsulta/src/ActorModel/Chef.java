package ActorModel;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Chef extends UntypedActor {
    public enum Mensaje {
        RECIBIR_PEDIDO
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private static final long TIEMPO_PEDIDO = 2000;

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Chef] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.RECIBIR_PEDIDO) {
            log.info("[Chef] está recibiendo el pedido...");
            obtenerMinerales();
            log.info("[Chef] ha recibido el pedido.");
            getSender().tell(Mesero.Mensaje.PEDIDO, getSelf());
        } else {
            unhandled(o);
        }
    }

    private void obtenerMinerales() throws InterruptedException {
        Thread.sleep(TIEMPO_PEDIDO);
    }
}

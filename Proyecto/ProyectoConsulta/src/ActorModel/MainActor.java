package ActorModel;

public final class MainActor {

    private MainActor() {}

    public static void main(String[] args) {
        akka.Main.main(new String[]{Comensal.class.getName()});
    }
}

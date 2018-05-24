package serverproxy;

public class ServerFactory {
    private static IServerProxy ourInstance = new XMLServerProxy();

    public static IServerProxy getInstance() {
        return ourInstance;
    }

    private ServerFactory() {
    }
}

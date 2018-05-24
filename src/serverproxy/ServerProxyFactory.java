package serverproxy;

public class ServerProxyFactory {
    private static IServerProxy ourInstance = new XMLServerProxy();

    public static IServerProxy getInstance() {
        return ourInstance;
    }

    private ServerProxyFactory() {
    }
}

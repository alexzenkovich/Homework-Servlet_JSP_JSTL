package connection;

public abstract class ConnectionFactory {

    private ConnectionFactory() {
    }

    private enum connectionImpl {JDBC, C3P0}

    public static DBConnection getConnectionPool (String conImpl) {

        switch (ConnectionFactory.connectionImpl.valueOf(conImpl.toUpperCase())) {
            case C3P0: return C3P0Pool.getInstance();
            case JDBC: return JDBCConnection.getInstance();
            default:throw new IllegalArgumentException(conImpl);
        }
    }
}

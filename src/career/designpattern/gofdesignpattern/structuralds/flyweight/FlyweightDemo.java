package career.designpattern.gofdesignpattern.structuralds.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface DataSource{
    void datasource();
}

class Oracle implements DataSource {

    @Override
    public void datasource() {
        System.out.println(" Oracle Datasource ");
    }
}

class MqSQL implements DataSource {

    @Override
    public void datasource() {
        System.out.println(" MqSQL Datasource ");
    }
}

class MongoDb implements DataSource {

    @Override
    public void datasource() {
        System.out.println(" MqSQL Datasource ");
    }
}

interface Connections{

    void get(DataSource dataSource);
    void open();
    void close();
}

class OracleConnection implements Connections {
    private DataSource dataSource;

    @Override
    public void get(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataSource.datasource();
        System.out.println(" get Oracle data source");
    }

    @Override
    public void open() {
        System.out.println("Open oracle connection ");
    }

    @Override
    public void close() {
        System.out.println("Close oracle connection ");
    }

}

class MySqlConnection implements Connections {
    private DataSource dataSource;

    @Override
    public void get(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataSource.datasource();
        System.out.println(" get MySql data source");
    }

    @Override
    public void open() {
        System.out.println("Open MySql connection ");
    }

    @Override
    public void close() {
        System.out.println("Close MySql connection ");
    }
}

class MongoDbConnection implements Connections {
    private DataSource dataSource;

    @Override
    public void get(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataSource.datasource();
        System.out.println(" get MongoDb data source");
    }

    @Override
    public void open() {
        System.out.println("Open MongoDb connection ");
    }

    @Override
    public void close() {
        System.out.println("Close MongoDb connection ");
    }
}

interface ConnectionPools{
//    void get(Connections connections);

    Connections getOracleConnection(DataSource dataSource);
    Connections getMysqlConnection(DataSource dataSource);
    Connections getMongoDbConnection(DataSource dataSource);
}

class ConnectionsFacade implements ConnectionPools {

    OracleConnection oracleConnection;
    MySqlConnection mySqlConnection;
    MongoDbConnection mongoDbConnection;

    public ConnectionsFacade() {
        oracleConnection = new OracleConnection();
        mySqlConnection = new MySqlConnection();
        mongoDbConnection = new MongoDbConnection();
    }

    @Override
    public Connections getOracleConnection(DataSource dataSource) {
        oracleConnection.get(dataSource);
        return oracleConnection;
    }

    @Override
    public Connections getMysqlConnection(DataSource dataSource) {
        mySqlConnection.get(dataSource);
        return mySqlConnection;
    }

    @Override
    public Connections getMongoDbConnection(DataSource dataSource) {
        mongoDbConnection.get(dataSource);
        return  mongoDbConnection;
    }
}

class ConeectionPools{
    HashMap<String, List<Connections>> connectionPool;
    HashMap<String, List<Connections>> connectionPoolUsed;

    public ConeectionPools() {
        connectionPool = new HashMap<>();
        connectionPool.put("orcle",new ArrayList<>());
        connectionPool.put("mysql",new ArrayList<>());
        connectionPool.put("mongodb",new ArrayList<>());

        connectionPoolUsed = new HashMap<>();
        connectionPoolUsed.put("orcle",new ArrayList<>());
        connectionPoolUsed.put("mysql",new ArrayList<>());
        connectionPoolUsed.put("mongodb",new ArrayList<>());

        connectionPool.get("orcle").add(new OracleConnection());
        connectionPool.get("orcle").add(new OracleConnection());
        connectionPool.get("orcle").add(new OracleConnection());

        connectionPool.get("mysql").add(new MySqlConnection());
        connectionPool.get("mysql").add(new MySqlConnection());
        connectionPool.get("mysql").add(new MySqlConnection());

        connectionPool.get("mongodb").add(new MongoDbConnection());
        connectionPool.get("mongodb").add(new MongoDbConnection());
        connectionPool.get("mongodb").add(new MongoDbConnection());

    }

    public Connections getConnection(String connection){
        Connections connections=null;
        switch (connection){
            case "oracle":
                          if(connectionPool.get(connection).size() > 0){
                              connections = connectionPool.get(connection).remove(0);
                              connectionPoolUsed.get(connection).add(connections);
                              connections.get(new Oracle());
                          }
                          break;
            case "mysql":
                         break;
            default:
                    break;
        }

        return connections;
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {

    }
}

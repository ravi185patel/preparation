package career.designpattern.gofdesignpattern.structuralds.facade;

interface DataSource{
    void datasource();
}

class Oracle implements DataSource{

    @Override
    public void datasource() {
        System.out.println(" Oracle Datasource ");
    }
}

class MqSQL implements DataSource{

    @Override
    public void datasource() {
        System.out.println(" MqSQL Datasource ");
    }
}

class MongoDb implements DataSource{

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

class OracleConnection implements Connections{
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

class MySqlConnection implements Connections{
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

class MongoDbConnection implements Connections{
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

class ConnectionsFacade implements ConnectionPools{

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
//
//class OracleConnection implements Connections{
//
//    @Override
//    public void get() {
//        System.out.println(" Oracle Connection open ");
//    }
//
//    @Override
//    public void close() {
//        System.out.println(" Oracle Connection close ");
//    }
//}
//
//class SqlConnection implements Connections{
//
//    @Override
//    public void get() {
//        System.out.println(" Sql Connection open ");
//    }
//
//    @Override
//    public void close() {
//        System.out.println(" Sql Connection close ");
//    }
//}
//
//class MongoDbConnection implements  Connections{
//
//    @Override
//    public void get() {
//        System.out.println(" MongoDb Connection open ");
//    }
//
//    @Override
//    public void close() {
//        System.out.println(" MongoDb Connection close ");
//    }
//}



public class FacadeDemo {
    public static void main(String[] args) {
        DataSource orcle = new Oracle();
        DataSource mysql = new MqSQL();

        ConnectionPools connectionPools = new ConnectionsFacade();

        Connections oracleConnection = connectionPools.getOracleConnection(orcle);
        oracleConnection.open();
        oracleConnection.close();

        Connections mysqlConnection = connectionPools.getMysqlConnection(mysql);
        mysqlConnection.open();
        mysqlConnection.close();
    }
}


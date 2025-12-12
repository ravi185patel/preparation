package career.designpattern.gofdesignpattern.creationalds.singleton;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


class DataSource implements  Serializable{

}

class Connection implements Cloneable{

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
enum DatabaseEnum {

    INSTANCE;

    public static void getDatabase() {
        // do something
    }
}

final class Database extends Connection implements Serializable {

    // The field must be declared volatile so that double check lock would work correctly.
    // We have declared the obj volatile which ensures that multiple threads offer the obj variable correctly
    // when it is being initialized to the Singleton instance.
    // This method drastically reduces the overhead of calling the synchronized method every time.

    // Voltile :  used to mark a Java variable as “being stored in main memory”.
    // Every thread that accesses a volatile variable will read it from main memory, and not from the CPU cache.
    // This way, all threads see the same value for the volatile variable.
    private static volatile Database database;

    private Database(){ // private will also secure to create object from reflection api
    }


    // The approach taken here is called double-checked locking (DCL). It
    // exists to prevent race condition between multiple threads that may
    // attempt to get singleton instance at the same time, creating separate
    // instances as a result.
    //
    // It may seem that having the `Database` variable here is completely
    // pointless. There is, however, a very important caveat when
    // implementing double-checked locking in Java, which is solved by
    // introducing this local variable.
    //
    // You can read more info DCL issues in Java here:
    // https://refactoring.guru/java-dcl-issue

    public static Database getDatabase(){ // two way looking
        if(database == null){
            synchronized (Database.class){
                if(database == null){
                    database = new Database();
                }
            }
        }
        return database;
    }


    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not supported ");
//         return instance; //which return same instance if you don't want to raise exception;
    }

    protected Object readResolve() { return database; }  // which return same instance when we try to deserialize it.

}
public class SingletonDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CloneNotSupportedException {
        /*Class<?> database = Class.forName("designpattern.gofdesignpattern.creationalds.singleton.Database");
        Constructor<?> cons1 = database.getConstructor();
//        Constructor<?> cons2 = birdClass.getConstructor(String.class);
//        Constructor<?> cons3 = birdClass.getConstructor(String.class,boolean.class);

        Database database1 = (Database) cons1.newInstance();
        System.out.println(database1.hashCode());*/

        /*
        // implement clone in parent
        Database database = Database.getDatabase();
        System.out.println(database.hashCode());
        Database database1 = (Database) database.clone();
        System.out.println(database1.hashCode());

        //        Resolve using overriding clone method and throw exception

        */


        // implement serialization in parent
        Database database = Database.getDatabase();
        System.out.println(database.hashCode());
        Database database1 = (Database) database.clone();
        System.out.println(database1.hashCode());

        //        Resolve using overriding clone method and throw exception



    }
}

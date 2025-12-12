package career.designpattern.gofdesignpattern.behaviorls.templatemethod;

/*
✔ Intent

Define algorithm steps in superclass; subclasses override specific parts.

✔ Real-life analogy

Cooking recipe

Software build pipeline: compile → test → deploy
✔ Spring Boot usage

JdbcTemplate (you override RowMapper logic)
RestTemplate
Spring Boot startup lifecycle


 */
abstract class DataParser {
    public final void parse(){
        read();
        process();
        write();
    }
    abstract void read();
    abstract void process();
    void write(){ System.out.println("Write file"); }
}

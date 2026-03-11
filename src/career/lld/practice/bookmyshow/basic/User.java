package career.lld.practice.bookmyshow.basic;

class User {

    private String userId;
    private String name;

    public User(String userId,String name){
        this.userId=userId;
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
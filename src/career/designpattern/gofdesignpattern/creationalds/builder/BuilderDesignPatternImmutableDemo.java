package career.designpattern.gofdesignpattern.creationalds.builder;

final class Request{
    /*
        used in passing values from ui to backend where all parameters are required but all request have not same parameters.
        So builder is useful in this situation.

        here we declare static builder inside main class which is tight coupling so in future if you need to change anything in builder it will change whole class.

        you can try to implement separate builder class too.
     */

    private final String id,name,type,nos;

    public Request(String id, String name, String type, String nos) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.nos = nos;
    }

    static class RequestBuilder{
        private String id,name,type,nos;

        public RequestBuilder setId(String id) {
            this.id=id;
            return this;
        }

        public RequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RequestBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public RequestBuilder setNos(String nos) {
            this.nos = nos;
            return this;
        }

        public Request build(){
            return new Request(this.id,this.name,this.type,this.nos);
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", nos='" + nos + '\'' +
                '}';
    }
}


public class BuilderDesignPatternImmutableDemo {
    public static void main(String[] args) {
        Request.RequestBuilder requestBuilder = new Request.RequestBuilder();
        requestBuilder.setId("123").setName("ravi"); // immutable plus not passing all parameters.
        Request request = requestBuilder.build();
        System.out.println(request.toString());

        requestBuilder.setId("1234").setName("pate").setType("developer");
        request = requestBuilder.build();
        System.out.println(request.toString());

    }
}

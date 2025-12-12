package career.designpattern.gofdesignpattern.creationalds.prototype;


abstract  class Search implements Cloneable{
    private String name;
    private String id;
    private String nos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNos() {
        return nos;
    }

    public void setNos(String nos) {
        this.nos = nos;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Search{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", nos='" + nos + '\'' +
                '}';
    }
}

final class SearchRequest extends Search{


    public SearchRequest() {

    }




}
public class PrototypeDemoSearchCrietria {
    public static void main(String[] args) throws CloneNotSupportedException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setId("123");
        searchRequest.setName("ravi");
        System.out.println(searchRequest.toString());

        SearchRequest searchRequestC = (SearchRequest) searchRequest.clone();
        searchRequestC.setId("1234");
        searchRequestC.setName("done");

        System.out.println("=============================================");
        System.out.println(searchRequest.hashCode());
        System.out.println(searchRequest.toString());
        System.out.println("=============================================");
        System.out.println("=============================================");
        System.out.println(searchRequestC.hashCode());
        System.out.println(searchRequestC.toString());
        System.out.println("=============================================");

    }
}

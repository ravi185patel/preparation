package career.designpattern.gofdesignpattern.behaviorls.chainresponsbility;

/*
✔ Spring Boot usage

Servlet Filters
Spring Security filter chain
Spring WebFlux WebFilter chain

 */
interface  Filter{
  void handleRequest();
  void setNextFilter(Filter filter);

}

class SecurityFilter implements Filter{
    Filter filter;
    @Override
    public void handleRequest() {
        System.out.println("Security");
        if(this.filter != null){
            filter.handleRequest();
        }
    }

    @Override
    public void setNextFilter(Filter filter) {
        this.filter=filter;
    }
}

class AuthenticationFilter implements Filter{

    Filter filter;
    @Override
    public void handleRequest() {
        System.out.println("Authentication");

        if(this.filter != null){
            filter.handleRequest();
        }
    }

    @Override
    public void setNextFilter(Filter filter) {
        this.filter=filter;
    }
}

class AutherizationFilter implements Filter{

    Filter filter;
    @Override
    public void handleRequest() {
        System.out.println("Autherization");

        if(this.filter != null){
            filter.handleRequest();
        }
    }

    @Override
    public void setNextFilter(Filter filter) {
        this.filter=filter;
    }
}

public class ChainResponsbilityDemo {

    public static void main(String[] args) {
        Filter securityFilter = new SecurityFilter();
        Filter authenticationFilter = new AuthenticationFilter();
        Filter autherizationFilter = new AutherizationFilter();

        securityFilter.setNextFilter(authenticationFilter);
        authenticationFilter.setNextFilter(autherizationFilter);

        securityFilter.handleRequest();

    }
}

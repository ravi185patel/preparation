package career.java.concept.immutable;

public class Address {
    private String city;
    private String pin;

    public Address(String city, String pin) {
        this.city = city;
        this.pin = pin;
    }

    public String getCity() { return city; }
    public String getPin() { return pin; }

    public void setCity(String city) { this.city = city; }
    public void setPin(String pin) { this.pin = pin; }
}

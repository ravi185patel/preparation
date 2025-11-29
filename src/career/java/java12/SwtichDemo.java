package career.java.java12;

public class SwtichDemo {
    public static void main(String[] args) {
        var day = java.time.DayOfWeek.MONDAY;
        int value = switch(day) {
            case MONDAY -> 1;
            case TUESDAY -> 2;
            default -> 0;
        };
        System.out.println(value);

    }
}

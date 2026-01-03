package career.java.scenario;

public class TrafficLightActions {
    public static void main(String[] args) {
        String lightColor = "YELLOW";   // Can be RED, YELLOW, GREEN
        boolean pedestrianWaiting = true;
        boolean emergencyVehicle = false;

        switch (lightColor) {
            case "RED":
                System.out.println("Stop");
                break;

            case "YELLOW":
                if (pedestrianWaiting) {
                    System.out.println("Stop, pedestrian crossing");
                } else {
                    System.out.println("Slow down");
                }
                break;

            case "GREEN":
                if (emergencyVehicle) {
                    System.out.println("Give way to emergency vehicle");
                } else {
                    System.out.println("Go");
                }
                break;

            default:
                System.out.println("Invalid light color");
        }
    }
}
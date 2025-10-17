package career.microservice.designpattern;

import java.util.ArrayList;
import java.util.List;

// Step interface
interface SagaStep {
    boolean execute();
    void compensate();
}

// Reserve inventory step
class ReserveInventoryStep implements SagaStep {
    @Override
    public boolean execute() {
        System.out.println("Reserving inventory...");
        // Simulate success
        return true;
    }
    @Override
    public void compensate() {
        System.out.println("Releasing reserved inventory!");
    }
}

// Process payment step
class ProcessPaymentStep implements SagaStep {
    @Override
    public boolean execute() {
        System.out.println("Processing payment...");
        // Simulate failure here for demo (change to true for success)
        return false;
    }
    @Override
    public void compensate() {
        System.out.println("Refunding payment!");
    }
}

// Confirm order step
class ConfirmOrderStep implements SagaStep {
    @Override
    public boolean execute() {
        System.out.println("Confirming order...");
        return true;
    }
    @Override
    public void compensate() {
        System.out.println("Cancelling order confirmation!");
    }
}

// Saga Coordinator
class Saga {
    private final List<SagaStep> steps = new ArrayList<>();
    private final List<SagaStep> executedSteps = new ArrayList<>();

    public void addStep(SagaStep step) {
        steps.add(step);
    }

    public boolean execute() {
        for (SagaStep step : steps) {
            if (step.execute()) {
                executedSteps.add(step);
            } else {
                System.out.println("Step failed, compensating...");
                compensate();
                return false;
            }
        }
        return true;
    }

    private void compensate() {
        for (int i = executedSteps.size() - 1; i >= 0; i--) {
            executedSteps.get(i).compensate();
        }
    }
}

// Demo
public class SagaPatternDemo {
    public static void main(String[] args) {
        Saga saga = new Saga();
        saga.addStep(new ReserveInventoryStep());
        saga.addStep(new ProcessPaymentStep());  // This will fail
        saga.addStep(new ConfirmOrderStep());

        boolean success = saga.execute();
        System.out.println("Saga completed successfully? " + success);
    }
}

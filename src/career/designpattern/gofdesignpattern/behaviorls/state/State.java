package career.designpattern.gofdesignpattern.behaviorls.state;

/*
✔ Real-life analogy

ATM machine: CardInserted, PinEntered, TransactionState
Traffic signal: Red → Yellow → Green
Order lifecycle: Created → Packed → Shipped → Delivered

✔ Spring Boot usage

Spring State Machine library
Workflow engines (Camunda, Activiti)
 */
interface State { void handle(); }

class DeliveredState implements State {
    public void handle(){

    }
}
class ShippedState implements State {
    public void handle(){

    }
}

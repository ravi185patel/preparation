package career.lld.DesignATM.ATMStates;

import career.lld.DesignATM.ATM;
import career.lld.DesignATM.Card;

public class IdleState extends ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card is inserted");
        atm.setCurrentATMState(new HasCardState());
    }
}

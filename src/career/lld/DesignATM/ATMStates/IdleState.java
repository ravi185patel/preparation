package target2023.LLD.LowLevelDesign.DesignATM.ATMStates;

import target2023.LLD.LowLevelDesign.DesignATM.ATM;
import target2023.LLD.LowLevelDesign.DesignATM.Card;

public class IdleState extends ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card is inserted");
        atm.setCurrentATMState(new HasCardState());
    }
}

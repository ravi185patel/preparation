package target2023.LLD.LowLevelDesign.DesignATM.ATMStates;

import target2023.LLD.LowLevelDesign.DesignATM.ATM;
import target2023.LLD.LowLevelDesign.DesignATM.Card;

public class CheckBalanceState extends ATMState{

    public CheckBalanceState() {
    }

    @Override
    public void displayBalance(ATM atm, Card card){
        System.out.println("Your Balance is: " + card.getBankBalance());
        exit(atm);
    }

    @Override
    public void exit(ATM atmObject){
        returnCard();
        atmObject.setCurrentATMState(new IdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard(){
        System.out.println("Please collect your card");
    }
}

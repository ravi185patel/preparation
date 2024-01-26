package target2023.LLD.LowLevelDesign.DesignATM;

public class UserBankAccount {

    int balance;

    public void withdrawalBalance(int amount) {
        balance = balance - amount;
    }
}

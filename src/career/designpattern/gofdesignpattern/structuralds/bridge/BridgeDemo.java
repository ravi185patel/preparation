package career.designpattern.gofdesignpattern.structuralds.bridge;


enum AccountType{
    SAVING,CURRENT;
}

abstract class Bank{
    Account account;

    public Bank(Account account) {
        this.account = account;
    }

    public abstract String getBankName();
}

interface  Account{

    public AccountType getAccountType();
}

class Saving implements Account{
    AccountType accountType;

    public Saving() {
        this.accountType = AccountType.SAVING;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "accountType=" + accountType +
                '}';
    }
}

class Current implements Account{

    AccountType accountType;

    public Current() {
        this.accountType = AccountType.CURRENT;
    }

    @Override
    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Current{" +
                "accountType=" + accountType +
                '}';
    }
}


class IcIcBank extends Bank {
    String name;

    public IcIcBank(String name, Account account) {
        super(account);
        this.name = name;
    }

    @Override
    public String getBankName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "IcIcBank{" +
                "name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}

class HdfcBank extends Bank {
    String name;

    public HdfcBank(String name, Account account) {
        super(account);
        this.name = name;
    }

    @Override
    public String getBankName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "HdfcBank{" +
                "name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Account savingAcc = new Saving();
        Account currentAcc = new Current();
        Bank icicBank = new IcIcBank("ICIC",savingAcc);
        Bank hdbcBank = new HdfcBank("HDBC",currentAcc);

        System.out.println(icicBank.toString());
        System.out.println(hdbcBank.toString());
    }
}


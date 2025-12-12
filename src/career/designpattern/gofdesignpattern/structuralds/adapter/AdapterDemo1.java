package career.designpattern.gofdesignpattern.structuralds.adapter;

interface CreditCard{
 public void getCreditCard();
}

abstract class Account{
  abstract public void getAccountDetails();
}

class BankCustomer extends  Account implements CreditCard{

    @Override
    public void getCreditCard() {
        System.out.println("Credit card");
    }

    @Override
    public void getAccountDetails() {
        System.out.println("Account details");
    }
}

public class AdapterDemo1 {
    public static void main(String[] args) {
        CreditCard creditCard = new BankCustomer(); // same class object assign into credit card and account reference which called adapter.
        creditCard.getCreditCard();

        Account account = new BankCustomer();
        account.getAccountDetails();
    }
}

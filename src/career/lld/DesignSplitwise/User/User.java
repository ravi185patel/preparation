package target2023.LLD.LowLevelDesign.DesignSplitwise.User;


import target2023.LLD.LowLevelDesign.DesignSplitwise.UserExpenseBalanceSheet;

public class User {

    String userId;
    String userName;
    UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String id, String userName){
        this.userId = id;
        this.userName = userName;
        userExpenseBalanceSheet = new UserExpenseBalanceSheet();
    }
    public String getUserId() {
        return userId;
    }

    public UserExpenseBalanceSheet getUserExpenseBalanceSheet() {
        return userExpenseBalanceSheet;
    }
}
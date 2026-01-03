package career.java.scenario;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        int lowerCount=0,upperCount=0,digit=0,specialCharacter=0;
        String password ="Abc@123456";
        for(char c:password.toCharArray()){
            if(Character.isDigit(c)){
                digit++;
            }else if(Character.isUpperCase(c)){
                upperCount++;
            }else if(Character.isLowerCase(c)){
                lowerCount++;
            }else{
                specialCharacter++;
            }

        }
        if(password.length() >= 8 && upperCount > 0 && lowerCount > 0 && specialCharacter > 0){
            System.out.println("Strong password");
        }else{
            System.out.println("Weak password");
        }
    }
}

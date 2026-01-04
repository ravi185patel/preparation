package career.java.scenario;

interface NotificationProvider{
    public void sendSms();
}

class Twili implements  NotificationProvider{
    @Override
    public void sendSms() {
        System.out.println("Twili set as notification provider...!");
    }
}
abstract class Notification {
    protected NotificationProvider notificationProvider;

    public Notification(NotificationProvider notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    abstract public void sendSms();

}
class SMS extends Notification{

    public SMS(NotificationProvider notificationProvider) {
        super(notificationProvider);
    }

    @Override
    public void sendSms() {
        super.notificationProvider.sendSms();
        System.out.println("Send SMS...!");
    }
}
class Email extends Notification{
    public Email(NotificationProvider notificationProvider) {
        super(notificationProvider);
    }

    @Override
    public void sendSms() {
        System.out.println("Send SMS...!");
    }
}



public class NotificationService {

    public static void main(String[] args) {
        NotificationProvider notificationProvider = new Twili();
        Notification notification = new SMS(notificationProvider);
        notification.sendSms();
    }
}

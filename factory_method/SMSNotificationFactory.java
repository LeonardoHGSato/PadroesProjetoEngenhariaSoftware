// Criador concreto: produz notificações por SMS
public class SMSNotificationFactory extends NotificationFactory {

    @Override
    protected Notification createNotification() {
        return new SMSNotification();
    }
}

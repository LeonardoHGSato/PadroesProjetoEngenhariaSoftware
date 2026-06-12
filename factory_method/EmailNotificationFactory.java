// Criador concreto: produz notificações por e-mail
public class EmailNotificationFactory extends NotificationFactory {

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

// Criador concreto: produz notificações push
public class PushNotificationFactory extends NotificationFactory {

    @Override
    protected Notification createNotification() {
        return new PushNotification();
    }
}

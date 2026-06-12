// Produto concreto: notificação push
public class PushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[Push] Enviando notificação push: " + message);
    }
}

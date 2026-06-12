// Produto concreto: notificação por SMS
public class SMSNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[SMS] Enviando SMS com a mensagem: " + message);
    }
}

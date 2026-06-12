// Produto concreto: notificação por e-mail
public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[Email] Enviando e-mail com a mensagem: " + message);
    }
}

// Criador (Creator) abstrato: declara o Factory Method
public abstract class NotificationFactory {

    // Factory Method: cada subclasse decide qual produto concreto criar
    protected abstract Notification createNotification();

    // Método que usa o produto criado, sem conhecer sua classe concreta
    public void notify(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
}

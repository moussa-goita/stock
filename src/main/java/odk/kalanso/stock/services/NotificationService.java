package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Manager;
import odk.kalanso.stock.entities.Notification;
import odk.kalanso.stock.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(String message, Manager manager) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDateNotification(new Date());
        notification.setManager(notification.getManager());
        notificationRepository.save(notification);
    }

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }
}

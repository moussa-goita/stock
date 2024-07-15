package odk.kalanso.stock.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity

public class Manager extends User{
@OneToMany
    private List<Notification> notifications;
}

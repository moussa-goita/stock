package odk.kalanso.stock.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usename;
    private String email;
    private String adresse;
    private  String pwd;

    @OneToMany
    private List<Role> roles;

}

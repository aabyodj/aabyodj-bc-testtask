package bootcamp.testtask.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname", length = 40, nullable = false)
    private String surname;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "patronymic", length = 40, nullable = false)
    private String patronymic;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 16, nullable = false)
    private Role role;

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        ADMIN("Administrator"), 
        SALES("Sale User"), 
        CUSTOMER("Customer User"), 
        SECURE("Secure API User");
        
        private final String longName;
    }

}

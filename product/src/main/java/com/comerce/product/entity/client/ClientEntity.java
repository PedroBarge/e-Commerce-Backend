package com.comerce.product.entity.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Email
    private String email;
    private String password;

    @Transient
    private PasswordEncoder passwordEncoder;

    @PrePersist
    @PreUpdate
    public void encryptPassword(){
        if (this.password != null && !this.password.startsWith("{bcrypt}")) {
            this.password = passwordEncoder.encode(this.password);
        }
    }
}

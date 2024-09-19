package com.comerce.product.entity.products;

import com.comerce.product.entity.client.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String linkPhoto;
    private String name;
    private String description;
    private String price;
    @Builder.Default
    private boolean isAvailable = true;
    private Category category;
    @ManyToOne
    private ClientEntity clientId;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private Date lastModifyAt;
}

package com.comerce.product.entity.products;

import com.comerce.product.entity.client.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

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
}

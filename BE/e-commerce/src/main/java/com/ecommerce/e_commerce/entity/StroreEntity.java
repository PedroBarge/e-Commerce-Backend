package com.ecommerce.e_commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StroreEntity {
    @Id
    private int id;
}

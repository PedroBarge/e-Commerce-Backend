package com.comerce.product.dto.client;

import com.comerce.product.entity.client.Roles;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoRequestUser {
    private String name;
    private Roles role;
    private String email;
    private String password;
}

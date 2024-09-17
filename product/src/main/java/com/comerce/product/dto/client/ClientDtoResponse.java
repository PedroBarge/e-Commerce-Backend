package com.comerce.product.dto.client;

import com.comerce.product.entity.client.Roles;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {
    private String id;
    private String name;
    private Roles role;
    private String email;
}

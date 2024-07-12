package com.comerce.product.dto.client;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoRequest {
    private String name;
    private String email;
    private String password;
}

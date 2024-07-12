package com.comerce.product.dto.client;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {
    private String id;
    private String name;
    private String email;
}

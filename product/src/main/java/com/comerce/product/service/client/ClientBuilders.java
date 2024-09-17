package com.comerce.product.service.client;

import com.comerce.product.dto.client.ClientDtoRequestUser;
import com.comerce.product.dto.client.ClientDtoResponse;
import com.comerce.product.entity.client.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientBuilders {
    private final PasswordEncoder passwordEncoder;

    public ClientEntity buildClientEntityUser(ClientDtoRequestUser client) {
        return ClientEntity.builder()
                .name(client.getName())
                .role(client.getRole())
                .email(client.getEmail())
                .password(passwordEncoder.encode(client.getPassword()))
                .passwordEncoder(passwordEncoder)
                .build();
    }

    public ClientDtoResponse buildClientDtoResponseUser(ClientEntity clientSave) {
        return ClientDtoResponse.builder()
                .id(clientSave.getId())
                .name(clientSave.getName())
                .role(clientSave.getRole())
                .email(clientSave.getEmail())
                .build();
    }
}

package com.comerce.product.service.client;

import com.comerce.product.dto.client.ClientDtoRequest;
import com.comerce.product.dto.client.ClientDtoResponse;
import com.comerce.product.entity.client.ClientEntity;
import com.comerce.product.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientDtoResponse createNewClient(ClientDtoRequest client) {
        if (checkIfEmailAlreadyExists(client)) return null;

        ClientEntity user = ClientEntity.builder()
                .name(client.getName())
                .email(client.getEmail())
                .password(passwordEncoder.encode(client.getPassword()))
                .passwordEncoder(passwordEncoder)
                .build();
        var clientSave = repository.save(user);

        return ClientDtoResponse.builder()
                .id(clientSave.getId())
                .name(clientSave.getName())
                .email(clientSave.getEmail())
                .build();
    }

    public List<ClientDtoResponse> getAllClients() {
        var getAllClients = repository.findAll();

        return getAllClients.stream()
                .map(client -> ClientDtoResponse.builder()
                        .id(client.getId())
                        .name(client.getName())
                        .email(client.getEmail())
                        .build()).toList();
    }

    private boolean checkIfEmailAlreadyExists(ClientDtoRequest client) {
        Optional<ClientEntity> checkIfEmailExists = repository.findByEmail(client.getEmail());
        if (checkIfEmailExists.isPresent()) {
            return true;
        }
        return false;
    }
}

package com.comerce.product.service.client;

import com.comerce.product.dto.client.ClientDtoRequestUser;
import com.comerce.product.dto.client.ClientDtoResponse;
import com.comerce.product.entity.client.ClientEntity;
import com.comerce.product.entity.client.Roles;
import com.comerce.product.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final Builders builders;

    public ClientDtoResponse createNewClient(ClientDtoRequestUser client) {
        if (checkIfEmailAlreadyExists(client)) return null;

        if (client.getRole().equals(Roles.USER)) {
            ClientEntity user = builders.buildClientEntityUser(client);
            ClientEntity clientSave = repository.save(user);
            return builders.buildClientDtoResponseUser(clientSave);
        }
        if (client.getRole().equals(Roles.SELLER)) {
            ClientEntity user = builders.buildClientEntityUser(client);
            ClientEntity clientSave = repository.save(user);
            return builders.buildClientDtoResponseUser(clientSave);
        }
        return null;
    }


    public List<ClientDtoResponse> getAllClients() {
        var getAllClients = repository.findAll();

        return getAllClients.stream()
                .map(builders::buildClientDtoResponseUser).toList();
    }

    private boolean checkIfEmailAlreadyExists(ClientDtoRequestUser client) {
        Optional<ClientEntity> checkIfEmailExists = repository.findByEmail(client.getEmail());
        if (checkIfEmailExists.isPresent()) {
            return true;
        }
        return false;
    }
}

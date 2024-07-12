package com.comerce.product.controller.client;

import com.comerce.product.dto.client.ClientDtoRequest;
import com.comerce.product.dto.client.ClientDtoResponse;
import com.comerce.product.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping
    public List<ClientDtoResponse> getAllClients(){
        return service.getAllClients();
    }
    @PostMapping
    public ClientDtoResponse createNewClient(@RequestBody ClientDtoRequest client) {
        return service.createNewClient(client);
    }


}

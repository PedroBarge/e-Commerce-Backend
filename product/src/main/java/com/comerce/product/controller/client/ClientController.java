package com.comerce.product.controller.client;

import com.comerce.product.dto.client.ClientDtoRequestUser;
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

    @GetMapping("/admin/listOfUsers")
    public List<ClientDtoResponse> getAllClients(){
        return service.getAllClients();
    }
    @PostMapping("/user")
    public ClientDtoResponse createNewClientUser(@RequestBody ClientDtoRequestUser client) {
        return service.createNewClient(client);
    }
//    @PostMapping("/seller")
//    public ClientDtoResponse createNewClientSeller(@RequestBody ClientDtoRequestUser client) {
//        return service.createNewClient(client);
//    }


}

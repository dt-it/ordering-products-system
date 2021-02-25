package pl.dtit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dtit.service.impl.ClientServiceImpl;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }
}

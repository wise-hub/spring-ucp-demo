package bg.fibank.spring_ucp_demo.controller;

import bg.fibank.spring_ucp_demo.service.ComplexCustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComplexCustomerController {

    private final ComplexCustomerService complexCustomerService;

    public ComplexCustomerController(ComplexCustomerService complexCustomerService) {
        this.complexCustomerService = complexCustomerService;
    }

    @GetMapping("/customer")
    public Map<String, Object> getCustomer() {
        return complexCustomerService.getCustomerData();
    }
}

package bg.fibank.spring_ucp_demo.service;

import bg.fibank.spring_ucp_demo.repository.ComplexCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ComplexCustomerService {

    private static final Logger logger = LoggerFactory.getLogger(ComplexCustomerService.class);

    private final ComplexCustomerRepository customerRepository;

    public ComplexCustomerService(ComplexCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Map<String, Object> getCustomerData() {
        try {
            logger.info("Fetching customer data from repository");
            return customerRepository.fetchCustomerData();
        } catch (Exception e) {
            logger.error("Error while retrieving customer data", e);
            throw new RuntimeException("Error while retrieving customer data", e);
        }
    }
}

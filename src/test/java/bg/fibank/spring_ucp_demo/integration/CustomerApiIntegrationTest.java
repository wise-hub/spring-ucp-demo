package bg.fibank.spring_ucp_demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") // Use the test profile
public class CustomerApiIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testFetchCustomerDataStructure() {
        // Test URL
        String url = "http://localhost:8081/api/customer"; // Adjust port or HTTPS as needed

        // Execute the GET request
        ResponseEntity<Map> response = testRestTemplate.getForEntity(url, Map.class);

        // Assert response status
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();

        // Validate top-level fields
        Map<String, Object> customer = response.getBody();
        assertThat(customer).containsKeys("customer_id", "full_name", "accounts", "address");

        // Validate structure of accounts
        List<Map<String, Object>> accounts = (List<Map<String, Object>>) customer.get("accounts");
        assertThat(accounts).isNotEmpty();

        // Validate fields in the first account
        Map<String, Object> account = accounts.get(0);
        assertThat(account).containsKeys("account_id", "account_category", "transactions");

        // Validate structure of transactions
        List<Map<String, Object>> transactions = (List<Map<String, Object>>) account.get("transactions");
        assertThat(transactions).isNotEmpty();

        // Validate fields in the first transaction
        Map<String, Object> transaction = transactions.get(0);
        assertThat(transaction).containsKeys("transaction_id", "transaction_date", "transaction_amount");

        // Validate structure of address
        Map<String, Object> address = (Map<String, Object>) customer.get("address");
        assertThat(address).containsKeys("street_address", "city_name", "postal_code", "phone_contacts");

        // Validate structure of phone_contacts
        List<Map<String, Object>> phoneContacts = (List<Map<String, Object>>) address.get("phone_contacts");
        assertThat(phoneContacts).isNotEmpty();

        // Validate fields in the first phone contact
        Map<String, Object> phoneContact = phoneContacts.get(0);
        assertThat(phoneContact).containsKeys("phone_category", "phone_number");
    }
}

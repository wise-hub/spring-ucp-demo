package bg.fibank.spring_ucp_demo.repository;

import bg.fibank.dbcon.udtmapper.UdtMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.Map;
import oracle.jdbc.OracleTypes;

@Repository
public class ComplexCustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UdtMapper udtMapper;

    public ComplexCustomerRepository(JdbcTemplate jdbcTemplate, UdtMapper udtMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.udtMapper = udtMapper;
    }

    public Map<String, Object> fetchCustomerData() {
        return jdbcTemplate.execute((Connection connection) -> {
            try (var callableStatement = connection.prepareCall("{ CALL get_customer_info(?) }")) {
                callableStatement.registerOutParameter(1, OracleTypes.STRUCT, "CUSTOMER_DETAILS");
                callableStatement.execute();

                return udtMapper.fetchAndMapUdt("CUSTOMER_DETAILS", callableStatement);
            }
        });
    }
}

-- Phone number type
CREATE OR REPLACE TYPE phone_details AS OBJECT (
    phone_category VARCHAR2(50),  -- e.g., "Mobile", "Home", "Work"
    phone_number VARCHAR2(20)
);

-- Phone number table type
CREATE OR REPLACE TYPE phone_list AS TABLE OF phone_details;

-- Address type with nested table for phone numbers
CREATE OR REPLACE TYPE address_details AS OBJECT (
    street_address VARCHAR2(200),
    city_name VARCHAR2(100),
    postal_code VARCHAR2(10),
    phone_contacts phone_list -- Nested table of phone numbers
);

-- Transaction type
CREATE OR REPLACE TYPE transaction_details AS OBJECT (
    transaction_id NUMBER,
    transaction_date DATE,
    transaction_amount NUMBER(15, 2)
);

-- Transaction table type
CREATE OR REPLACE TYPE transaction_list AS TABLE OF transaction_details;

-- Account type
CREATE OR REPLACE TYPE account_details AS OBJECT (
    account_id NUMBER,
    account_category VARCHAR2(50), -- e.g., "Savings", "Checking"
    transactions transaction_list -- Nested table of transactions
);

-- Account table type
CREATE OR REPLACE TYPE account_list AS TABLE OF account_details;

-- Customer type with accounts and address
CREATE OR REPLACE TYPE customer_details AS OBJECT (
    customer_id NUMBER,
    full_name VARCHAR2(100),
    accounts account_list, -- Table of accounts
    address address_details -- Address object
);

-- Procedure to return customer data
CREATE OR REPLACE PROCEDURE get_customer_info(customer OUT customer_details) AS
BEGIN
    customer := customer_details(
        customer_id => 1,
        full_name => 'John Doe',
        accounts => account_list(
            account_details(
                account_id => 101,
                account_category => 'Savings',
                transactions => transaction_list(
                    transaction_details(1, SYSDATE, 100.50),
                    transaction_details(2, SYSDATE - 1, 2000.75)
                )
            ),
            account_details(
                account_id => 102,
                account_category => 'Checking',
                transactions => transaction_list(
                    transaction_details(3, SYSDATE - 2, 50.25),
                    transaction_details(4, SYSDATE - 3, 20.00)
                )
            )
        ),
        address => address_details(
            street_address => '123 Main St',
            city_name => 'Springfield',
            postal_code => '62701',
            phone_contacts => phone_list(
                phone_details('Mobile', '555-1234'),
                phone_details('Work', '555-5678')
            )
        )
    );
END;

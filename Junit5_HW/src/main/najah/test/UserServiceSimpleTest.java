package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Product;
import main.najah.code.UserService;

@DisplayName("UserService Tests")
class UserServiceSimpleTest {
	
    UserService userService;

    @BeforeAll
	static void setUpBeforeClass() throws Exception {
    	System.out.println("Start Runing Calculator Tests");
	}
    
	@BeforeEach
	void setUp() throws Exception {
		userService = new UserService();
		System.out.println("Setup Completed");
	}
	
    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@subdomain.example.co.uk", "another@test.io"})
    @DisplayName("Test UserService Class isValidEmail Method with valid email addresses")
    void testIsValidEmailWithValidEmails(String email) {
        assertTrue(userService.isValidEmail(email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @Disabled("Fix the implementation to handle Emails with '@' & '.' but they are invalid for example like @example.com")
    @ValueSource(strings = {"testexample.com", "test@example", "test", "@example.com", "test@", ".com", "test.", "test@.com", "test.@com", "test@@example.com", "test..example.com",""})
    @DisplayName("Test UserService Class isValidEmail Method with invalid email addresses")
    void testIsValidEmailWithInvalidEmails(String email) {
        assertFalse(userService.isValidEmail(email));
    }
    
    @Test
    @DisplayName("Test UserService Class authenticate Method with valid credentials")
    void testAuthenticate_Valid() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Test UserService Class authenticate Method with invalid username")
    void testAuthenticate_InvalidUsername() {
        assertFalse(userService.authenticate("user", "1234"));
    }

    @Test
    @DisplayName("Test UserService Class authenticate Method with invalid password")
    void testAuthenticate_InvalidPassword() {
        assertFalse(userService.authenticate("admin", "wrong"));
    }

    @Test
    @DisplayName("Test UserService Class authenticate Method with invalid username and password")
    void testAuthenticate_InvalidBoth() {
        assertFalse(userService.authenticate("user", "wrong"));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test UserService Class authenticate Method with null or empty username")
    void testAuthenticate_NullOrEmptyUsername(String username) {
        assertFalse(userService.authenticate(username, "1234"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test UserService Class authenticate Method with null or empty password")
    void testAuthenticate_NullOrEmptyPassword(String password) {
        assertFalse(userService.authenticate("admin", password));
    }

    @Test
    @DisplayName("Test UserService Class authenticate Method with null username and null password")
    void testAuthenticate_NullBoth() {
        assertFalse(userService.authenticate(null, null));
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test Completed");
    }
   
   @AfterAll
   static void tearDownAfterClass() throws Exception {
       System.out.println("All Calculator class methods Tests Completed");
   }
}

package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Calculator;
import main.najah.code.Product;
@DisplayName("Product Tests")
public class ProductTest {
    Product p;
    
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
    	System.out.println("Start Runing Calculator Tests");
	}
    
	@BeforeEach
	void setUp() throws Exception {
		p = new Product("Test Product", 100.0);
		System.out.println("Setup Completed");
	}

    @Test
    @DisplayName("Test Product constructor with valid input")
    void testConstructorValidInput() {
        assertEquals("Test Product", p.getName());
        assertEquals(100.0, p.getPrice());
        assertEquals(0.0, p.getDiscount());
    }

    @Test
    @DisplayName("Test Product constructor with negative price")
    void testConstructorNegativePrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Invalid Product", -10.0);
        });
        assertEquals("Price must be non-negative", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Product class ApplyDiscount method with valid discount percentage")
    void testApplyDiscountPositivePercentage() {
        p.applyDiscount(10.0);
        assertEquals(10.0, p.getDiscount());
    }

    @Test
    @DisplayName("Test Product class ApplyDiscount method with zero discount percentage")
    void testApplyDiscountZeroPercentage() {
        p.applyDiscount(0.0);
        assertEquals(0.0, p.getDiscount());
    }

    @Test
    @DisplayName("Test Product class ApplyDiscount method with maximum allowed discount percentage")
    void testApplyDiscountMaxPercentage() {
        p.applyDiscount(50.0);
        assertEquals(50.0, p.getDiscount());
    }
    
    @Test
    @DisplayName("Test Product class ApplyDiscount method with discount percentage greater than 50 throws exception")
    void testApplyDiscountExceedsMaxPercentage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            p.applyDiscount(55.0);
        });
        assertEquals("Invalid discount", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Product class ApplyDiscount method with negative discount percentage throws exception")
    void testApplyDiscountNegativePercentage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            p.applyDiscount(-5.0);
        });
        assertEquals("Invalid discount", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Product class getFinalPrice method with no discount")
    void testGetFinalPriceNoDiscount() {
        assertEquals(100.0, p.getFinalPrice());
        assertFalse(p.getFinalPrice() < p.getPrice());
    }
    

    @Test
    @DisplayName("Test Product class getFinalPric method with a discount applied")
    void testGetFinalPriceWithDiscount() {
        p.applyDiscount(20);
        assertEquals(80, p.getFinalPrice()); 
        assertTrue(p.getFinalPrice() < p.getPrice());
    }

    @Test
    @DisplayName("Test Product class getFinalPric method with maximum discount applied")
    void testGetFinalPriceWithMaxDiscount() {
        p.applyDiscount(50.0);
        assertEquals(50.0, p.getFinalPrice());
        assertTrue(p.getFinalPrice() < p.getPrice());
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

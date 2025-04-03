package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
public class CalculatorTest {
    
    Calculator calc;
    
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
    	System.out.println("Start Runing Calculator Tests");
	}
    
	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculator();
		System.out.println("Setup Completed");
	}
	
	@Test
	@Order(1)
	@DisplayName("Test Calculator class Add method with Positive Numbers")
	void testAddPositive() {
		assertEquals(8, calc.add(2, 2, 4));
	}
	
	@Test
	@Order(2)
	@DisplayName("Test Calculator class Add method with Zero")
	void testAddZero() {
		assertEquals(0, calc.add(0));
	}
	
	@Test
	@Order(3)
	@DisplayName("Test Calculator class Add method with Nigative Numbers")
	void testAddNigative() {
		assertEquals(-8, calc.add(-2, -2, -4));
	}
	
	@Test
	@Order(4)
	@Disabled("Fix the implementation to handle Integer overflow cases")
	@DisplayName("Test Calculator class Add method with Positive large Numbers Integer overflow")
	void testAddLargePositive() {
		assertThrows(ArithmeticException.class, () -> calc.add(2,147,483,647, 2,147,483,647));
	}
    
	
	@ParameterizedTest
	@Order(5)
    @CsvSource({"6, 3, 2", "10, 2, 5", "15, 3, 5"})
    @DisplayName("Test Calculator class Divide method with Positive Numbers")
    void testDividePositiveNumbers(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }

	@ParameterizedTest
	@Order(6)
    @CsvSource({"-8, -4, 2", "-10, -2, 5", "-15, -3, 5"})
	 @DisplayName("Test Calculator class Divide method with Negative Numbers")
    void testDivideNegativeNumbers(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }
	
	@ParameterizedTest
	@Order(7)
	@CsvSource({"6, -3, -2", "10, -2, -5", "15, -3, -5"})
	@DisplayName("Test Calculator class Divide method with Positive By Negative Numbers")
    void testDividePositiveByNegative(int a, int b, int expected) {
		assertEquals(expected, calc.divide(a, b));
    }
	
	@ParameterizedTest
	@Order(8)
	@CsvSource({"6, -3, -2", "10, -2, -5", "15, -3, -5"})
	@DisplayName("Test Calculator class Divide method with Negative By Positive Numbers")
    void testDivideNegativeByPositive(int a, int b, int expected) {
		assertEquals(expected, calc.divide(a, b));
    }
	
    @Test
    @Order(9)
	@DisplayName("Test Calculator class Divide method with Denominator of One")
    void testDivideByOne() {
        assertEquals(10, calc.divide(10, 1));
    }

    @Test
    @Order(10)
	@DisplayName("Test Calculator class Divide method with Numerator of Zero")
    void testDivideWithZeroNumerator() {
        assertEquals(0, calc.divide(0, 5));
    }

    @Test
    @Order(11)
	@DisplayName("Test Calculator class Divide method with Denominator of Zero")
    void testDivideWithZeroDenominator() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
        	calc.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    
    @Test
    @Order(12)
    @DisplayName("Test Calculator class Factorial method with Value of Zero")
    void testFactorialWithZero() {
        assertEquals(1, calc.factorial(0));
    }

    @Test
    @Order(13)
    @DisplayName("Test Calculator class Factorial method with Value of One")
    void testFactorialWithOne() {
        assertEquals(1, calc.factorial(1));
    }

    @ParameterizedTest
    @Order(14)
    @CsvSource({"120, 5", "40320, 8", "3628800, 10"})
    @DisplayName("Test Calculator class Factorial method with Positive Numbers")
    void testFactorialWithPositiveNumbers(int expected, int n) {
        assertEquals(expected, calc.factorial(n));
    }

    @Test
    @Order(15)
    @DisplayName("Test Calculator class Factorial method with Negative Number")
    void testFactorialWithNegativeNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        	calc.factorial(-1);
        });
        assertEquals("Negative input", exception.getMessage());
    }
   
    @Test
    @Order(16)
    @Disabled("Fix the implementation to handle Integer overflow cases")
    @DisplayName("Test Calculator class Factorial method with Large Factorial")
    void testLargeFactorial() {
    	assertThrows(ArithmeticException.class, () -> calc.factorial(100000));
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

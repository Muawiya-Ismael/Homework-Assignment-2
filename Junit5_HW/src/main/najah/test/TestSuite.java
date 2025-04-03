package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import main.najah.code.Calculator;

@Suite
@SelectClasses({CalculatorTest.class, ProductTest.class, RecipeBookTest.class, UserServiceSimpleTest.class})
class TestSuite {}

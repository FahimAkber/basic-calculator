package com.practice.basiccalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "calculator.interactive=false")
class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    void testAdd() {
        double result = calculatorService.add(5.0, 3.0);
        assertEquals(8.0, result, 0.001);
    }

    @Test
    void testAddWithNegativeNumbers() {
        double result = calculatorService.add(-5.0, -3.0);
        assertEquals(-8.0, result, 0.001);
    }

    @Test
    void testAddWithDecimals() {
        double result = calculatorService.add(2.5, 3.7);
        assertEquals(6.2, result, 0.001);
    }

    @Test
    void testSubtract() {
        double result = calculatorService.subtract(10.0, 4.0);
        assertEquals(6.0, result, 0.001);
    }

    @Test
    void testSubtractWithNegativeNumbers() {
        double result = calculatorService.subtract(-10.0, -4.0);
        assertEquals(-6.0, result, 0.001);
    }

    @Test
    void testSubtractWithDecimals() {
        double result = calculatorService.subtract(5.5, 2.3);
        assertEquals(3.2, result, 0.001);
    }

    @Test
    void testMultiply() {
        double result = calculatorService.multiply(4.0, 5.0);
        assertEquals(20.0, result, 0.001);
    }

    @Test
    void testMultiplyWithNegativeNumbers() {
        double result = calculatorService.multiply(-4.0, 5.0);
        assertEquals(-20.0, result, 0.001);
    }

    @Test
    void testMultiplyWithTwoNegatives() {
        double result = calculatorService.multiply(-4.0, -5.0);
        assertEquals(20.0, result, 0.001);
    }

    @Test
    void testMultiplyWithDecimals() {
        double result = calculatorService.multiply(2.5, 4.0);
        assertEquals(10.0, result, 0.001);
    }

    @Test
    void testDivide() {
        double result = calculatorService.divide(20.0, 4.0);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    void testDivideWithNegativeNumbers() {
        double result = calculatorService.divide(-20.0, 4.0);
        assertEquals(-5.0, result, 0.001);
    }

    @Test
    void testDivideWithDecimals() {
        double result = calculatorService.divide(7.5, 2.5);
        assertEquals(3.0, result, 0.001);
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(10.0, 0.0);
        });
    }

    @Test
    void testDivideByZeroWithNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(-10.0, 0.0);
        });
    }
}

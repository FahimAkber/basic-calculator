package com.practice.basiccalculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class BasicCalculatorApplication {

    private final CalculatorService calculatorService;

    public BasicCalculatorApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicCalculatorApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(
            name = "calculator.interactive",
            havingValue = "true"
    )
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Calculator ===");
                System.out.println("Choose operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.print("Enter choice (1-4): ");

                int choice = scanner.nextInt();

                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                double result = 0;
                String operation = "";

                switch (choice) {
                    case 1:
                        result = calculatorService.add(num1, num2);
                        operation = "+";
                        break;
                    case 2:
                        result = calculatorService.subtract(num1, num2);
                        operation = "-";
                        break;
                    case 3:
                        result = calculatorService.multiply(num1, num2);
                        operation = "*";
                        break;
                    case 4:
                        try {
                            result = calculatorService.divide(num1, num2);
                            operation = "/";
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                            continue;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }

                System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operation, num2, result);

                Thread.sleep(2000);

                System.out.print("\nType 'exit' to close or press Enter to open calculator again: ");
                scanner.nextLine(); // consume newline
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Calculator closed. Goodbye!");
                    break;
                }
            }

            scanner.close();
            SpringApplication.exit(SpringApplication.run(BasicCalculatorApplication.class, args), () -> 0);
        };
    }
}

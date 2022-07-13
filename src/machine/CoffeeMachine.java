package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need: ");
        int cups = scanner.nextInt();
        System.out.printf("For %d cups of coffee you will need:%n", cups);
        int water = 200 * cups;
        System.out.printf("%d ml of water%n", water);
        int milk = 50 * cups;
        System.out.printf("%d ml of milk%n", milk);
        int beans = 15 * cups;
        System.out.printf("%d g of coffee beans%n", beans);
    }
}

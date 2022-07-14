package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int haveWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int haveMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int haveBeans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        int cups = scanner.nextInt();
        int haveCups = Math.min(Math.min(haveWater / 200, haveMilk / 50), haveBeans / 15);
        if (haveCups < cups) {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", haveCups);
        } else if (haveCups == cups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n", haveCups - cups);
        }
    }
}

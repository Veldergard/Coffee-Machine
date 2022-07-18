package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static Scanner scanner;
    public static int waterPool = 400;
    public static int milkPool = 540;
    public static int beansPool = 120;
    public static int cupsPool = 9;
    public static int money = 550;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        doAction();
    }

    public static void printStats() {
        System.out.printf("The coffee machine has:%n" +
                "%d ml of water%n" +
                "%d ml of milk%n" +
                "%d g of coffee beans%n" +
                "%d disposable cups%n" +
                "$%d of money%n", waterPool, milkPool, beansPool, cupsPool, money);
    }

    public static void doAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            System.out.println();
            switch (action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    System.out.printf("I gave you $%d%n", money);
                    money = 0;
                    break;
                case "remaining":
                    printStats();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Error! Write correct action (buy, fill, take, remaining, exit):");
                    break;
            }
            System.out.println();
        }
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        int order = 0;
        while (order == 0) {
            String orderS = scanner.next();
            if (isInt(orderS)) {
                order = Integer.parseInt(orderS);
                if (order < 1 || order > 3) {
                    order = 0;
                }
            } else if ("back".equals(orderS)) {
                return;
            } else {
                System.out.println("Error");
            }
        }
        makeCoffee(order - 1);
    }

    public static void fill() {
        int water = -1;
        int milk = -1;
        int beans = -1;
        int cups = -1;

        System.out.println("Write how many ml of water you want to add:");
        while (water == -1) {
            String temp = scanner.next();
            if (isInt(temp)) {
                water = Integer.parseInt(temp);
            }
        }
        System.out.println("Write how many ml of milk you want to add:");
        while (milk == -1) {
            String temp = scanner.next();
            if (isInt(temp)) {
                milk = Integer.parseInt(temp);
            }
        }
        System.out.println("Write how many grams of coffee beans you want to add:");
        while (beans == -1) {
            String temp = scanner.next();
            if (isInt(temp)) {
                beans = Integer.parseInt(temp);
            }
        }
        System.out.println("Write how many disposable cups of coffee you want to add:");
        while (cups == -1) {
            String temp = scanner.next();
            if (isInt(temp)) {
                cups = Integer.parseInt(temp);
            }
        }
        waterPool += water;
        milkPool += milk;
        beansPool += beans;
        cupsPool += cups;
    }

    public static void makeCoffee(int type) {
        final int[] water = {250, 350, 200};
        final int[] milk = {0, 75, 100};
        final int[] beans = {16, 20, 12};
        final int[] price = {4, 7, 6};

        if (waterPool >= water[type] && milkPool >= milk[type] && beansPool >= beans[type] && cupsPool >= 1) {
            waterPool -= water[type];
            milkPool -= milk[type];
            beansPool -= beans[type];
            money += price[type];
            cupsPool -= 1;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough water!");
        }
    }

    public static boolean isInt(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(0) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

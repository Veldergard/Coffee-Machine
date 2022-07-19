package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static int waterPool = 400;
    public static int milkPool = 540;
    public static int beansPool = 120;
    public static int cupsPool = 9;
    public static int money = 550;
    static int water = -1;
    static int milk = -1;
    static int beans = -1;
    static int cups = -1;

    enum State {
        EXITED, STARTING, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS, BUYING
    }

    static State state = State.STARTING;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        reset();
        String input;
        while (state != State.EXITED) {
            input = scanner.next();
            doAction(input);
        }
    }

    public static void doAction(String input) {
        switch (state) {
            case STARTING:
                System.out.println();
                switch (input) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        state = State.BUYING;
                        break;
                    case "fill":
                        state = State.FILLING_WATER;
                        System.out.println("Write how many ml of water you want to add:");
                        break;
                    case "take":
                        take();
                        System.out.println();
                        reset();
                        break;
                    case "remaining":
                        printStats();
                        System.out.println();
                        reset();
                        break;
                    case "exit":
                        state = State.EXITED;
                        return;
                    default:
                        System.out.println("Error! Write correct action (buy, fill, take, remaining, exit):");
                        break;
                }
                break;
            case BUYING:
                buy(input);
                break;
            case FILLING_WATER:
            case FILLING_MILK:
            case FILLING_BEANS:
            case FILLING_CUPS:
                fill(input);
                break;
        }
    }

    public static void buy(String input) {
        if (isInt(input)) {
            int order = Integer.parseInt(input);
            if (order < 1 || order > 3) {
                System.out.println("Error");
                return;
            }
            makeCoffee(order - 1);
            System.out.println();
            reset();
        } else if ("back".equals(input)) {
            reset();
        } else {
            System.out.println("Error");
        }
    }

    public static void take() {
        System.out.printf("I gave you $%d%n", money);
        money = 0;
    }

    public static void fill(String input) {
        switch (state) {
            case FILLING_WATER:
                if (isInt(input)) {
                    water = Integer.parseInt(input);
                    state = State.FILLING_MILK;
                    System.out.println("Write how many ml of milk you want to add:");
                }
                break;
            case FILLING_MILK:
                if (isInt(input)) {
                    milk = Integer.parseInt(input);
                    state = State.FILLING_BEANS;
                    System.out.println("Write how many grams of coffee beans you want to add:");
                }
                break;
            case FILLING_BEANS:
                if (isInt(input)) {
                    beans = Integer.parseInt(input);
                    state = State.FILLING_CUPS;
                    System.out.println("Write how many disposable cups of coffee you want to add:");
                }
                break;
            case FILLING_CUPS:
                if (isInt(input)) {
                    cups = Integer.parseInt(input);
                    waterPool += water;
                    milkPool += milk;
                    beansPool += beans;
                    cupsPool += cups;
                    System.out.println();
                    reset();
                }
                break;
        }
    }

    public static void reset() {
        state = State.STARTING;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        water = 0;
        milk = 0;
        beans = 0;
        cups = 0;
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

    public static void printStats() {
        System.out.printf("The coffee machine has:%n" +
                "%d ml of water%n" +
                "%d ml of milk%n" +
                "%d g of coffee beans%n" +
                "%d disposable cups%n" +
                "$%d of money%n", waterPool, milkPool, beansPool, cupsPool, money);
    }
}

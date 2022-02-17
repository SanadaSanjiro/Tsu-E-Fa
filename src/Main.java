import java.util.Locale;
import java.util.Scanner;

public class Main {
    static boolean cheaterMode=false;
    static suEFa playersChoice;

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру Камень-Ножницы-Бумага");
        System.out.println("Как вас зовут?");
        String playerName = consoleInput();
        System.out.println("Привет, " + playerName + "!");
        int turnsNumber = setTurnsNumber();
        while (turnsNumber <= 0) {
            System.out.println("Неверное число партий!");
            turnsNumber = setTurnsNumber();
        }
        turn(turnsNumber, playerName);
        while (true) {
            System.out.println("\nСыграем еще(y/n)?");
            String input = consoleInput();
            if (input.toLowerCase().equals("y")) {
                turnsNumber = setTurnsNumber();
                while (turnsNumber <= 0) {
                    System.out.println("Неверное число партий!");
                    turnsNumber = setTurnsNumber();
                }
                turn(turnsNumber, playerName);
            } else {
                if (input.toLowerCase().equals("n")) {
                    break;
                }
            }
        }
    }

    public static void turn(int turnsNumber, String playerName) {
        int playerWins = 0;
        int computerWins = 0;
        String count = "0 - 0";
        for (int i = 0; i < turnsNumber; i++) {
            int turnResult = whoIsWin(makeTurn(), computerTurn());
            switch (turnResult) {
                case 0 -> {
                    System.out.println("Ничья!");
                }
                case 1 -> {
                    System.out.println("Выиграл игрок "+ playerName);
                    playerWins++;
                }
                case 2 -> {
                    System.out.println("Выиграл компьютер!");
                    computerWins++;
                }
            }
            count = playerWins + " - " + computerWins;
            System.out.println("Счет " + count + "\n");
        }
        if (playerWins==computerWins) {
            System.out.println("Ничья! Итоговый счет: " + count);
        }
        else {
            if (playerWins>computerWins) {
                System.out.println("Со счетом " + count + " победил игрок " + playerName + "!" + "\nПоздравляем!!!");
            } else {
                System.out.println("Со счетом " + count + " победил компьютер!");
            }
        }
    }

    public enum suEFa {
        КАМЕНЬ,
        НОЖНИЦЫ,
        БУМАГА
    }

    public static int setTurnsNumber() {
        System.out.println("Сколько партий сыграем?");
        int turnNumber = Integer.parseInt(consoleInput());
        return turnNumber;
    }

    public static suEFa makeTurn() {
        suEFa playersTurn = suEFa.КАМЕНЬ;
        System.out.println("Выберите [К]амень, [Н]ожницы или [Б]магу");
        boolean badChoice = true;
        while (badChoice) {
            switch (consoleInput().toLowerCase()) {
                case "к", "камень" -> {
                    playersTurn = suEFa.КАМЕНЬ;
                    System.out.println("Вы выбрали камень!");
                    badChoice = false;
                }
                case "б", "бумага" -> {
                    playersTurn = suEFa.БУМАГА;
                    System.out.println("Вы выбрали бумагу!");
                    badChoice = false;
                }
                case "н", "ножницы" -> {
                    playersTurn = suEFa.НОЖНИЦЫ;
                    System.out.println("Вы выбрали ножницы!");
                    badChoice = false;
                }
                case "cheater mode on" -> {
                    cheaterMode=true;
                    System.out.println("CHEATER MODE ON");
                }
                case "cheater mode off" -> {
                    cheaterMode = false;
                    System.out.println("CHEATER MODE OFF");
                }
                case "cheater mode" -> {
                    System.out.println("CHEATER MODE " + cheaterMode);
                }
                default -> {
                    System.out.println("Неверный ввод! Выберите камень, ножницы или бумагу (к, н, или б)");
                }
            }
       /**     if ((input2.toLowerCase().equals("к")) || (input2.toLowerCase().equals("камень"))) {
                playersTurn = suEFa.КАМЕНЬ;
                System.out.println("Вы выбрали камень!");
                badChoice = false;
            } else {
                if ((input2.toLowerCase().equals("н")) || (input2.toLowerCase().equals("ножницы"))) {
                    playersTurn = suEFa.НОЖНИЦЫ;
                    System.out.println("Вы выбрали ножницы!");
                    badChoice = false;
                } else {
                    if ((input2.toLowerCase().equals("б")) || (input2.toLowerCase().equals("бумага"))) {
                        playersTurn = suEFa.БУМАГА;
                        System.out.println("Вы выбрали бумагу!");
                        badChoice = false;
                    } else {
                        if (input2.toLowerCase().equals("cheater mode on")) {
                            cheaterMode=true;
                            System.out.println("CHEATER MODE ON");
                        } else {
                            if (input2.toLowerCase().equals("cheater mode off")) {
                                cheaterMode = false;
                                System.out.println("CHEATER MODE OFF");
                            } else {
                                if (input2.toLowerCase().equals("cheater mode")) {
                                    System.out.println("CHEATER MODE " + cheaterMode);
                                }
                            }
                        }
                    }
                }
            }
            //System.out.println("Вы выбрали " + input2);
        **/
        }
        playersChoice=playersTurn;
        return playersTurn;
    }

    public static suEFa computerTurn() {
        suEFa compTurn = suEFa.НОЖНИЦЫ;
        if (cheaterMode) {
            switch (playersChoice) {
                case КАМЕНЬ -> {
                    compTurn = suEFa.БУМАГА;
                    System.out.println("Компьютер выбрал бумагу!");
                }
                case БУМАГА -> {
                    compTurn= suEFa.НОЖНИЦЫ;
                    System.out.println("Компьюетр выбрал ножницы!");
                }
                case НОЖНИЦЫ -> {
                    compTurn = suEFa.КАМЕНЬ;
                    System.out.println("Компьютер выбрал камень!");
                }
            }
        }
        else {
            double turn = Math.random();
            if (turn < 0.3) {
                compTurn = suEFa.БУМАГА;
                System.out.println("Компьютер выбрал бумагу!");
            } else {
                if (turn < 0.6) {
                    compTurn = suEFa.КАМЕНЬ;
                    System.out.println("Компьютер выбрал камень!");
                } else {
                    System.out.println("Компьюетр выбрал ножницы!");
                }
            }
        }
        return compTurn;
    }

    public static int whoIsWin (suEFa turn1, suEFa turn2) {
        int result=0;
        if (turn1==turn2) {
            return result;
        }
        switch (turn1) {
            case БУМАГА -> {
                if (turn2==suEFa.КАМЕНЬ) {
                    result=1;
                }
                else {
                    result=2;
                }
            }
            case КАМЕНЬ -> {
                if (turn2==suEFa.НОЖНИЦЫ) {
                    result=1;
                }
                else {
                    result=2;
                }
            }
            case НОЖНИЦЫ -> {
                if (turn2==suEFa.БУМАГА) {
                    result=1;
                }
                else {
                    result=2;
                }
            }
        }
        return result;
    }

    public static String consoleInput () {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
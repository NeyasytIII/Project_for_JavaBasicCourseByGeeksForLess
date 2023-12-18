package com.GeeksForLess.task;

import com.GeeksForLess.task.JDBCLogic.ByRootGetter;
import com.GeeksForLess.task.expressionsCheackers.ExpressionController;
import com.GeeksForLess.task.expressionsCheackers.RootChecker;
import com.GeeksForLess.task.expressionsCheackers.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MathAssistantApp {
    static {
        System.out.println("MathAssistantApp запущено...");
    }

    public static void main(String[] args) {

        boolean play = true;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Для пошуку рівнянь введіть - 1" + "\n" + "Для операцій з рівнянням введіть - 2");
                int ch = scanner.nextInt();

                scanner.nextLine();
                if (ch == 2) {
                    System.out.println("Введіть ваше рівняння(проміжки будуть видалені автоматично) --->>>>>");
                    String expression = scanner.nextLine().toLowerCase().replace(" ", "");
                    System.out.println("Якщо ви хочете перевірити ваше рівняння введіть - 1" +
                            "\n" + "Якщо хочете перевірити валідність кореня рівняння введіть - 2" +
                            "\n" + "Якщо хочете зберегти рівняння у БД введіть - 3"
                    );
                    int switcher = scanner.nextInt();
                    switch (switcher) {
                        case 1 -> {
                            if (Validator.expressionIsValid(expression)) {
                                System.out.println("Ваше рівняння - валідне");
                            } else {
                                System.out.println("Ваше рівняння - не правильне");
                            }

                        }
                        case 2 -> {
                            System.out.println("Введіть корінь рівняння ---->>>>");
                            int root = scanner.nextInt();
                            if (RootChecker.isExpressionRoot(expression, root)) {
                                System.out.println(root + " - є коренем рівняння " + expression);
                            } else {
                                System.out.println(root + " - не є коренем рівняння " + expression);
                            }
                        }
                        case 3 -> {
                            System.out.println("Для відправлення одного виразу введіть - 1"
                                    + "\n" + "Для відправлення виразу з коренем введіть -2");
                            int subSwitcher = scanner.nextInt();
                            if (subSwitcher == 1) {
                                ExpressionController.upload(expression);
                            } else if (subSwitcher == 2) {
                                System.out.println("Введіть корінь рівняння ---->>>>");
                                scanner.nextLine();
                                int root = scanner.nextInt();
                                ExpressionController.upload(expression);
                            }
                        }
                        default -> {
                            System.out.println("Введено не коректне значення, спробуйте ще раз");
                            continue;
                        }
                    }
                } else if (ch == 1) {
                    System.out.println("Для пошуку рівнянь за одним коренем введіть - 1"
                            + "\n" + "Для пошуку рівнянь за набором коренів введіть - 2"
                            + "\n" + "Для пошуку за усіма з введених коренів введіть - 3");
                    int subSwitcher = scanner.nextInt();
                    if (subSwitcher == 1) {
                        scanner.nextLine();
                        System.out.println("Введіть корінь ---->>>>");
                        int root = scanner.nextInt();
                        ByRootGetter.getByRootInDB(root);
                    } else if (subSwitcher == 2) {
                        scanner.nextLine();
                        System.out.println("Введіть набір чисел через ',' ---->>>>");
                        String roots;
                        roots = scanner.nextLine();
                        try {


                            ByRootGetter.getFromSetOfRoots(roots(roots));
                        } catch (NumberFormatException e) {
                            System.out.println("було введено не коректний символ");
                            continue;
                        }
                    } else if (subSwitcher == 3) {
                        scanner.nextLine();
                        System.out.println("Введіть корені через ',' ---->>>>");
                        String roots = scanner.nextLine();
                        try {
                            ByRootGetter.getByRoots(roots(roots));
                        } catch (NumberFormatException e) {
                            scanner.nextLine();
                            System.out.println("було введено не коректний символ");
                            continue;
                        }
                    }
                } else {
                    System.out.println("Введено не коректне значення, спробуйте ще раз");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("було введено не коректний символ");
                scanner.nextLine();
                continue;
            }
            System.out.println("Бажаєте продовжити роботу ?" +
                    "\n" + "так - довільне число" +
                    "\n" + "ні - 0");
            try {

                int res = scanner.nextInt();
                if (res == 0) {
                    play = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("було введено не коректний символ");
                scanner.nextLine();
                continue;
            }
        }
        while (play);
    }

    public static double[] roots(String stringOfRoots) {

        String[] values = stringOfRoots.split(",");

        double[] roots = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            roots[i] = Double.parseDouble(values[i]);
        }

        return roots;
    }
}



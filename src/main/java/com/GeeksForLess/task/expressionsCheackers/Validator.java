package com.GeeksForLess.task.expressionsCheackers;

public class Validator {


    // Перевірка коректності чисел у виразі
    private static boolean numsIsValid(String expression) {
        // Лічільник крапок у числі.
        int dotCounter = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '0' && isNumber(expression.charAt(i + 1))) { // Припустимо що "0" перед числом - теж є помилковим значенням.
                return false;
            }
            // Відразу виключаємо можливість того, що точка може стояти по за числом, у якості першого чи останнього елементу.
            if (i == 0 && expression.charAt(i) == '.') {
                return false;
            } else if (i == expression.length() - 1 && expression.charAt(i) == '.') {
                return false;
            } else if (expression.charAt(i) == '.' && (isNumber(expression.charAt(i - 1)) == false || isNumber(expression.charAt(i + 1)) == false)) {
                return false;
            }
            // Виловлюємо елементи що являють собою число
            if (isNumber(expression.charAt(i)) || expression.charAt(i) == '.') {
                if (expression.charAt(i) == '.') {
                    dotCounter++;
                }
                // У випадку, коли кількість крапок у числі перевищує 1 - ми відразу викидаємо "false".
                if (dotCounter > 1) {
                    return false;
                }
            }
            // Якщо ми вийшли з проміжку, на якому знаходится число, обнуляємо кількість крапок для можливого наступного застосування.
            else {
                dotCounter = 0;
            }

        }
        return true;
    }

    // Перевіряємо чи є символ - числом, користуючись Id символів.
    private static boolean isNumber(char num) {
        return num >= 48 && num <= 57;
    }

    // Перевірка відповідності між відкритими та закритими скобками.
    private static boolean bracketsIsValid(String expression) {
        // втановлені лічільники для порівняння кількості відкритих та закритих дужок.
        int open = 0;
        int close = 0;

        for (char el : expression.toCharArray()) {
            if (el == '(') {
                open++;
            }
            if (el == ')') {
                if (open <= close) {
                    return false; // випадок коли закритих скобок більше ніж відкритих - не можливий тому відразу повертаємо "false".
                }
                close++;
            }
            if (el == '=' && close != open) {
                return false;// якщо скобки не закриті до знаку "=" вираз є не коректним, повертаємо "false".
            }
        }
        return close == open;
    }


    //Перевірка валідності операторів та знаку "=" на коректність їх розташування
    private static boolean operatorsIsValid(String expression) {

        for (int i = 0; i < expression.length(); i++) {

            //перевіряємо виключення - якщо оператор знаходиться на початку виразу та не є знаком "-" виводимо "false"
            if (i == 0 && isNotMinus(expression.charAt(i))) {
                return false;
            }
            // перевіряємо виключення - якщо останній елемент виразу є оператором - виводимо "false".
            if (i == expression.length() - 1 && isOperator(expression.charAt(i))) {
                return false;
            }


            //  Якщо локальний символ є оператором, то перевіряємо наступний елемент.
            if (isOperator(expression.charAt(i))) {
                // У тому випадку, якщо наступний після оператора знак не "-" - відразу викидаємо помилку.
                if (isNotMinus(expression.charAt(i + 1))) {
                    return false;
                }

            }
        }
        // Якщо все пройшло за планом  - повертаємо "true".
        return true;
    }


    // Створимо метод який буде перевіряти чи не є наступний оператор "-"
    private static boolean isNotMinus(char operator) {
        return operator == '=' || operator == '+' || operator == '/' || operator == '*';
    }

    // Створимо метод для визначення того, чи є символ оператором чи "="
    private static boolean isOperator(char operator) {
        return operator == '=' || operator == '+' || operator == '/' || operator == '*' || operator == '-';
    }

    // Створимо загальний метод для визначення коректності усіх виразів одночасно.
    public static boolean expressionIsValid(String expression) {
        if (expression.isEmpty()){return false;}
        // щоб уникнути зайвих помилок ми спрощуємо формат нашого виразу
        expression = expression.toLowerCase().replaceAll(" ", "");
        for (char el : expression.toCharArray()) {
            if (validChar(el) != true) {
                return false;
            }
        }
        // повертаємо результат всіх перевірок.
        return operatorsIsValid(expression) && numsIsValid(expression) && bracketsIsValid(expression);
    }

    // Створимо метод для перевірки санкціонованості символу.
    private static boolean validChar(char el) {
        return isNumber(el) || isOperator(el) || el == 'x' || el == '(' || el == ')' || el == '.';
    }


}

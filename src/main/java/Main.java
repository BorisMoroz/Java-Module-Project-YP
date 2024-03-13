
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Guests guests = new Guests();
        CalculatorOfGoods calculator = new CalculatorOfGoods();
        int numberOfGuests = guests.inputNumberOfGuests();
        calculator.inputGoods();
        calculator.showGoods();
        calculator.showExpensePerGuest(numberOfGuests);
    }
}
class Guests {
    public int inputNumberOfGuests(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число гостей:");
        while (true){
            if (scanner.hasNextInt()) {
                int numberOfGuests = scanner.nextInt();
                if (numberOfGuests > 1){
                    return numberOfGuests;
                }
                else
                    showErrorMessage();
            }
            else {
                scanner.nextLine();
                showErrorMessage();
            }
        }
    }
    private void showErrorMessage(){
        System.out.println("Некорректное значение! Число гостей должно быть целым положительным числом больше 1. \nПопробуйте еще раз:");
    }
}

class CalculatorOfGoods {
    private double sumPrice = 0;
    private ArrayList<Good> goodsList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void inputGoods(){
        String name;
        double price;

        while(true) {
            System.out.println("Введите название товара и его стоимость:");
            name = scanner.next();
            price = inputCorrectPriceOfGood(name);
            goodsList.add(new Good(name, price));
            sumPrice = sumPrice + price;
            System.out.println("Товар " + name + " успешно добавлен в счет!");
            System.out.println("Добавить еще один товар?");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("завершить"))
                break;
        }
    }
    private double inputCorrectPriceOfGood(String name){
        double price;

        while (true) {
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine();
                if (price > 0)
                    return price;
                else {
                    showErrorMessage(name);
                }
            }
            else {
               scanner.nextLine();
                showErrorMessage(name);
            }
        }
    }
    private void showErrorMessage(String name){
        System.out.println("Некорректное значение стоимости! Стоимость должна быпь положительным числом в формате рубли.копейки \nПопробуйте еще раз:");
        System.out.print(name + " ------ ");
    }
    public void showGoods() {
        System.out.println("\nДобавленные товары:");
        for (Good good : goodsList) {
            System.out.println(good.name);
        }
    }
    public void showExpensePerGuest(int numberOfGuests){
        double expensePerGuest = sumPrice/numberOfGuests;
        FormatterOfNumber formatterOfNumber = new FormatterOfNumber();
        String formattedString = formatterOfNumber.formatDoubleToString(expensePerGuest);
        System.out.println(String.format("\nКаждый гость должен заплатить: %.2f %s",expensePerGuest, formattedString));
    }
}
class Good {
    String name;
    double price;
    public Good(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
class FormatterOfNumber {
    String formatDoubleToString(double value) {
        String formattedString = "";
        int intPart = (int) Math.floor(value);
        int valueToFormat = intPart % 100;
        if (valueToFormat >= 10 && valueToFormat <= 20)
            formattedString = "рублей";
        else {
            valueToFormat = intPart % 10;
            switch (valueToFormat) {
                case 1:
                    formattedString = "рубль";
                    break;
                case 2, 3, 4:
                    formattedString = "рубля";
                    break;
                case 0, 5, 6, 7, 8, 9:
                    formattedString = "рублей";
                    break;
            }
        }
        return formattedString;
    }
}

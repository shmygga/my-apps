import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите ФИО для проверки:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String regex = "([А-ЯЁ][а-яё]+(-[А-ЯЁ][а-яё]+)?)" +
                    "\\s" + "([А-ЯЁ][а-яё]+)" + "\\s" + "([А-ЯЁ][а-яё]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (input.matches(regex) && matcher.find()) {
                System.out.println("Фамилия: " + matcher.group(1) + "\n" +
                        "Имя: " + matcher.group(3) + "\n" +
                        "Отчество: " + matcher.group(4));
            }
            else {
                System.out.println("Введенная строка не является ФИО\n" +
                        "Пример: Иванов Иван Иванович");
            }
        }
    }

}
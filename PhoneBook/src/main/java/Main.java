import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final PhoneBook PHONE_BOOK = new PhoneBook();
    public static final Pattern PATTERN = Pattern.compile("([А-ЯЁ][а-яё]+)|(LIST)|(7\\d{10})");
    public static final String WRONG_FORMAT = "Неверный формат ввода";
    public static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {

        for (;;) {
            System.out.println("Введите номер, имя или команду:");
            String input = SCANNER.nextLine();
            Matcher matcher = PATTERN.matcher(input);
            if (!matcher.matches()) {
                System.out.println(WRONG_FORMAT);
                continue;
            }
            String name = matcher.group(1);
            String command = matcher.group(2);
            String phone = matcher.group(3);

            if (name != null) {
                if (!PHONE_BOOK.getContactByName(name).isEmpty()) {
                    System.out.println(PHONE_BOOK.getContactByName(name));
                }
                else {
                    for (;;) {
                        System.out.println("Такого имени в телефонной книге нет.\n" +
                                "Введите номер телефона для абонента " + "\"" + name + "\":");
                        String newPhone = SCANNER.nextLine();
                        if (checkName(name) && checkPhone(newPhone)) {
                            PHONE_BOOK.addContact(newPhone, name);
                            System.out.println("Контакт сохранен!");
                            break;
                        }
                        else {
                            System.out.println(WRONG_FORMAT);
                        }

                    }
                }
            }

            if (command != null) {
                System.out.println(PHONE_BOOK.getAllContacts());
            }
            if (phone != null) {
                if (!PHONE_BOOK.getContactByPhone(phone).isEmpty()) {
                    System.out.println(PHONE_BOOK.getContactByPhone(phone));
                }
                else {
                    for (;;) {
                        System.out.println("Такого номера нет в телефонной книге.\n" +
                                "Введите имя абонента для номера " + "\"" +  phone + "\":");
                        String newName = SCANNER.nextLine();
                        if (checkName(name) && checkPhone(newName)) {
                            PHONE_BOOK.addContact(phone, newName);
                            System.out.println("Контакт сохранен!");
                            break;
                        }
                        else {
                            System.out.println(WRONG_FORMAT);
                        }
                    }
                }
            }
        }

    }

    public static boolean checkName(String name) {
        String regex = "[А-ЯЁ][а-яё]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean checkPhone(String phone) {
        String regex = "7\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

}
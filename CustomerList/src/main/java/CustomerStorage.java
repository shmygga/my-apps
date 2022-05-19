import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IllegalArgumentException {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        try {
            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
            if (components.length != 4) {
                throw new IllegalArgumentException();
            }
            String emailRegex = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
            String numberRegex = "\\+7\\d{10}";
            if (!components[INDEX_EMAIL].matches(emailRegex) || !components[INDEX_PHONE].matches(numberRegex)) {
                throw new InvalidParameterException();
            }
        }
        catch (InvalidParameterException ex) {
            System.out.println("Wrong format. Correct format: \n" +
                    "\"Василий Петров vasily.petrov@gmail.com +79215637722\"");
            throw ex;
        }
        catch (IllegalArgumentException exception) {
            System.out.println("Components.length != 4");
            throw exception;
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
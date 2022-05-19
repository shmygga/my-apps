import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private final TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        if (checkName(name) && checkPhone(phone)) {
            phoneBook.put(phone, name);
        }

    }

    public String getContactByPhone(String phone) {
        if (phoneBook.containsKey(phone)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                return value + " - " + key;
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        Set<String> contact = new TreeSet<>();
        if (phoneBook.containsValue(name)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                contact.add(value + " - " + key);
                return contact;
            }
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        Set<String> contactSet = new TreeSet<>();
        if (!phoneBook.isEmpty()) {
            for (String name : phoneBook.values()) {
                StringBuilder result = new StringBuilder(name + " - ");
                for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                    if (name.equals(entry.getValue())) {
                        result.append(entry.getKey()).append(", ");
                    }
                }
                contactSet.add(result.substring(0, result.length() - 2));
            }
            return contactSet;
        }
        return new TreeSet<>();
    }

    public boolean checkPhone(String phone) {
        String regex = "7\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean checkName(String name) {
        String regex = "[А-ЯЁ][а-яё]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
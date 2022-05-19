import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите путь до копируемой папки:");
        Scanner scanner = new Scanner(System.in);
        String sourceDirectory = scanner.nextLine();
        System.out.println("Введите путь до папки назначения:");
        String destinationDirectory = scanner.nextLine();
        FileUtils.copyFolder(sourceDirectory, destinationDirectory);
    }
}
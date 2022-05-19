import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {

        File folder = new File(sourceDirectory);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    BasicFileAttributes basicFileAttributes =
                            Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    if (basicFileAttributes.isRegularFile() && file.exists()) {
                        Files.copy(file.toPath(),
                                new File(destinationDirectory +
                                        File.separator + file.getName()).toPath());
                    }
                    else if (basicFileAttributes.isDirectory() && file.exists()) {
                        Path directory = Files.createDirectory(
                                Path.of(destinationDirectory + File.separator + file.getName())
                        );
                        copyFolder(file.getAbsolutePath(), directory.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Вы ввели неверный путь или папка больше не существует");
        }
    }
}

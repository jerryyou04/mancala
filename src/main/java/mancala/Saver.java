package mancala;
import java.io.*;
import java.nio.file.*;

public class Saver {

    private static final String ASSETS_FOLDER_NAME = "assets";
    private static final Path ASSETS_FOLDER_PATH;

    static {
        // Initialize the assets folder path
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        ASSETS_FOLDER_PATH = currentDirectory.resolve(ASSETS_FOLDER_NAME);

        // Check if the assets folder exists; if not, create it
        try {
            if (Files.notExists(ASSETS_FOLDER_PATH)) {
                Files.createDirectory(ASSETS_FOLDER_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing assets folder", e);
        }
    }

    public static void saveObject(Serializable toSave, String filename) throws IOException {
        Path filePath = ASSETS_FOLDER_PATH.resolve(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            out.writeObject(toSave);
        }
    }

    public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
        Path filePath = ASSETS_FOLDER_PATH.resolve(filename);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Serializable) in.readObject();
        }
    }
}

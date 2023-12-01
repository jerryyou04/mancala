package mancala;
import java.io.*;
import java.nio.file.*;

public final class Saver {
    private static final String ASSETS = "assets";
    private static final Path ASSETSPATH;

    private Saver() {
        // This constructor will never be called
    }

    static {
        // Initialize the assets folder path
        final Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        ASSETSPATH = currentDirectory.resolve(ASSETS);

        // Check if the assets folder exists; if not, create it
        try {
            if (Files.notExists(ASSETSPATH)) {
                Files.createDirectory(ASSETSPATH);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error initializing assets folder", e);
        }
    }

    public static void saveObject(final Serializable toSave, final String filename) throws IOException {
        final Path filePath = ASSETSPATH.resolve(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            out.writeObject(toSave);
        }
    }

    public static Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        final Path filePath = ASSETSPATH.resolve(filename);
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Serializable) input.readObject();
        }
    }
}

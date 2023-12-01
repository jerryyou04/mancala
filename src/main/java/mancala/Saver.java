package mancala;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Provides static methods to save and load game objects to and from files.
 * This utility class manages file operations in the "assets" folder.
 */
public final class Saver {
    private static final String ASSETS = "assets";
    private static final Path ASSETSPATH;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
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
    /**
     * Saves a Serializable object to a file in the assets folder.
     *
     * @param toSave The Serializable object to save.
     * @param filename The name of the file to save the object in.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveObject(final Serializable toSave, final String filename) throws IOException {
        final Path filePath = ASSETSPATH.resolve(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            out.writeObject(toSave);
        }
    }

    /**
     * Loads a Serializable object from a file in the assets folder.
     *
     * @param filename The name of the file to load the object from.
     * @return The loaded Serializable object.
     * @throws IOException If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        final Path filePath = ASSETSPATH.resolve(filename);
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Serializable) input.readObject();
        }
    }

    /**
     * Gets the path to the assets folder.
     *
     * @return The Path object representing the assets folder.
     */
    public static Path getAssetsFolderPath() {
        return ASSETSPATH;
    }
}

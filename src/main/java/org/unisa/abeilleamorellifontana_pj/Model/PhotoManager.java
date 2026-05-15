package org.unisa.abeilleamorellifontana_pj.Model;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PhotoManager {
    public static final int MAX_PHOTO_COUNT = 10;

    public static boolean PhotosUpload(List<Part> fileParts, int id_product, String path) throws IOException {
        if (fileParts.size() > MAX_PHOTO_COUNT) {
            return false;
        }

        // Creazione cartella immagini del prodotto
        Path uploadPath = Paths.get(path, "product_images", String.valueOf(id_product));
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Caricamento immagini
        int counter = 1;
        for (Part part : fileParts) {
            String fileName = counter++ + ".png"; // Genera il nome del file
            Path filePath = uploadPath.resolve(fileName); // Costruisci il percorso completo del file
            try (InputStream inputStream = part.getInputStream()) {
                Files.copy(inputStream, filePath); // Usa Files.copy per una copia più sicura
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Ritorna false se si verifica un errore durante il salvataggio
            }
        }

        return true;
    }

    /**
     * Conta il numero di foto nella directory specificata.
     *
     * @param id_product Il percorso della directory delle foto/ id del prodotto
     * @return Il numero di foto nella directory
     */
    public static int countPhotosInDirectory(int id_product, String path) {
        int photoCount = 0;
        String directoryPath = path + File.separator + "product_images" + File.separator + id_product;
        File directory = new File(directoryPath);

        try {
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            photoCount++;
                        }
                    }
                }
            } else {
                System.out.println("La directory non esiste o non è una directory valida.");
            }
        } catch (SecurityException e) {
            System.out.println("Errore di sicurezza: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante il conteggio delle foto: " + e.getMessage());
        }

        return photoCount;
    }
}

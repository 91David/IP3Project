import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("SameParameterValue")
class Document {

    /**
     * Generates MD5 checksum from file.
     *
     * @param filename name of file to generate checksum for.
     * @return MD5 checksum as string
     */
    static String generateChecksum(String filename) {

        try {
            // Instantiating file and Hashing Algorithm.
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream file = new FileInputStream(filename);

            // Generation of checksum.
            byte[] dataBytes = new byte[1024];
            int nread;

            while ((nread = file.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();

            // Convert byte to hex.
            StringBuilder hexString = new StringBuilder();

            for (byte mdbyte : mdbytes) {
                String hex = Integer.toHexString(0xff & mdbyte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Return checksum as completed string.
            return hexString.toString();

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Downloads a file from DropBox
     *
     * @param client   DropBox connection instance
     * @param fileName Filename we want to download.
     * @return true if file is downloaded.
     */
    static Boolean downloadFile(DbxClientV2 client, String fileName) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            FileMetadata metadata = client.files().downloadBuilder("/" + fileName).download(outputStream);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Deletes a file from DropBox
     *
     * @param client   DropBox connection instance
     * @param fileName Filename we want to delete.
     * @return true if file is deleted.
     */
    static Boolean deleteFile(DbxClientV2 client, String fileName) {
        OutputStream outputStream = null;
        try {
            Metadata metadata = client.files().delete("/" + fileName);
        } catch (DbxException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Uploads a file to a specified folder path in DropBox.
     *
     * @param filename location and name of file you want to upload.
     * @param client   DB connection instance
     */
    static void uploadFile(String filename, DbxClientV2 client) {

        try (InputStream in = new FileInputStream(filename)) {

            // We can manipulate the path for the file to be saved in using this. StringBuilder to Filepath will do.
            FileMetadata metadata = client.files().uploadBuilder("/test.txt").uploadAndFinish(in);
            System.out.println("Successfully uploaded " + filename);

        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }

    }

    /**
     * View the contents of a specific folder in the DropBox repo.
     *
     * @param path   Folder path you want to access
     * @param client DB connection instance
     */
    static void viewFolder(String path, DbxClientV2 client) {

        ListFolderResult result = null;
        try {
            result = client.files().listFolder(path);
        } catch (DbxException e) {
            e.printStackTrace();
        }

        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            try {
                result = client.files().listFolderContinue(result.getCursor());
            } catch (DbxException e) {
                e.printStackTrace();
            }
        }
    }

}

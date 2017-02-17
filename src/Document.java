import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Document {

    /**
     * Generates MD5 checksum from file.
     *
     * @param filename
     * @return
     */
    public static String generateChecksum(String filename) {

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

            // TODO - convert byte to hex.


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

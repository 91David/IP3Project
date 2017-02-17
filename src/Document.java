import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Document {

    /**
     * Generates MD5 checksum from file.
     *
     * @param filename name of file to generate checksum for.
     * @return MD5 checksum as string
     */
    static String generateChecksum(@SuppressWarnings("SameParameterValue") String filename) {

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

}

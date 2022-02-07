import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class model {
    FileReader file;
    BufferedReader reader;
    Scanner scanner;

    public void encryptTextFile(File inputFile, File outputFile)  {
        String line = null;
        try {
            file = new FileReader(inputFile);
            reader = new BufferedReader(file);
            scanner = new Scanner(file);
            //line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Encrypting file " + inputFile + " \n and outputting to " + outputFile);
        int lineNumber = 0;

        while (scanner.hasNext()) {
            lineNumber++;
            System.out.println("Line " + lineNumber + " in txt file is = '" + scanner.nextLine() + "'");
        }
    }

    public SecretKey generateKey(int length) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    static String readFileString(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void testprint() {
        System.out.println("test");
    }
}

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

    public int[] keyToArray(String secretKeyString) {
        int[] secretKeyIntArray = new int[secretKeyString.length()];
        for (int i = 0; i < secretKeyString.length(); i++) {
            char currentChar = secretKeyString.charAt(i);
            int charAsInt = currentChar;
            secretKeyIntArray[i] = charAsInt;
        }
        return secretKeyIntArray;
    }

    public int[] stringToArray(String secretInputString) {
        int numberOfCharacters = 0;
        int numberOfInts = 0;
        int[] secretKeyIntArray = new int[secretInputString.length()];
        for (int i = 0; i < secretInputString.length(); i++) {
            if (secretInputString.charAt(i) == '1' || secretInputString.charAt(i) == '2' || secretInputString.charAt(i) == '3' || secretInputString.charAt(i) == '4' || secretInputString.charAt(i) == '5' || secretInputString.charAt(i) == '6' || secretInputString.charAt(i) == '7' || secretInputString.charAt(i) == '8' || secretInputString.charAt(i) == '9') {
                numberOfCharacters++;
            }
            if (secretInputString.charAt(i) == ' ') {
                String subString = secretInputString.substring(i-numberOfCharacters, i);
                subString = subString.strip();
                secretKeyIntArray[numberOfInts] = Integer.parseInt(subString);
                numberOfInts++;
                System.out.println("Found integer = " + subString + "!");
                numberOfCharacters = 0;
            }
            if (i == (secretInputString.length()-1)) {
                String subString = secretInputString.substring(i-numberOfCharacters, i+1);
                subString = subString.strip();
                secretKeyIntArray[numberOfInts] = Integer.parseInt(subString);
                numberOfInts++;
                System.out.println("Found integer = " + subString + "!");
                numberOfCharacters = 0;
            }
        }
        return secretKeyIntArray;
    }

    public void encryptTextFile(File inputFile, File outputFile, int[] secretKeyIntArray)  {
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
        int encryptionKeyCharPosition = 0;

        while (scanner.hasNext()) {
            System.out.println("Line " + lineNumber + " in txt file is = '" + scanner.nextLine() + "'");
            System.out.println("Line " + lineNumber + " in bin file is = '" + encrypt(scanner.nextLine(), (char) secretKeyIntArray[encryptionKeyCharPosition]) + "' \n");
            lineNumber++;
        }
    }

    public int[] encrypt(String text, char inputKey) {
        System.out.println("Encrypting char " + text);
        char key = inputKey;
        String crypt = "";
        int[] intArray = new int[text.length()];
        int[] cryptArray = new int[text.length()];

        for (int i = 0 ; i < text.length() ; i++) {
            intArray[i] = text.charAt(i);
            cryptArray[i] = (char)(intArray[i]^key);
        }

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

        System.out.println("");
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(cryptArray[i]);
        }

        return cryptArray;
    }


    public SecretKey generateKey() throws NoSuchAlgorithmException {
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

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
            if (secretInputString.charAt(i) == '1' || secretInputString.charAt(i) == '2' || secretInputString.charAt(i) == '3' || secretInputString.charAt(i) == '4' || secretInputString.charAt(i) == '5' || secretInputString.charAt(i) == '6' || secretInputString.charAt(i) == '7' || secretInputString.charAt(i) == '8' || secretInputString.charAt(i) == '9' || secretInputString.charAt(i) == '0') {
                numberOfCharacters++;
            }
            if (secretInputString.charAt(i) == ' ') {
                String subString = secretInputString.substring(i-numberOfCharacters, i);
                subString = subString.strip();
                secretKeyIntArray[numberOfInts] = Integer.parseInt(subString);
                numberOfInts++;
                //System.out.println("Found integer = " + subString + "!");
                numberOfCharacters = 0;
            }
            if (i == (secretInputString.length()-1)) {
                String subString = secretInputString.substring(i-numberOfCharacters, i+1);
                subString = subString.strip();
                secretKeyIntArray[numberOfInts] = Integer.parseInt(subString);
                numberOfInts++;
                //System.out.println("Found integer = " + subString + "!");
                numberOfCharacters = 0;
            }
        }
        System.out.println(numberOfInts);
        secretKeyIntArray[numberOfInts] = numberOfInts;

        return shrinkArray(secretKeyIntArray, numberOfInts+1);
    }

    public void encryptTextFile(File inputFile, File outputFile, int[] secretKeyIntArray) throws IOException {
        try {
            file = new FileReader(inputFile);
            reader = new BufferedReader(file);
            scanner = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Encrypting file " + inputFile + " \n and outputting to " + outputFile);
        int lineNumber = 0;
        int encryptionKeyCharPosition = 0;
        int[] currentLineEncryptedChars = new int[ readFileString(inputFile.getAbsolutePath(), StandardCharsets.UTF_8).length()/2];

        System.out.println("FILE TEXTLENGHT =" + readFileString(inputFile.getAbsolutePath(), StandardCharsets.UTF_8).length()/2 + "\n");

        while (scanner.hasNext()) {
            String currentLineText = scanner.nextLine();
            for (int i = 0; i < currentLineText.length(); i++) {
                System.out.println("Line " + lineNumber + " in txt file is = '" + currentLineText + "'");
                currentLineEncryptedChars[i] = encryptChar(currentLineText.charAt(i), (char) secretKeyIntArray[encryptionKeyCharPosition]);

                if (encryptionKeyCharPosition > 254) {
                    encryptionKeyCharPosition = 0;
                }
                else encryptionKeyCharPosition++;
            }
            lineNumber++;
        }
    }

    public int encryptChar(char character, char inputKey) {
        char key = inputKey;
        int encryptedChar;

        System.out.println("Encrypting " + character + " with the ASCII char nr" + (int)inputKey);
        System.out.println(addZeroPad(Integer.toBinaryString(character)) + "\n" + addZeroPad(Integer.toBinaryString(inputKey)));
        encryptedChar = (char)(character^key);
        System.out.println(addZeroPad(Integer.toBinaryString(encryptedChar)) + " was the result" + "\n");
        return encryptedChar;
    }


    public String generateKey() throws NoSuchAlgorithmException {
        String secretKey = "";
        int keyBitSizeInDecimal = 128;
        int keyLength = 256;
        for (int i = 0; i < keyLength; i++) {
            double randomValue = Math.floor(Math.random() * keyBitSizeInDecimal);
            secretKey += (int)randomValue + " ";
        }
        secretKey = secretKey.substring(0, secretKey.length() - 1);
        return secretKey;
    }

    public String readFileString(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public int[] shrinkArray(int[] array, int desiredSize) {
        int [] newArray = new int[desiredSize];

        for (int i = 0; i < desiredSize; i++) {
            newArray[i] = array[i];
        }
        System.out.println("Made a new array with length = " + desiredSize);
        return newArray;
    }

    public String addZeroPad(String input) {
        String zeros = "";
        int bitSize = 7;
        for (int i = 0; i < bitSize - input.length(); i++) {
            zeros += "0";
        }
        input = zeros + input;
        return input;
    }
}

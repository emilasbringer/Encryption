import java.io.*;
import java.util.Random;

/**
 * This is a class
 * Created 2019-09-17
 *
 * @author Magnus Silverdal
 */
public class main {

    public static void Encrypt(String text) {
        char key = '(';
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

    }

    public static void main(String[] args) {

        Encrypt("abcabc");

        //Encrypt("{MKZMEM[[IOM");
        //System.out.println(eightBitBinaryConverter(" "));
        //System.out.println(eightBitBinaryConverter("t"));



        String filenameTxt = "RandomNumbers.txt";
        String filenameBin = "RandomNumbers.bin";

        try {
            DataOutputStream binOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filenameBin)));
            PrintWriter txtOut = new PrintWriter(new BufferedOutputStream(new FileOutputStream(filenameTxt)));
            Random r = new Random();
            for (int i = 0 ; i < 1000 ; i++) {
                int randomNumber = r.nextInt();
                binOut.writeInt(randomNumber);
                txtOut.println(randomNumber);
            }
            binOut.flush();
            txtOut.flush();
            binOut.close();
            txtOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DataInputStream binIn = new DataInputStream(new BufferedInputStream(new FileInputStream(filenameBin)));
            int number;
            System.out.println("Binary data");
            for (int i = 0 ;  i < 1000 ; i++) {
                number = binIn.readInt();
                System.out.println(number);
            }
            System.out.println("Text data--------------");
            BufferedReader txtIn = new BufferedReader(new FileReader(filenameTxt));
            for (int i = 0 ; i < 1000 ; i++) {
                number = Integer.parseInt(txtIn.readLine());
                System.out.println(number);
            }
            binIn.close();
            txtIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
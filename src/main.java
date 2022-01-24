import java.util.ArrayList;

/**
 * This is a class
 * Created 2019-09-17
 *
 * @author Magnus Silverdal
 */
public class main {

    public static String XOR(String value1, String value2) {
        System.out.println(value1 + "\n" + value2);

        String output = "";
        for (int i = 0; i < value1.length(); i++) {
            boolean comparison = value1.charAt(i) == value2.charAt(i);
            if (comparison) {output += "0";}
            else if (!(comparison)) {output += "1";}
        }
        return output;
    }

    public static String eightBitBinaryConverter(String text) {
        String output = "";

        for (int i = 0 ; i < text.length() ; i++) {
            output += Integer.toBinaryString( text.charAt(i) );
        }

        while (output.length() < 8) {
            output = "0" + output;
        }

        return output;
    }


    public static void Encrypt(String text) {
        char key = '(';
        String crypt = "";

        for (int i = 0 ; i < text.length() ; i++) {
            System.out.println("Text.charAt(" + i + ") = " + eightBitBinaryConverter(Character.toString(text.charAt(i))));                                       // Convert text charAt(i) to ASCII binary
            System.out.println("Key.charAt(" + i + ") = " + eightBitBinaryConverter(Character.toString(key)));                                             // Concert key to binary
            System.out.println("XORed binary = " + XOR( eightBitBinaryConverter(text), eightBitBinaryConverter(Character.toString(key))));   //  XOR charAt(i) + key
        }
        crypt += XOR( eightBitBinaryConverter(text), eightBitBinaryConverter(Character.toString(key)));
        System.out.println("Crypt = " + crypt);
    }

    public static void main(String[] args) {


        Encrypt("Secret message");
        Encrypt("{MKZMEM[[IOM");


/*
        System.out.println("1011000");
        System.out.println("0101010");
        System.out.println(XOR("1011000", "0101010"));
*/
        /*
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
        }*/

    }

}
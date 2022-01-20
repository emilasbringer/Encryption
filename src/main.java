import java.util.ArrayList;

/**
 * This is a class
 * Created 2019-09-17
 *
 * @author Magnus Silverdal
 */
public class main {

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
            System.out.println(Integer.toBinaryString( text.charAt(i) ));                                       // Convert text charAt(i) to ASCII binary
            System.out.println("0" + Integer.toBinaryString(key) );                                             // Concert key to binary
            System.out.println(Integer.toBinaryString(text.charAt(i)^key) + "\n" + text.charAt(i) + "\n");   //  XOR charAt(i) + key
            crypt += (char)(text.charAt(i)^key);
        }
        System.out.println(crypt);
    }

    public static void main(String[] args) {


        Encrypt("Secret message");
        Encrypt("{MKZMEM[[IOM");
        System.out.println(eightBitBinaryConverter(" "));
        System.out.println(eightBitBinaryConverter("t"));

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
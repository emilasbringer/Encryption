import javax.swing.*;
import javax.swing.text.View;
import java.io.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class
 * Created 2019-09-17
 *
 * @author Magnus Silverdal
 */
public class controller {
    private view view;
    private model model;

    public controller() {
        view = new view();
        model = new model();

        //Encrypt("abcabc");

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\emila\\IdeaProjects\\Encryption");
        JFrame frame = new JFrame("Encryptor 6000 machine");
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,500);

        view.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.testprint();
            }
        });

        view.getSelectFileToEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int choosenFile = fileChooser.showOpenDialog(null);
               System.out.println(choosenFile);
            }
        });

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
                //System.out.println(number);
            }
            System.out.println("Text data--------------");
            BufferedReader txtIn = new BufferedReader(new FileReader(filenameTxt));
            for (int i = 0 ; i < 1000 ; i++) {
                number = Integer.parseInt(txtIn.readLine());
                //System.out.println(number);
            }
            binIn.close();
            txtIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int[] Encrypt(String text) {
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
        return cryptArray;
    }

    public static void main(String[] args) {controller mainController = new controller();}

}
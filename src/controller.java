import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
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
    private boolean fileSelected = false;
    private File outputFile = new File("C:\\Users\\emila\\IdeaProjects\\Encryption\\output.bin");
    private Font largeFont = new Font("SansSerif", Font.BOLD, 30);
    private int[] secretKeyIntArray;
    private String secretKeyIntString = "";
    private int numberOfInts;
    private boolean userInputKey = true;



    public controller() {
        view = new view();
        model = new model();

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\emila\\IdeaProjects\\Encryption");
        JFrame frame = new JFrame("Encryptor 6000 machine");
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
        view.getProgramTitle().setFont(largeFont);


        //Encrypt button
        view.getEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected && !userInputKey) {
                    try {
                        model.encryptTextFile(fileChooser.getSelectedFile(), outputFile, secretKeyIntArray);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    view.setEncryptionStatus("Encrypting with computer generated AES key");

                } else if (fileSelected) {
                    System.out.println(view.getEncryptionTextField().getText());
                    view.setEncryptionStatus("Encrypting with user input key");
                    secretKeyIntArray = model.stringToArray(view.getEncryptionTextField().getText());
                    System.out.println(secretKeyIntArray[0]);
                } else view.setEncryptionStatus("No file selected (⋋▂⋌)");
            }
        });

        //Select file to encrypt button
        view.getSelectFileToEncryptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(null);
                view.setSelectedFileText(fileChooser.getSelectedFile().getAbsolutePath());
                fileSelected = true;
            }
        });

        //Generate encryption key
        view.getGenerateKeyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secretKeyIntString = model.generateKey();
                    view.setEncryptionTextField(secretKeyIntString);
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Add zeropadtest 1010000 = " + model.addZeroPad("1010000"));
                userInputKey = false;
                secretKeyIntArray = model.stringToArray(secretKeyIntString);
                System.out.println("SecretKeyIntString as ASCII, length = " + secretKeyIntArray.length + "\n" + secretKeyIntString);
                numberOfInts = secretKeyIntArray[secretKeyIntArray.length-1];
                secretKeyIntArray = model.shrinkArray(secretKeyIntArray, secretKeyIntArray.length-1);
                System.out.println("The number of ints are = " + numberOfInts);
            }
        });

    }

    public static void main(String[] args) {controller c = new controller();}

}
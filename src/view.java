import javax.swing.*;

public class view {
    private JPanel encryptPanel;
    private JButton selectFileToEncryptButton;
    private JButton encryptButton;
    private JLabel selectedFile;
    private JTextField encryptionTextField;
    private JLabel key;
    private JLabel encryptionStatus;
    private JLabel programTitle;
    private JButton generateKeyButton;


    public JPanel getPanel() {
        return encryptPanel;
    }


    public JButton getSelectFileToEncryptButton() {return selectFileToEncryptButton;}

    public void setSelectedFileText(String text) {this.selectedFile.setText(text);}

    public void setEncryptionStatus(String text) {this.encryptionStatus.setText(text);}

    public void setEncryptionTextField(String text) {this.encryptionTextField.setText(text);}

    public JButton getEncryptButton() {return encryptButton;}

    public JLabel getKey() {return key;}

    public JPanel getEncryptPanel() {return encryptPanel;}

    public JTextField getEncryptionTextField() {return encryptionTextField;}

    public JLabel getProgramTitle() {return programTitle;}

    public JButton getGenerateKeyButton() {return generateKeyButton;}
}

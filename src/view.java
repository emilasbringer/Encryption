import javax.swing.*;

public class view {
    private JPanel encryptPanel;
    private JButton selectFileToEncryptButton;
    private JButton encryptButton;
    private JLabel selectedFile;



    public JPanel getPanel() {
        return encryptPanel;
    }

    public JButton getEncryptButton() {return encryptButton;}

    public JButton getSelectFileToEncryptButton() {return selectFileToEncryptButton;}

    public JLabel getSelectedFile() {return selectedFile;}

    public void setSelectedFileText(String text) {this.selectedFile.setText(text);}
}

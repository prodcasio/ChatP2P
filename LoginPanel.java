package chatbeta1.pkg0;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
    public static String username;
    LoginPanel(){
        setLayout(null);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(170, 120, 150, 30);
        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(170, 150, 150, 30);
        JButton login = new JButton("Login");
        login.addActionListener(e -> {
            username = usernameInput.getText();
            if(!(username==null) && (username.length()>1 && username.length()<20)){
                Client.loginFrame.dispose();
                try {
                    Client.createChatFrame();
                } catch (IOException ex) {
                    Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(username==null)
                    JOptionPane.showMessageDialog(null, "Devi inserire un username", "Errore", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "L'username deve essere compreso tra 1 e 20 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        
        });
        Client.loginFrame.getRootPane().setDefaultButton(login);
        login.setBounds(195, 200, 100, 30);
        add(usernameLabel);
        add(usernameInput);
        add(login);
        
    }
}

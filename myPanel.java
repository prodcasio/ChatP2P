package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class myPanel extends JPanel {

    public static ArrayList<OutputStream> osList = new ArrayList<>();
    public static ArrayList<PrintWriter> pwList = new ArrayList<>();
    public static ArrayList<Socket> socketList = new ArrayList<>();
    public static BufferedReader br;
    public String user1;
    public String user2;
    public static JTextArea chatOutput;
    public static JTextArea userDisponibili;
    private static Remoto r;

    myPanel() throws IOException {
        //setBackground();
        
        setLayout(null);
        chatOutput = new JTextArea();
        JScrollPane sp = new JScrollPane(chatOutput);
        chatOutput.setWrapStyleWord(true);
        sp.setBounds(50, 50, 600, 500);
        chatOutput.setEditable(false);

        JTextField chatInput = new JTextField();
        chatInput.setBounds(50, 550, 520, 50);

        JTextField userScelto = new JTextField();
        JButton send = new JButton("Invia");

        JLabel username = new JLabel("Sei connesso come: " + LoginPanel.username);
        username.setBounds(50, 20, 250, 30);

        send.addActionListener(e -> {
            if (!(chatInput.getText()).equals("")) {
                for (int i = 0; i < pwList.size(); i++) {
                    pwList.get(i).println(Bomber.username + ": " + chatInput.getText());
                    chatOutput.setText(chatOutput.getText() + "\n" + Bomber.username + ": " + chatInput.getText());
                    chatInput.setText("");
                }
            }
        });
        send.setBounds(570, 550, 80, 50);

        JButton connect = new JButton("Connetti");
        connect.addActionListener(e -> {
            r = new Remoto();
            r.start();
            if (userScelto.getText().isEmpty()) {
                for (int i = 0; i < UserList.userList.size(); i++) {
                    try {
                        socketList.add(new Socket(UserList.userList.get(i).get(0), 1234));
                        osList.add(socketList.get(i).getOutputStream());
                        pwList.add(new PrintWriter(osList.get(i), true));
                    } catch (IOException ex) {
                        Logger.getLogger(myPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            chatOutput.setText("Connesso alla chat pubblica.");
        });
        connect.setBounds(50, 700, 150, 50);

        JButton disconnect = new JButton("Disconnettiti");
        disconnect.addActionListener(e -> {
            disconnect();
        });
        userDisponibili = new JTextArea();
        userDisponibili.setBounds(725, 50, 200, 500);
        userDisponibili.setEditable(false);
        disconnect.setBounds(500, 700, 150, 50);
        Client.chatFrame.getRootPane().setDefaultButton(send);
        send.requestFocus();
        add(userDisponibili);
        userScelto.setBounds(725, 650, 200, 50);
        add(userScelto);
        add(sp);
        add(chatInput);
        add(send);
        add(connect);
        add(disconnect);
        add(username);
        Land l = new Land();
        l.start();
        MainBomber m = new MainBomber(LoginPanel.username);
        m.start();
    }

    public static void rimuoviUser(int i) {
        pwList.remove(i);
        osList.remove(i);
        socketList.remove(i);
    }

    public static void aggiornaUserDisponibili() {
        String contenuto = "";
        for (int i = 0; i < UserList.userList.size(); i++) {
            contenuto += UserList.userList.get(i).get(0) + "/" + UserList.userList.get(i).get(1) + "\n";
        }
        userDisponibili.setText(contenuto);
    }
    public static void disconnect(){
        for (int i = 0; i < UserList.userList.size(); i++) {
                try {
                    Socket socket = new Socket(UserList.userList.get(i).get(0), 1235);
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println("!" + IP.ip + "/" + Bomber.username);
                    socket.close();
                    socketList.get(i).close();
                    socketList.remove(i);
                    osList.remove(i);
                    pwList.remove(i);
                } catch (IOException ex) {
                    
                }
            }
            try {
                if(r!=null){
                    r.closeConnection();
                    r.interrupt();
                }
            } catch (IOException ex) {
                System.out.println("Errore chiusura socket server.");
            }
            chatOutput.setText(chatOutput.getText() + "\n\nTi sei disconnesso.");
    }
}

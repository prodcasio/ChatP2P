package chatbeta1.pkg0;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Client extends Thread {

    public static OutputStream os;
    public static PrintWriter pw;
    public static BufferedReader br;
    public static JFrame chatFrame;
    public static JFrame loginFrame;
    public static String version = "ChatBeta 1.0";

    public static void main(String[] args) throws IOException, InterruptedException {
        IP ip = new IP();
        createLoginFrame();
    }
    public static void createChatFrame() throws IOException{
        chatFrame = new JFrame(version + " - Chat");
        chatFrame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                try{
                    myPanel.disconnect();
                }catch(Exception e){}
            }
        });
        myPanel panel = new myPanel();
        chatFrame.add(panel);
        chatFrame.setSize(1000,1000);
        chatFrame.setVisible(true);
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void createLoginFrame() throws IOException{
        loginFrame = new JFrame(version  + " - Login");
        LoginPanel lpanel = new LoginPanel();
        loginFrame.add(lpanel);
        loginFrame.setSize(500,500);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

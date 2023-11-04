package chatbeta1.pkg0;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
    public static String ip;
    
    IP() throws UnknownHostException{
        if(InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[0].getHostAddress().startsWith("10."))
            ip = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[0].getHostAddress();
        else if(InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[1].getHostAddress().startsWith("10."))
            ip = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[1].getHostAddress();
        else if(InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[2].getHostAddress().startsWith("10."))
            ip = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())[2].getHostAddress();
    }
}

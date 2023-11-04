package chatbeta1.pkg0;

import java.util.ArrayList;

public class UserList {
    public static ArrayList<ArrayList<String>> userList = new ArrayList<>();
    
    UserList(){}
    
    public static void addUser(String stringa){
        userList.add(new ArrayList<>());
        userList.get(userList.size()-1).add(stringa.split("/")[0]);
        userList.get(userList.size()-1).add(stringa.split("/")[1]);
    }
    
}

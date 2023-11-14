public class Main{

    public static void main(String[] args){
        Username.username = "Mattia";
        MainBomber bomber = new MainBomber();
        bomber.start();
        Retriever retriever = new Retriever();
        retriever.start();
    }
}
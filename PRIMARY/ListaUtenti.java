package chatbeta1.pkg0;

public class ListaUtenti {
    public ArrayList<String> lista = new ArrayList<>();

    public static void stampaUtenti(){
        for (utente:lista) {
            System.out.println(utente);
        }
    }
}

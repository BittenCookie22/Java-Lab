import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Main {
    public static void main (String[] args) throws Exception{


        InputStreamReader wpis = new InputStreamReader(System.in);
        BufferedReader bufor = new BufferedReader(wpis);
        System.out.println("Podaj numer pesel");
        String pesel = bufor.readLine();
        // PESEL obj1 = new PESEL(pesel);// statycznej klasy nie można wywołac na konkretnym obiekcie

        boolean flaga =PESEL.check(pesel);
        if (flaga){
            System.out.println("Pesel OK");}
        else {
            System.out.println("Bledny PEsel");}

    }


}








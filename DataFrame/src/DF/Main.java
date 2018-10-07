package DF;

public class Main {
    public static void main (String[] args) {
        System.out.println("ok");
        DataFrame tmp = new DataFrame(new String[]{"z","g","r"},new String []{"y","h","u"});
        System.out.println(tmp.ilosc_wierszy);
        System.out.println(tmp.get("k"));
    }

}
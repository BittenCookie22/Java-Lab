package pkg2;

import pkg1.B;
// pola o dostępie pakietowym NIE są widziane w klasie pochodnej
// z POZA pakietu
public class C extends  B{
    //int nr = number;  <=  nie da się'

    void changeName(){
        String nowa_nazwa = "domyslna nowa nazwa z klasy C";
        this.name=nowa_nazwa;
        System.out.println(this.name);
    }
}

package pkg1;
// pola o dostępie pakietowym są widziane w klasie pochodnej
// z pakietu
public class B extends A {
    //String haha;
    //int numer = number;

    public B(){
        super();
    }

    protected void decrement (){
        number-=2;
        System.out.println(number);
    }

    private void increment(){
        number=number+2;
        System.out.println(number);
    }

    void changeName(){
        String nowa_nazwa = "domyslna nowa nazwa z klasy B";
        this.name=nowa_nazwa;
        System.out.println(this.name);
    }

}

package pkg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    int number;
    protected String name;

    public A (int number,String name){
        this.name=name;
        this.number=number;
    }

    private void increment(){
        number++;
        System.out.println(number);
    }

    protected void decrement(){
        number--;
        System.out.println(number);
    }

    public void callDecrement(){
        decrement();
    }

    public void callChangeName() throws IOException {
        changeName();
    }

    public void callIncrement(){
        increment();
    }

    void changeName() throws IOException {
        String nowa_nazwa = "domyslna nowa nazwa z klasy A";
        this.name=nowa_nazwa;
        System.out.println(this.name);
    }

    public A(){
        this.name="nic";
        this.number=0;
    }
}

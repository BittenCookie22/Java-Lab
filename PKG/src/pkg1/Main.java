package pkg1;
import pkg2.C;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        A a = new A();
        B b = new B();
        C c = new C();
        a.callChangeName();
        b.callChangeName();
        c.callChangeName();
       // a.callDecrement();
        //b.callDecrement();
       //c.callDecrement();
        System.out.println("-------------------");
        System.out.println(a.number);
        System.out.println(b.number);
        //System.out.println(c.number);
        System.out.println("-------------------");
      a.callIncrement();
      b.callIncrement();
      c.callIncrement();
    }
}

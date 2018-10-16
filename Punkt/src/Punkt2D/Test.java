package Punkt2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static LinkedList<Punkt3D> punkty;

    public static void main(String[] args) throws IOException {
        boolean flag =true;
        punkty = new LinkedList<Punkt3D>();
        while (flag) {
            System.out.println("1. Wczytaj punkt 3D");
            System.out.println("2. Wyświetl wszystkie punkty");
            System.out.println("3. Oblicz odległość");
            System.out.println("4. Zakończ");
            System.out.println("?");

            Scanner input = new Scanner(System.in);
            int opcja = input.nextInt();


            //System.out.println(opcja);
            switch (opcja) {
                case 1:
                    System.out.println("Wpisz wspolrzedna x:");
                    Scanner input1 = new Scanner(System.in);
                    int opcja1 = input1.nextInt();
                    System.out.println("Wpisz wspolrzedna y:");
                    Scanner input2 = new Scanner(System.in);
                    int opcja2 = input2.nextInt();
                    System.out.println("Wpisz wspolrzedna z:");
                    Scanner input3 = new Scanner(System.in);
                    int opcja3 = input3.nextInt();
                    Punkt2D pkt2D = new Punkt2D(opcja1, opcja2);
                    Punkt3D punkt = new Punkt3D(pkt2D, opcja3);

                    punkty.push(punkt);
                    break;

                case 2:
                    // punkty =  new LinkedList<Punkt3D>();
                    if (punkty.size() == 0) {
                        System.out.println("Brak punktow");
                        break;
                    } else {
                        for (Punkt3D pkt : punkty) {
                            System.out.println("(" + pkt.x + "," + pkt.y + "," + pkt.z + ")");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Wpisz wspolrzedna x:");
                    Scanner input11 = new Scanner(System.in);
                    int opcja11 = input11.nextInt();
                    System.out.println("Wpisz wspolrzedna y:");
                    Scanner input22 = new Scanner(System.in);
                    int opcja22 = input22.nextInt();
                    System.out.println("Wpisz wspolrzedna z:");
                    Scanner input33 = new Scanner(System.in);
                    int opcja33 = input33.nextInt();
                    Punkt2D pkt22D = new Punkt2D(opcja11, opcja22);
                    Punkt3D punkt2 = new Punkt3D(pkt22D, opcja33);
                    System.out.println(punkt2.distance(punkt2));
                    break;


                case 4:
                    System.out.println("Koniec");
                    flag=false;
                    break;

            }
        }


    }
    }



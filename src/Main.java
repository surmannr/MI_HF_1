import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int hossz;
    static int szelesseg;
    static int oszlopok_szama;
    static int raklapok_szama;
    static ArrayList<Raklap> raklapok = new ArrayList<>();
    static Raktar raktar;

    public static void main(String [ ] args) throws IOException {


        Scanner sc = new Scanner(System.in);

        // Raktar hossz es szelesseg beolvasas

        String[] adatok = sc.nextLine().split("\t");
        hossz = Integer.parseInt(adatok[0]);
        szelesseg = Integer.parseInt(adatok[1]);

        raktar = new Raktar(hossz,szelesseg);

        // Oszlopok szama

        String adat = sc.nextLine();
        oszlopok_szama = Integer.parseInt(adat);

        // Raklapok szama

        adat = sc.nextLine();
        raklapok_szama = Integer.parseInt(adat);

        // Oszlopok beolvasasa

        for (int i = 0; i<oszlopok_szama; i++){
            adatok = sc.nextLine().split("\t");
            int h = Integer.parseInt(adatok[0]);
            int sz = Integer.parseInt(adatok[1]);
            raktar.addOszlop(new Oszlop(h,sz));
        }

        //Raklapok beolvasasa

        for (int i = 0; i<raklapok_szama; i++){
            adatok = sc.nextLine().split("\t");
            int h = Integer.parseInt(adatok[0]);
            int sz = Integer.parseInt(adatok[1]);
            Raklap r = new Raklap(h,sz);
            r.forgatas();
            raklapok.add(r);
        }

        initHanyszorFerBele();

        while (raklapok_szama!=0){
            Raklap r = raktar.legkevesebbElhelyezes(raklapok);
            boolean megvan = raktar.hovaHelyezze(r);
            if(megvan)raktar.addRaklap(r);
            else {

            }
            initHanyszorFerBele();
        }
        raktar.megjelenit();
    }
    public static void initHanyszorFerBele(){
        for(int i = 0; i<raklapok_szama;i++){
            raklapok.get(i).elfer = raktar.hanyszorFerBele(raklapok.get(i));
        }
    }
}

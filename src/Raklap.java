/**
 * Raklap osztaly, ami a raktarban levo raklapokat reprezentalja.
 */
public class Raklap {

    /**
     * Minden raklap peldany egyedi ID-val rendelkezik.
     */
    public static int counter = 1;
    public int id;

    /**
     *  Bal felső sarkanak a pozicioja
     */
    public int k_hossz;
    public int k_szelesseg;

    /**
     *  A raklap szelesseget es hosszat tarolo attributumok.
     */
    public int hossz;
    public int szelesseg;

    /**
     * Hanyszor fer el a raktarban.
     */
    public int elfer;

    /**
     * Raklap osztaly konstruktora
     * @param _hossz             Beallitjuk a raklap hosszat.
     * @param _szelesseg         Beallitjuk a raklap szelesseget.
     */
    Raklap(int _hossz, int _szelesseg){
        hossz = _hossz;
        szelesseg = _szelesseg;
        k_hossz = 0;
        k_szelesseg = 0;
        id = counter;
        elfer = 0;
        counter++;
    }

    /**
     * Raklap elforgatasahoz szukseges fuggveny.
     */
    public void forgatas(){
        int temp = hossz;
        hossz = szelesseg;
        szelesseg = temp;
    }
}

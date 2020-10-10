import java.util.ArrayList;

/**
 *  Raktar osztaly, ami megvalositja magat a raktarat, ahova majd kerulnek a raklapok.
 *  Ketdimenzios megvalositasban.
 */
public class Raktar {

    /**
     *  A raktar szelesseget es magassagat tarolo attributumok.
     */
    public int hossz;
    public int szelesseg;

    public int[][] raktartomb;

    private ArrayList<Oszlop> oszlopok;
    private ArrayList<Raklap> raklapok;

    Raktar(int _hossz, int _szelesseg){
        hossz = _hossz;
        szelesseg = _szelesseg;
        oszlopok = new ArrayList<Oszlop>();
        raklapok = new ArrayList<Raklap>();
        raktartomb = new int[szelesseg][hossz];
    }

    public void addOszlop(Oszlop o){
        oszlopok.add(o);
    }

    public void addRaklap(Raklap r){
        raklapok.add(r);
        for(Raklap raklap : raklapok){
            for(int i = raklap.k_hossz; i < (raklap.k_hossz + raklap.hossz); i++){
                for(int j = raklap.k_szelesseg; j < ( raklap.k_szelesseg + raklap.szelesseg); j++){
                    raktartomb[j][i] = raklap.id;
                }
            }
        }
    }

    public void deleteRaklap(){
        Raklap raklap = raklapok.get(raklapok.size()-1);
        for(int i = raklap.k_hossz; i < (raklap.k_hossz + raklap.hossz); i++){
            for(int j = raklap.k_szelesseg; j < ( raklap.k_szelesseg + raklap.szelesseg); j++){
                raktartomb[j][i] = 0;
            }
        }
        raklapok.remove(raklapok.size()-1);
    }

    public void megjelenit(){
        for(int i = 0; i<hossz; i++){
            for(int j = 0; j<szelesseg; j++){
                System.out.print(raktartomb[j][i]);
                if(j != (szelesseg-1)) System.out.print('\t');
            }
            System.out.println();
        }
    }

    public boolean erintOszlopot(Raklap r){

        boolean felt1 = false;
        boolean felt2 = false;

        for(Oszlop oszlop : oszlopok){

            felt1 = false;
            felt2 = false;

            int max_szelesseg = r.k_szelesseg + r.szelesseg;
            int max_hossz = r.k_hossz + r.hossz;

            for(int i = r.k_hossz; i<max_hossz;i++){
                for(int j = r.k_szelesseg; j<max_szelesseg; j++){
                    if(i == oszlop.h1 && j == oszlop.sz1) felt1 = true;
                    if(i == oszlop.h2 && j == oszlop.sz2) felt2 = true;
                }
            }
            if(felt1 && felt2) return true;
        }
        return false;
    }

    public int hanyszorFerBele(Raklap r){
        int counter = 0;

        counter = getCounter(r, counter);

        if(r.szelesseg != r.hossz){
            r.forgatas();
            counter = getCounter(r, counter);
            r.forgatas();
        }

        return counter;
    }

    private int getCounter(Raklap r, int counter) {
        r.k_hossz = 0;
        for(int i = 0; i<= (hossz-r.hossz);i++){
            r.k_szelesseg=0;
            for (int j = 0; j<=(szelesseg-r.szelesseg);j++){
                if(!erintOszlopot(r)&&vanHely(r))counter++;
                r.k_szelesseg++;
            }
            r.k_hossz++;
        }
        r.k_szelesseg = 0;
        r.k_hossz = 0;
        return counter;
    }

    public boolean vanHely(Raklap r){
        for(int i = r.k_hossz; i<(r.hossz+r.k_hossz);i++){
            for (int j = r.k_szelesseg; j<(r.szelesseg+r.k_szelesseg);j++){
                if(raktartomb[j][i]!=0) return false;
            }
        }
        return true;
    }

    public Raklap legkevesebbElhelyezes(ArrayList<Raklap> list){
        Raklap r = list.get(0);
        for(int i = 1; i<list.size();i++){
            if(r.elfer>list.get(i).elfer) r = list.get(i);
        }
        list.remove(r);
        Main.raklapok_szama--;
        return r;
    }

    public boolean hovaHelyezze(Raklap r){
        if (ellenorzes(r)) return true;
        r.forgatas();
        if (ellenorzes(r)) return true;
        return false;
    }

    private boolean ellenorzes(Raklap r) {
        for(int i = 0; i<= (hossz-r.hossz);i++){
            for (int j = 0; j<=(szelesseg-r.szelesseg);j++){
                //System.out.println(i + " es " + j+ " nyoma");
                r.k_hossz = i;
                r.k_szelesseg = j;
                if(!erintOszlopot(r) && vanHely(r)){
                    //System.out.println(i + " a hossz es " + j+ " a szelessseg");
                    return true;
                }
            }
        }
        return false;
    }

}

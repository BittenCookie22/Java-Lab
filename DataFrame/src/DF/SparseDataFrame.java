package DF;

import DF.Values.Value;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SparseDataFrame extends DataFrame {

// ----------------COOValue - (immutable) przechowuje indeksy niezerowych elementów i ich wartość------------------
    final class COOValue extends Value {
        private final Value wartosc;
        private final int indeks;

         COOValue(Value wartosc, int indeks) {
            this.wartosc = wartosc;
            this.indeks = indeks;
        }
        public int zwrocIndeks() {
            return indeks;
        }

        public Object zwrocWartosc(){
            return wartosc;
        }


        public Value zwrocObiekt1(){
             return this.wartosc;
        }


    @Override
    public  Value getValue(){
        return wartosc;
    }


    @Override
    public String toString() {
        return "COOValue{" + "wartosc=" + wartosc + ", indeks=" + indeks + '}';
    }


    @Override
    public COOValue add(Value val)throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public COOValue sub(Value val)throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public COOValue mul(Value val)throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public COOValue div(Value val) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public COOValue pow(Value val) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean eq(Value val)throws UnsupportedOperationException{ //są równe jeśli mają te samą warotść na tym samym indeksie
             if(val instanceof COOValue){
                 if( ((COOValue)val).indeks==this.indeks && (((COOValue)val).wartosc).equals(this.wartosc)){return true;}
             }else{return false;}
             throw new UnsupportedOperationException();
    }


    @Override
    public boolean neq(Value val)throws UnsupportedOperationException{
        if(val instanceof COOValue){
            if( !((((COOValue)val).wartosc).equals(this.wartosc)  || ((COOValue)val).indeks==this.indeks)){return true;}
        }else{return false;}
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean lte(Value val)throws UnsupportedOperationException{ //this jest mniejsza od val jesli jej warotsc jest mniejsza
        if(val instanceof COOValue){

            return wartosc.lte(((COOValue) val).wartosc);
        }
        return wartosc.lte(val);
    }


    @Override
    public boolean gte(Value val)throws UnsupportedOperationException{
        if(val instanceof COOValue){
            if(isNumeric.isThisValueNumeric(this.wartosc) & isNumeric.isThisValueNumeric(val)){
                if(this.wartosc.gte(val)){return true;}
            }else{return false;}
        }
        throw new UnsupportedOperationException();
    }


    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof COOValue){
            COOValue otherCOO = (COOValue) other;
            if(this.wartosc.eq(((COOValue) other).wartosc) & this.indeks == ((COOValue) other).indeks){return true;}

        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(wartosc)*31 + Objects.hashCode(indeks)*7;
    }

    @Override
    public COOValue create(String s) throws UnsupportedOperationException{
             throw new UnsupportedOperationException();
    }
}






//----------------SparseKolumna - specjalna kolumna dla SprsDF, przechowuje info o tym jaki obiekt ukrywać (hide) i ilość wierszy w kolumnie---------------------------------------------------------------------------

    public class SparseKolumna extends Kolumna {
        Value hide;
        int ilosc_wierszy;

        public SparseKolumna(String nazwa, Class<? extends Value> typ, Value hide){
            super(nazwa,typ);
            this.hide=hide;
            ilosc_wierszy=0;
        }

        @Override
        public int size() {
            return ilosc_wierszy;
        }

        public SparseKolumna(SparseKolumna sprsKol){
            super(sprsKol);
            this.hide=sprsKol.hide;
            this.ilosc_wierszy=sprsKol.ilosc_wierszy;
        }

        @Override
        public void dodaj(Value element){
            boolean check = hide.equals(element);
            if (check==false){ //jesli obiekt nie jest hide to dodajemy do kolumny
                dane.add(new COOValue(element,ilosc_wierszy));
            }
            ilosc_wierszy++;
        }

        @Override
        public Value zwrocObiekt(int index){
            //binary search
            int iR=dane.size()-1;
            int iL=0;
            int t,i;
            if(dane.size()==0 && ilosc_wierszy>0)
                return hide;
            do{
                i = Math.floorDiv((iR+iL),2);
                t=((COOValue)(dane.get(i))).zwrocIndeks();

                if(t<index)
                    iL=i+1;
                else if(t>index)
                    iR=i-1;
                else
                    return ((COOValue)(dane.get(i))).getValue();

            }while(iL<=iR);

            return hide;
        }

    }

    //--------------Konstruktory SparseDataFrame------------------------------------

    public SparseDataFrame(){};

    public SparseDataFrame(String[] lista_nazw,  Class<? extends Value>[] lista_typow, Value []hide) {
        super(lista_nazw, lista_typow);
        for (int i=0;i<lista_typow.length;i++){
            kolumny[i]=new SparseKolumna(lista_nazw[i],lista_typow[i],hide[i]);
        }
    }

    public SparseDataFrame(SparseKolumna[] kolumny) {
        super(kolumny);
        ilosc_wierszy=kolumny[0].size();
    }

    public SparseDataFrame(DataFrame DF, Value[] hide) {
        super(DF.zwroc_nazwy(), DF.lista_typow);
        String []lista_kolumn = DF.zwroc_nazwy();
        Class<? extends Value> [] lista_typow = DF.lista_typow;
            for (int i = 0; i < DF.lista_typow.length; i++) {
                kolumny[i]=new SparseKolumna(lista_kolumn[i],lista_typow[i],hide[i]);
            }

        Value [] tablica_typow = new Value[kolumny.length];
            for (int i=0;i<DF.ilosc_wierszy;i++){
                int tmp=0;
                for (Kolumna kol:DF.kolumny){
                    tablica_typow[tmp++]=kol.zwrocObiekt(i);
                }
                dodajElement(tablica_typow);

            }
    }



//--------------------ToDense - zwraca DataFrame (konwertuje SparseDataFrame do DataFrame)------------------------------------------------------------

    public DataFrame toDense(SparseDataFrame SPD){
        String []nazwy = new String[SPD.kolumny.length];
        Class<? extends Value> [] typy = new  Class[SPD.kolumny.length];

        nazwy = SPD.zwroc_nazwy();
        typy = SPD.zwroc_typy();

        DataFrame df =  new DataFrame(nazwy,typy);

        for(int i=0;i<size();i++)
        {
            df.dodajElement(zwrocWiersz(i));
        }
        return df;
    }
// ------------------------Implementacja metod z DataFrame------------------------

    @Override
    public int size() {
        return ilosc_wierszy;
    }

    @Override
    public SparseKolumna get(String colname) {//zwracającą kolumnę o podanej nazwie
        for (Kolumna i : this.kolumny) {
            if (i.nazwa.equals(colname)) {
                return (SparseKolumna)i;
            }
        }
        throw new NoSuchElementException("Nie ma takiej kolumny" + " " + colname + "w tej Ramce Danych");

    }

    @Override
    public SparseDataFrame get(String[] cols, boolean copy) {
        SparseKolumna[] kolumny = new SparseKolumna[cols.length];

        for(int i=0;i<cols.length;i++){
            if(copy){
                kolumny[i]=(new SparseKolumna(get(cols[i]) ));}
            else{
                kolumny[i]= get(cols[i]);}
        }
        return new SparseDataFrame(kolumny);
    }
    @Override
    public SparseDataFrame iloc(int i) {
        return iloc(i,i);
    }

    @Override
    public SparseDataFrame iloc(int from, int to) {
        if (from<0 || from >= ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + from);
        }

        if(to<0 || to>=ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + to);
        }

        if(to<from){
            throw new IndexOutOfBoundsException("Niepoprawny zakres");
        }
        String[] nazwy = new String[kolumny.length];
        Class<? extends Value>[] typy = (Class<? extends Value>[] )new Class[kolumny.length];
        Value[] hide= new Value[kolumny.length];

        for(int i=0;i<kolumny.length;i++) {
            nazwy[i] =kolumny[i].nazwa;
            typy[i] = kolumny[i].typ;
            hide[i] = ((SparseKolumna)kolumny[i]).hide;
        }

        SparseDataFrame df=new SparseDataFrame(nazwy,typy,hide);
        for(int i=from;i<=to;i++){
            df.dodajElement(zwrocWiersz(i));
        }

        return df;
    }

    //---czyteanie z pliku KONSTRUKTORY---------
    public SparseDataFrame(String path, Class<? extends Value>[] typy_kolumn,Value[] hide) throws IOException {
        this(path,typy_kolumn,null,hide); //nazwy kolumn są zawarte w 1szej linii pliku

    }

    public SparseDataFrame(String path, Class<? extends Value>[] typy_kolumn, String[]nazwy_kolumn,Value[]hide) throws IOException { // header==false
        super(typy_kolumn.length);
        boolean header = nazwy_kolumn==null;
        for(int i=0;i<typy_kolumn.length;i++)
            kolumny[i]=new SparseKolumna(header? "" : nazwy_kolumn[i],typy_kolumn[i],hide[i]);
        readingFromFileFunction(path,header);


    }
    //---------------------------------------------------------------------------------------------------------------------------------------
    }



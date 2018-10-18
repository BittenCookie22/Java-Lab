package DF;
import java.lang.*;

public enum TmpTypDanych {
    INT("int"), DOUBLE("double"), FLOAT("float"), STRING("string"), NIEZNANY("nieznany");

    final String nazwa_typu;

    TmpTypDanych(final String nazwa_typu) {
        this.nazwa_typu = nazwa_typu;
    }

    public static Object konewrsjaDoDanegoTypuZeStringa(TmpTypDanych typ, String nazwa) {
        switch (typ) {
            case INT:
                return Integer.parseInt(nazwa);
            case DOUBLE:
                return Double.parseDouble(nazwa);
            case FLOAT:
                return Float.parseFloat(nazwa);
            case STRING:
                return nazwa;
            case NIEZNANY:
                return nazwa;
            default:
                return nazwa;
        }
    }

//   zwraca typ danych w formacie TmpTypDanych z podanego w argumencie stringa
    public static TmpTypDanych zwrocTypDanej(String nazwa) {
        for (TmpTypDanych tmp : TmpTypDanych.values()) {
            if (tmp.nazwa_typu.equals(nazwa)) {
                return tmp;
            }
        }
        return NIEZNANY;
    }
}

public class PESEL {
    public String nr_pesel;

    public PESEL(String peselik) {
        nr_pesel = peselik;
    }

    public static boolean check(String pesel) {
        if (pesel.length() == 11) {

            int[] pesel_tab = new int[11];
            for (int i = 0; i <= pesel_tab.length - 1; i++) {
                char tmp = pesel.charAt(i);   // przypisanie pojedynczego char
                int tmp_digit = Character.digit(tmp, 10); //  zmiana char na int
                pesel_tab[i] = tmp_digit; // dodanie int do tablicy
            }
            //System.out.println(pesel_tab[10]);

            int[] tab_control = new int[]{9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
            int check_sum = 0;
            for (int j = 0; j < 10; j++) {
                check_sum += pesel_tab[j] * tab_control[j];
            }


            if (check_sum % 10 == pesel_tab[pesel_tab.length - 1]) {
                return true;
            } else {
                return false;
            }

        }
        else{return false;}
    }


}

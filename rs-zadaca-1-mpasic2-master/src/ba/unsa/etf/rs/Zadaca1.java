package ba.unsa.etf.rs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Zadaca1 {


    public static int sumaCifara(int a) {
        int s = 0;
        while (a != 0) {
            s = s + a % 10;
            a = a / 10;
        }
        return s;
    }

    public static int najvecaSuma(int... x) {
        ArrayList<Integer> clanovi = new ArrayList<>();
        for (int i : x) {
            clanovi.add(i);
        }
        int max = clanovi.get(0);
        for (int i = 0; i < clanovi.size(); i++) {
            if (sumaCifara(clanovi.get(i)) > sumaCifara(max))
                max = clanovi.get(i);
        }
        return max;
    }


    public static String najveciGrad(String ulaz) {
        String[] gradovi = ulaz.split("\n");
        String ispis = gradovi[0];


        String[] pod = gradovi[0].split(",");

        if (pod[2].startsWith(" ")) pod[2] = pod[2].replace(" ", "");
        String max = pod[2];
        String maxGrad = pod[0];
        String maxDrzava = pod[1];

        for (int j = 0; j < gradovi.length; j++) {
            String[] opis = gradovi[j].split(",");

            opis[2] = opis[2].replace(" ", "");

            if (Integer.parseInt(opis[2]) > Integer.parseInt(max)) {
                max = opis[2];
                maxGrad = opis[0];
                maxDrzava = opis[1];
            }

        }
        System.out.println(maxGrad);
        ispis = maxDrzava;

        return ispis;

    }


    public static double[] najmanjaSrednjaVrijednost(double[][] matrica) {
        double pomSuma = 0;
        double suma = 0;
        double pomocna = 0;
        double[] minimalniRed = new double[0];
        double[] redovi;

        for (int i = 0; i < matrica.length; i++) {
            suma = 0;
            int j;
            redovi = new double[matrica[i].length];
            for (j = 0; j < matrica[i].length; j++) {
                suma += matrica[i][j];
                redovi[j] = matrica[i][j];
            }
            pomSuma = suma / j;

            if (i == 0 || pomocna>pomSuma) {
                pomocna = pomSuma;
                minimalniRed = redovi;
            }

        }
        return minimalniRed;

    }


    public static void main(String[] strings) {

        System.out.printf("Unesite string sa gradovima (prazan red za kraj): ");
        Scanner ulaz = new Scanner(System.in);
        String Grad = "";
        StringBuilder Gradovi = new StringBuilder();
        do {
            Grad = "";
            Grad = ulaz.nextLine();
            Gradovi.append(Grad + "\n");
        }
        while (!Objects.equals(Grad,""));
        System.out.println(Zadaca1.najveciGrad(Gradovi.toString()));

        System.out.printf("Unesite članove niza cijelih brojeva (0 za kraj): ");
        int[] niz = new int [100];
        for(int i=0;i<100;i++){
            int elementi = ulaz.nextInt();
            if(elementi==0) break;

            niz[i] = elementi;
        }
        System.out.printf("Broj sa najvećom sumom cifara je: ");
        System.out.println(Zadaca1.najvecaSuma(niz));


        System.out.printf("Unesite broj redova matrice: ");
        int n = ulaz.nextInt();
        double[][] matrica = new double[][]{};

        for(int i=0;i<n;i++){
            System.out.printf("Unesite broj elemenata u " + (i+1) +". redu: ");
            int brElemenata = ulaz.nextInt();
            matrica = Arrays.copyOf(matrica,matrica.length+1);
            double[] niz1 = new double[brElemenata];
            System.out.printf("Unesite elemente: ");
            for(int j=0;j<n;j++){
                niz1[j] = Double.parseDouble(ulaz.next());
            }
            matrica[i] = niz1;

        }

        double[] izlaz = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        System.out.printf("Red sa najmanjom srednjom vrijednošću glasi:\n");
        for(double x : izlaz)
            if(x>((int) x))
                System.out.printf(x+" ");
        else
            System.out.printf((int)x + " ");

    }
}
package ba.unsa.etf.rs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Zadaca1Test {
    @Test
    public void najvecaSuma1Test() {
        assertEquals(1, Zadaca1.najvecaSuma(1));
    }

    @Test
    public void najvecaSuma2Test() {
        assertEquals(9, Zadaca1.najvecaSuma(123,9,34));
    }

    @Test
    public void najvecaSuma3Test() {
        assertEquals(300, Zadaca1.najvecaSuma(100000,200,10,10000,2000,300,1,20000));
    }

    @Test
    public void najvecaSuma4Test() {
        assertEquals(539, Zadaca1.najvecaSuma(539,168,20225,157,755,11121));
    }

    @Test
    public void najvecaSuma5Test() {
        assertEquals(952, Zadaca1.najvecaSuma(252,253,254,255,652,257,952));
    }

    @Test
    public void nsvTest() {
        double[][] matrica = {{9,8,7},{3,2},{5}};
        double[] red = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        assertEquals(2, red.length);
        assertEquals(3, red[0]);
        assertEquals(2, red[1]);
    }

    @Test
    public void nsv2Test() {
        double[][] matrica = {{1}};
        double[] red = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        assertEquals(1, red.length);
        assertEquals(1, red[0]);
    }

    @Test
    public void nsv3Test() {
        double[][] matrica = new double[100][100];
        for (int i=0; i<100; i++)
            for (int j=0; j<100; j++)
                matrica[i][j] = -0.1;
        matrica[50][50] = -0.2;
        double[] red = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        assertEquals(-0.2, red[50]);
    }

    @Test
    public void nsv4Test() {
        double[][] matrica = new double[100][100];
        for (int i=0; i<100; i++)
            for (int j=0; j<100; j++)
                matrica[i][j] = j-i;
        double[] red = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        assertEquals(-99, red[0]);
        assertEquals(-98, red[1]);
        assertEquals(-97, red[2]);
    }

    @Test
    public void nsv5Test() {
        double[][] matrica = {{1,2,3}, {1,2,3}, {1,2,3,1}, {1,2,3}, {1,2,3}};
        double[] red = Zadaca1.najmanjaSrednjaVrijednost(matrica);
        assertEquals(4, red.length);
        assertEquals(1, red[3]);
    }
}
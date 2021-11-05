package ba.unsa.etf.rs;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class StdIOTest {
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void najveciGradTest() {
        String ulaz = "Sarajevo,Bosna i Hercegovina,350000\n" +
                "Pariz,Francuska,2141000\n" +
                "Washington,Sjedinjene Američke Države, 700000";
        String drzava = Zadaca1.najveciGrad(ulaz);
        assertEquals("Francuska", drzava);
        assertEquals("Pariz", outContent.toString().trim());
    }

    @Test
    public void najveci1GradTest() {
        String ulaz = "Sarajevo,Bosna i Hercegovina,1";
        String drzava = Zadaca1.najveciGrad(ulaz);
        assertEquals("Bosna i Hercegovina", drzava);
        assertEquals("Sarajevo", outContent.toString().trim());
    }

    @Test
    public void najveciPosljednjiGradTest() {
        String ulaz = "Sarajevo1,Bosna i Hercegovina1,350000\n" +
                "Sarajevo2,Bosna i Hercegovina2,350001\n" +
                "Sarajevo3,Bosna i Hercegovina3,350002\n" +
                "Sarajevo4,Bosna i Hercegovina4,350003";
        String drzava = Zadaca1.najveciGrad(ulaz);
        assertEquals("Bosna i Hercegovina4", drzava);
        assertEquals("Sarajevo4", outContent.toString().trim());
    }

    @Test
    public void najveciPrviGradTest() {
        String ulaz = "Sarajevo1,Bosna i Hercegovina1,350000\n" +
                "Sarajevo2,Bosna i Hercegovina2,349999\n" +
                "Sarajevo3,Bosna i Hercegovina3,349998\n" +
                "Sarajevo4,Bosna i Hercegovina4,349997";
        String drzava = Zadaca1.najveciGrad(ulaz);
        assertEquals("Bosna i Hercegovina1", drzava);
        assertEquals("Sarajevo1", outContent.toString().trim());
    }

    @Test
    public void najveciPunoGradovaTest() {
        String ulaz = "";
        for (int i=0; i<500; i++)
            ulaz = ulaz + "Grad" + i + ",Drzava" + i + "," + (100000 + i*100) + "\n";
        for (int i=500; i<1000; i++)
            ulaz = ulaz + "Grad" + i + ",Drzava" + i + "," + (100000 - i*100) + "\n";
        String drzava = Zadaca1.najveciGrad(ulaz);
        assertEquals("Drzava499", drzava);
        assertEquals("Grad499", outContent.toString().trim());
    }

    @Test
    public void mainTypicalTest() {
        String input = "Sarajevo,Bosna i Hercegovina,350000\n" +
                "Pariz,Francuska,2141000\n" +
                "Washington,Sjedinjene Američke Države, 700000\n" +
                "\n" +
                "123 9 34 0\n" +
                "3\n" +
                "3\n" +
                "9 8 7\n" +
                "2\n" +
                "3 2\n" +
                "1\n" + "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Zadaca1.main(new String[0]);
        String expectedOutput = "Unesite string sa gradovima (prazan red za kraj): Pariz\n" +
                "Francuska\n" +
                "Unesite članove niza cijelih brojeva (0 za kraj): Broj sa najvećom sumom cifara je: 9\n" +
                "Unesite broj redova matrice: Unesite broj elemenata u 1. redu: Unesite elemente: Unesite broj elemenata u 2. redu: Unesite elemente: Unesite broj elemenata u 3. redu: Unesite elemente: Red sa najmanjom srednjom vrijednošću glasi:\n" +
                "3 2";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void mainSimpleTest() {
        String input = "Sarajevo,Bosna i Hercegovina,350000\n" +
                "\n" +
                "1 0\n" +
                "1\n" +
                "1\n" +
                "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Zadaca1.main(new String[0]);
        String expectedOutput = "Unesite string sa gradovima (prazan red za kraj): Sarajevo\n" +
                "Bosna i Hercegovina\n" +
                "Unesite članove niza cijelih brojeva (0 za kraj): Broj sa najvećom sumom cifara je: 1\n" +
                "Unesite broj redova matrice: Unesite broj elemenata u 1. redu: Unesite elemente: Red sa najmanjom srednjom vrijednošću glasi:\n" +
                "1";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void mainStuffTest1() {
        String input = "Sarajevo1,Bosna i Hercegovina1,350000\n" +
                "Sarajevo2,Bosna i Hercegovina2,350001\n" +
                "Sarajevo3,Bosna i Hercegovina3,350002\n" +
                "Sarajevo4,Bosna i Hercegovina4,350003\n" +
                "\n" +
                "100000 200 10 10000 2000 300 1 20000 0\n" +
                "5\n" +
                "3\n" +
                "1 2 3\n" +
                "3\n" +
                "1 2 3\n" +
                "4\n" +
                "1 2 3 1\n" +
                "3\n" +
                "1 2 3\n" +
                "3\n" +
                "1 2 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Zadaca1.main(new String[0]);
        String expectedOutput = "Unesite string sa gradovima (prazan red za kraj): Sarajevo4\n" +
                "Bosna i Hercegovina4\n" +
                "Unesite članove niza cijelih brojeva (0 za kraj): Broj sa najvećom sumom cifara je: 300\n" +
                "Unesite broj redova matrice: Unesite broj elemenata u 1. redu: Unesite elemente: Unesite broj elemenata u 2. redu: Unesite elemente: Unesite broj elemenata u 3. redu: Unesite elemente: Unesite broj elemenata u 4. redu: Unesite elemente: Unesite broj elemenata u 5. redu: Unesite elemente: Red sa najmanjom srednjom vrijednošću glasi:\n" +
                "1 2 3 1";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void mainStuffTest2() {
        String input = "Grad1,Drzava1,3\n" +
                "Grad2,Drzava2,2\n" +
                "Grad3,Drzava3,1\n" +
                "\n" +
                "539 168 20225 157 755 11121 0\n" +
                "3\n" +
                "3\n" +
                "-0.1 -0.1 -0.1\n" +
                "3\n" +
                "-0.2 -0.1 -0.1\n" +
                "3\n" +
                "-0.1 -0.1 -0.1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Zadaca1.main(new String[0]);
        String expectedOutput = "Unesite string sa gradovima (prazan red za kraj): Grad1\n" +
                "Drzava1\n" +
                "Unesite članove niza cijelih brojeva (0 za kraj): Broj sa najvećom sumom cifara je: 539\n" +
                "Unesite broj redova matrice: Unesite broj elemenata u 1. redu: Unesite elemente: Unesite broj elemenata u 2. redu: Unesite elemente: Unesite broj elemenata u 3. redu: Unesite elemente: Red sa najmanjom srednjom vrijednošću glasi:\n" +
                "-0.2 -0.1 -0.1";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void mainStuffTest3() {
        String input = "G,D,1\n" +
                "\n" +
                "22 12 12 21 0\n" +
                "3\n" +
                "1\n" +
                "1.2\n" +
                "2\n" +
                "1.2 1.1\n" +
                "3\n" +
                "1.2 1.1 1.2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Zadaca1.main(new String[0]);
        String expectedOutput = "Unesite string sa gradovima (prazan red za kraj): G\n" +
                "D\n" +
                "Unesite članove niza cijelih brojeva (0 za kraj): Broj sa najvećom sumom cifara je: 22\n" +
                "Unesite broj redova matrice: Unesite broj elemenata u 1. redu: Unesite elemente: Unesite broj elemenata u 2. redu: Unesite elemente: Unesite broj elemenata u 3. redu: Unesite elemente: Red sa najmanjom srednjom vrijednošću glasi:\n" +
                "1.2 1.1";
        assertEquals(expectedOutput, outContent.toString().trim());
    }
}

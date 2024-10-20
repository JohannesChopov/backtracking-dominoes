package be.domino;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoritmeTest {

    @Test //IsVeilig testen (gebruikt alle 4 de testen)
    public void testIsVeilig() {
        testIsVeiligVoor2DominosMetZelfdeKleur_zouFalseMoetenGeven();
        testIsVeiligVoor2DominosMetOngelijkeNummers_zouFalseMoetenGeven();
        testIsVeiligVoor2DominosMetEersteNummerPassend_zouTrueMoetenGeven();
        testIsVeiligVoor2DominosMetTweedeNummerPassend_zouTrueMoetenGeven();
    }
    //@Test
    public void testIsVeiligVoor2DominosMetZelfdeKleur_zouFalseMoetenGeven() {
        Algoritme algo = new Algoritme();
        Steen huidig = new Steen(1,5,'g');
        Steen volgende = new Steen(5,2,'g');
        Boolean result = algo.isVeilig(huidig,volgende);
        assertFalse(result);
    }
    //@Test
    public void testIsVeiligVoor2DominosMetOngelijkeNummers_zouFalseMoetenGeven() {
        Algoritme algo = new Algoritme();
        Steen huidig = new Steen(1,5,'g');
        Steen volgende = new Steen(4,2,'b');
        Boolean result = algo.isVeilig(huidig,volgende);
        assertFalse(result);
    }
    //@Test
    public void testIsVeiligVoor2DominosMetEersteNummerPassend_zouTrueMoetenGeven() {
        Algoritme algo = new Algoritme();
        Steen huidig = new Steen(1,5,'g');
        Steen volgende = new Steen(5,2,'b');
        Boolean result = algo.isVeilig(huidig,volgende);
        assertTrue(result);
    }
    //@Test
    public void testIsVeiligVoor2DominosMetTweedeNummerPassend_zouTrueMoetenGeven() {
        Algoritme algo = new Algoritme();
        Steen huidig = new Steen(1,5,'g');
        Steen volgende = new Steen(2,5,'b');
        Boolean result = algo.isVeilig(huidig,volgende);
        assertTrue(result);
    }


    @Test //checkLengte testen (gebruikt alle 3 de testen)
    public void TestCheckLengte() {
        testIsCheckLengteVoorEenArrayVan8Met2StenenGelijkAan00B_zou6MoetenTeruggeven();
        testIsCheckLengteVoorEenArrayVan8Met8StenenGelijkAan00B_zou0MoetenTeruggeven();
        testIsCheckLengteVoorEenArrayVan8Met0StenenGelijkAan00B_zou8MoetenTeruggeven();
    }
    //@Test
    public void testIsCheckLengteVoorEenArrayVan8Met2StenenGelijkAan00B_zou6MoetenTeruggeven() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[8];
        for (int i=0;i<6;i++) {
            lijst[i] = new Steen(1,5,'g');
        }
        lijst[6] = new Steen(0,0,'B');
        lijst[7] = new Steen(0,0,'B');
        int berekend = algo.checkEchteLengte(lijst);
        assertEquals(6,berekend);
    }
    //@Test
    public void testIsCheckLengteVoorEenArrayVan8Met8StenenGelijkAan00B_zou0MoetenTeruggeven() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[8];
        for (int i=0;i<8;i++) {
            lijst[i] = new Steen(0,0,'B');
        }
        int result = algo.checkEchteLengte(lijst);
        assertEquals(0,result);
    }
    //@Test
    public void testIsCheckLengteVoorEenArrayVan8Met0StenenGelijkAan00B_zou8MoetenTeruggeven() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[8];
        for (int i=0;i<8;i++) {
            lijst[i] = new Steen(1,5,'g');
        }
        int result = algo.checkEchteLengte(lijst);
        assertEquals(8,result);
    }


    @Test //checkEchteLengte testen
    public void TestCheckEchteLengte() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[4];
        lijst[0] = new Steen(1,2,'R');
        lijst[1] = new Steen(4,1,'B');
        lijst[2] = new Steen(0,0,'B');
        lijst[3] = new Steen(0,0,'B');
        int berekend = algo.checkEchteLengte(lijst);
        assertEquals(2,berekend);
    }


    @Test //checkLaatste testen (gebruikt alle 2 de testen)
    public void TestChecklaatste() {
        TestChecklaatsteVoorEenArrayVan4Met2GevuldeElementenEn2ElementenVan00B();
        TestChecklaatsteVoorEenArrayVanAlleen00B();
    }
    //@Test
    public void TestChecklaatsteVoorEenArrayVan4Met2GevuldeElementenEn2ElementenVan00B() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[4];
        lijst[0] = new Steen(1,2,'R');
        lijst[1] = new Steen(4,1,'B');
        lijst[2] = new Steen(0,0,'B');
        lijst[3] = new Steen(0,0,'B');
        boolean result = algo.checklaatste(lijst);
        assertEquals(true,result);
    }
    //@Test
    public void TestChecklaatsteVoorEenArrayVanAlleen00B() {
        Algoritme algo = new Algoritme();
        Steen[] lijst = new Steen[4];
        lijst[0] = new Steen(0,0,'B');
        lijst[1] = new Steen(0,0,'B');
        lijst[2] = new Steen(0,0,'B');
        lijst[3] = new Steen(0,0,'B');
        boolean result = algo.checklaatste(lijst);
        assertEquals(false,result);
    }


    @Test //berekenLangsteLengteInOplossingen testen
    public void testBerekenLangsteLengteInOplossingenMetLijstVan2EnLijstVan3_Zou3moetenTeruggeven() {
        Algoritme algo = new Algoritme();
        ArrayList<Steen> lijst1 = new ArrayList<Steen>();
        lijst1.add(new Steen(2,5,'B'));
        lijst1.add(new Steen(2,5,'B'));
        lijst1.add(new Steen(2,5,'B'));

        ArrayList<Steen> lijst2 = new ArrayList<Steen>();
        lijst2.add(new Steen(2,5,'B'));
        lijst2.add(new Steen(2,5,'B'));

        ArrayList<ArrayList<Steen>> oplossingen = new ArrayList<>();
        oplossingen.add(lijst1);
        oplossingen.add(lijst2);
        int berekend = algo.berekenLangsteLengteInOplossingen(oplossingen);

        assertEquals(3,berekend);
    }
}

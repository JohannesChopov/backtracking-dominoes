package be.domino;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Johannes Chopov
 * uren gewerkt = 20-30 uren
 */
public class Algoritme {
    private static Steen[] todoArray; //Array van de ongesorteerde stenen.
    private static Steen[] sortedArray; //Array van de gesorteerde stenen.
    private static ArrayList<Steen> langstMogelijke; //de langst mogelijke ketting van stenen is de oplossing.
    private static ArrayList<ArrayList<Steen>> oplossingen; //lijst van oplossingen van de stenen.
    private static int[] indexArray; //Array dat is zoals sortedArray, maar ipv een steen op elke plaats, is er de index dat die steen heeft in de todoArray. Belangrijk voor het backtracken.
    private int cursor = 0; //Cursor om te weten wat de meest rechtse steen is in sortedArray.
    private int aantalVerschillendeEersteStenenGeplaatst = 0; //indicator nodig om te zien of elke mogelijkheid is geprobeerd. Als elke steen eens als eerste gebruikt zijn we klaar.
    private boolean[] available; //Array zoals todoArray, maar ipv een steen op elke plaats staat er een boolean die aanduidt of deze mag gebruikt worden.

    public ArrayList<ArrayList<Steen>> maakKetting(ArrayList<Steen> todo) throws CloneNotSupportedException {
        todoArray = new Steen[todo.size()];
        for (int i = 0; i < todo.size(); i++) {//converteren de arraylist van ongesorteerde stenen naar een array van ongesorteerde stenen.
            todoArray[i] = todo.get(i);
        }
        indexArray = new int[todo.size()];
        Arrays.fill(indexArray, -1); //Voor te beginnen zijn er nog geen gesorteerde stenen dus elke waarde is "null" of -1. Geen echte null.
        sortedArray = maakLege(todo.size()); //in de gesorteerde array moeten er waardes zijn voor we beginnen. Er mag niks "null" zijn. Hier is de steen "00B" de "null".
        available = new boolean[todoArray.length]; //true voor "nog niet gebruikt". false voor "al gebruikt".
        for (int i = 0; i < todoArray.length; i++) { //Om te beginnen is er nog geen enkele steen gebruikt.
            available[i] = true;
        }
        oplossingen = new ArrayList<>();
        System.out.println("origineel: " + Arrays.toString(todoArray)); //ongesorteerde lijst printen.
        plaats(); //recursieve functie oproepen.
        return oplossingen;
    }

    public void plaats() throws CloneNotSupportedException {
        for (int i = 0; i < todoArray.length; i++) { //kijk voor elke steen in ongesorteerd...
            if (available[i]) { //...of die mogelijk is om te gebruiken
                if (isVeilig(sortedArray[cursor], todoArray[i])) { //...of die veilig is om te plaatsen langs de meest rechtse.
                    if (sortedArray[cursor].getOgen1() != 0) { //Als we op de eerste plaats van de sortedArray staan en die is niet leeg is...
                        cursor++; //spring met de cursor naar de nieuwe meestrechtse (nog leeg). Als we op de allerseertse plaats staan en die is leeg, moet de cursor nog op index 0 staan en nog niet naar rechts verschuiven.
                    } else {//Als we op de eerste plaast staan en die is leeg, gaat er een nieuwe steen als eerste worden geplaatst
                        aantalVerschillendeEersteStenenGeplaatst++; //houdt bij hoeveel stenen van de todoArray al eens als eerste zijn geplaatst geweest.
                        langstMogelijke = maakKopie(array2List(sortedArray)); //langstmogelijke mag niet null zijn.
                    }
                    sortedArray[cursor] = todoArray[i]; //Zet de steen van de todoArray op de plaats waar de cursor staat. Deze plaats was leeg.
                    available[i] = false; //Deze steen mag momenteel niet meer gebruikt worden tot deze wordt gebacktracked.
                    indexArray[cursor] = i; //Zet op de indexArray op de plaats waar de steen juist is gezet de index dat die steen heeft in todoArray
                    //System.out.println("vooruit: " + Arrays.toString(sortedArray));
                    plaats(); //roep plaats nog eens op, recursief. of volgende wordt geplaatst of er wordt gebacktracked of we zijn klaar. (Uiteindelijk werkt deze functie steeds als een forloop in een vorige forloop)
                }
            }
        }
        if (checklaatste(sortedArray)) {//Als de laatste steen past op de eerste. hebben we een oplossing (korter of volledig)
            if (array2List(sortedArray).size() == langstMogelijke.size()) { //Als deze even groot is als de voorgaande gevonden oplossing...
                oplossingen.add(maakKopie(array2List(sortedArray))); //...Voeg die dan toe bij de lijst van oplossingen.
            }
            if (array2List(sortedArray).size() > langstMogelijke.size()) { //Als de huidige gesorteerde array groter is dan de gekende langstmogelijke...
                langstMogelijke = maakKopie(array2List(sortedArray)); //...Dan wordt deze de nieuwe langstmogelijke.
                oplossingen.add(langstMogelijke); //Deze wordt automatisch toegevoegd, maar als deze korter is dan een toekomstige langstmogelijke, wordt deze later uit de lijst van oplossingen verwijderd.
            }
        }

        if (checkEchteLengte(sortedArray) == 0 && aantalVerschillendeEersteStenenGeplaatst == todoArray.length) {
            //Als we elke eerste steen van de todoArray al eens hebben gebruikt en we zijn zo ver gebacktrackt dat alles "00B" is weten we dat we elke mogelijkheid hebben.
            berekenLangsteLengteInOplossingen(oplossingen); //Vindt de lengte van de grootste lijst.
            verwijderKortereLijsten(oplossingen); //Met deze lengte kunnen we alle kortere lijsten verwijderen uit de oplossingen.
            System.out.println("__________LANGSTMOGELIJKE_OPLOSSINGEN__________");
        } else { //Als de er niks meer geplaatste kan worden (forloop volledig gerunt), de laatste niet past op de eerste of als we nog niet volledig klaar zijn...
            //KEER DAN TERUG(backtracken)
            //Helemaal geloopt en niks gevonden?
            available[indexArray[cursor]] = true; //huidige terug op beschikbaar zetten
            indexArray[cursor] = -1; //De index in de indexArray wordt weer "null".
            sortedArray[cursor] = new Steen(0, 0, 'B'); //Zet de huidige steen op "null".
            if (cursor != 0) {
                cursor--; //zet een stap terug...
                //System.out.println("backtracking: " + Arrays.toString(sortedArray));
            } //...en laat (als deze nog niet voorbij was) de forloop die deze instantie van plaats() heeft opgeroepen verder doen vanaf de "i" waar die gebleven was.
        }
    }

    public int berekenLangsteLengteInOplossingen(ArrayList<ArrayList<Steen>> oplossingen) {
        int grootsteLengte = 0;
        for (ArrayList<Steen> oplossing : oplossingen) {
            int lijstLengte = 0;
            for (Steen s : oplossing) {
                lijstLengte++;
            }
            if (lijstLengte > grootsteLengte) {
                grootsteLengte = lijstLengte;
            }
        }
        return grootsteLengte;
    }

    public void verwijderKortereLijsten(ArrayList<ArrayList<Steen>> oplossingen) {
        int grootteLangsteLijst = berekenLangsteLengteInOplossingen(oplossingen);
        ArrayList<ArrayList<Steen>> teVerwijderen = new ArrayList<>();
        for (ArrayList l : oplossingen) {
            if (l.size() < grootteLangsteLijst) {
                teVerwijderen.add(l);
            }
        }
        for (ArrayList l : teVerwijderen) {
            oplossingen.remove(l);
        }
    }

    public int checkEchteLengte(Steen[] array) {
        int elementen = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getOgen1() != 0) {
                elementen++;
            }
        }
        return elementen;
    }

    public boolean checklaatste(Steen[] array) {
        Steen eerste = array[0];
        if (checkEchteLengte(array) == 0) {
            return false;
        }
        Steen laatste = array[checkEchteLengte(array) - 1];
        if (laatste.getOgen2() != eerste.getOgen1() || eerste.getKleur() == laatste.getKleur()) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Steen> maakKopie(ArrayList<Steen> teKopieren) throws CloneNotSupportedException {
        ArrayList<Steen> kopie = new ArrayList<>();
        for (Steen s : teKopieren) {
            kopie.add((Steen) s.clone());
        }
        return kopie;
    }

    public Steen[] maakLege(int size) {
        Steen[] array = new Steen[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Steen(0, 0, 'b');
        }
        return array;
    }

    protected boolean isVeilig(Steen huidig, Steen volgende) {
        if (huidig.getOgen1() == 0 || huidig.getOgen2() == 0) {
            return true;
        }
        if (huidig.getKleur() == volgende.getKleur()) {
            return false;
        } else {
            if (huidig.getOgen2() == volgende.getOgen1()) {
                return true;
            } else {
                volgende.flip();
                if (huidig.getOgen2() == volgende.getOgen1()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public ArrayList<Steen> array2List(Steen[] array) {
        ArrayList list = new ArrayList<Steen>();
        if (checkEchteLengte(array) == 1) {
            list.add(array[0]);
        } else {
            for (int i = 0; i < checkEchteLengte(array); i++) {
                list.add(array[i]);
            }
        }
        return list;
    }
}


package be.domino;

import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author JohannesChopov
 */
public class Steen implements Cloneable{
    private int ogen1, ogen2;
    private Color kleur;
    private boolean flipped;



    public Steen(int ogen1, int ogen2, Color kleur) {
        this.ogen1 = ogen1;
        this.ogen2 = ogen2;
        this.kleur = kleur;
        flipped = false;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Steen(int ogen1, int ogen2, char kleur) {
        this.ogen1 = ogen1;
        this.ogen2 = ogen2;
        switch (kleur) {
            case 'r':
            case 'R':
                this.kleur = Color.RED;
                break;
            case 'g':
            case 'G':
                this.kleur = Color.GREEN;
                break;
            case 'b':
            case 'B':
                this.kleur = Color.BLUE;
                break;
            case 'y':
            case 'Y':
                this.kleur = Color.YELLOW;
                break;
            case 'p':
            case 'P':
            default:
                this.kleur = Color.PINK;
                break;

        }
    }

    /** genereer een willekeurige steen met een maximale waarde en 1 van de 5 kleuren
     *
     * @param maxWaarde maximale waarde voor elke zijde van de steen
     */
    public Steen(int maxWaarde) {
        this.ogen1 = (int)(Math.random()*maxWaarde+1);
        this.ogen2 = (int)(Math.random()*maxWaarde+1);
        int kleurnummer = (int)(Math.random()*5);
        Color[] kleuren = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK};
        kleur = kleuren[kleurnummer];
    }

    public int getOgen1() {
        return ogen1;
    }

    public int getOgen2() {
        return ogen2;
    }

    public Color getKleur() {
        return kleur;
    }

    public void flip() {
        flipped = !flipped;
        int temp = ogen1;
        ogen1 = ogen2;
        ogen2 = temp;
    }

    public boolean isFlipped() {
        return flipped;
    }

    @Override
    public String toString() {
        String kl;
        if (kleur.equals(Color.RED)) {
            kl = "R";
        }
        else if (kleur.equals(Color.GREEN)) {
            kl = "G";
        }
        else if (kleur.equals(Color.BLUE)) {
            kl = "B";
        }
        else if (kleur.equals(Color.YELLOW)) {
            kl = "Y";
        }
        else if (kleur.equals(Color.PINK)) {
            kl = "P";
        }
        else {
            kl = kleur.toString();
        }

        return "" + ogen1 + "" + ogen2 + "" + kl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Steen other = (Steen) obj;
        if (this.ogen1 != other.ogen1) {
            return false;
        }
        if (this.ogen2 != other.ogen2) {
            return false;
        }
        return Objects.equals(this.kleur, other.kleur);
    }


}

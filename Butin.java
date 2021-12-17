import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Butin extends JLabel {
    private Wagon train[];
    private int valeur;
    private int VALEUR_MAX = 500;
    private int VALEUR_MIN = 0;
    private Random rand = new Random();
    private String nom;
    public Butin(String nom, int valeur, Wagon train[]){
        super(nom);
        this.valeur = valeur;
        this.train = train;
        this.nom = nom;
        setFont(new Font(Font.SERIF,Font.BOLD,12));
        setLayout(null);

    }

    public Butin(Wagon train[], String nom){
        super(nom);
        this.train = train;
        this.nom = nom;
        setFont(new Font(Font.SERIF,Font.BOLD,12));
        setLayout(null);
    }



    public void donnerValeurAleatoire(){
        valeur = rand.nextInt(VALEUR_MAX - VALEUR_MIN + 1) + VALEUR_MIN;

    }

    public void ajouterDansWagon(int i){
            train[i].getWagon().add(this);
            train[i].ajouterButinListWagon(this);
    }



    public void suppButinWagon(int i){
        train[i].getWagon().remove(this);

    }

    public void suppButinToit(int i){
        train[i].getToit().remove(this);

    }



    public void ajouterDansToit(int i){
        train[i].getToit().add(this);
        train[i].ajouterButinListToit(this);
    }

    public int getValeur(){
        return valeur;
    }
    public String getNom(){
        return nom;
    }


}

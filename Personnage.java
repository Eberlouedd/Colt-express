import javax.swing.*;

public abstract class Personnage extends JLabel {

    public Personnage (String nom){
        super(nom);
    }

    public abstract void ajoutePersonnageWagon(int i);

    public abstract void suppPersonnageWagon(int i);

    public abstract void action(Action action);
}

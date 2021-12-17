import javax.swing.*;


public class Panneau extends JPanel {
    private JLabel bijoux, magot, bourse;
    private Bandits bandits;
    public Panneau(int X, int Y, Bandits bandits){
        setBounds(X,Y, 60, 90);
        JLabel bijoux = new JLabel("Bijoux :" + bandits.getNb_bijoux());
        JLabel magot = new JLabel("Magots :" + bandits.getNb_magot());
        JLabel bourse = new JLabel("Bourse :" + bandits.getNb_bourse());
        JLabel nom = new JLabel(bandits.getNom());

        this.add(nom);
        this.add(bijoux);
        this.add(bourse);
        this.add(magot);

        this.bijoux = bijoux;
        this.magot = magot;
        this.bourse =  bourse;
        this.bandits = bandits;


    }

    public void update(){
        bijoux.setText("Bijoux :" + bandits.getNb_bijoux());
        bourse.setText("Bourse :" + bandits.getNb_bourse());
        magot.setText("Margot :" + bandits.getNb_magot());


    }




}

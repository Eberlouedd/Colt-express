import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controle extends JPanel implements ActionListener{

    private final JButton action, depHaut, depBas, depGauche, depDroite, tirDroit, tirGauche, tirHaut, tirBas, braquer;
    private final Bandits bandits1, bandits2, bandits3;
    private final Marshall marshall;
    private int i = 0, NOMBRE_TOUR, compteur_ordre = 0;
    private final Panneau panneau1, panneau2, panneau3;
    private final ArrayList<Action> preActionlist = new ArrayList<>();
    private final ArrayList<Action> reajustedActionlist = new ArrayList<>();
    private JLabel suivi;
    private boolean finPartie = false;



    JFrame frame;

   public Controle(JFrame frame, Bandits bandits1, Bandits bandits2, Bandits bandits3, Marshall marshall, Panneau panneau1, Panneau panneau2, Panneau panneau3, int NOMBRE_TOUR){
        JButton action, depHaut, depBas, depGauche, depDroite, tirDroit, tirGauche, tirHaut, tirBas, braquer;
        action = new JButton();
        depHaut = new JButton();
        depBas = new JButton();
        depDroite = new JButton();
        depGauche = new JButton();
        tirBas = new JButton();
        tirDroit = new JButton();
        tirGauche = new JButton();
        tirHaut = new JButton();
        braquer = new JButton();
        suivi = new JLabel(bandits1.getNom() + " | ordre : " + compteur_ordre);

        this.NOMBRE_TOUR = NOMBRE_TOUR;
        this.action = action;
        this.depHaut = depHaut;
        this.depBas = depBas;
        this.depDroite = depDroite;
        this.depGauche = depGauche;
        this.tirBas = tirBas;
        this.tirDroit = tirDroit;
        this.tirGauche = tirGauche;
        this.tirHaut = tirHaut;
        this.bandits1 = bandits1;
        this.bandits2 = bandits2;
        this.bandits3 = bandits3;
        this.marshall = marshall;
        this.braquer = braquer;
        this.frame = frame;
        this.panneau1 = panneau1;
        this.panneau2 = panneau2;
        this.panneau3 = panneau3;


        action.setText("Action !");
        action.setBounds(810,400, 100, 50);
        action.addActionListener(this);
        tirBas.setText("Fire v");
        tirBas.setBounds(565,425, 70, 25);
        tirBas.addActionListener(this);
        tirDroit.setText("Fire >");
        tirDroit.setBounds(635,425, 70, 25);
        tirDroit.addActionListener(this);
        tirGauche.setText("Fire <");
        tirGauche.setBounds(495,425, 70, 25);
        tirGauche.addActionListener(this);
        tirHaut.setText("Fire ^");
        tirHaut.setBounds(565,400, 70, 25);
        tirHaut.addActionListener(this);
        depBas.setText("v");
        depBas.setBounds(285,425, 50, 25);
        depBas.addActionListener(this);
        depHaut.setText("^");
        depHaut.setBounds(285,400, 50, 25);
        depHaut.addActionListener(this);
        depGauche.setText("<");
        depGauche.setBounds(235,425, 50, 25);
        depGauche.addActionListener(this);
        depDroite.setText(">");
        depDroite.setBounds(335,425, 50, 25);
        depDroite.addActionListener(this);
        braquer.setText("Braquer !");
        braquer.setBounds(35,400, 100, 50);
        braquer.addActionListener(this);
        suivi.setBounds(420, 125, 150, 25);
        frame.add(action);
        frame.add(tirBas);
        frame.add(tirDroit);
        frame.add(tirGauche);
        frame.add(tirHaut);
        frame.add(depBas);
        frame.add(depHaut);
        frame.add(depGauche);
        frame.add(depDroite);
        frame.add(braquer);
        frame.add(suivi);



   }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == action){
            if(preActionlist.size() == 12 && !finPartie) {
                if (i % 3 == 0 ){
                    bandits1.action(reajustedActionlist.get(i));
                    panneau1.update();
                    panneau2.update();
                    panneau3.update();
                    frame.repaint();



                    if(marshall.getEmplacementX() == 4){
                        marshall.action(Action.GAUCHE);
                    }else if(marshall.getEmplacementX() == 0){
                        marshall.action(Action.DROITE);
                    }else if(Math.random() > 0.5){
                        marshall.action(Action.DROITE);
                    }else{
                        marshall.action(Action.GAUCHE);
                    }

                    frame.repaint();

                    if(marshall.getEmplacementX() == bandits1.getEmplacementX() && bandits1.testPosition()){
                        bandits1.fuiteMarshall();
                        panneau1.update();
                        frame.repaint();
                    }
                    if(marshall.getEmplacementX() == bandits2.getEmplacementX() && bandits2.testPosition()){
                        bandits2.fuiteMarshall();
                        panneau2.update();
                        frame.repaint();
                    }
                    if(marshall.getEmplacementX() == bandits3.getEmplacementX() && bandits3.testPosition()){
                        bandits3.fuiteMarshall();
                        panneau3.update();
                        frame.repaint();

                    }
                    i++;
                } else if (i == 1 || i == 4 || i == 7 || i == 10) {
                    bandits2.action(reajustedActionlist.get(i));
                    panneau1.update();
                    panneau2.update();
                    panneau3.update();
                    frame.repaint();
                    if(marshall.getEmplacementX() == 4){
                        marshall.action(Action.GAUCHE);
                    }else if(marshall.getEmplacementX() == 0){
                        marshall.action(Action.DROITE);
                    }else if(Math.random() > 0.5){
                        marshall.action(Action.DROITE);
                    }else{
                        marshall.action(Action.GAUCHE);
                    }

                    frame.repaint();
                    if(marshall.getEmplacementX() == bandits1.getEmplacementX() && bandits1.testPosition()){
                        bandits1.fuiteMarshall();
                        panneau1.update();
                        frame.repaint();

                    }
                    if(marshall.getEmplacementX() == bandits2.getEmplacementX() && bandits2.testPosition()){
                        bandits2.fuiteMarshall();
                        panneau2.update();
                        frame.repaint();
                    }
                    if(marshall.getEmplacementX() == bandits3.getEmplacementX() && bandits3.testPosition()){
                        bandits3.fuiteMarshall();
                        panneau3.update();
                        frame.repaint();
                    }

                    i++;
                } else if (i == 2 || i == 5 || i == 8 || i == 11 ) {
                    bandits3.action(reajustedActionlist.get(i));
                    panneau1.update();
                    panneau2.update();
                    panneau3.update();
                    frame.repaint();
                    if(marshall.getEmplacementX() == 4){
                        marshall.action(Action.GAUCHE);
                    }else if(marshall.getEmplacementX() == 0){
                        marshall.action(Action.DROITE);
                    }else if(Math.random() > 0.5){
                        marshall.action(Action.DROITE);
                    }else{
                        marshall.action(Action.GAUCHE);
                    }

                    frame.repaint();
                    if(marshall.getEmplacementX() == bandits1.getEmplacementX() && bandits1.testPosition()){
                        bandits1.fuiteMarshall();
                        panneau1.update();
                        frame.repaint();
                    }
                    if(marshall.getEmplacementX() == bandits2.getEmplacementX() && bandits2.testPosition()){
                        bandits2.fuiteMarshall();
                        panneau2.update();
                        frame.repaint();
                    }
                    if(marshall.getEmplacementX() == bandits3.getEmplacementX() && bandits3.testPosition()){
                        bandits3.fuiteMarshall();
                        panneau3.update();
                        frame.repaint();
                    }
                    i++;
                    if (i == 12) {
                        preActionlist.clear();
                        reajustedActionlist.clear();
                        i = 0;
                        NOMBRE_TOUR--;
                        compteur_ordre = 0;
                        if(NOMBRE_TOUR == 0){
                            suivi.setText("Fin de parti");
                            System.out.println(bandits1.getNom() + " a voler pour une valeur de : " + bandits1.getvaleurButin());
                            System.out.println(bandits2.getNom() + " a voler pour une valeur de : " + bandits2.getvaleurButin());
                            System.out.println(bandits3.getNom() + " a voler pour une valeur de : " + bandits3.getvaleurButin());

                            finPartie = true;
                        }else {
                            suivi.setText(bandits1.getNom() + " | ordre " + (compteur_ordre));
                        }
                    }
                }
            }else if(preActionlist.size() < 12 && !finPartie){
                System.out.println("la planification n'est pas fini tu ne peux pas faire ca");
            }

        }else if(e.getSource() == tirBas){
            if(preActionlist.size() == 12 && !finPartie){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.TIRBAS);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == tirDroit){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.TIRDROIT);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == tirGauche){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.TIRGAUCHE);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == tirHaut){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.TIRHAUT);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == depBas){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.BAS);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == depDroite){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.DROITE);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == depGauche){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
               preActionlist.add(Action.GAUCHE);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12) {
                    for (int b = -4; b < 0; b++) {
                        int t = b;
                        for (int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }
        }else if(e.getSource() == depHaut){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.HAUT);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12){
                    for(int b = -4; b < 0; b++){
                        int t = b;
                        for(int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }
                }
            }



        }else if(e.getSource() == braquer){
            if(preActionlist.size() == 12){
                System.out.println("L'heure n'est plus a la planification place a l'action");
            }else if(preActionlist.size() < 12 && !finPartie){
                preActionlist.add(Action.BRAQUER);
                compteur_ordre++;
                if(compteur_ordre < 5){
                    suivi.setText(bandits1.getNom() + " | ordre : " + compteur_ordre);
                }else if(compteur_ordre < 9){
                    suivi.setText(bandits2.getNom() + " | ordre : " + (compteur_ordre - 4));
                }else {
                    suivi.setText(bandits3.getNom() + " | ordre : " + (compteur_ordre - 8));
                }
                if(preActionlist.size() == 12){
                    for(int b = -4; b < 0; b++){
                        int t = b;
                        for(int c = 0; c < 3; c++) {
                            t = t + 4;
                            reajustedActionlist.add(preActionlist.get(t));
                        }
                    }

                }
            }
        }
    }

}

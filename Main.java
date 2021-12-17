import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        final int NOMBRE_WAGON = 5;
        int VALEUR_MAX = 4, VALEUR_MIN = 1, nb_chose;
        int positionWagon = 0;
        int nbtour;
        String nom1, nom2, nom3;
        Random rand;
        Wagon train[];
        Panneau panBandit1;
        Panneau panBandit2;
        Panneau panBandit3;
        Bandits bandits1, bandits2, bandits3;

        Marshall marshall;
        Controle controle;

        Butin magot;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Le nombre de tours souhaités : ");
        nbtour = sc.nextInt();
        System.out.print("Nom du premier bandit : ");
        nom1 = sc2.nextLine();
        System.out.print("Nom du deuxieme bandit : ");
        nom2 = sc2.nextLine();
        System.out.print("Nom du troisieme bandit : ");
        nom3 = sc2.nextLine();


        JFrame fenetre = new JFrame("Colt Express");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.getContentPane().setBackground(new Color(255,255,255));
        fenetre.setSize(1000,600);
        fenetre.setLayout(null);
        fenetre.setBackground(Color.GRAY);


        rand = new Random();

        train = new Wagon[NOMBRE_WAGON];
        for(int i = 0; i < NOMBRE_WAGON; i++){
            positionWagon += 135;
            Wagon wag = new Wagon(fenetre, positionWagon);
            train[i] = wag;
        }

        bandits1 = new Bandits(nom1,train);
        bandits2 = new Bandits(nom2, train);
        bandits3 = new Bandits(nom3, train);

        panBandit1 = new Panneau(5, 10,bandits1);
        panBandit2 = new Panneau(450, 10,bandits2);
        panBandit3 = new Panneau(900, 10,bandits3);


        marshall = new Marshall(train);
        controle = new Controle(fenetre, bandits1, bandits2, bandits3, marshall, panBandit1, panBandit2, panBandit3, nbtour);


        magot = new Butin("Magots", 1000, train);


        magot.ajouterDansWagon(4);
        train[4].ajouterButinListWagon(magot);


        for(int i = 0; i < NOMBRE_WAGON - 1; i++){
            nb_chose = rand.nextInt(VALEUR_MAX - VALEUR_MIN + 1) + VALEUR_MIN;
            for(int c = 0; c < nb_chose; c++){
                if(Math.random() > 0.5) {
                    Butin bourse = new Butin(train,"Bourse");
                    bourse.donnerValeurAleatoire();
                    bourse.ajouterDansWagon(i);
                }else{
                    Butin bijoux = new Butin(train,"Bijoux");
                    bijoux.donnerValeurAleatoire();
                    bijoux.ajouterDansWagon(i);
                }


            }



        }

        System.out.println(bandits1.getNb_bijoux());
        fenetre.add(panBandit1);
        fenetre.add(panBandit2);
        fenetre.add(panBandit3);




        fenetre.setVisible(true);



    }


}

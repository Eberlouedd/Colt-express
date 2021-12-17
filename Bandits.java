import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

public class Bandits extends Personnage{

    private int emplacementX;
    private boolean emplacementY;
    private final Wagon train[];
    private final String nom;
    private int NB_BALLES = 6, perteHasard, choixBraquage, nb_bijoux = 0, nb_bourse = 0, nb_magot = 0, valeurButin;
    private Random  rand = new Random();
    private Random hasard = new Random();
    private ArrayList<Butin> listButin = new ArrayList<>();


    public Bandits(String nom, Wagon train[]){
        super(nom);
        setFont(new Font(Font.SERIF,Font.BOLD,12));

        this.train = train;
        this.nom = nom;



        train[0].getToit().add(this);


    }
    public void ajouteBanditsToit(int i){
       train[i].getToit().add(this);
       emplacementX = i;
       emplacementY = false;
       train[i].ajouterBanditListToit(this);
    }


    @Override
    public void ajoutePersonnageWagon(int i) {
        train[i].getWagon().add(this);
        emplacementX = i;
        emplacementY = true;
        train[i].ajouterBanditsListWagon(this);
    }



    public void suppBanditsToit(int i){
        train[i].getToit().remove(this);
        train[i].getListBanditsToit().remove(this);
    }

    @Override
    public void suppPersonnageWagon(int i) {
        train[i].getWagon().remove(this);
        train[i].getListBanditsWagon().remove(this);
    }

    public boolean testPosition(){
        return emplacementY;
    }



    @Override
    public void action(Action action) {
        if (action == Action.DROITE) {
            if (emplacementX < 4) {
                if (emplacementY) {
                    suppPersonnageWagon(emplacementX);
                    emplacementX += 1;
                    ajoutePersonnageWagon(emplacementX);

                } else {
                    suppBanditsToit(emplacementX);
                    emplacementX += 1;
                    ajouteBanditsToit(emplacementX);

                }
            } else {
                System.out.println("le bandit ne peut pas aller a droite ");
            }
        }else if(action == Action.GAUCHE){
            if (emplacementX > 0 ) {
                if (testPosition()) {
                    this.suppPersonnageWagon(emplacementX);
                    emplacementX -= 1;
                    this.ajoutePersonnageWagon(emplacementX);

                } else {
                    this.suppBanditsToit(emplacementX);
                    emplacementX -= 1;
                    this.ajouteBanditsToit(emplacementX);

                }
            }else{
                System.out.println("le bandit ne peut pas aller a gauche ");
            }
        }else if(action == Action.BAS){
            if(!testPosition()){
                this.suppBanditsToit(emplacementX);
                this.ajoutePersonnageWagon(emplacementX);


            }else{
                System.out.println("le bandit ne peut pas descendre il est deja a l'interieur du wagon");
            }
        }else if(action == Action.HAUT){
            if(testPosition()){
                this.suppPersonnageWagon(emplacementX);
                this.ajouteBanditsToit(emplacementX);


            }else{
                System.out.println("le bandit ne peut pas monter il est deja sur le toit");
            }
        }else if(action == Action.BRAQUER){


            if(train[emplacementX].getListButinWagon().size() >= 1 && testPosition()){
                choixBraquage = rand.nextInt(train[emplacementX].getListButinWagon().size() - 1 + 1) + 1;
                listButin.add(train[emplacementX].getListButinWagon().get(choixBraquage - 1));
                if(train[emplacementX].getListButinWagon().get(choixBraquage - 1).getNom().equals("Bourse")){
                    nb_bourse++;
                }else if(train[emplacementX].getListButinWagon().get(choixBraquage - 1).getNom().equals("Bijoux")){
                    nb_bijoux++;
                }else {
                    nb_magot++;
                }
                train[emplacementX].getListButinWagon().get(choixBraquage - 1).suppButinWagon(emplacementX);
                train[emplacementX].viderButinWagon(choixBraquage - 1);
                System.out.println(nom + " braque le wagon");

            }else if(train[emplacementX].getListButinToit().size() >= 1 && !testPosition()){
                choixBraquage = rand.nextInt(train[emplacementX].getListButinToit().size() - 1 + 1) + 1;
                listButin.add(train[emplacementX].getListButinToit().get(choixBraquage - 1));
                if(train[emplacementX].getListButinToit().get(choixBraquage - 1).getNom().equals("Bourse")){
                    nb_bourse++;
                }else if(train[emplacementX].getListButinToit().get(choixBraquage - 1).getNom().equals("Bijoux")){
                    nb_bijoux++;
                }else {
                    nb_magot++;
                }
                train[emplacementX].getListButinToit().get(choixBraquage - 1).suppButinToit(emplacementX);
                train[emplacementX].viderButinToit(choixBraquage - 1);
                System.out.println(nom + "  braque le wagon");
            }else if(train[emplacementX].getListButinWagon().size() < 1 || testPosition()){
                System.out.println(nom + "ne trouve rien a braqué braqué sur ce wagon");
            }else if(train[emplacementX].getListButinToit().size() < 1 || !testPosition()) {
                System.out.println(nom + " ne trouve rien a braqué sur ce toit");
            }
        }else if(action == Action.TIRBAS){
            if(NB_BALLES == 0){
                System.out.println(nom + " ne me peut pas tirer il n'a plus de balles");
            }else if(testPosition() && train[emplacementX].getListBanditsWagon().size() > 1) {
                train[emplacementX].getListBanditsWagon().remove(this);
                perteHasard = rand.nextInt(train[emplacementX].getListBanditsWagon().size() - 1 + 1) + 1;
                train[emplacementX].getListBanditsWagon().get(perteHasard - 1).drop();
                train[emplacementX].getListBanditsWagon().add(this);
                NB_BALLES--;
                System.out.println(nom + " tire sur son emplacement " + NB_BALLES);
            }else if(!testPosition() && train[emplacementX].getListBanditsWagon().size() != 0){
                perteHasard = rand.nextInt(train[emplacementX].getListBanditsWagon().size() - 1 + 1) + 1;
                train[emplacementX].getListBanditsWagon().get(perteHasard - 1).drop();
                NB_BALLES--;
                System.out.println(nom + " tire en bas " + NB_BALLES);
            }else if(train[emplacementX].getListBanditsWagon().size() == 0){
                NB_BALLES--;
                System.out.println(nom + " tire mais ne trouve personne " + NB_BALLES);
            }

        }else if(action == Action.TIRDROIT){
            if(NB_BALLES == 0){
                System.out.println(nom + " ne me peut pas tirer il n'a plus de balles");
            }else if(emplacementX == 3){
                System.out.println("le bandit ne peut pas tirer a droite");
            }else {
                if(testPosition() && train[emplacementX + 1].getListBanditsWagon().size() != 0) {
                    perteHasard = rand.nextInt(train[emplacementX + 1].getListBanditsWagon().size() - 1 + 1) + 1;
                    train[emplacementX + 1].getListBanditsWagon().get(perteHasard - 1).drop();
                    NB_BALLES--;
                    System.out.println(nom + " tire sur sa droite " + NB_BALLES);
                }else if(!testPosition() && train[emplacementX + 1].getListBanditsToit().size() != 0){
                    perteHasard = rand.nextInt(train[emplacementX + 1].getListBanditsToit().size() - 1 + 1) + 1;
                    train[emplacementX + 1].getListBanditsToit().get(perteHasard - 1).drop();
                    NB_BALLES--;
                    System.out.println(nom + " tire sur sa droite " + NB_BALLES);
                }else{
                    NB_BALLES--;
                    System.out.println(nom + " tire mais ne trouve personne a droite, il lui reste " + NB_BALLES);
                }
            }

        }else if(action == Action.TIRGAUCHE){
            if(NB_BALLES == 0){
                System.out.println(nom + " ne me peut pas tirer il n'a plus de balles");
            }else if(emplacementX == 0){
                System.out.println(nom + " ne peut pas tirer a gauche");
            }else {
                if(testPosition() && train[emplacementX - 1].getListBanditsWagon().size() != 0) {
                    perteHasard = rand.nextInt(train[emplacementX - 1].getListBanditsWagon().size() - 1 + 1) + 1;
                    train[emplacementX - 1].getListBanditsWagon().get(perteHasard - 1).drop();
                    NB_BALLES--;
                    System.out.println(nom + " tire sur sa gauche, il lui reste " + NB_BALLES);
                }else if(!testPosition() && train[emplacementX - 1].getListBanditsToit().size() != 0){
                    perteHasard = rand.nextInt(train[emplacementX - 1].getListBanditsToit().size() - 1 + 1) + 1;
                    train[emplacementX - 1].getListBanditsToit().get(perteHasard - 1).drop();
                    NB_BALLES--;
                    System.out.println(nom + " tire sur sa gauche, il lui reste " + NB_BALLES);
                }else{
                    NB_BALLES--;
                    System.out.println(nom + " a tiré mais t'as trouvé personne, il lui reste " + NB_BALLES);
                }
            }
        }else if(action == Action.TIRHAUT){
            if(NB_BALLES == 0){
                System.out.println(nom + " ne me peut pas tirer il n'a plus de balles");
            }else if(!testPosition() && train[emplacementX].getListBanditsToit().size() > 1) {
                train[emplacementX].getListBanditsToit().remove(this);
                perteHasard = rand.nextInt(train[emplacementX].getListBanditsToit().size() - 1 + 1) + 1;
                train[emplacementX].getListBanditsToit().get(perteHasard - 1).drop();
                train[emplacementX].getListBanditsToit().add(this);
                NB_BALLES--;
                System.out.println(nom + " tire sur son emplacement, il lui reste " + NB_BALLES);
            }else if(train[emplacementX].getListBanditsToit().size() != 0){
                perteHasard = rand.nextInt(train[emplacementX].getListBanditsToit().size() - 1 + 1) + 1;
                train[emplacementX].getListBanditsToit().get(perteHasard - 1).drop();
                NB_BALLES--;
                System.out.println(nom + " tire en haut, il lui reste " + NB_BALLES);
            }else{
                NB_BALLES--;
                System.out.println(nom + " tire mais ne trouve personne, il lui reste " + NB_BALLES);
            }
        }

    }


    public String getNom(){
        return nom;
    }


    public void fuiteMarshall(){
        System.out.println(nom + " a fuit");
        this.drop();
        this.action(Action.HAUT);


    }


    public void drop(){
            if(testPosition() && listButin.size() > 0) {
                perteHasard = hasard.nextInt(listButin.size() - 1 + 1) + 1;
                listButin.get(perteHasard - 1).ajouterDansWagon(emplacementX);
                train[emplacementX].ajouterButinListWagon(listButin.get(perteHasard - 1));
                if (listButin.get(perteHasard - 1).getNom().equals("Bourse")) {
                    nb_bourse--;
                } else if (listButin.get(perteHasard - 1).getNom().equals("Bijoux")) {
                    nb_bijoux--;
                } else {
                    nb_magot--;
                }
                listButin.remove(perteHasard - 1);
                System.out.println(nom + " laisse tomber un butin dans wagon");
            }else if(!testPosition() && listButin.size() > 0){
                perteHasard = hasard.nextInt(listButin.size() - 1 + 1) + 1;
                listButin.get(perteHasard - 1).ajouterDansToit(emplacementX);
                train[emplacementX].ajouterButinListToit(listButin.get(perteHasard - 1));
                if (listButin.get(perteHasard - 1).getNom().equals("Bourse")) {
                    nb_bourse--;
                } else if (listButin.get(perteHasard - 1).getNom().equals("Bijoux")) {
                    nb_bijoux--;
                } else {
                    nb_magot--;
                }
                listButin.remove(perteHasard - 1);
                System.out.println(nom + " laisse tomber un butin sur le toit");
            }else {
                System.out.println(nom + " n'a pas de butin");
            }

    }
    public int getEmplacementX(){
        return emplacementX;
    }

    public int getNb_bourse() {
        return nb_bourse;
    }

    public int getNb_magot() {
        return nb_magot;
    }

    public int getNb_bijoux() {
        return nb_bijoux;
    }

    public int getvaleurButin(){
        for(int i = 0; i < listButin.size(); i++){
            valeurButin = valeurButin + listButin.get(i).getValeur();
        }
        return valeurButin;
    }
}



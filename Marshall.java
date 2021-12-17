public class Marshall extends Personnage {
    private Wagon train[];
    private int emplacementX = 4;

    public Marshall(Wagon train[]) {
        super("Marshall");
        this.train = train;
        train[4].getWagon().add(this);
    }

    @Override
    public void ajoutePersonnageWagon(int i) {
        train[i].getWagon().add(this);
        emplacementX = i;
    }

    @Override
    public void suppPersonnageWagon(int i) {
        train[i].getWagon().remove(this);
    }

    @Override
    public void action(Action action) {
        if(action == Action.GAUCHE){
            this.suppPersonnageWagon(emplacementX);
            emplacementX -= 1;
            this.ajoutePersonnageWagon(emplacementX);
        }else{
            this.suppPersonnageWagon(emplacementX);
            emplacementX += 1;
            this.ajoutePersonnageWagon(emplacementX);
        }

    }

    public int getEmplacementX(){
        return emplacementX;
    }

}



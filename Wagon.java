

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Wagon extends JPanel {
    private  int positionX;
    private JPanel toit;
    private JPanel wagon;
    private ArrayList<Butin> listButinWagon = new ArrayList<>();
    private ArrayList<Butin> listButinToit = new ArrayList<>();
    private ArrayList<Bandits> listBanditsToit = new ArrayList<>();
    private ArrayList<Bandits> listBanditsWagon = new ArrayList<>();


    public Wagon(JFrame frame, int positionX){

        this.positionX = positionX;



        JPanel wagon = new JPanel();
        wagon.setBounds(positionX,222, 135, 67);
        wagon.setBackground(Color.GRAY);
        wagon.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JPanel toit = new JPanel();
        toit.setBounds(positionX,155, 135, 67);
        toit.setBackground(Color.WHITE);
        toit.setBorder(BorderFactory.createLineBorder(Color.BLACK));



        this.toit = toit;
        this.wagon = wagon;
        frame.add(wagon);
        frame.add(toit);


    }

    public JPanel getToit(){
        return toit;
    }

    public JPanel getWagon() {
        return wagon;
    }

    public void ajouterButinListWagon(Butin butin){
        listButinWagon.add(butin);
    }

    public void ajouterButinListToit(Butin butin){
        listButinToit.add(butin);
    }

    public void viderButinWagon(int indice){
        listButinWagon.remove(indice);
    }

    public void viderButinToit(int indice){
        listButinToit.remove(indice);
    }

    public ArrayList<Butin> getListButinWagon(){
        return listButinWagon;
    }

    public ArrayList<Butin> getListButinToit(){
        return listButinToit;
    }

    public void ajouterBanditsListWagon(Bandits bandits){
        listBanditsWagon.add(bandits);
    }

    public void ajouterBanditListToit(Bandits bandits){
        listBanditsToit.add(bandits);
    }

    public void viderBanditsWagon(int indice){
        listBanditsWagon.remove(indice);
    }

    public void viderBanditsToit(int indice){
        listBanditsToit.remove(indice);
    }

    public ArrayList<Bandits> getListBanditsWagon(){
        return listBanditsWagon;
    }

    public ArrayList<Bandits> getListBanditsToit(){
        return listBanditsToit;
    }
}







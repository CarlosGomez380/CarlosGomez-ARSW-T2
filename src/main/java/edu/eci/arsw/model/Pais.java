package edu.eci.arsw.model;

public class Pais {

    private String name;
    private int muertos;
    private int infectados;
    private int curados;

    public Pais(String name,int muertos, int infectados, int curados){
        this.name=name;
        this.muertos=muertos;
        this.infectados=infectados;
        this.curados=curados;
    }

    public String getName() {
        return name;
    }

    public int getMuertos() {
        return muertos;
    }

    public int getInfectados() {
        return infectados;
    }

    public int getCurados() {
        return curados;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuertos(int muertos) {
        this.muertos = muertos;
    }

    public void setInfectados(int infectados) {
        this.infectados = infectados;
    }

    public void setCurados(int curados) {
        this.curados = curados;
    }
}

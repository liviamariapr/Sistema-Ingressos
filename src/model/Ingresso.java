package model;

import java.io.Serializable;

public class Ingresso implements Serializable {

    private String nomeDaFesta;
    private int id;

    public Ingresso() {
    }

    public Ingresso(String nomeDaFesta) {
        this.nomeDaFesta = nomeDaFesta;
    }

    public Ingresso(int id, String nomeDaFesta) {
        this.nomeDaFesta = nomeDaFesta;
        this.id = id;
    }

    public String getNomeDaFesta() {
        return nomeDaFesta;
    }

    public void setNomeDaFesta(String nomeDaFesta) {
        this.nomeDaFesta = nomeDaFesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Nome: " + this.getNomeDaFesta()
               + "\nId: " + this.getId();
    }
}
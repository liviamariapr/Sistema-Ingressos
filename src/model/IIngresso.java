package model;

import java.util.ArrayList;

public interface IIngresso {
    public ArrayList<Ingresso> getAllIngressos();
    public void createIngresso (Ingresso Ingresso);
    public Ingresso readIngresso(int id);
    public void updateIngresso(Ingresso Ingresso);
    public void deleteIngresso(Ingresso Ingresso);
}
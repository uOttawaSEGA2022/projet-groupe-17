package com.example.mealer;

public class UserHelperClass {
    String name, type, cuisine, ingredients, allergene, prix;
    int offert=0;

    public UserHelperClass() {
    }
    public UserHelperClass(String name, String cuisine, String ingredients, String type, String allergene, String prix) {
        this.name = name;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.type=type;
        this.allergene=allergene;
        this.prix=prix;
    }

    public Boolean validinputs(String name, String type, String cuisine, String ingredients, String allergene, String prix){
        if(name.equals("")||cuisine.equals("")||ingredients.equals("")||type.equals("")||allergene.equals("")||prix.equals(""))
            return false;
        return true;
    }

    public Boolean offert(){
        return this.offert==1;
    }

    public Boolean supprimer(String name){
        if (name!=this.name)
            return false;
        if (this.offert==1)
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setOffert(int offre) {
        this.offert = offre;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}

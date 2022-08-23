package com.example.socialshoppingbeta;

import java.net.MalformedURLException;
import java.net.URL;

public class Product {
    private String nomProduit, nomMarque, prixProduit, lienProduit;


    public Product() {

    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(String prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getLienProduit() {
        return lienProduit;
    }

    public void setLienProduit(String lienProduit) {
        this.lienProduit = lienProduit;
    }

    public static boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        }
        catch (MalformedURLException e) {
            return false;
        }
    }
}

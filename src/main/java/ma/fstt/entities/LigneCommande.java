package ma.fstt.entities;

public class LigneCommande {
    private int id;
    private Produit produit;
    private int quantite;
    private double prixUnitaire;

    public LigneCommande() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public double getTotal() { return quantite * prixUnitaire; }
}
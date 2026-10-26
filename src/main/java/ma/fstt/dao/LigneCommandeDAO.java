package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.entities.LigneCommande;
import ma.fstt.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LigneCommandeDAO {

    public void save(LigneCommande l, int commandeId) {
        String sql = "INSERT INTO ligne_commande(commande_id, produit_id, quantite, prix_unitaire) VALUES(?,?,?,?)";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, commandeId);
            ps.setInt(2, l.getProduit().getId());
            ps.setInt(3, l.getQuantite());
            ps.setDouble(4, l.getPrixUnitaire());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void delete(int id) {
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM ligne_commande WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<LigneCommande> findByCommande(int commandeId) {
        List<LigneCommande> list = new ArrayList<>();
        String sql = "SELECT l.id, l.quantite, l.prix_unitaire, p.id AS pid, p.designation " +
                     "FROM ligne_commande l JOIN produit p ON l.produit_id = p.id " +
                     "WHERE l.commande_id = ? ORDER BY l.id";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, commandeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produit p = new Produit();
                    p.setId(rs.getInt("pid"));
                    p.setDesignation(rs.getString("designation"));
                    LigneCommande l = new LigneCommande();
                    l.setId(rs.getInt("id"));
                    l.setProduit(p);
                    l.setQuantite(rs.getInt("quantite"));
                    l.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                    list.add(l);
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}
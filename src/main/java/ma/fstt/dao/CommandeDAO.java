package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.service.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommandeDAO implements IDao<Commande> {

    private final LigneCommandeDAO ligneDao = new LigneCommandeDAO();

    @Override
    public void save(Commande c) {
        String sql = "INSERT INTO commande(date_commande, client_id) VALUES(?,?)";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, c.getDateCommande());
            ps.setInt(2, c.getClient().getId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void update(Commande c) {
        String sql = "UPDATE commande SET date_commande=?, client_id=? WHERE id=?";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, c.getDateCommande());
            ps.setInt(2, c.getClient().getId());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void delete(int id) {
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM commande WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Commande findById(int id) {
        String sql = "SELECT c.id, c.date_commande, cl.id AS cid, cl.nom, cl.prenom " +
                     "FROM commande c JOIN client cl ON c.client_id = cl.id WHERE c.id = ?";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Commande c = map(rs);
                    c.setLignes(ligneDao.findByCommande(c.getId()));
                    return c;
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    @Override
    public List<Commande> findAll() {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT c.id, c.date_commande, cl.id AS cid, cl.nom, cl.prenom " +
                     "FROM commande c JOIN client cl ON c.client_id = cl.id ORDER BY c.id DESC";
        try (Connection cn = ConnexionDB.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    private Commande map(ResultSet rs) throws SQLException {
        Client cl = new Client();
        cl.setId(rs.getInt("cid"));
        cl.setNom(rs.getString("nom"));
        cl.setPrenom(rs.getString("prenom"));
        Commande c = new Commande();
        c.setId(rs.getInt("id"));
        c.setDateCommande(rs.getDate("date_commande"));
        c.setClient(cl);
        return c;
    }
}
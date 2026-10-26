package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.entities.Produit;
import ma.fstt.service.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProduitDAO implements IDao<Produit> {

    @Override
    public void save(Produit p) {
        String sql = "INSERT INTO produit(designation, prix, stock) VALUES(?,?,?)";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void update(Produit p) {
        String sql = "UPDATE produit SET designation=?, prix=?, stock=? WHERE id=?";
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void delete(int id) {
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM produit WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Produit findById(int id) {
        try (Connection cn = ConnexionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM produit WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> list = new ArrayList<>();
        try (Connection cn = ConnexionDB.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM produit ORDER BY id")) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    private Produit map(ResultSet rs) throws SQLException {
        return new Produit(rs.getInt("id"), rs.getString("designation"),
                           rs.getDouble("prix"), rs.getInt("stock"));
    }
}
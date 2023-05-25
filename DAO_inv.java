package inventory.DAO;

import inventory.DAOImplement.Implement_inv;
import inventory.Koneksi.Koneksi_invDB;
import inventory.Model.Model_inv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * author Kyy
 */
public class DAO_inv implements Implement_inv {
    
    Connection connection;
    final String insert = "INSERT INTO tbl_inventory (kode, nama, stok) VALUES (?, ?, ?)";
    final String update = "UPDATE tbl_inventory SET kode=?, nama=?, stok=? WHERE id=?";
    final String delete = "DELETE FROM tbl_inventory WHERE id=?";
    final String selectAll = "SELECT * FROM tbl_inventory";
    final String searchByNameOrCode = "SELECT * FROM tbl_inventory WHERE nama LIKE ? OR kode LIKE ?";

    public DAO_inv() {
        connection = Koneksi_invDB.connection();
    }

    @Override
    public void insert(Model_inv item) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, item.getKode());
            statement.setString(2, item.getNama());
            statement.setInt(3, item.getStok());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Model_inv item) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, item.getKode());
            statement.setString(2, item.getNama());
            statement.setInt(3, item.getStok());
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Model_inv> getAll() {
        List<Model_inv> itemList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectAll);
            while (rs.next()) {
                Model_inv item = new Model_inv();
                item.setId(rs.getInt("id"));
                item.setKode(rs.getString("kode"));
                item.setNama(rs.getString("nama"));
                item.setStok(rs.getInt("stok"));
                itemList.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_inv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemList;
    }

    @Override
    public List<Model_inv> getCariNama(String keyword) {
        List<Model_inv> itemList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(searchByNameOrCode);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Model_inv item = new Model_inv();
                item.setId(rs.getInt("id"));
                item.setKode(rs.getString("kode"));
                item.setNama(rs.getString("nama"));
                item.setStok(rs.getInt("stok"));
                itemList.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_inv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemList;
    }
}

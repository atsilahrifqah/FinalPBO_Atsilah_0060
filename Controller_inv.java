package inventory.controller;

import inventory.DAO.DAO_inv;
import inventory.DAOImplement.Implement_inv;
import inventory.Model.Model_inv;
import inventory.Model.Tabel_Model_inv;
import inventory.View.View_Inventory;
import java.util.List;
import javax.swing.JOptionPane;

public class Controller_inv {
    
    View_Inventory frame_inv;
    Implement_inv implement_inv;
    List<Model_inv> list_inv;
    
    public Controller_inv(View_Inventory frame_inv) {
        this.frame_inv = frame_inv;
        implement_inv = new DAO_inv();
        list_inv = implement_inv.getAll();
  
        // Menampilkan data pada tabel saat aplikasi dijalankan
        isiTable();
    }
   
    // Tombol Reset
    public void reset(){
        frame_inv.getTxtid().setText("");
        frame_inv.getTxtkode().setText("");
        frame_inv.getTxtnamabarang().setText("");
        frame_inv.getTxtstok().setText("");
        frame_inv.getTxtcari().setText("");
    }
    
    // Tampil Data Ke Tabel
    public void isiTable(){
        list_inv = implement_inv.getAll();
        Tabel_Model_inv tmb = new Tabel_Model_inv(list_inv);
        frame_inv.getTabel_Datawarung().setModel(tmb);
    }
    
    // Menampilkan Data Ke Form Ketika Data Di-Klik
    public void isiField(int row){
        frame_inv.getTxtid().setText(String.valueOf(list_inv.get(row).getId()));
        frame_inv.getTxtkode().setText(list_inv.get(row).getKode());
        frame_inv.getTxtnamabarang().setText(list_inv.get(row).getNama());
        frame_inv.getTxtstok().setText(String.valueOf(list_inv.get(row).getStok()));
    }
    
    // Insert Data Dari Form Ke Database
    public void insert(){
        if(!frame_inv.getTxtkode().getText().trim().isEmpty() && !frame_inv.getTxtnamabarang().getText().trim().isEmpty() && !frame_inv.getTxtstok().getText().trim().isEmpty()){
            Model_inv inv = new Model_inv();
            inv.setKode(frame_inv.getTxtkode().getText());
            inv.setNama(frame_inv.getTxtnamabarang().getText());
            inv.setStok(Integer.parseInt(frame_inv.getTxtstok().getText()));
            
            implement_inv.insert(inv);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } else {
            JOptionPane.showMessageDialog(frame_inv, "Data Tidak Boleh Kosong");
        }
    }
    
    // Update Data Dari Tabel Ke Database
    public void update() {
        if (!frame_inv.getTxtid().getText().trim().isEmpty()) {
            Model_inv inv = new Model_inv();
            inv.setId(Integer.parseInt(frame_inv.getTxtid().getText()));
            inv.setKode(frame_inv.getTxtkode().getText());
            inv.setNama(frame_inv.getTxtnamabarang().getText());
            inv.setStok(Integer.parseInt(frame_inv.getTxtstok().getText()));
            
            implement_inv.update(inv);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } else {
            JOptionPane.showMessageDialog(frame_inv, "Silahkan Pilih Data Yang Akan Diupdate");
        }
    }
    
    // Hapus Data Pada Tabel
    public void delete(){
        if(!frame_inv.getTxtid().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame_inv.getTxtid().getText());
            implement_inv.delete(id);
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } else {
            JOptionPane.showMessageDialog(frame_inv, "Silahkan Pilih Data Yang Akan Dihapus");
        }
    }
    
    // Cari Data
    public void isiTableCariNama(){
        list_inv = implement_inv.getCariNama(frame_inv.getTxtcari().getText());
        Tabel_Model_inv tmb = new Tabel_Model_inv(list_inv);
        frame_inv.getTabel_Datawarung().setModel(tmb);
    }
    
    public void cariNama(){
        if(!frame_inv.getTxtcari().getText().trim().isEmpty()){
            implement_inv.getCariNama(frame_inv.getTxtcari().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame_inv, "Silahkan Masukkan Keyword Pencarian");
        }
    }
}

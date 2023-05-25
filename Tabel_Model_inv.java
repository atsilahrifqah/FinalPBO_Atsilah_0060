package inventory.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Tabel_Model_inv extends AbstractTableModel {
    
    List<Model_inv> list_inv;
    
    public Tabel_Model_inv(List<Model_inv> list_inv){
        this.list_inv = list_inv;
    }

    @Override
    public int getRowCount() {
        return list_inv.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "ID";
            case 1:
                return "KODE BARANG";
            case 2:
                return "NAMA BARANG";
            case 3:
                return "STOK";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0:
                return list_inv.get(row).getId();
            case 1:
                return list_inv.get(row).getKode();
            case 2:
                return list_inv.get(row).getNama();
            case 3:
                return list_inv.get(row).getStok();
            default:
                return null;
        }
    }
    
}

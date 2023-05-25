package inventory.DAOImplement;

import inventory.Model.Model_inv;
import java.util.List;

public interface Implement_inv {
    public void insert(Model_inv b);
    
    public void update(Model_inv b);
    
    public void delete(int id);
    
    public List<Model_inv> getAll();
    
    public List<Model_inv> getCariNama(String nama);
}

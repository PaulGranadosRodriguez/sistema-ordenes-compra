import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product>products;
    
    public InventoryManager(){
        this.products=new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
    
    public List<Product> getAllProducts(){
        return products;
    }
    
}

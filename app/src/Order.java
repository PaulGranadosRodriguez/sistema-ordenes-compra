import java.util.ArrayList;
import java.util.List;


public class Order {
    private int id;
    private String status;
    private List<String> products;

    public Order (int id){
        this.id=id;
        this.status="Pendiente";
        this.products= new ArrayList<>();
    }

    public void addProduct(String product, User user){
        if(user.getRole()==UserRole.COMPRADOR){
            products.add(product);
            System.out.println("Producto "+ product+" agregado por "+ user.getUsername());
        }else{
            System.err.println("Acceso denegado: solo los compradores puede editar productos");
        }
    }
    public void approve(User user){
        if(user.getRole()==UserRole.ADMIN){
            this.status="APROVADA";
            System.out. print("Order # "+ id+ " Aprovada por el administrador "+ user.getUsername() +"  ");
            
        }else{
            System.out.println("Error: El usuario "+ user.getUsername()+ " no tiene permisos de administrador.");
        }
    }
    public String getStatus(){return status;}

    
}

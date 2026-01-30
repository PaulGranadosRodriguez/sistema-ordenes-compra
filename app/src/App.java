public class App {
    public static void main(String[] args)  {
        User miAdmin=new User("Boss", UserRole.ADMIN);
        User miCliente=new User("Juan",UserRole.COMPRADOR);
        
        Order order1=new Order(5001);
        order1.addProduct("Laptop Dell", miCliente);
        order1.approve(miCliente);
        order1.approve(miAdmin);

        System.out.println("Estado final: "+ order1.getStatus());
    }
}

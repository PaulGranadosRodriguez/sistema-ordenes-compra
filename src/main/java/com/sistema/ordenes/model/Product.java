package com.sistema.ordenes.model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Product {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty stock;


    public Product(int id, String name, int stock,double price ){
        this.id= new SimpleIntegerProperty(id);
        this.name= new SimpleStringProperty(name);
        this.stock= new SimpleIntegerProperty(stock);
        this.price= new SimpleDoubleProperty(price);

    }

    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public double getPrice(){
        return price.get();
    }
    public int getStock(){
        return stock.get();
    }

    public SimpleIntegerProperty idProperty(){
        return id;
    }
    public SimpleStringProperty nameProperty(){
        return name;
    }
    public SimpleDoubleProperty priceProperty(){
        return price;
    }
    public SimpleIntegerProperty stockProperty(){
        return stock;
    }

    @Override
    public String toString(){
        return name + "($" + price +")";
    }
    
}

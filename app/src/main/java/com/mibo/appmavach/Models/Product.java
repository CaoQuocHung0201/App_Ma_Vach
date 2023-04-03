package com.mibo.appmavach.Models;

public class Product {
    private String Id, Name, dateImport, dateExport;
    private int quantity, Inventory_number;

    public Product(String id, String name, String dateImport, String dateExport, int quantity, int inventory_number) {
        Id = id;
        Name = name;
        this.dateImport = dateImport;
        this.dateExport = dateExport;
        this.quantity = quantity;
        Inventory_number = inventory_number;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateImport() {
        return dateImport;
    }

    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public String getDateExport() {
        return dateExport;
    }

    public void setDateExport(String dateExport) {
        this.dateExport = dateExport;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventory_number() {
        return Inventory_number;
    }

    public void setInventory_number(int inventory_number) {
        Inventory_number = inventory_number;
    }
}

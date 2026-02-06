package com.example.inventory_system.inventory;

import com.example.inventory_system.model.Electronics;
import com.example.inventory_system.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String location;
    private int totalCapacity;

    public Inventory(String location, int totalCapacity) {
        this.location = location;
        this.totalCapacity = totalCapacity;
    }

    public void addProduct(Product product){
        String sql = "Insert into products(name,price,quantity,description,brand) values (?,?,?,?,?)";
        try (Connection conn =DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,product.getName());
            pstmt.setDouble(2,product.getPrice());
            pstmt.setInt(3,product.getQuantity());
            pstmt.setString(4, product.getdescription());

            if(product instanceof Electronics){
                pstmt.setString(5,((Electronics) product).getBrand());
            }else{
                pstmt.setNull(5,java.sql.Types.VARCHAR);
            }
            pstmt.executeUpdate();
            System.out.println("Product Added: "+product.getName());
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        };
    }

    public Product getProductbyname(String name){
        String sql = "SELECT * FROM products WHERE name = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Product(rs.getString("name"),rs.getDouble("price"),rs.getInt("quantity"),rs.getString("description"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println("Error:"+ e.getMessage());}
        return null;
    }

    public List<Product> getLowStock(int threshold){
        List<Product> lowStocklist = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE quantity < ?";

        try(Connection conn = DB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,threshold);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lowStocklist.add(new Product(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description")));
            }} catch(SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
        return lowStocklist;
    }


    public List<Product> sortbyprice(){
        List<Product> sortedlist = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY price ASC";
        try(Connection conn = DB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String brand = rs.getString("brand");
                if(brand!=null){
                    sortedlist.add(new Electronics(name,price,quantity,description,brand));
                }else{
                    sortedlist.add(new Product(name,price,quantity,description));
                }
            }}catch(SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
        return sortedlist;
    }


    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        String sql = "Select * FROM products";
        try(Connection conn = DB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql)){
            while(rs.next()){
                int id=rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String brand = rs.getString("brand");
                if(brand!=null) {
                    products.add(new Electronics(name,price,quantity,description,brand));
                }else{
                    products.add(new Product(name,price,quantity,description));
                }
            }} catch(SQLException e){
            System.out.println("error:" + e.getMessage());
        }
        return products;
    }

    public boolean deleteProduct(String nametoremove){
        String sql = "DELETE FROM products WHERE name = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nametoremove);
            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0);
        }catch(SQLException e){
            System.out.println("error:" + e.getMessage());
            return false;
        }
    }


    public void adddescription(){
        String sql = "Alter TABLE products ADD Column description TEXT";
        try(Connection conn = DB.getConnection();
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);}
        catch(SQLException e){
            System.out.println("error:"+e.getMessage());
        }}

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int totalCapacity) { this.totalCapacity = totalCapacity; }

    @Override
    public String toString() {
        return "Inventory{location='" + location + "', capacity=" + totalCapacity + "}";
    }}

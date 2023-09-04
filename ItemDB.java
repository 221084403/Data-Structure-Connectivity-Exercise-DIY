/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.*;
import java.util.*;
import za.ac.tut.entity.*;

/**
 *
 * @author MNCEDISI
 */
public class ItemDB implements DAO<Item>
{
    private Connection connection;

    public ItemDB(String url , String userName , String password) throws SQLException 
    {
        connection = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public boolean addItem(Item item) throws SQLException 
    {
        String sql = "INSERT INTO ITEM_TBL VALUES(? , ? ,? ,? ) ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, item.getIdNumber());
        ps.setString(2,item.getDescription());
        ps.setInt(3, item.getQuantity());
        ps.setDouble(4, item.getUnityPrice());
        
        if(ps.executeUpdate()!=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean changeQuantityItem(Integer idNumber, Integer quantity) throws SQLException
    {
        String sql = "UPDATE ITEM_TBL "+
                     "SET Quantity = ? "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, quantity);
        ps.setInt(2, idNumber);
        
        if(ps.executeUpdate()!=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean changeUnityPriceItem(Integer idNumber, Double unityPrice) throws SQLException 
    {
        String sql = "UPDATE ITEM_TBL "+
                     "SET UnityPrice = ? "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setDouble(1, unityPrice);
        ps.setInt(2, idNumber);
        
        if(ps.executeUpdate()!=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteItem(Integer idNumber) throws SQLException 
    {
        String sql = "DELETE FROM ITEM_TBL "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, idNumber);
        
        if(ps.executeUpdate()!=0)
            return true;
        else
            return false;
    }

    @Override
    public Item getItem(Integer idNumber) throws SQLException 
    {
        Item item = null;
        
        String sql = "SELECT * FROM ITEM_TBL "+
                     "WHERE IdNumber = ? ";
       
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, idNumber);
      
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
        {
            String description = rs.getString("Description");
            Integer quantity = rs.getInt("Quantity");
            Double unityPrice = rs.getDouble("UnityPrice");
            
            item = new Item(idNumber, description, quantity, unityPrice);
        }
        
        rs.close();
        
        return item;
    }

    @Override
    public List<Item> getAllItem() throws SQLException 
    {
        String sql = "SELECT * FROM ITEM_TBL ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        List<Item> theItems = new ArrayList<>();
        
        while(rs.next())
        {
            Integer idNumber = rs.getInt("IdNumber");
            String description = rs.getString("Description");
            Integer quantity = rs.getInt("Quantity");
            Double unityPrice = rs.getDouble("UnityPrice");
            
            Item item = new Item(idNumber, description, quantity, unityPrice);
            
            theItems.add(item);
        }
        
        rs.close();
        
        return theItems;
    }

    public Connection getConnection() {
        return connection;
    }
}

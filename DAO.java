/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.*;
import java.util.*;

/**
 *
 * @author MNCEDISI
 */
public interface DAO<T> 
{
    public boolean addItem(T item) throws SQLException;
    
    public boolean changeQuantityItem(Integer idNumber , Integer quantity) throws SQLException;
    
    public boolean changeUnityPriceItem(Integer idNumber , Double unityPrice) throws SQLException;
    
    public boolean deleteItem(Integer idNumber) throws SQLException;
    
    public T getItem(Integer idNumber) throws SQLException;
    
    public List<T> getAllItem() throws SQLException;
    
}

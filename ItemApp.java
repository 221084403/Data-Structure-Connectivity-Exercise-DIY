/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemapp;

import java.sql.*;
import java.text.*;
import java.util.*;
import za.ac.tut.bl.ItemDB;
import za.ac.tut.entity.Item;

/**
 *
 * @author MNCEDISI
 */
public class ItemApp 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        //databse connection
        String url = "jdbc:derby://localhost:1527/ItemDataBase";
        String userName ="ItemDB";
        String password = "123";
        
        try
        {
            ItemDB itemDB = new ItemDB(url, userName, password);
            
            int option = showOption();

            do
            {
                switch(option)
                {
                    case 1:
                        if(itemDB.addItem(item()))
                            System.out.println("Item is added");
                        else
                            System.err.println("Item is not added");
                    break;
                        
                    case 2:
                        Integer idNumber = promptingIdNumber();
                        Integer quantity = promptingQuantity();
                        
                        if(itemDB.changeQuantityItem(idNumber, quantity))
                            System.out.println("The item is updated");
                        else
                            System.err.println("The item is not updated");
                    break;
                        
                    case 3:
                        idNumber = promptingIdNumber();
                        Double unityPrice = promptingUnityPrice(); 
                        
                        if(itemDB.changeUnityPriceItem(idNumber, unityPrice))
                            System.out.println("The unity Price is updated");
                        else
                            System.err.println("The unity Price is not updated");
                    break;
                          
                    case 4:
                        idNumber = promptingIdNumber();
                        
                        if(itemDB.deleteItem(idNumber))
                            System.out.println("The item is removed");
                        else
                            System.err.println("The item is not removed");
                    break;
                        
                    case 5:
                        idNumber = promptingIdNumber();
                        Item item = itemDB.getItem(idNumber);
                        
                        if(item!=null)
                            displayItem(item);
                        else
                            System.err.println("Not item much this id number");
                    break;
                        
                    case 6:
                        List<Item> theItem = itemDB.getAllItem();
                        
                        if(!theItem.isEmpty())
                            displayAllItems(theItem);
                        else
                            System.err.println("Nothing on the list");
                    break;
                        
                    default:
                        System.err.println("Invalid option. Please re-enter again ");
                    break;
                        
                }

                option = showOption();
            }
            while(option!=7);
        } 
        catch (SQLException ex) 
        {
            System.err.println("Something went wrong\n"+ex.getMessage());
        }
        
        catch(InputMismatchException ex)
        {
            System.err.println("Invalid. Please enter a option number");
        }
    }

    private static int showOption() 
    {
        Scanner sc = new Scanner(System.in);
        
        String menu = "\nPlease select one of the following option :\n\n"+
                      "1. Add the details of an item to the database.\n"+
                      "2. Change the details quantity of an item.\n"+
                      "3. Change the unit price of an item.\n"+
                      "4. Delete an item.\n"+
                      "5. Get an item.\n"+
                      "6. Get all the items.\n"+
                      "7. Exit\n\n"+
                      "Your option :";
        System.out.print(menu);
        
        return sc.nextInt();
    }

    private static Item item() 
    {
        Scanner  sc = new Scanner(System.in);
        
        System.out.print("Please enter the ID number\t:");
        Integer idNumber = sc.nextInt();
        
        sc.nextLine();
        
        System.out.print("Enter the description\t\t:");
        String description = sc.nextLine();
        
        System.out.print("Enter the quantity\t\t:");
        Integer quantity = sc.nextInt();
        
        System.out.print("Enter the unity Price\t\t:R");
        Double  unityPrice = sc.nextDouble();
        
        return new Item(idNumber, description, quantity, unityPrice);
    }

    private static Integer promptingIdNumber() 
    {
        Scanner  sc = new Scanner(System.in);
        
        System.out.print("Please enter the ID number of the item you want to [make/view/remove] change on :");
        
        return sc.nextInt();
    }

    private static Integer promptingQuantity() 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a new number of quantity\t:");
        
        return sc.nextInt();
    }

    private static Double promptingUnityPrice()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the new unityPrice\t:R");
        
        return sc.nextDouble();
    }

    private static void displayItem(Item item)
    {
        DecimalFormat dec = new DecimalFormat("#.00");
        
        String output = "ID Number\t:"+item.getIdNumber()+"\n"+
                        "Description\t:"+item.getDescription()+"\n"+
                        "Quantity\t:"+item.getQuantity()+"\n"+
                        "Unity Price\t:R"+dec.format(item.getUnityPrice())+"\n";
        System.out.print(output);
    }

    private static void displayAllItems(List<Item> theItem)
    {
        DecimalFormat dec = new DecimalFormat("#.00");
        String output = "";
        
        for (Item display : theItem)
        {
            output+="\nID Number\t:"+display.getIdNumber()+"\n"+
                    "Description\t:"+display.getDescription()+"\n"+
                    "Quantity\t:"+display.getQuantity()+"\n"+
                    "Unity Price\t:R"+dec.format(display.getUnityPrice())+"\n";
        }
        
        System.out.println(output);
    }
}

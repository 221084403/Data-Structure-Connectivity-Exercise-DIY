/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.entity;

/**
 *
 * @author MNCEDISI
 */
public class Item 
{
    private Integer idNumber;
    private String description;
    private Integer quantity;
    private Double unityPrice;

    public Item() {}

    public Item(Integer idNumber, String description, Integer quantity, Double unityPrice)
    {
        this.idNumber = idNumber;
        this.description = description;
        this.quantity = quantity;
        this.unityPrice = unityPrice;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Double unityPrice) {
        this.unityPrice = unityPrice;
    }
}

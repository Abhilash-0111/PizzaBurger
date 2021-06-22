package com.sits.pizzaburger;

public class SubmenuDetail {
    String Description,Image ,Name,MenuId;
    int Discount;
    int Price ;

    public SubmenuDetail(String description, String image, String name, int discount, int price, String menuId) {
        Description = description;
        Image = image;
        Name = name;
        Discount = discount;
        Price = price;
        MenuId = menuId;
    }

    public  SubmenuDetail(){}

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}


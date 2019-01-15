package com.example.brianbaek.practicemvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("company")
    public String company;

    @SerializedName("price")
    public int price;

    @SerializedName("imageLink")
    public String imageLink;

    @SerializedName("quantity")
    public int quantity;

    public Category category;

    public class Category{

        @SerializedName("id")
        public int id;

        @SerializedName("name")
        public String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

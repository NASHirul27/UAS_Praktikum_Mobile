package com.nashirul.uts_praktikum_mobile;

import android.os.Parcel;
import android.os.Parcelable;

public class Items {
    private String name, description, itemId;
    public Items(){

    }
    public Items(String name, String description, String itemId){
        this.name = name;
        this.description = description;
        this.itemId = itemId;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }






}

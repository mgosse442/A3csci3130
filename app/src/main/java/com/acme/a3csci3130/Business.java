package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String UID;
    public  String number;
    public  String name;
    public  String business;
    public  String address;
    public  String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Business
     * @param UID: ID for business in database
     * @param number: business number
     * @param name: business name
     * @param business: primary business
     * @param address: address
     * @param province: province or territory
     **/
    public Business(String UID, String number, String name, String business, String address, String province){
        this.UID = UID;
        this.number = number;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("UID", UID);
        result.put("number", number);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}

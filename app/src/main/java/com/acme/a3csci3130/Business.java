package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format.
 * @author  Haofan Hou
 * @version 1.0
 * @since   2018-03-06
 */

public class Business implements Serializable {

    public  String bno;
    public  String name;
    public  String business;
    public  String address;
    public  String province;

    /**
     * This method is a default constructor required for calls to DataSnapshot.getValue.
     */
    public Business() {
    }

    /**
     * This method is a constructor.
     * @param bno Business number.
     * @param name Name.
     * @param business Primary business type.
     * @param address Address.
     * @param province Province or territory.
     */
    public Business(String bno, String name, String business, String address, String province) {
        this.bno = bno;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    /**
     * This method builds a HashMap to store the information of a business.
     * @return HashMap that stores the information of a business.
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bno", bno);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
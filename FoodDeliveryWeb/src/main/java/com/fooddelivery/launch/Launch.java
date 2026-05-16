package com.fooddelivery.launch;

import com.fooddelivery.util.DBConnection;

public class Launch {
    public static void main(String[] args) {
        System.out.println(DBConnection.getDBConnection());
    }
}
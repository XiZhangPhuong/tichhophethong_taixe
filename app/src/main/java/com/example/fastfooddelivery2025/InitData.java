package com.example.fastfooddelivery2025;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InitData {
    public static DatabaseReference referenceAccountEmployee = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("AccountEmployee");
    public static DatabaseReference referenceHistory = FirebaseDatabase.getInstance().getReference("History");
    public static DatabaseReference referenceOrder = FirebaseDatabase.getInstance().getReference("Order");
    public static DatabaseReference referenceHelpCenter = FirebaseDatabase.getInstance().getReference("HelpCenter");
    public static String KeyUser = "Key_Staff";
}

package com.springboot.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.springboot.dto.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    private static final String COLLECTION_NAME = "orders";

    public void saveOrder(Order order) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = db.collection(COLLECTION_NAME).document().set(order);

        apiFuture.get();
    }

    public List<Order> getAllOrders() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = db.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();

        List<Order> orders = new ArrayList<>();
        while(iterator.hasNext()){
            DocumentReference dr = iterator.next();
            ApiFuture<DocumentSnapshot> future = dr.get();
            DocumentSnapshot documentSnapshot = future.get();

            if(documentSnapshot.exists()){
                orders.add(documentSnapshot.toObject(Order.class));
            }
        }

        return orders;
    }

    public List<Order> getOrderDetailsByIdBuyer(String idBuyer) throws ExecutionException, InterruptedException {
        List<Order> orders = getAllOrders();

        List<Order> orderList = new ArrayList<>();
        for(Order order: orders){
            if(order.getIdBuyer().equals(idBuyer))
                orderList.add(order);
        }
        return orderList;
    }

    public String deleteOrderByIdOrder(String idOrder){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = db.collection(COLLECTION_NAME).document(idOrder).delete();

        return "Order with id " + idOrder + " has been deleted";
    }
}

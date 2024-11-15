package ec.edu.uisrael.apiorders.data;

import com.github.javafaker.Faker;

import java.util.HashMap;

public class Storage {

    private static Storage instance;
    private HashMap<String, Order> orders;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
            instance.buildFakeData();
        }
        return instance;
    }

    private void buildFakeData() {
        Faker faker = new Faker();
        orders = new HashMap<>();
        for (int x = 0; x < 20; x++) {
        }
    }

    public HashMap<String, Order> getData() {
        return orders;
    }

    public Order getDataByKey(String key) {
        return orders.get(key);
    }

    public Order addElement(String key,Order orden) {
        orders.put(key, orden);
        return orders.get(key);
    }

}

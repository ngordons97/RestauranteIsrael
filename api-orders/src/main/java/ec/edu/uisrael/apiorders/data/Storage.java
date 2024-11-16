package ec.edu.uisrael.apiorders.data;

import com.github.javafaker.Faker;
import ec.edu.uisrael.constants.Constants;

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
        for (int x = 1; x < 3; x++) {
            String id= "000000000"+x;
            orders.put(id,Order.builder()
                    .orderId(id)
                    .state(Constants.STATE_PENDING).build()
            );
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
    public Order updateElement(String key,Order orden) {
        orders.replace(key, orden);
        return orders.get(key);
    }

    public void removeElement(String key) {
        orders.remove(key);
    }

}

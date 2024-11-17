package ec.edu.uisrael.apiproducts.data;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FakeData {

    private static FakeData instance;
    private HashMap<String, Product> products;

    public static FakeData getInstance() {
        if (instance == null) {
            instance = new FakeData();
            instance.buildFakeData();
        }
        return instance;
    }

    private void buildFakeData() {
        Faker faker = new Faker();
        products = new HashMap<>();
        List<String> entradasBase = Arrays.asList("Ensalada César", "Sopa de Tomate", "Croquetas", "Gazpacho", "Spring Rolls");
        List<String> platosFuertesBase = Arrays.asList("Paella", "Asado de Cordero", "Lasagna", "Pollo al Curry", "Filete Mignon");

        for (int x = 0; x < 4; x++) {
            String id = UUID.randomUUID().toString();
            products.put(id, Product.builder()
                    .id(id)
                    .type("ENTRADAS")
                    .name(entradasBase.get(x))
                    .cost(price(2.0, 5.0, faker))
                    .stock(100)
                    .build()
            );
            id = UUID.randomUUID().toString();
            products.put(id, Product.builder()
                    .id(id)
                    .type("PLATO_FUERTE")
                    .name(platosFuertesBase.get(x))
                    .cost(price(3.0, 10.0, faker))
                    .stock(100)
                    .build()
            );
        }
    }

    private Double price(Double min, Double max, Faker faker) {
        Double cost = min + faker.random().nextDouble() * (max - min);
        return (Math.round(cost * 100.0) / 100.0);
    }

    public HashMap<String, Product> getData() {
        return products;
    }

    public HashMap<String, Product> addData(Product product) {
        products.put(product.getId(), product);
        return products;
    }

    public void removeData(String id) {
        products.remove(id);
    }

    public Product updateData(String id,Product product) {
        products.replace(id, product);
        return products.get(id);
    }

    public Product updateDataById(String id,Product product) {
        products.replace(id, product);
        return products.get(id);
    }
    public Product getDataByKey(String key) {
        return products.get(key);
    }
}

package ec.edu.uisrael.apiperson.data;

import com.github.javafaker.Faker;
import ec.edu.uisrael.dto.Person;

import java.util.HashMap;

public class FakeData {

    private static FakeData instance;
    private HashMap<String, Person> persons;

    public static FakeData getInstance() {
        if (instance == null) {
            instance = new FakeData();
            instance.buildFakeData();
        }
        return instance;
    }

    private void buildFakeData() {
        Faker faker = new Faker();
        persons = new HashMap<>();
        for (int x = 0; x < 20; x++) {
            String dni=faker.idNumber().valid();
            persons.put(dni, Person.builder()
                    .dni(dni)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .build()
            );
        }
    }

    public HashMap<String, Person> getData() {
        return persons;
    }

    public Person getDataByKey(String key) {
        return persons.get(key);
    }

    public Person addElement(String key,Person person) {
        persons.put(key, person);
        return persons.get(key);
    }

    public Person updateElement(String key,Person person) {
        persons.replace(key, person);
        return persons.get(key);
    }

    public void deleteElement(String key,Person person) {
        persons.remove(key);
    }
}

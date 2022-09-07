package com.tdd.tddTest.api.restTemplate;

import com.tdd.tddTest.domain.car.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("http GET request, get single car by id")
    public void testGetCarById() {
        Car car = restTemplate.getForObject(restTemplate.getRootUri() + "/cars/1", Car.class);
        System.out.println(car.getCarName());
        assertNotNull(car);
    }

    @Test
    @DisplayName("http GET request, get all cars")
    public void testGetAllCars() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/cars",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("http POST request")
    public void testCreateCar() {
        Car car = new Car();
        car.setCarName("Prius");
        car.setDoors(4);

        ResponseEntity<Car> postResponse = restTemplate.postForEntity(restTemplate.getRootUri() + "/cars", car, Car.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @DisplayName("http PUT request")
    public void testUpdateCar() {
        int id = 1;
        Car car = restTemplate.getForObject(restTemplate.getRootUri() + "/cars/" + id, Car.class);
        car.setCarName("Tesla");
        car.setDoors(2);

        restTemplate.put(restTemplate.getRootUri() + "/cars/" + id, car);

        Car updatedCar = restTemplate.getForObject(restTemplate.getRootUri() + "/cars/" + id, Car.class);
        assertNotNull(updatedCar);
    }
}

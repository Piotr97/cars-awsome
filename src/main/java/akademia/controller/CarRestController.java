package akademia.controller;

import akademia.model.Car;
import akademia.repository.CarRepository;
import akademia.service.CarService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@CrossOrigin //łączenie localhost z localhost
@RestController
public class CarRestController {

    private CarService carService;

    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/api/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/api/cars/{id}")
    public Car getCars(@PathVariable long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/api/cars")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PutMapping("/api/cars/{id}")
    public Car updateCarType(@PathVariable long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/api/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable long id) {
        return carService.deleteCar(id);
    }

}

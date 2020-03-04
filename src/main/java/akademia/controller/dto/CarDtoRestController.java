package akademia.controller.dto;


import akademia.parser.CreatorXLS;
import akademia.model.dto.CarDto;
import akademia.service.dto.CarDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
@CrossOrigin //łączenie localhost z localhost
@RestController
public class CarDtoRestController {

    private CarDtoService carDtoService;

    public CarDtoRestController(CarDtoService carDtoService) {
        this.carDtoService = carDtoService;
    }

    @GetMapping("/api/cars/dto")
    public List<CarDto> getCars() {
        return carDtoService.getCars();
    }

    @PostMapping("/api/cars/dto")
    public ResponseEntity<CarDto> createCar (@RequestBody CarDto carDto) {
        CarDto result = carDtoService.create(carDto);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); //Bruce Lee says
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //
    @GetMapping("/api/cars/dto/xls")
    public List<CarDto> getCarsToXlsFile() {

        List<CarDto> series = carDtoService.getCars();
        CreatorXLS<CarDto> creatorXLS = new CreatorXLS<>(CarDto.class);
        try {
            creatorXLS.createFile(series,"src/main/resources/","cars");
        } catch (NoSuchMethodException | IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return series;
    }
}

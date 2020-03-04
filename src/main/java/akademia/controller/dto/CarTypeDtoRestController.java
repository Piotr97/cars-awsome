package akademia.controller.dto;


import akademia.parser.CreatorXLS;
import akademia.model.dto.CarTypeDto;
import akademia.service.dto.CarTypeDtoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class CarTypeDtoRestController {

    private CarTypeDtoService carTypeDtoService;

    public CarTypeDtoRestController(CarTypeDtoService carTypeDtoService) {
        this.carTypeDtoService = carTypeDtoService;
    }

    @GetMapping("/api/cars/type/dto")
    public List<CarTypeDto> getCarTypes() {
        return carTypeDtoService.getCarTypes();
    }

    @GetMapping("/api/cars/type/dto/xls")
    public List<CarTypeDto> getCarsToXlsFile() {

        List<CarTypeDto> series = carTypeDtoService.getCarTypes();
        CreatorXLS<CarTypeDto> creatorXLS = new CreatorXLS<>(CarTypeDto.class);
        try {
            creatorXLS.createFile(series,"src/main/resources/","carTypes");
        } catch (NoSuchMethodException | IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return series;
    }


}

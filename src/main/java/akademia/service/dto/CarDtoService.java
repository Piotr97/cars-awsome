package akademia.service.dto;

import akademia.mapper.CarMapper;
import akademia.mapper.CarTypeDtoMapper;
import akademia.model.Car;
import akademia.model.CarType;
import akademia.model.dto.CarDto;
import akademia.repository.CarRepository;
import akademia.repository.CarTypeRepository;
import akademia.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarDtoService {

    private static final Logger logger = LoggerFactory.getLogger(CarDtoService.class);

    private CarRepository carRepository;
    private CarMapper carMapper;
    private CarTypeDtoMapper carTypeDtoMapper;
    private CarTypeRepository carTypeRepository;
    private TagRepository tagRepository;


    public CarDtoService(CarRepository carRepository, CarMapper carMapper, CarTypeRepository carTypeRepository, TagRepository tagRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.carTypeRepository = carTypeRepository;
        this.tagRepository = tagRepository;
    }

    public List<CarDto> getCars() {
        List<CarDto> carDtos = new ArrayList<>();
        carRepository.findAll().forEach(c -> {
            carDtos.add(carMapper.map(c));
        });
        return carDtos;
    }

    public CarDto create(CarDto carDto) {
        logger.info("Received cardDto {}", carDto);
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setDescribe(carDto.getDescribe());
        car.setProductionYear(carDto.getProductionYear());

        //todo sposob 1
        //   Optional<CarType> carOptional = carTypeRepository.findCarTypeByTitleOptional(carDto.getCarType());
        //   carOptional.ifPresent(car::setCarType);

        //todo sposob 2
        CarType carType = carTypeRepository.findCarTypeByTitle(carDto.getCarType());
        if(carType == null) {
            logger.error("Error while getting carType!");
            return null;
        }

        car.setCarType(carType);
        logger.info("Try to save Car to DB {}", car);
        Car result = carRepository.save(car);
        if(result == null) {
            logger.error("Error while save car to data base!");
            return null;
        }
        return carMapper.map(car);
    }
    //todo pełny CRUD na reszte API
}

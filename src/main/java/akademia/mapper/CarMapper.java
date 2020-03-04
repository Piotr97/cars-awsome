package akademia.mapper;

import akademia.model.Car;
import akademia.model.dto.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements Mapper<Car, CarDto>{

    @Override
    public CarDto map(Car from) {
        return new CarDto
                .Builder()
                .brand(from.getBrand())
                .carType(from.getCarType().getTitle())
                .describe(from.getDescribe())
                .model(from.getModel())
                .productionYear(from.getProductionYear())
                .build();
    }

    @Override
    public Car revers(CarDto to) {
        return null;
    }
}

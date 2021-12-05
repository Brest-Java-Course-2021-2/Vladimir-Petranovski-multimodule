package com.epam.brest.controller.validator;

import com.epam.brest.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.model.constant.CarConstants.CAR_MODEL_SIZE;

@Component
public class CarValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Car.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "carModel", "carModel.empty", "Please provide car model");
        ValidationUtils.rejectIfEmpty(errors, "driverId", "driverId.empty", "Please provide driver's unique number");

         Car car = (Car) o;

         if(StringUtils.hasLength(car.getCarModel()) && car.getCarModel().length() > CAR_MODEL_SIZE) {
             errors.rejectValue("carModel", "carModel.max.size", "Car name size have to be <= 20 symbols");
         }
    }
}

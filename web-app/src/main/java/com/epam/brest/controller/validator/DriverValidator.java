package com.epam.brest.controller.validator;

import com.epam.brest.model.Driver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.model.constant.DriverConstants.DRIVER_NAME_SIZE;

@Component
public class DriverValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Driver.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "driverName", "driverName.empty");
        ValidationUtils.rejectIfEmpty(errors, "driverDateStartWork", "driverDateStartWork.empty");
        ValidationUtils.rejectIfEmpty(errors, "driverSalary", "driverSalary.empty", "Please provide driver salary");
        Driver driver = (Driver) target;

        if (StringUtils.hasLength(driver.getDriverName())
                && driver.getDriverName().length() > DRIVER_NAME_SIZE) {
            errors.rejectValue("driverName", "driverName.maxSize");
        }
    }
}

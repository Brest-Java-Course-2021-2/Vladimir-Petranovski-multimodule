package com.epam.brest.controller.validator;

import com.epam.brest.model.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.model.constant.DriverConstants.DRIVER_NAME_SIZE;

@Component
public final class DriverValidator implements Validator {

    public static final Logger LOG = LogManager.getLogger(
            DriverValidator.class);

    @Override
    public boolean supports(final Class<?> clazz) {
        LOG.info("Method supports()"
                + "started in {}", getClass().getName());
        return Driver.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        LOG.info("Method validate()"
                + "started in {}", getClass().getName());

        ValidationUtils.rejectIfEmpty(errors,
                "driverName", "driverName.empty");
        ValidationUtils.rejectIfEmpty(errors,
                "driverDateStartWork",
                "driverDateStartWork.empty");
        ValidationUtils.rejectIfEmpty(errors,
                "driverSalary", "driverSalary.empty",
                "Please provide driver salary");
        Driver driver = (Driver) target;

        if (StringUtils.hasLength(driver.getDriverName())
                && driver.getDriverName().length() > DRIVER_NAME_SIZE) {
            errors.rejectValue("driverName", "driverName.maxSize");
        }
    }
}

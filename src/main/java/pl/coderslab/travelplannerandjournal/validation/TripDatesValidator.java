package pl.coderslab.travelplannerandjournal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.coderslab.travelplannerandjournal.model.dto.TripRequest;

import java.time.LocalDate;

public class TripDatesValidator implements ConstraintValidator<ValidTripDates, TripRequest> {
    @Override
    public boolean isValid(TripRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }
        
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        if(startDate == null || endDate == null) {
            return true;
        }

        return (endDate.isAfter(startDate) || endDate.isEqual(startDate));
    }
}

package ryan.task2.interconnectingflights.api;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LegTest {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @Test
    public void shouldThrowExceptionWhenDepartureAirportLengthIsGreaterThan3() {
        //given
        Leg leg = new Leg.Builder().departureAirport("AAAB").build();

        //when
        Set<ConstraintViolation<Leg>> constraintViolations = validator.validate(leg);

        //then
        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 3", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenDepartureAirportLengthIsLessThan3() {
        //given
        Leg leg = new Leg.Builder().departureAirport("AA").build();

        //when
        Set<ConstraintViolation<Leg>> constraintViolations = validator.validate(leg);

        //then
        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 3", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenArrivalAirportLengthIsGreaterThan3() {
        //given
        Leg leg = new Leg.Builder().arrivalAirport("AAAB").build();

        //when
        Set<ConstraintViolation<Leg>> constraintViolations = validator.validate(leg);

        //then
        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 3", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenArrivalAirportLengthIsLessThan3() {
        //given
        Leg leg = new Leg.Builder().arrivalAirport("AA").build();

        //when
        Set<ConstraintViolation<Leg>> constraintViolations = validator.validate(leg);

        //then
        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 3", constraintViolations.iterator().next().getMessage());
    }
}
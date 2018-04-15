package seedu.address.logic.parser;

import static junit.framework.TestCase.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.appointment.Appointment;

//@@author chenxing1992
public class AddAppointmentParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AddAppointmentParser parser = new AddAppointmentParser();

    @Test
    public void prefixesNotPresent() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("1 lunch tomorrow 5pm");
    }

    @Test
    public void illegalExpression() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("n/@@@@ d/2018/02/10 10:10");
    }

    @Test
    public void nonParsableString() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("appt d/lunch ,cant parse this string");
    }
    @Test
    public void parseDateExpression() throws ParseException, java.text.ParseException {

        AddAppointmentCommand command = parser.parse("appt 1 d/Lunch, tomorrow 5pm");
        Appointment appointment = AddAppointmentParser.getAppointmentFromString("Lunch, tomorrow 5pm");
        assertEquals(new AddAppointmentCommand(appointment), command);

    }

    @Test
    public void parseAppointmentsWithDuration() {

        try {
            AddAppointmentCommand command = parser.parse("appt 1 d/Lunch, tomorrow 5pm to 7pm");
            Appointment appointment = AddAppointmentParser.getAppointmentFromString("Lunch, tomorrow 5pm to 7pm");
            assertEquals(new AddAppointmentCommand(appointment), command);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

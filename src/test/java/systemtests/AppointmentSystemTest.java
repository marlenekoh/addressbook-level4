package systemtests;

import static org.junit.Assert.assertEquals;

import seedu.address.model.Model;




//@@author chenxing1992
public class AppointmentSystemTest extends AddressBookSystemTest {
    /**
    @Test
    public void addAndRemoveAppointment() throws Exception {
        Model model = getModel();
        ReadOnlyPerson toAddAppointment = model.getAddressBook().getPersonList().get(0);
        String description = "dinner";
        String str = " 1 d/" + description + ", tonight 7pm to 10pm";
        String command = AddAppointmentCommand.COMMAND_WORD + str;
        assertCommandSuccess(command, toAddAppointment, AddAppointmentParser.getAppointmentFromString(str));

        command = CancelAppointmentCommand.COMMAND_WORD + " " + description + " with " + toAddAppointment.getName();
        assertCommandSuccess(command, toAddAppointment, AddAppointmentParser.getAppointmentFromString(str));
    }*/

    /**
    @Test
    public void changeCalendarView() {
        assertCommandSuccess(CalendarViewCommand.COMMAND_WORD + " d", CalendarViewCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(CalendarViewCommand.COMMAND_WORD + " w", CalendarViewCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(CalendarViewCommand.COMMAND_WORD + " m", CalendarViewCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(CalendarViewCommand.COMMAND_WORD + " y", CalendarViewCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(CalendarViewCommand.COMMAND_WORD + " q",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalendarViewCommand.MESSAGE_USAGE));
    }*/

    /**
     * Performs verification that calendarview changed
     */
    private void assertCommandSuccess(String command, String message) {
        executeCommand(command);
        assertEquals(getResultDisplay().getText() , message);
    }

    /**
     * Performs verification that the expected model is the same after command is executing

    private void assertCommandSuccess(String command, ReadOnlyPerson toAdd, appointment appointment) {
        Model expectedModel = getModel();
        String expectedResultMessage;

        try {
            if (!command.contains("cancel")) {
                expectedModel.addAppointment(toAdd, appointment);
                expectedResultMessage = AddAppointmentCommand.MESSAGE_SUCCESS;
            } else {
                expectedModel.removeAppointment(toAdd, appointment);
                expectedResultMessage = CancelAppointmentCommand.MESSAGE_SUCCESS;
            }
        } catch (PersonNotFoundException e) {
            throw new IllegalArgumentException("person not found in model.");
        }

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }
     */
    /**
     * Performs the same verification as {@code assertCommandSuccess(String, ReadOnlyPerson)} except that the result
     * display box displays {@code expectedResultMessage} and the model related components equal to
     * {@code expectedModel}.
     *
     * //@see AppointmentSystemTest#assertCommandSuccess(String, ReadOnlyPerson, appointment)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
    }

}

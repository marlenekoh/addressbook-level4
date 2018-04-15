package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Calendar;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.exceptions.PersonNotFoundException;

//@@author chenxing1992

/**
 * Command to add appointment to a person in addressBook
 */
public class AddAppointmentCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "appointment";
    public static final String COMMAND_ALIAS = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to a person in address book. \n"
            + COMMAND_ALIAS + ": Shorthand equivalent for add. \n"
            + "Parameters: " + COMMAND_WORD + " INDEX "
            + PREFIX_DATE + "DESCRIPTION, TIME" + "\n"
            + "Example 1:" + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "Lunch, Next Monday 3pm";

    public static final String MESSAGE_SUCCESS = "New appointment added. ";
    public static final String INVALID_PERSON = "This person is not in your address book";
    public static final String INVALID_DATE = "Invalid Date. Please enter a valid date.";
    public static final String DUPLICATE_APPT = "Duplicate Event found on same timing. Please select other time.";

    private final Appointment appointment;

    public AddAppointmentCommand(Appointment appointment) {
        this.appointment = appointment;
    }

    //@@author chenxing1992
    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {

        ReadOnlyPerson personToAddAppointment = model.getPartner();

        if (appointment.getDate() != null && !isDateValid()) {
            return new CommandResult(INVALID_DATE);
        }

        try {
            model.addAppointment(personToAddAppointment, appointment);
        } catch (PersonNotFoundException e) {
            return new CommandResult(INVALID_PERSON);
        }

        return new CommandResult(MESSAGE_SUCCESS);

    }
    //@@author chenxing1992

    /**
     * Checks if appointment date set to after current time
     */
    private boolean isDateValid() {
        requireNonNull(appointment);
        Calendar calendar = Calendar.getInstance();
        return !appointment.getDate().before(calendar.getTime());
    }

    //@@author chenxing1992
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && (this.appointment.equals(((AddAppointmentCommand) other).appointment)));
    }

}

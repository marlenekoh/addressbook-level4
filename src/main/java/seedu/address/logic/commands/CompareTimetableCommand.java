package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMETABLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.timetable.Timetable;
import seedu.address.model.person.timetable.TimetableUtil;

/**
 * Compares the partner's timetable with a given timetable
 */
public class CompareTimetableCommand extends Command {

    public static final String COMMAND_WORD = "tcompare";
    public static final String COMMAND_ALIAS = "tc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Compares your partner's timetable with a given timetable.\n"
            + "Parameters: " + PREFIX_TIMETABLE + "TIMETABLE_URL\n"
            + "Example: " + COMMAND_WORD + " http://modsn.us/wNuIW";

    public static final String MESSAGE_TIMETABLE_COMPARE_SUCCESS = "Compared timetable";
    public static final String MESSAGE_TIMETABLE_COMPARE_FAILURE = "Invalid timetable provided.\n";

    private ReadOnlyPerson partner;
    private Timetable otherTimetable;

    public CompareTimetableCommand(Timetable otherTimetable) {
        this.otherTimetable = otherTimetable;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            partner = model.getPartner();
            if (partner == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException npe) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        if (otherTimetable == null) {
            throw new CommandException(MESSAGE_TIMETABLE_COMPARE_FAILURE);
        }
        otherTimetable = TimetableUtil.setUpTimetableInfoCompare(partner.getTimetable(), otherTimetable);

        model.indicateTimetableChanged(otherTimetable);
        model.requestShowTimetable();
        return new CommandResult(MESSAGE_TIMETABLE_COMPARE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompareTimetableCommand // instanceof handles nulls
                && this.otherTimetable.equals(((CompareTimetableCommand) other).otherTimetable)); // state check
    }
}

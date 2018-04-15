package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;

//@@author marlenekoh
/**
 * Deselects your partner from NUSCouples.
 */
public class ShowCalendarCommand extends Command {
    public static final String COMMAND_WORD = "cview";
    public static final String COMMAND_ALIAS = "cv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows your calendar view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DESELECT_PERSON_SUCCESS = "Calendar view displayed.";
    public static final String MESSAGE_DESELECT_PERSON_FAILURE = "Calendar view is already displayed.";

    private ReadOnlyPerson partner;

    public ShowCalendarCommand() {
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            partner = model.getPartner();
            partner.getTimetable();
            model.requestHideTimetable();
        } catch (NullPointerException npe) {
            throw new CommandException(MESSAGE_DESELECT_PERSON_FAILURE);
        }
        return new CommandResult(MESSAGE_DESELECT_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCalendarCommand // instanceof handles nulls
                && this.partner.equals(((ShowCalendarCommand) other).partner)); // state check
    }

}

package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.timetable.Timetable;
import seedu.address.model.person.timetable.TimetableUtil;

/**
 * Selects your partner from NUSCouples.
 */
public class ShowTimetableCommand extends Command {

    public static final String COMMAND_WORD = "tview";
    public static final String COMMAND_ALIAS = "tv";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows your partner's NUSMods timetable.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SELECT_PERSON_SUCCESS = "NUSMods timetable displayed.";
    private ReadOnlyPerson partner;

    public ShowTimetableCommand() { }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            partner = model.getPartner();
            Timetable timetable = partner.getTimetable();
            TimetableUtil.setUpTimetableInfoView(timetable);

            model.indicateTimetableChanged(timetable);
            model.requestShowTimetable();
        } catch (NullPointerException e) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
        return new CommandResult(MESSAGE_SELECT_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowTimetableCommand); // instanceof handles nulls
    }

}

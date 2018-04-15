package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON;
import static seedu.address.logic.commands.CompareTimetableCommand.MESSAGE_TIMETABLE_COMPARE_FAILURE;
import static seedu.address.logic.commands.CompareTimetableCommand.MESSAGE_TIMETABLE_COMPARE_SUCCESS;
import static seedu.address.testutil.TypicalJournalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.getTypicalPerson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ShowTimetableRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.timetable.Timetable;
import seedu.address.ui.testutil.EventsCollectorRule;

//@@author marlenekoh
public class CompareTimetableCommandTest {

    private static final String VALID_SHORT_URL = "http://modsn.us/wNuIW";

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model withPartnerModel;
    private Model noPartnerModel;

    @Before
    public void setUp() {
        withPartnerModel = new ModelManager(getTypicalPerson(), getTypicalJournal(), new UserPrefs());
        noPartnerModel = new ModelManager(null, getTypicalJournal(), new UserPrefs());
    }

    @Test
    public void execute_success() throws IllegalArgumentException {
        Timetable timetable = new Timetable(VALID_SHORT_URL);
        CompareTimetableCommand compareTimetableCommand = prepareCommand(withPartnerModel, timetable);

        try {
            CommandResult result = compareTimetableCommand.execute();
            assertEquals(MESSAGE_TIMETABLE_COMPARE_SUCCESS, result.feedbackToUser);
        } catch (CommandException ce) {
            throw new IllegalArgumentException("Execution of command should not fail.", ce);
        }
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowTimetableRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 2);
    }

    @Test
    public void execute_failure() throws IllegalArgumentException {
        Timetable timetable = new Timetable(VALID_SHORT_URL);
        assertExecutionFailure(noPartnerModel, timetable, MESSAGE_INVALID_PERSON);
        assertExecutionFailure(noPartnerModel, null, MESSAGE_INVALID_PERSON);
        assertExecutionFailure(withPartnerModel, null, MESSAGE_TIMETABLE_COMPARE_FAILURE);
    }

    @Test
    public void equals() {
        Timetable timetable = new Timetable(VALID_SHORT_URL);
        CompareTimetableCommand compareTimetableCommand = new CompareTimetableCommand(timetable);

        // different types -> returns false
        assertFalse(compareTimetableCommand.equals(1));

        // null -> returns false
        assertFalse(compareTimetableCommand.equals(null));
    }

    /**
     * Executes a {@code CompareTimetableCommand} with the given {@code model} and {@code timetable},
     * and checks that a {@code CommandException} is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Model model, Timetable timetable, String expectedMessage) {
        CompareTimetableCommand compareTimetableCommand = prepareCommand(model, timetable);

        try {
            compareTimetableCommand.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(expectedMessage, ce.getMessage());
            assertTrue(eventsCollectorRule.eventsCollector.isEmpty());
        }
    }

    /**
     * Returns a {@code CompareTimetableCommand} with new CommandHistory and new UndoRedoStack.
     * @param myModel either contains null partner or valid partner
     * @param timetable the other person's timetable to compare with
     */
    private CompareTimetableCommand prepareCommand(Model myModel, Timetable timetable) {
        CompareTimetableCommand compareTimetableCommand = new CompareTimetableCommand(timetable);
        compareTimetableCommand.setData(myModel, new CommandHistory(), new UndoRedoStack());
        return compareTimetableCommand;
    }
}

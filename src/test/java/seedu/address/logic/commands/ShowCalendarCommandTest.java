package seedu.address.logic.commands;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static seedu.address.testutil.TypicalJournalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.getTypicalPerson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.HideTimetableRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.testutil.EventsCollectorRule;

//@@author marlenekoh
/**
 * Contains integration tests (interaction with the Model) for {@code ShowCalendarCommand}.
 */
public class ShowCalendarCommandTest {
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
    public void equals() {
        ShowCalendarCommand showCalendarCommand = new ShowCalendarCommand();

        // different types -> returns false
        assertFalse(showCalendarCommand.equals(1));

        // null -> returns false
        assertFalse(showCalendarCommand.equals(null));
    }

    @Test
    public void execute_success() throws IllegalArgumentException {
        ShowCalendarCommand showCalendarCommand = prepareCommand(withPartnerModel);

        try {
            CommandResult commandResult = showCalendarCommand.execute();
            assertEquals(showCalendarCommand.MESSAGE_DESELECT_PERSON_SUCCESS, commandResult.feedbackToUser);
        } catch (CommandException ce) {
            throw new IllegalArgumentException("Execution of command should not fail.", ce);
        }

        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof HideTimetableRequestEvent);
    }

    @Test
    public void execute_failure() throws IllegalArgumentException {
        ShowCalendarCommand showCalendarCommand = prepareCommand(noPartnerModel);

        try {
            showCalendarCommand.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(showCalendarCommand.MESSAGE_DESELECT_PERSON_FAILURE, ce.getMessage());
            assertTrue(eventsCollectorRule.eventsCollector.isEmpty());
        }
    }

    /**
     * Returns a {@code ShowTimetableCommand} with new CommandHistory and new UndoRedoStack.
     */
    private ShowCalendarCommand prepareCommand(Model myModel) {
        ShowCalendarCommand showCalendarCommand = new ShowCalendarCommand();
        showCalendarCommand.setData(myModel, new CommandHistory(), new UndoRedoStack());
        return showCalendarCommand;
    }
}

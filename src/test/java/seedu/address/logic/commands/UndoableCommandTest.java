package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static seedu.address.testutil.TypicalJournalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.getTypicalPerson;

import org.junit.Ignore;
import org.junit.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

@Ignore
public class UndoableCommandTest {
    private final Model model = new ModelManager(getTypicalPerson(), getTypicalJournal(), new UserPrefs());
    private final DummyCommand dummyCommand = new DummyCommand(model);

    private Model expectedModel = new ModelManager(null , getTypicalJournal(), new UserPrefs());

    @Test
    public void executeUndo() throws Exception {
        dummyCommand.execute();
        expectedModel.deletePerson();
        assertEquals(expectedModel, model);

        // undo() should undo delete person
        dummyCommand.undo();
        expectedModel = new ModelManager(getTypicalPerson(), getTypicalJournal(), new UserPrefs());
        assertEquals(expectedModel, model);
    }

    @Test
    public void redo() {

        dummyCommand.redo();
        expectedModel.deletePerson();
        assertEquals(expectedModel, model);
    }

    /**
     * Deletes the person in the model.
     */
    class DummyCommand extends UndoableCommand {
        DummyCommand(Model model) {
            this.model = model;
        }

        @Override
        public CommandResult executeUndoableCommand() throws CommandException {
            try {
                model.deletePerson();
            } catch (NullPointerException npe) {
                fail("Impossible: personToDelete was retrieved from model.");
            }
            return new CommandResult("");
        }
    }
}

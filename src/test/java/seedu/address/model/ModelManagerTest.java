package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalJournalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.person.Person;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //TODO: FIND OUT WHAT THIS IS
    @Ignore
    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        ModelManager modelManager = new ModelManager();
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getPersonAsList().remove(0);
    }

    @Test
    public void equals() {
        Person person = new Person(ALICE);
        Journal journal = getTypicalJournal();
        Person differentPerson = new Person(AMY);
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        ModelManager modelManager = new ModelManager(person, journal, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(person, journal, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different person -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPerson, journal, userPrefs)));

        // empty journal -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPerson, new Journal(), userPrefs)));

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setNusCouplesName("differentName");
        assertTrue(modelManager.equals(new ModelManager(person, journal, differentUserPrefs)));
    }
}

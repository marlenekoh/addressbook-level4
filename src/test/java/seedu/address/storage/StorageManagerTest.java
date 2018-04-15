package seedu.address.storage;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPerson;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.events.model.PersonChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.ui.testutil.EventsCollectorRule;

public class StorageManagerTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private StorageManager storageManager;

    @Before
    public void setUp() {
        XmlPersonStorage personStorage = new XmlPersonStorage(getTempFilePath("p"));
        XmlJournalStorage journalStorage = new XmlJournalStorage(getTempFilePath("j"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        FileTimetableStorage timetableStorage = new FileTimetableStorage(getTempFilePath("1"),
                getTempFilePath("2"), getTempFilePath("3"));
        storageManager = new StorageManager(personStorage, journalStorage, userPrefsStorage, timetableStorage);
    }

    private String getTempFilePath(String fileName) {
        return testFolder.getRoot().getPath() + fileName;
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(300, 600, 4, 6);
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void personReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link XmlPersonStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link XmlAddressBookStorageTest} class.
         */
        Person original = getTypicalPerson();

        storageManager.savePerson(original);
        ReadOnlyPerson retrieved = storageManager.readPerson().get();
        assertEquals(original, new Person(retrieved));
    }

    @Test
    public void getPersonFilePath() {
        assertNotNull(storageManager.getPersonFilePath());
    }

    @Test
    public void handlePersonChangedEvent_exceptionThrown_eventRaised() {
        // Create a StorageManager while injecting a stub that  throws an exception when the save method is called
        Storage storage = new StorageManager(new XmlAddressBookStorageExceptionThrowingStub(
                "dummy"), new XmlJournalStorage("Dummy"), new JsonUserPrefsStorage("dummy"),
                new FileTimetableStorage("dummy1", "dummy2",
                        "dummy3"));
        storage.handlePersonChangedEvent(new PersonChangedEvent(new Person(ALICE)));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof DataSavingExceptionEvent);
    }


    /**
     * A Stub class to throw an exception when the save method is called
     */
    class XmlAddressBookStorageExceptionThrowingStub extends XmlPersonStorage {

        public XmlAddressBookStorageExceptionThrowingStub(String filePath) {
            super(filePath);
        }

        @Override
        public void savePerson(ReadOnlyPerson person, String filePath) throws IOException {
            throw new IOException("dummy exception");
        }
    }

    /**
     * A Stub class to throw an exception when the save method is called
     */
    class XmlJournalStorageExceptionThrowingStub extends XmlJournalStorage {

        public XmlJournalStorageExceptionThrowingStub(String filePath) {
            super(filePath);
        }

        @Override
        public void saveJournal(ReadOnlyJournal readOnlyJournal, String filePath) throws IOException {
            throw new IOException("dummy exception");
        }
    }


}

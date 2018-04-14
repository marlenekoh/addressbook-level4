# chenxing1992
###### /java/systemtests/AppointmentSystemTest.java
``` java
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

    private void assertCommandSuccess(String command, ReadOnlyPerson toAdd, Appointment appointment) {
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
     * //@see AppointmentSystemTest#assertCommandSuccess(String, ReadOnlyPerson, Appointment)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
    }

}
```
###### /java/seedu/address/ui/BrowserPanelTest.java
``` java
    @Before
    public void setUp() {
        selectionChangedEventStub = new PersonPanelSelectionChangedEvent(new PersonCard(ALICE, 0));

        guiRobot.interact(() -> browserPanel =
                new BrowserPanel(TypicalPersons.getTypicalAddressBook().getPersonList()));
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Ignore
    @Test
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of a person
        postNow(selectionChangedEventStub);
        URL expectedPersonUrl = new URL(BrowserPanel.SEARCH_PAGE_URL + ALICE.getName().fullName.replaceAll(" ", "%20"));

        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedPersonUrl, browserPanelHandle.getLoadedUrl());
    }
}
```
###### /java/seedu/address/logic/parser/AddAppointmentParserTest.java
``` java
public class AddAppointmentParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AddAppointmentParser parser = new AddAppointmentParser();

    @Test
    public void prefixesNotPresent() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("1 lunch tomorrow 5pm");
    }

    @Test
    public void illegalExpression() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("n/@@@@ d/2018/02/10 10:10");
    }

    @Test
    public void nonParsableString() throws ParseException {
        thrown.expect(ParseException.class);
        parser.parse("appt 1 d/lunch ,cant parse this string");
    }
    @Test
    public void parseDateExpression() throws ParseException, java.text.ParseException {

        AddAppointmentCommand command = parser.parse("appt 1 d/Lunch, tomorrow 5pm");
        Appointment appointment = AddAppointmentParser.getAppointmentFromString("Lunch, tomorrow 5pm");
        assertEquals(new AddAppointmentCommand(Index.fromOneBased(1), appointment), command);

    }

    @Test
    public void parseAppointmentsWithDuration() {

        try {
            AddAppointmentCommand command = parser.parse("appt 1 d/Lunch, tomorrow 5pm to 7pm");
            Appointment appointment = AddAppointmentParser.getAppointmentFromString("Lunch, tomorrow 5pm to 7pm");
            assertEquals(new AddAppointmentCommand(Index.fromOneBased(1), appointment), command);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
```
###### /java/seedu/address/logic/parser/CancelAppointmentParserTest.java
``` java
public class CancelAppointmentParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseException() throws ParseException {
        String str = "this cant be parsed";
        CancelAppointmentParser parser = new CancelAppointmentParser();
        thrown.expect(ParseException.class);
        parser.parse(str);
    }

    @Test
    public void parseWithoutKeyWordWith() throws ParseException {
        String str = "Lunch Alice Pauline";
        CancelAppointmentParser parser = new CancelAppointmentParser();
        thrown.expect(ParseException.class);
        parser.parse(str);
    }

    @Test
    public void parseSuccess() throws ParseException {
        String str = "Lunch with Alice Pauline";
        CancelAppointmentParser parser = new CancelAppointmentParser();
        CancelAppointmentCommand command = (CancelAppointmentCommand) parser.parse(str);
        CancelAppointmentCommand command2 = new CancelAppointmentCommand("Alice Pauline", "Lunch");

        assertEquals(command, command2);

    }
}
```
###### /java/seedu/address/logic/commands/AddAppointmentCommandTest.java
``` java
public class AddAppointmentCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void equals() throws ParseException, seedu.address.logic.parser.exceptions.ParseException {
        String arg = "Lunch, tomorrow 5pm";

        AddAppointmentCommand command = new AddAppointmentCommand(Index.fromOneBased(1), setAppointment(arg));
        AddAppointmentCommand command2 = new AddAppointmentCommand(Index.fromOneBased(1), setAppointment(arg));
        assertEquals(command, command2);
        assertNotEquals(command, new AddAppointmentCommand(Index.fromOneBased(2), setAppointment(arg)));
    }

    @Test
    public void execute() throws ParseException, CommandException {

        Index index1 = Index.fromOneBased(1);
        try {
            //Invalid date
            String arg = "lunch, yesterday 5pm";
            Command command = setCommand(index1, setAppointment(arg));
            CommandResult result = command.execute();
            assertEquals(result.feedbackToUser, AddAppointmentCommand.INVALID_DATE);

            //Set to valid date
            arg = "lunch, tomorrow 5pm";
            command = setCommand(index1, setAppointment(arg));
            result = command.execute();
            assertEquals(result.feedbackToUser, AddAppointmentCommand.MESSAGE_SUCCESS);

            //Set to valid date with end time
            arg = "lunch, tomorrow 5pm to 7pm";
            command = setCommand(index1, setAppointment(arg));
            result = command.execute();
            assertEquals(result.feedbackToUser, AddAppointmentCommand.MESSAGE_SUCCESS);
        } catch (seedu.address.logic.parser.exceptions.ParseException ive) {
            fail();
        }

    }

    @Test
    public void outOfBoundsIndex() throws CommandException, seedu.address.logic.parser.exceptions.ParseException {
        thrown.expect(CommandException.class);
        setCommand(Index.fromOneBased(100),
                AddAppointmentParser.getAppointmentFromString("lunch,tomorrow 5pm")).execute();
    }

    /**
     * Util methods to set appointment command
     */
    private Command setCommand(Index index, Appointment appointment) {
        AddAppointmentCommand command = new AddAppointmentCommand(index, appointment);
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalJournal(), new UserPrefs());
        command.setData(model);
        return command;
    }

    private Appointment setAppointment(String str) throws seedu.address.logic.parser.exceptions.ParseException {
        return AddAppointmentParser.getAppointmentFromString(str);
    }

}
```
###### /java/seedu/address/logic/commands/AddCommandTest.java
``` java
        @Override
        public void addAppointment(ReadOnlyPerson person, Appointment appointment) throws PersonNotFoundException {
            fail("This method should not be called");
        }

```
###### /java/seedu/address/logic/commands/AddCommandTest.java
``` java
        @Override
        public void removeAppointment(ReadOnlyPerson target, Appointment appointment) throws PersonNotFoundException {
            fail("This method should not be called");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ReadOnlyJournal getJournal() {
            fail("This method should not be called.");
            return null;
        }
```
###### /java/seedu/address/logic/commands/AddCommandTest.java
``` java
        @Override
        public void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }



        @Override
        public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ObservableList<JournalEntry> getJournalEntryList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate) {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always throw a DuplicatePersonException when trying to add a person.
     */
    private class ModelStubThrowingDuplicatePersonException extends ModelStub {
        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<ReadOnlyPerson> personsAdded = new ArrayList<>();

        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
```

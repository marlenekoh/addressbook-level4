# marlenekoh
###### /java/seedu/address/model/person/timetable/TimetableParserUtilTest.java
``` java
public class TimetableParserUtilTest {

    public static final String VALID_LONG_URL = "https://nusmods.com/timetable/sem-2/"
            + "share?CS2101=SEC:C01&CS2103T=TUT:C01&CS3230=LEC:1,TUT:4&"
            + "CS3241=LAB:3,LEC:1,TUT:3&CS3247=LAB:1,LEC:1&GES1021=LEC:SL1";
    public static final String VALID_SHORT_URL = "http://modsn.us/wNuIW";
    public static final String INVALID_SHORT_URL = "http://modsn.us/123";
    private static final int CURRENT_SEMESTER = 2;
    private static HashMap<String, TimetableModule> expectedListOfModules;

    @Before
    public void setUp() {
        expectedListOfModules = new HashMap<String, TimetableModule>();
        HashMap<String, String> tempLessonPair;

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Sectional Teaching", "C01");
        expectedListOfModules.put("CS2101", new TimetableModule("CS2101",
                tempLessonPair));

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Tutorial", "C01");
        expectedListOfModules.put("CS2103T", new TimetableModule("CS2103T",
                tempLessonPair));

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Lecture", "1");
        tempLessonPair.put("Tutorial", "4");
        expectedListOfModules.put("CS3230", new TimetableModule("CS3230",
                tempLessonPair));

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Laboratory", "3");
        tempLessonPair.put("Lecture", "1");
        tempLessonPair.put("Tutorial", "3");
        expectedListOfModules.put("CS3241", new TimetableModule("CS3241",
                tempLessonPair));

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Laboratory", "1");
        tempLessonPair.put("Lecture", "1");
        expectedListOfModules.put("CS3247", new TimetableModule("CS3247",
                tempLessonPair));

        tempLessonPair = new HashMap<String, String>();
        tempLessonPair.put("Lecture", "SL1");
        expectedListOfModules.put("GES1021", new TimetableModule("GES1021",
                tempLessonPair));
    }

    @Test
    public void expandShortTimetableUrl_invalidShortUrl_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("")))); // empty string
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("www.google.com")))); // invalid host
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("http://www.facebook.com")))); // invalid host
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("http://www.modsn.us/")))); // invalid host
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("http://www.modsn.us/q7cLP")))); // invalid host
        Assert.assertThrows(IllegalArgumentException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(
                        new Timetable(("http://www.modsn.us/")))); // code-part needs at least 1 character
    }

    @Test
    public void expandShortTimetableUrl_validUrl() throws ParseException {
        Timetable timetable = new Timetable(VALID_SHORT_URL);
        TimetableParserUtil.setExpandedTimetableUrl(timetable);
        assertEquals(timetable.getExpandedUrl(), VALID_LONG_URL);
    }

    @Ignore
    @Test
    public void expandShortTimetableUrl_invalidUrl_throwsParseException() {
        //TODO: Fix this bug
        Assert.assertThrows(ParseException.class, () ->
                TimetableParserUtil.setExpandedTimetableUrl(new Timetable(INVALID_SHORT_URL)));
    }

    @Test
    public void setUpTimetableInfo() {
        Timetable actualTimetable = new Timetable(VALID_SHORT_URL);

        Assert.assertDoesNotThrow(() -> TimetableParserUtil.setExpandedTimetableUrl(actualTimetable));
        assertEquals(VALID_LONG_URL, actualTimetable.getExpandedUrl());
        assertEquals(expectedListOfModules, actualTimetable.getListOfModules());
        assertEquals(CURRENT_SEMESTER, actualTimetable.getCurrentSemester());
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableDisplayUtilTest.java
``` java
public class TimetableDisplayUtilTest {
    private static final String EXPECTED_FILE_CONTENTS_PATH = "src/test/data/sandbox/expectedTimetableDisplayInfo";
    private Timetable timetable = new Timetable(VALID_SHORT_URL);

    @Test
    public void writeToTimetableFile_assertsSameFileContents() {
        //TODO: Write TimetableDisplayUtilTest
        TimetableDisplayUtil.setUpTimetableDisplayInfo(timetable);
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableTest.java
``` java
public class TimetableTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Timetable(null));
    }

    @Test
    public void constructor_invalidTimetable_throwsIllegalArgumentException() {
        String invalidTimetable = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Timetable(invalidTimetable));
    }

    @Test
    public void isValidTimetable() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Timetable.isValidTimetable(null));

        // invalid timetables
        assertFalse(Timetable.isValidTimetable("")); // empty string
        assertFalse(Timetable.isValidTimetable(" ")); // spaces only
        assertFalse(Timetable.isValidTimetable("www.google.com")); // invalid host
        assertFalse(Timetable.isValidTimetable("http://www.facebook.com")); // invalid host
        assertFalse(Timetable.isValidTimetable("http://www.modsn.us/")); // invalid host
        assertFalse(Timetable.isValidTimetable("http://www.modsn.us/q7cLP")); // invalid host
        assertFalse(Timetable.isValidTimetable("http://modsn.us/")); // code-part needs at least 1 character

        // valid timetables
        assertTrue(Timetable.isValidTimetable("http://modsn.us/wNuIW"));
        assertTrue(Timetable.isValidTimetable("http://modsn.us/q7cLP")); // code-part can be alphanumeric
    }
}
```
###### /java/seedu/address/testutil/Assert.java
``` java
    /**
     * Asserts that the {@code callable} does not throw any exception.
     */
    public static void assertDoesNotThrow(VoidCallable callable) {
        try {
            callable.call();
        } catch (Throwable unexpectedException) {
            String errorMessage = String.format("Expected nothing thrown, however %s thrown",
                    unexpectedException.getMessage());
            throw new AssertionFailedError(errorMessage);
        }
    }
}
```

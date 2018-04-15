package seedu.address.model.person.timetable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

//@@author marlenekoh
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

    @Test
    public void  equals_assertsTrue() {
        Timetable timetable = new Timetable("http://modsn.us/wNuIW");
        Timetable timetableCopy = new Timetable("http://modsn.us/wNuIW");

        // same short NUSMods URL -> returns true
        assertTrue(timetable.equals(timetableCopy));

        // same object -> returns true
        assertTrue(timetable.equals(timetable));

        // different attributes other than value -> returns true
        // different timetableDisplayInfo, different listOfDays
        timetableCopy = TimetableUtil.setUpTimetableInfoCompare(timetable, timetableCopy);
        assertTrue(timetable.equals(timetableCopy));
    }

    @Test
    public void  equals_assertsFalse() {
        Timetable timetable = new Timetable("http://modsn.us/wNuIW");
        Timetable differentTimetable = new Timetable("http://modsn.us/q7cLP");

        // null -> returns false
        assertFalse(timetable.equals(null));

        // different short NUSMods URL -> returns false
        assertFalse(timetable.equals(differentTimetable));
    }

    @Test
    public void toString_assertEquals() {
        Timetable timetable = new Timetable("http://modsn.us/wNuIW");
        assertEquals("http://modsn.us/wNuIW", timetable.value);

    }
}

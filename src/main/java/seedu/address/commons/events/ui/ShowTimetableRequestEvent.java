package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

//@@author marlenekoh
/**
 * An event requesting to view the partner's timetable.
 */
public class ShowTimetableRequestEvent extends BaseEvent {

    public ShowTimetableRequestEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

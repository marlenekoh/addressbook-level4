package seedu.address.ui;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.PersonChangedEvent;
import seedu.address.commons.events.ui.CalendarViewEvent;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.appointment.Appointment;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String TIMETABLE_PAGE = "TimetablePage.html";
    public static final String SEARCH_PAGE_URL =
            "https://se-edu.github.io/addressbook-level4/DummySearchPage.html?name=";

    private static final String JAR_DATA_FILE_FOLDER = "/data/";
    private static final String INTELLIJ_DATA_FILE_FOLDER = "\\data\\";
    private static final String FILE_PREFIX = "file:";
    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    @FXML
    private CalendarView calendarView;
    private ReadOnlyPerson partner;

    //@@author chenxing1992
    public BrowserPanel(ReadOnlyPerson partner) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        this.partner = partner;

        calendarView = new CalendarView();
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        if (partner != null) {
            updateCalendar();
        }

        disableViews();
        registerAsAnEventHandler(this);

    }

    //@@author chenxing1992

    /**
     * Remove clutter from interface
     */
    private void disableViews() {
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowSearchResultsTray(false);
        calendarView.setShowPrintButton(false);
        calendarView.showDayPage();
    }

    /**
     * Explicitly set the Root object to CalendarView
     */
    //@@author chenxing1992
    public CalendarView getCalendarRoot() {
        return this.calendarView;
    }

    /**
     * Changes calendar view accordingly
     */
    private void showPage(Character c) {
        switch(c) {
        case ('d'):
            calendarView.showDayPage();
            return;
        case ('w'):
            calendarView.showWeekPage();
            return;
        case ('m'):
            calendarView.showMonthPage();
            return;
        case ('y'):
            calendarView.showYearPage();
            return;
        default:
        //should not reach here
        assert (false);
        }
    }
    //@@author chenxing1992
    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        calendarView.getCalendarSources().clear();
    }

    //@@author chenxing1992
    private Calendar getCalendar(int styleNum, ReadOnlyPerson person) {
        Calendar calendar = new Calendar(person.getName().toString());
        calendar.setStyle(Calendar.Style.getStyle(styleNum));
        calendar.setLookAheadDuration(Duration.ofDays(365));
        return calendar;
    }

    //@@author chenxing1992
    private ArrayList<Entry> getEntries(ReadOnlyPerson person) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (Appointment appointment : person.getAppointments()) {
            LocalDateTime ldtstart = LocalDateTime.ofInstant(appointment.getDate().toInstant(),
                    ZoneId.systemDefault());
            LocalDateTime ldtend = LocalDateTime.ofInstant(appointment.getEndDate().toInstant(),
                    ZoneId.systemDefault());

            entries.add(new Entry(appointment.getDescription() + " with " + person.getName(),
                    new Interval(ldtstart, ldtend)));
        }
        return entries;
    }

    //@@author chenxing1992

    /**
     * Creates a new a calendar with the update information
     */
    private void updateCalendar() {
        try {
            setTime();
            CalendarSource calendarSource = new CalendarSource("Appointments");
            int styleNum = 0;
            Calendar calendar = getCalendar(styleNum,  partner);
            calendarSource.getCalendars().add(calendar);
            ArrayList<Entry> entries = getEntries(partner);
            for (Entry entry : entries) {
                calendar.addEntry(entry);
            }
            calendarView.getCalendarSources().add(calendarSource);
        } catch (NullPointerException npe) {
            return;
        }
    }
    //@@author chenxing1992
    @Subscribe
    private void handleCalendarViewEvent(CalendarViewEvent event) {
        Character c = event.c;
        Platform.runLater(() -> showPage(c));
    }

    //@@author marlenekoh
    /**
     * Loads the timetable page of a person into browser panel.
     */
    public void loadTimetablePage() {
        String timetablePageUrl;
        if (runningFromIntelliJ()) {
            timetablePageUrl = FILE_PREFIX + getIntellijRootDir() + INTELLIJ_DATA_FILE_FOLDER + TIMETABLE_PAGE;
        } else {
            timetablePageUrl = getJarDir() + JAR_DATA_FILE_FOLDER + TIMETABLE_PAGE;
        }
        loadPage(timetablePageUrl);
    }

    /**
     * Gets the directory containing the root folder
     * @return a String containing the directory path
     */
    private String getIntellijRootDir() {
        return System.getProperty("user.dir");
    }

    /**
     * Gets the directory containing the executing jar.
     * @return a String containing the directory path
     */
    private String getJarDir() {
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        try {
            jarPath = URLDecoder.decode(jarPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warning("The Character Encoding is not supported.");
        }
        return jarPath.substring(0, jarPath.lastIndexOf('/'));
    }

    /**
     * Checks if the current code is running from IntelliJ (for debugging) or from the jar file.
     * @return true if running from IntelliJ
     */
    private static boolean runningFromIntelliJ() {
        String classPath = System.getProperty("java.class.path");
        Logger logger = LogsCenter.getLogger(MainApp.class);
        return classPath.contains("idea_rt.jar");
    }

    //@@author
    private void loadPersonPage(ReadOnlyPerson person) {
        loadPage(SEARCH_PAGE_URL + person.getName().fullName);
    }

    private void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    public void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    @Subscribe
    private void handleNewAppointmentEvent(PersonChangedEvent event) {
        Platform.runLater(
                this::updateCalendar
        );
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

}

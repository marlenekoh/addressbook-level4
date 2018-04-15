package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static seedu.address.testutil.Assert.assertDoesNotThrow;
import static seedu.address.testutil.TestUtil.getFilePathInSandboxFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import seedu.address.MainApp;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.util.SampleDataUtil;

//@@author marlenekoh
public class FileTimetableStorageTest {

    private static final String TEST_PATH = getFilePathInSandboxFolder("testReadingWriting");

    private static final String EXPECTED_TIMETABLE_PAGE_HTML_PATH = "TimetableTest/expectedTimetablePage.html";
    private static final String EXPECTED_TIMETABLE_DISPLAY_INFO_FILE_PATH =
            "TimetableTest/expectedTimetableDisplayInfoView";
    private static final String TIMETABLE_PAGE_HTML_PATH = getFilePathInSandboxFolder("TimetablePage.html");
    private static final String TIMETABLE_PAGE_CSS_PATH = getFilePathInSandboxFolder("TimetableStyle.css");
    private static final String TIMETABLE_DISPLAY_INFO_FILE_PATH =
            getFilePathInSandboxFolder("timetableDisplayInfo");
    private static String expectedTimetableDisplayInfoContents;
    private static String expectedTimetableHtmlContents;
    private FileTimetableStorage fileTimetableStorage;

    @Before
    public void initialize() throws IOException {
        fileTimetableStorage = new FileTimetableStorage(TIMETABLE_PAGE_HTML_PATH, TIMETABLE_PAGE_CSS_PATH,
                TIMETABLE_DISPLAY_INFO_FILE_PATH);
        File testFile = new File(TEST_PATH);
        File file1 = new File(TIMETABLE_PAGE_HTML_PATH);
        File file2 = new File(TIMETABLE_PAGE_CSS_PATH);
        File file3 = new File(TIMETABLE_DISPLAY_INFO_FILE_PATH);

        PrintWriter writer = new PrintWriter(testFile);
        writer.print("hello 123\n");
        writer.close();

        writer = new PrintWriter(file1);
        writer.print("");
        writer.close();

        writer = new PrintWriter(file2);
        writer.print("");
        writer.close();

        writer = new PrintWriter(file3);
        writer.print("");
        writer.close();

        URL timetableDisplayInfoViewUrl = getTestFileUrl(EXPECTED_TIMETABLE_DISPLAY_INFO_FILE_PATH);
        expectedTimetableDisplayInfoContents = Resources.toString(timetableDisplayInfoViewUrl, Charsets.UTF_8);

        URL timetablePageUrl = getTestFileUrl(EXPECTED_TIMETABLE_PAGE_HTML_PATH);
        expectedTimetableHtmlContents = Resources.toString(timetablePageUrl, Charsets.UTF_8);

    }

    @Test
    public void setUpTimetableDisplayFiles() throws IOException {
        fileTimetableStorage.setUpTimetableDisplayFiles(expectedTimetableDisplayInfoContents);
        assertEquals(expectedTimetableHtmlContents,
                FileUtil.readFromFile(new File(TIMETABLE_PAGE_HTML_PATH)));
        assertEquals(expectedTimetableDisplayInfoContents,
                FileUtil.readFromFile(new File(TIMETABLE_DISPLAY_INFO_FILE_PATH)));
        assertEquals(SampleDataUtil.getDefaultTimetablePageCss(),
                FileUtil.readFromFile(new File(TIMETABLE_PAGE_CSS_PATH)));
    }

    @Test
    public void writeToFile() throws IOException {
        String toWrite = "hello 123\n";
        fileTimetableStorage.writeToFile(toWrite, TEST_PATH);
        File testFile = new File(TEST_PATH);
        assertDoesNotThrow(() -> FileUtil.readFromFile(testFile));
        assertEquals(toWrite, FileUtil.readFromFile(testFile));
    }

    @Test
    public void getFileContents() throws FileNotFoundException {
        String expected = "hello 123\n";
        String actual = fileTimetableStorage.getFileContents(TEST_PATH);
        assertDoesNotThrow(() -> fileTimetableStorage.getFileContents(TEST_PATH));
        assertEquals(expected, actual);
    }

    @Test
    public void replaceLineExcludingStartEnd() {
        String contents = "This is a story about the weather.\n"
                + "Today it rained and the ground became muddy.\n"
                + "After the rain stopped, the sun came out.\n";
        String replace = "Yesterday it didn't rain at all and";
        String start = "Today";
        String end = ",";

        String expected = "This is a story about the weather.\n"
                + "Yesterday it didn't rain at all and the sun came out.\n";
        String result = fileTimetableStorage.replaceLineExcludingStartEnd(contents, replace, start, end);
        assertEquals(expected, result);
    }


    /**
     * Returns an url to the test resource
     * @param testFilePath path of the test resource
     */
    private URL getTestFileUrl(String testFilePath) {
        String testFilePathInView = "/view/" + testFilePath;
        URL testFileUrl = MainApp.class.getResource(testFilePathInView);
        assertNotNull(testFilePathInView + " does not exist.", testFileUrl);
        return testFileUrl;
    }
}

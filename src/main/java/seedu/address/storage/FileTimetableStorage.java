package seedu.address.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.util.SampleDataUtil;

//@@author marlenekoh
/**
 * A class to write and read Timetable data stored on the hard disk.
 */
public class FileTimetableStorage implements TimetableStorage {
    private static final Logger logger = LogsCenter.getLogger(FileTimetableStorage.class);

    private String timetablePageHtmlPath;
    private String timetablePageCssPath;
    private String timetableInfoFilePath;

    public FileTimetableStorage(String timetablePageHtmlPath, String timetablePageCssPath,
                                String timetableInfoFilePath) {
        this.timetablePageHtmlPath = timetablePageHtmlPath;
        this.timetablePageCssPath = timetablePageCssPath;
        this.timetableInfoFilePath = timetableInfoFilePath;
    }

    public String getTimetablePageHtmlPath() {
        return timetablePageHtmlPath;
    }

    public String getTimetableInfoFilePath() {
        return timetableInfoFilePath;
    }

    public String getTimetablePageCssPath() {
        return timetablePageCssPath;
    }

    @Override
    public void setUpTimetableDisplayFiles(String toWrite) {
        writeToFile(toWrite, timetableInfoFilePath);
        createTimetablePageCssFile();
        setUpTimetablePageHtmlFile();
    }

    @Override
    public void createTimetablePageCssFile() {
        try {
            writeToFile(SampleDataUtil.getDefaultTimetablePageCss(), timetablePageCssPath);
        } catch (IOException e) {
            logger.severe("Unable to get default timetable style");
        }
    }

    @Override
    public void setUpTimetablePageHtmlFile() {
        try {
            writeToFile(SampleDataUtil.getDefaultTimetablePageHtml(), timetablePageHtmlPath);
            String oldContent = getFileContents(timetablePageHtmlPath);
            String toReplace = getFileContents(timetableInfoFilePath);
            String newContent = replaceLine(oldContent, toReplace, "timetable", "];");
            writeToFile(newContent, timetablePageHtmlPath);
        } catch (FileNotFoundException e) {
            logger.warning("File not found");
        } catch (IOException e) {
            logger.severe("Unable to get default timetable page");
        }
    }

    @Override
    public void writeToFile(String toWrite, String path) {
        File file = new File(path);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(toWrite);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.warning("File not found");
        }
    }

    @Override
    public String getFileContents(String path) throws FileNotFoundException {
        File file = new File(path);
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                br.close();
                return sb.toString();
            } else {
                throw new FileNotFoundException("File does not exist");
            }
        } catch (IOException e) {
            logger.warning("Exception in reading file");
        }
        return null;
    }

    @Override
    public String replaceLine(String contents, String replace, String startLine, String endLine) {
        StringBuilder sb = new StringBuilder();
        int startPos = contents.indexOf(startLine);
        int endPos = contents.indexOf(endLine);
        sb.append(contents.substring(0, startPos));
        sb.append(replace);
        sb.append(contents.substring(endPos + 2));
        return sb.toString();
    }
}

# marlenekoh
###### /resources/view/TimetableStyle.css
``` css
body {
    font-family: "Arial";
}

th,td {
    margin: 0;
    text-align: center;
    border-collapse: collapse;
    -webkit-column-width: 100px; /* Chrome, Safari, Opera */
    -moz-column-width: 100px; /* Firefox */
    column-width: 100px;
    outline: 1px solid #e3e3e3;
}

td {
    padding: 5px 10px;
}

th {
    background: #666;
    color: white;
    padding: 5px 10px;
}
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    /**
     * Loads the timetable page of a person into browser panel
     */
    public void loadTimetablePage() {
        URL timetablePage = MainApp.class.getResource(FXML_FILE_FOLDER + TIMETABLE_PAGE);
        loadPage(timetablePage.toExternalForm());
    }

```
###### /java/seedu/address/ui/MainWindow.java
``` java
    /**
     * Replaces the Calendar with Timetable Page in Browser Panel
     */
    public void handleShowTimetable() {
        browserPanel.loadTimetablePage();
        browserPlaceholder.getChildren().add(browserPanel.getRoot());
    }

    /**
     * Replaces the Timetable Page with Calendar in Browser Panel
     */
    public void handleHideTimetable() {
        browserPlaceholder.getChildren().clear();
        browserPlaceholder.getChildren().add(browserPanel.getCalendarRoot());
    }

```
###### /java/seedu/address/ui/MainWindow.java
``` java
    @Subscribe
    private void handleShowTimetableRequestEvent(ShowTimetableRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleShowTimetable();
    }

    @Subscribe
    private void handleHideTimetableRequestEvent(HideTimetableRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleHideTimetable();
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleShowTimetable();
    }
}
```
###### /java/seedu/address/commons/events/ui/ShowTimetableRequestEvent.java
``` java
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
```
###### /java/seedu/address/commons/events/ui/HideTimetableRequestEvent.java
``` java
/**
 * An event requesting to view the partner's timetable.
 */
public class HideTimetableRequestEvent extends BaseEvent {

    public HideTimetableRequestEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableComparatorUtil.java
``` java

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class containing utility methods for comparing two timetables
 */
public class TimetableComparatorUtil {

    /**
     * Overloads compareTimetable method.
     * @param first Timetable to compare with
     * @param second String containing valid short NUSMods URL
     */
    public static void compareTimetable(Timetable first, String second) {
        compareTimetable(first, new Timetable(second));
    }

    /**
     * Set ups files for comparing.
     * @param first Timetable to compare
     * @param second Timetable to compare
     */
    public static void compareTimetable(Timetable first, Timetable second) {
        ArrayList<TimetableModuleSlot> allUnsortedModulesSlots =
                TimetableDisplayUtil.setUpUnsortedModuleSlotsForComparing(first, second);
        HashMap<String, ArrayList<TimetableModuleSlot>> sortedModuleSlots =
                TimetableParserUtil.sortModuleSlotsByDay(allUnsortedModulesSlots);
        String timetableString = TimetableDisplayUtil.convertTimetableToString(sortedModuleSlots);
        TimetableDisplayUtil.setUpTimetableDisplayInfoFile(timetableString);
        TimetableDisplayUtil.setUpTimetablePageScriptFile();
    }

}
```
###### /java/seedu/address/model/person/timetable/TimetableDisplayUtil.java
``` java
/**
 * A class containing utility methods for displaying a Timetable in the browser panel.
 */
public class TimetableDisplayUtil {
    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    public static final String[] TIMES = {
        "0800", "0830", "0900", "0930", "1000", "1030", "1100", "1130",
        "1200", "1230", "1300", "1330", "1400", "1430", "1500", "1530",
        "1600", "1630", "1700", "1730", "1800", "1830", "1900", "1930",
        "2000", "2030", "2100", "2130", "2200", "2230", "2300", "2330"
    };
    public static final String[] WEEKS = {"Odd Week", "Even Week", "Every Week"};
    private static String timetableInfoFilePath = "src/main/resources/timetableDisplayInfo";
    private static String timetablePageJsPath = "src/main/resources/view/TimetablePageScript.js";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
```
###### /java/seedu/address/model/person/timetable/TimetableDisplayUtil.java
``` java
            + "timetable = [\n"
            + "];\n"
            + "var myTimetable = \"\";\n"
            + "var nRows = \"\";\n"
            + "var nCells = \"\";\n"
            + "function displaySchedule(){\n"
            + "    for (i=0; i<nRows; i++) {\n"
            + "        for (n=0; n<nCells; n++) {\n"
            + "            myTimetable.rows[i+1].cells[n+1].lastChild.data = timetable[n+(i*nCells)];\n"
            + "        }\n"
            + "    }\n"
            + "}\n"
            + "function mapTable(){\n"
            + "    myTimetable = document.getElementById('myTimetable');\n"
            + "    nRows = myTimetable.rows.length-1;\n"
            + "    nCells = myTimetable.rows[0].cells.length-1;\n"
            + "    displaySchedule();\n"
            + "}\n"
            + "onload=mapTable;\n";

    /**
     * Sets up the javascript file at path {@code timetablePageJsPath}
     * @param timetable Timetable to be set up
     */
    public static void setUpTimetableDisplayInfo(Timetable timetable) {
        setUpTimetableDisplayInfoFile(timetable);
        setUpTimetablePageScriptFile();
    }

    /**
     * Updates TimetablePageScript file at path {@code timetablePageJsPath} with new timetable module information
     */
    public static void setUpTimetablePageScriptFile() {
        try {
            String oldContent = getFileContents(timetablePageJsPath);
            String toReplace = getFileContents(timetableInfoFilePath);
            String newContent = replaceFirstLine(oldContent, toReplace);
            writeToTimetablePageScriptFile(newContent);
        } catch (FileNotFoundException e) {
            timetablePageJsPath = "data/TimetablePageScript.js";
            writeToTimetablePageScriptFile(DEFAULT_TIMETABLE_PAGE_SCRIPT);
        }
    }

    /**
     * Writes Timetable information to a text file
     * @param timetable the timetable to convert into string and write
     */
    public static void setUpTimetableDisplayInfoFile(Timetable timetable) {
        File timetableDisplayInfo = new File(timetableInfoFilePath);
        try {
            PrintWriter printWriter = new PrintWriter(timetableDisplayInfo);
            String toWrite = convertTimetableToString(timetable);
            printWriter.write(toWrite);
            timetable.setTimetableDisplayInfo(toWrite);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.warning("File not found, creating new file");
            try {
                timetableInfoFilePath = "data/timetableDisplayInfo";
                timetableDisplayInfo = new File(timetableInfoFilePath);
                timetableDisplayInfo.createNewFile();
                setUpTimetableDisplayInfo(timetable);
            } catch (IOException ioe) {
                logger.severe("Unable to create new file");
            }
        }
    }

    /**
     * Writes a string to the file at {@code timetableInfoFilePath}
     * @param toWrite the String to write
     */
    public static void setUpTimetableDisplayInfoFile(String toWrite) {
        File timetableDisplayInfo = new File(timetableInfoFilePath);
        try {
            PrintWriter printWriter = new PrintWriter(timetableDisplayInfo);
            printWriter.write(toWrite);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.warning("File not found, creating new file");
            try {
                timetableInfoFilePath = "data/timetableDisplayInfo";
                timetableDisplayInfo = new File(timetableInfoFilePath);
                timetableDisplayInfo.createNewFile();
                setUpTimetableDisplayInfoFile(toWrite);
            } catch (IOException ioe) {
                logger.severe("Unable to create new file");
            }
        }
    }

    /**
     * Processes the given timetable for viewing.
     * @param timetable to view
     * @return an ArrayList containing the combined {@code TimetableModuleSlots} from both Timetables.
     */
    public static ArrayList<TimetableModuleSlot> setUpUnsortedModuleSlotsForViewing(Timetable timetable) {
        ArrayList<TimetableModuleSlot> allUnsortedModulesSlots = timetable.getAllModulesSlots();

        for (TimetableModuleSlot t : allUnsortedModulesSlots) {
            t.setComparing(false);
        }
        return allUnsortedModulesSlots;
    }

    /**
     * Combines the two lists of {@code TimetableModuleSlots} from each timetable and process them for comparing.
     * @param first Timetable to compare
     * @param second Timetable to compare
     * @return an ArrayList containing the combined {@code TimetableModuleSlots} from both Timetables.
     */
    public static ArrayList<TimetableModuleSlot> setUpUnsortedModuleSlotsForComparing(Timetable first,
                                                                                      Timetable second) {
        ArrayList<TimetableModuleSlot> allUnsortedModulesSlots = new ArrayList<TimetableModuleSlot>();
        allUnsortedModulesSlots.addAll(first.getAllModulesSlots());
        allUnsortedModulesSlots.addAll(second.getAllModulesSlots());

        for (TimetableModuleSlot t : allUnsortedModulesSlots) {
            t.setComparing(true);
        }
        return allUnsortedModulesSlots;
    }

    /**
     * Converts the {@code listOfDays} belonging to {@code timetable} into a String object for parsing
     * @param timetable which contains schedule to convert into JSON object
     */
    public static String convertTimetableToString(Timetable timetable) {
        return convertTimetableToString(timetable.getListOfDays());
    }

    /**
     * Converts the {@code listOfDays} into a String object for parsing
     * @param listOfDays ArrayLists of timetableModuleSlots sorted by time
     */
    public static String convertTimetableToString(HashMap<String, ArrayList<TimetableModuleSlot>> listOfDays) {
        StringBuilder sb = new StringBuilder();

        ArrayList<TimetableModuleSlot> slotsForTheDay = null;
        for (int i = 0; i < TIMES.length; i++) {
            if (i < TIMES.length - 1) {
                sb.append(listOfDays.get(DAYS[MONDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[TUESDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[WEDNESDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[THURSDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[FRIDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
            } else {
                sb.append(listOfDays.get(DAYS[MONDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[TUESDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[WEDNESDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[THURSDAY_INDEX].toUpperCase()).get(i).toString());
                sb.append(", ");
                sb.append(listOfDays.get(DAYS[FRIDAY_INDEX].toUpperCase()).get(i).toString());
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Gets file contents from the file at the given path
     * @return String containing file contents
     */
    public static String getFileContents(String path) throws FileNotFoundException {
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

    /**
     * Replaces file contents of file at {@code timetablePageJsPath} with {@code contents}
     * @param contents the new contents of the file
     */
    public static void writeToTimetablePageScriptFile(String contents) {
        File timetablePageScript = new File(timetablePageJsPath);
        try {
            PrintWriter printWriter = new PrintWriter(timetablePageScript);
            printWriter.write(contents);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.warning("File not found");
        }
    }

    /**
     * Replaces first line of {@code contents} with {@code replace}
     * @param contents original content of the javascript file
     * @param replace new line
     * @return new content
     */
    public static String replaceFirstLine(String contents, String replace) {
        StringBuilder sb = new StringBuilder();
```
###### /java/seedu/address/model/person/timetable/TimetableDisplayUtil.java
``` java
        sb.append("timetable = [");
        sb.append(replace);
        int pos = contents.indexOf(';');
        sb.append(contents.substring(pos - 1));
        return sb.toString();
    }
}
```
###### /java/seedu/address/model/person/timetable/Timetable.java
``` java
/**
 * Represents the NUSMODS timetable of the partner
 */
public class Timetable {
    public static final String MESSAGE_TIMETABLE_CONSTRAINTS = "Short NUSMods timetable URL should be of the format "
            + "http://modsn.us/code-part "
            + "and adhere to the following constraints:\n"
            + "1. The URL should start with http://modsn.us/\n"
            + "2. The code-part should not be empty and should only contain alphanumeric characters.";
    private static final String SHORT_NUSMODS_URL_REGEX = "http://modsn.us/";
    private static final String CODE_PART_REGEX = "[\\w]+";
    private static final String TIMETABLE_VALIDATION_REGEX = SHORT_NUSMODS_URL_REGEX + CODE_PART_REGEX;

    public final String value;
    private int currentSemester;
    private HashMap<String, ArrayList<TimetableModuleSlot>>
            listOfDays; // HashMap of <Day, Sorted list of TimetableModuleSlots>
    private HashMap<String, TimetableModule> listOfModules; // HashMap of <module code, TimetableModule>
    private ArrayList<TimetableModuleSlot> allModulesSlots; //ArrayList Containing all TimetableModuleSlots
    private String expandedUrl;
    private String timetableDisplayInfo;


    public Timetable(String timetableUrl) {
        requireNonNull(timetableUrl);
        checkArgument(isValidTimetable(timetableUrl), MESSAGE_TIMETABLE_CONSTRAINTS);
        this.value = timetableUrl;
        TimetableParserUtil.setUpTimetableInfo(this);
        TimetableDisplayUtil.setUpTimetableDisplayInfo(this);
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public void setListOfModules(HashMap<String, TimetableModule> listOfModules) {
        this.listOfModules = listOfModules;
    }

    public HashMap<String, TimetableModule> getListOfModules() {
        return listOfModules;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void setListOfDays(HashMap<String, ArrayList<TimetableModuleSlot>> listOfDays) {
        this.listOfDays = listOfDays;
    }

    public HashMap<String, ArrayList<TimetableModuleSlot>> getListOfDays() {
        return listOfDays;
    }

    public ArrayList<TimetableModuleSlot> getAllModulesSlots() {
        return allModulesSlots;
    }

    public void setAllModulesSlots(ArrayList<TimetableModuleSlot> allModulesSlots) {
        this.allModulesSlots = allModulesSlots;
    }

    public void setTimetableDisplayInfo(String timetableDisplayInfo) {
        this.timetableDisplayInfo = timetableDisplayInfo;
    }

    /**
     * Returns if a given string is a valid short NUSMods timetable URL.
     */
    public static boolean isValidTimetable(String test) {
        return test.matches(TIMETABLE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timetable // instanceof handles nulls
                && this.value.equals(((Timetable) other).value)); // state check
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableParserUtil.java
``` java
/**
 * A class containing utility methods for parsing an NUSMods short URL and setting up a Timetable
 */
public class TimetableParserUtil {
    public static final int MONDAY_INDEX = 0;
    public static final int TUESDAY_INDEX = 1;
    public static final int WEDNESDAY_INDEX = 2;
    public static final int THURSDAY_INDEX = 3;
    public static final int FRIDAY_INDEX = 4;
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private static final String SPLIT_QUESTION_MARK = "\\?";
    private static final String SPLIT_AMPERSAND = "&";
    private static final String SPLIT_EQUALS = "=";
    private static final String SPLIT_COMMA = ",";
    private static final String SPLIT_COLON = ":";
    private static final String REPLACE_NON_DIGIT_CHARACTERS = "[^0-9]";
    private static final String INVALID_SHORT_URL_RESULT = "http://modsn.us";
    private static final String MESSAGE_INVALID_SHORT_URL = "Invalid short NUSMods URL provided.";
    private static final String MESSAGE_INVALID_CLASS_TYPE = "Invalid class type";
    private static final String MESSAGE_INVALID_DAY_TYPE = "Invalid day type";
    private static final String HTTP_METHOD_GET = "GET";
    private static final int HTTP_METHOD_RESPONSE_OK = 200;
    private static final int SEM_NUMBER_INDEX = 0;
    private static final int MODULE_INFORMATION_INDEX = 1;
    private static final int MODULE_CODE_INDEX = 0;
    private static final int MODULE_CODE_REMAINING_INDEX = 1;
    private static final int LESSON_TYPE_INDEX = 0;
    private static final int CLASS_TYPE_INDEX = 1;

    /**
     * Sets up attributes of a given {@code Timetable}.
     * @param timetable Timetable to be set up
     */
    public static void setUpTimetableInfo(Timetable timetable) {
        try {
            setExpandedTimetableUrl(timetable);
            setSemNumFromExpandedUrl(timetable);
            setListOfModules(timetable);
            setListOfDays(timetable);
        } catch (ParseException e) {
            logger.warning(MESSAGE_INVALID_SHORT_URL);
        }
    }

    /**
     * Sets the expanded URL for {@code timetable}.
     * @param timetable Timetable whose expanded URL is to be set
     */
    public static void setExpandedTimetableUrl(Timetable timetable) throws ParseException {
        String expandedUrl = expandShortTimetableUrl(timetable);
        timetable.setExpandedUrl(expandedUrl);
    }

    /**
     * Expands short NUSMods timetable URL to a long NUSMods timetable URL from {@timetable}.
     * @param timetable whose url is to be parsed
     * @return expanded NUSMods timetable URL
     * @throws ParseException if short url provided is invalid short NUSMods timetable URL
     */
    private static String expandShortTimetableUrl(Timetable timetable) throws ParseException {
        String timetableUrl = timetable.value;
        checkArgument(Timetable.isValidTimetable(timetableUrl), Timetable.MESSAGE_TIMETABLE_CONSTRAINTS);
        String expandedUrl = null;
        try {
            final URL shortUrl = new URL(timetableUrl);
            final HttpURLConnection urlConnection = (HttpURLConnection) shortUrl.openConnection();
            urlConnection.setInstanceFollowRedirects(false);
            expandedUrl = urlConnection.getHeaderField("location");

            if (expandedUrl.equals(INVALID_SHORT_URL_RESULT)) {
                throw new ParseException(MESSAGE_INVALID_SHORT_URL);
            }
        } catch (MalformedURLException e) {
            logger.warning("URL provided is malformed");
        } catch (IOException e) {
            logger.warning("Failed to open connection");
        }
        return expandedUrl;
    }

    /**
     * Sets the {@code currentSemester} for {@code timetable}.
     * @param timetable Timetable whose {@code currentSemester} is to be set
     */
    public static void setSemNumFromExpandedUrl(Timetable timetable) {
        timetable.setCurrentSemester(getSemNumFromExpandedUrl(timetable));
    }

    /**
     * Parses for {@code currentSemester} from expandedUrl of {@code timetable}
     * @param timetable whose {@code currentSemester} is to be set
     */
    private static int getSemNumFromExpandedUrl(Timetable timetable) {
        String expandedUrl = timetable.getExpandedUrl();
        requireNonNull(expandedUrl);
        String[] moduleInformation = expandedUrl.split(SPLIT_QUESTION_MARK);
        return Integer.valueOf(moduleInformation[SEM_NUMBER_INDEX]
                .replaceAll(REPLACE_NON_DIGIT_CHARACTERS, ""));
    }

    /**
     * Sets listOfModules in {@code timetable}
     * @param timetable whose long url is to be split
     */
    public static void setListOfModules(Timetable timetable) {
        HashMap<String, TimetableModule> listOfModules = splitExpandedUrl(timetable);
        timetable.setListOfModules(listOfModules);
    }

    /**
     * Splits expanded NUSMods timetable URL into the different {@code TimetableModule}s.
     * Expanded timetable URL is in the format ...?[MODULE_CODE]=[LESSON_TYPE]:[CLASS_NUM]&...
     * @param timetable whose long url is to be split
     * @return HashMap containing list of modules
     */
    private static HashMap<String, TimetableModule> splitExpandedUrl(Timetable timetable) {
        String expandedUrl = timetable.getExpandedUrl();
        requireNonNull(expandedUrl);
        String[] moduleInformation = expandedUrl.split(SPLIT_QUESTION_MARK);
        String[] modules = moduleInformation[MODULE_INFORMATION_INDEX].split(SPLIT_AMPERSAND);

        HashMap<String, TimetableModule> listOfModules = new  HashMap<String, TimetableModule>();
        HashMap<String, String> listOfLessons;
        String moduleCode;
        String lessonType;
        String classType;
        String[] lessons;

        for (String currentModule : modules) {
            listOfLessons = new HashMap<String, String>();

            moduleCode = currentModule.split(SPLIT_EQUALS)[MODULE_CODE_INDEX];
            lessons = currentModule.split(SPLIT_EQUALS)[MODULE_CODE_REMAINING_INDEX].split(SPLIT_COMMA);
            for (String currLesson : lessons) {
                lessonType = currLesson.split(SPLIT_COLON)[LESSON_TYPE_INDEX];
                classType = currLesson.split(SPLIT_COLON)[CLASS_TYPE_INDEX];

                try {
                    listOfLessons.put(convertLessonType(lessonType), classType);
                } catch (IllegalValueException e) {
                    logger.warning("Unable to convert lesson type");
                }
            }
            listOfModules.put(moduleCode, new TimetableModule(moduleCode, listOfLessons));
        }
        return listOfModules;
    }

    /**
     * Sets {@code listOfDays} in {@code timetable} given
     * @param timetable timetable to set List of days
     */
    public static void setListOfDays(Timetable timetable) {
        requireNonNull(timetable.getListOfModules());
        ArrayList<TimetableModuleSlot> allTimetableModuleSlots = retrieveModuleSlotsFromApi(timetable);
        timetable.setAllModulesSlots(allTimetableModuleSlots);
        HashMap<String, ArrayList<TimetableModuleSlot>> sortedTimetableModuleSlots =
                sortModuleSlotsByDay(allTimetableModuleSlots);
        timetable.setListOfDays(sortedTimetableModuleSlots);
    }

    /**
     * Gets module information from nusmods api for the all modules in listOfModules in {@code timetable}
     * @param timetable containing list of all modules
     */
    private static ArrayList<TimetableModuleSlot> retrieveModuleSlotsFromApi(Timetable timetable) {
        String currentModuleInfo;
        ArrayList<TimetableModuleSlot> allTimetableModuleSlots = new ArrayList<TimetableModuleSlot>();
        Set<Map.Entry<String, TimetableModule>> entrySet = timetable.getListOfModules().entrySet();

        for (Map.Entry<String, TimetableModule> currentModule : entrySet) {
            //TODO: Remove magic number acadYear
            currentModuleInfo = getJsonContentsFromNusModsApiForModule("2017-2018",
                    Integer.toString(timetable.getCurrentSemester()), currentModule.getKey());
            allTimetableModuleSlots.addAll(getAllTimetableModuleSlots(currentModuleInfo, timetable,
                    currentModule.getKey()));
        }
        return allTimetableModuleSlots;
    }

    /**
     * Retrieves json file from NUSMods api and converts to String
     * @param acadYear String representing academic year
     * @param semNum String representing semester number
     * @param moduleCode String representing module code
     * Format: http://api.nusmods.com/[acadYear]/[semNum]/modules/[moduleCode].json
     * e.g. http://api.nusmods.com/2017-2018/2/modules/CS3241.json
     * @return String containing contents of json file
     */
    private static String getJsonContentsFromNusModsApiForModule(String acadYear, String semNum, String moduleCode) {
        String contents = null;
        String nusmodsApiUrlString = "http://api.nusmods.com/" + acadYear + "/" + semNum + "/modules/" + moduleCode
                + ".json";
        try {
            URL nusmodsApiUrl = new URL(nusmodsApiUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) nusmodsApiUrl.openConnection();
            urlConnection.setRequestMethod(HTTP_METHOD_GET);
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HTTP_METHOD_RESPONSE_OK) {
                contents = readStream(urlConnection.getInputStream());
            } else {
                contents = "Error in accessing API - " + readStream(urlConnection.getErrorStream());
            }
        } catch (MalformedURLException e) {
            logger.warning("URL provided is malformed");
        } catch (ProtocolException e) {
            logger.warning("Protocol exception");
        } catch (IOException e) {
            logger.warning("Failed to open connection");
        }
        return contents;
    }

    /**
     * Read the responded result and stores in a String
     * @param inputStream from nusmods api
     * @return String containing contents of nusmods api
     * @throws IOException from readLine()
     */
    private static String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;

        while ((inputLine = reader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        reader.close();
        return stringBuilder.toString();
    }

    /**
     * Parses contents of json file contents result from {@code readStream()}
     * @param contents contents of json file in String
     * @param timetable timetable to set list of modules slots
     * @param moduleCode current module
     * @return all TimetableModuleSlots for the timetable
     */
    public static ArrayList<TimetableModuleSlot> getAllTimetableModuleSlots(String contents, Timetable timetable,
                                                               String moduleCode) {
        requireNonNull(timetable.getListOfModules());

        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        ArrayList<TimetableModuleSlot> listOfModuleSlots = new ArrayList<TimetableModuleSlot>();

        try {
            Object obj = parser.parse(contents);
            jsonObject = (JSONObject) obj;
            JSONArray arrOfClassInformation = null;
            Object object = jsonObject.get("Timetable");
            arrOfClassInformation = (JSONArray) object;

            String tempLessonType;
            String tempClassType;
            String tempWeekFreq;
            String tempDay;
            String tempStartTime;
            String tempEndTime;
            String tempVenue;

            HashMap<String, TimetableModule> listOfModules = timetable.getListOfModules();
            TimetableModule timetableModule = listOfModules.get(moduleCode);
            HashMap<String, String> listOfLessons = timetableModule.getListOfLessons();

            for (Object t : arrOfClassInformation) {
                tempLessonType = ((JSONObject) t).get("LessonType").toString();
                tempClassType = ((JSONObject) t).get("ClassNo").toString();

                if (listOfLessons.get(tempLessonType).equals(tempClassType)) {
                    tempWeekFreq = ((JSONObject) t).get("WeekText").toString();
                    tempDay = ((JSONObject) t).get("DayText").toString();
                    tempStartTime = ((JSONObject) t).get("StartTime").toString();
                    tempEndTime = ((JSONObject) t).get("EndTime").toString();
                    tempVenue = ((JSONObject) t).get("Venue").toString();
                    listOfModuleSlots.add(new TimetableModuleSlot(moduleCode, tempLessonType, tempClassType,
                            tempWeekFreq, tempDay, tempVenue, tempStartTime, tempEndTime));
                }
            }
        } catch (Exception e) {
            logger.warning("Exception caught in parsing JSONObject");
        }
        return listOfModuleSlots;
    }

    /**
     * Sorts TimetableModuleSlots
     * @return HashMap of {@code Day}, {@code list of TimetableModuleSlots sorted by time}
     */
    public static HashMap<String, ArrayList<TimetableModuleSlot>> sortModuleSlotsByDay(
            ArrayList<TimetableModuleSlot> unsortedTimetableModuleSlots) {
        ArrayList<ArrayList<TimetableModuleSlot>> listOfDays = new ArrayList<ArrayList<TimetableModuleSlot>>();

        // add ArrayList for Monday to Friday
        listOfDays.add(new ArrayList<TimetableModuleSlot>());
        listOfDays.add(new ArrayList<TimetableModuleSlot>());
        listOfDays.add(new ArrayList<TimetableModuleSlot>());
        listOfDays.add(new ArrayList<TimetableModuleSlot>());
        listOfDays.add(new ArrayList<TimetableModuleSlot>());

        for (TimetableModuleSlot t : unsortedTimetableModuleSlots) {
            try {
                listOfDays.get(convertDayToInteger(t.getDay())).add(t);
            } catch (IllegalValueException e) {
                logger.warning("Invalid day entered");
            }
        }

        for (ArrayList<TimetableModuleSlot> t : listOfDays) {
            ArrayList<TimetableModuleSlot> temp = sortModuleSlotsByTime(t);
            t.clear();
            t.addAll(temp);
        }

        HashMap<String, ArrayList<TimetableModuleSlot>> sortedTimetableModuleSlots =
                new HashMap<String, ArrayList<TimetableModuleSlot>>();
        sortedTimetableModuleSlots.put("MONDAY", listOfDays.get(MONDAY_INDEX));
        sortedTimetableModuleSlots.put("TUESDAY", listOfDays.get(TUESDAY_INDEX));
        sortedTimetableModuleSlots.put("WEDNESDAY", listOfDays.get(WEDNESDAY_INDEX));
        sortedTimetableModuleSlots.put("THURSDAY", listOfDays.get(THURSDAY_INDEX));
        sortedTimetableModuleSlots.put("FRIDAY", listOfDays.get(FRIDAY_INDEX));
        return sortedTimetableModuleSlots;
    }

    /**
     * Sorts Module Slots by Time
     * @param timetableModuleSlots
     * @return
     */
    private static ArrayList<TimetableModuleSlot> sortModuleSlotsByTime(
            ArrayList<TimetableModuleSlot> timetableModuleSlots) {
        Collections.sort(timetableModuleSlots);
        return splitIntoHalfHourSlots(timetableModuleSlots);
    }

    /**
     * Splits each TimetableModuleSlots into half hour slots and adds empty slots to represent breaks
     * @param timetableModuleSlots the ArrayList to split into half hour slots
     * @return an ArrayList of TimetableModuleSlot with each slot representing one half-hour slot in the timetable
     */
    private static ArrayList<TimetableModuleSlot> splitIntoHalfHourSlots(
            ArrayList<TimetableModuleSlot> timetableModuleSlots) {
        ArrayList<TimetableModuleSlot> filled = new ArrayList<TimetableModuleSlot>();

        int j = 0;
        for (int i = 0; i < TIMES.length; i++) {
            if (j < timetableModuleSlots.size() && timetableModuleSlots.get(j).getStartTime().equals(TIMES[i])) {
                while (!timetableModuleSlots.get(j).getEndTime().equals(TIMES[i])) {
                    filled.add(timetableModuleSlots.get(j));
                    i++;
                }
                j++;
                i--;
            } else if (j < timetableModuleSlots.size()
                    && Integer.valueOf(timetableModuleSlots.get(j).getStartTime()) < Integer.valueOf(TIMES[i])
                    && Integer.valueOf(timetableModuleSlots.get(j).getEndTime()) > Integer.valueOf(TIMES[i])) {
                while (!timetableModuleSlots.get(j).getEndTime().equals(TIMES[i])) {
                    filled.add(timetableModuleSlots.get(j));
                    i++;
                }
                j++;
                i--;
            } else {
                filled.add(new TimetableModuleSlot());
            }
        }
        return filled;
    }

    /**
     * Converts shortened lesson type from URL to long lesson type in API
     * @param lessonType shortened lesson type from URL
     * @return long lesson type in API
     */
    private static String convertLessonType(String lessonType) throws IllegalValueException {
        switch (lessonType) {
        case "LEC":
            return "Lecture";

        case "TUT":
            return "Tutorial";

        case "LAB":
            return "Laboratory";

        case "SEM":
            return "Seminar-Style Module Class";

        case "SEC":
            return "Sectional Teaching";

        case "REC":
            return "Recitation";

        case "TUT2":
            return "Tutorial Type 2";

        case "TUT3":
            return "Tutorial Type 3";

        default:
            throw new IllegalValueException(MESSAGE_INVALID_CLASS_TYPE);
        }
    }

    /**
     * Converts {@code Day} to Integer for comparative purposes
     * @param day in String
     * @return int representing day
     */
    private static int convertDayToInteger(String day) throws IllegalValueException {
        switch (day.toLowerCase()) {
        case "monday":
            return MONDAY_INDEX;

        case "tuesday":
            return TUESDAY_INDEX;

        case "wednesday":
            return WEDNESDAY_INDEX;

        case "thursday":
            return THURSDAY_INDEX;

        case "friday":
            return FRIDAY_INDEX;

        default:
            throw new IllegalValueException(MESSAGE_INVALID_DAY_TYPE);
        }
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableModule.java
``` java
/**
 * Represents a module from NUSMods timetable.
 */
public class TimetableModule {
    private final String moduleCode;
    private HashMap<String, String> listOfLessons; // Key is lesson type, Value is class type

    public TimetableModule(String moduleCode, HashMap<String, String> listOfLessons) {
        this.moduleCode = moduleCode;
        this.listOfLessons = listOfLessons;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public HashMap<String, String> getListOfLessons() {
        return listOfLessons;
    }

    @Override
    public boolean equals(Object other) {
        return (other == this // short circuit if same object
                || (other instanceof TimetableModule // instanceof handles nulls
                && this.moduleCode.equals(((TimetableModule) other).moduleCode)
                && this.listOfLessons.equals(((TimetableModule) other).listOfLessons))); // state check
    }
}
```
###### /java/seedu/address/model/person/timetable/TimetableModuleSlot.java
``` java
/**
 * Represents the module information of one module slot in one day
 */
public class TimetableModuleSlot implements Comparable<TimetableModuleSlot> {
    private String moduleCode;
    private String lessonType;
    private String classType;
    private String weekFreq;
    private String day;
    private String venue;
    private String startTime;
    private String endTime;
    private boolean isComparing; // for comparing timetables
    private final boolean isEmpty; // for displaying normal timetable

    public TimetableModuleSlot() {
        this.moduleCode = null;
        this.lessonType = null;
        this.classType = null;
        this.weekFreq = null;
        this.day = null;
        this.venue = null;
        this.startTime = null;
        this.endTime = null;
        isEmpty = true;
        isComparing = false;
    }

    public TimetableModuleSlot(String moduleCode, String lessonType, String classType, String weekFreq, String day,
                               String venue, String startTime, String endTime) {
        this.moduleCode = moduleCode;
        this.lessonType = lessonType;
        this.classType = classType;
        this.weekFreq = weekFreq;
        this.day = day;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        isEmpty = false;
        isComparing = false;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getClassType() {
        return classType;
    }

    public String getWeekFreq() {
        return weekFreq;
    }

    public String getDay() {
        return day;
    }

    public String getVenue() {
        return venue;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setComparing(boolean comparing) {
        isComparing = comparing;
    }

    @Override
    public int compareTo(TimetableModuleSlot other) {
        return this.startTime.compareTo(other.startTime);
    }

    @Override
    public String toString() {
        if (isComparing) {
            return isEmpty
                    ? "\"\"" : "\"X\"";
        } else {
            return isEmpty
                    ? "\"\"" : "\"" + moduleCode + "\"";
        }
    }
}
```

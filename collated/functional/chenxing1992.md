# chenxing1992
###### /resources/view/MainWindow.fxml
``` fxml
<VBox xmlns="http://javafx.com/javafx/8.0.111" xmlns:fx="http://javafx.com/fxml/1">
    <stylesheets>
        <URL value="@NUSCouples.css" />
        <URL value="@Extensions.css" />
    </stylesheets>

    <MenuBar fx:id="menuBar" VBox.vgrow="NEVER">
        <Menu mnemonicParsing="false" text="File">
            <MenuItem mnemonicParsing="false" onAction="#handleExit" text="Exit" />
        </Menu>
        <Menu mnemonicParsing="false" text="Help">
            <MenuItem fx:id="helpMenuItem" mnemonicParsing="false" onAction="#handleHelp" text="Help" />
        </Menu>
    </MenuBar>

    <SplitPane id="splitPane" fx:id="splitPane" dividerPositions="0.4" VBox.vgrow="ALWAYS">
        <VBox fx:id="personList" minWidth="100" prefWidth="500" SplitPane.resizableWithParent="false">
            <padding>
                <Insets bottom="10" left="10" right="10" top="10" />
            </padding>
            <StackPane fx:id="listPanelPlaceholder" VBox.vgrow="ALWAYS" />
        </VBox>

        <StackPane fx:id="browserPlaceholder" prefWidth="200">
            <padding>
                <Insets bottom="10" left="10" right="10" top="10" />
            </padding>
        </StackPane>
    </SplitPane>

    <StackPane fx:id="resultDisplayPlaceholder" maxHeight="100" minHeight="100" prefHeight="100" styleClass="pane-with-border" VBox.vgrow="NEVER">
        <padding>
            <Insets bottom="5" left="10" right="10" top="5" />
        </padding>
    </StackPane>

    <StackPane fx:id="commandBoxPlaceholder" styleClass="pane-with-border" VBox.vgrow="NEVER">
        <padding>
            <Insets left="20.0" right="20.0" top="5.0" />
        </padding>
        <VBox.margin>
            <Insets />
        </VBox.margin>
    </StackPane>

    <StackPane fx:id="statusbarPlaceholder" prefHeight="13.0" prefWidth="692.0" VBox.vgrow="ALWAYS" />
</VBox>
```
###### /resources/view/NUSCouples.css
``` css
background {
    -fx-background-color: #FF69B4;
    background-color: #DDDDDD; /* Used in the default.html file */
}

.label { /* Used in status bar font size, tag font family */
    -fx-font-size: 10pt;
    -fx-font-family: sans-serif;
    -fx-text-fill: red;
    -fx-opacity: 0.9;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: sans-serif;
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: sans-serif;
    -fx-border-color: transparent;
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: black;
    -fx-control-inner-background: red;
    -fx-background-color: red;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color: red;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider { /* Used for split pane divider color */
    -fx-background-color: white;
    -fx-border-color: transparent transparent transparent white;
}

.split-pane { /* Borders left pane. Borders right pane. */
    -fx-background-color: white;
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
}

.list-cell {
    -fx-background-color: #FF69B4;
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-padding: 0 0 0 0;
}

.list-cell:filled(even) { /* Used for cell background */
    -fx-background-color: #F2F2F2;
}

.list-cell:filled(odd) { /* Used for cell background */
    -fx-background-color: #F2F2F2;
}

.list-cell:filled:selected { /* Used for cell background when selected */
    -fx-background-color: #CCCCCC;
}

.list-cell:filled:selected #cardPane { /* Used for border on selected cell */
    -fx-border-color: green;
    -fx-border-width: 0;
}

.list-cell .label {
    -fx-text-fill: white;
}

.cell_big_label { /* Used for person's names */
    -fx-font-family: sans-serif;
    -fx-font-size: 16px;
    -fx-text-fill: #333333;
}

.cell_small_label { /* Used for people's details */
    -fx-font-family: sans-serif;
    -fx-font-size: 13px;
    -fx-text-fill: #666666;
}

.anchor-pane { /* Effectively the command bar background */
    -fx-background-color: #CE4E4E;
    -fx-background-radius: 10px;
}

.anchor-pane-status { /* Effectively the status bar background */}

.pane-with-border { /* Command pane border background color */
    -fx-background-color: white;
}

.status-bar {
    -fx-background-color: white;
    -fx-text-fill: black;
}

.result-display { /* Used for command results */
    -fx-font-family: sans-serif;
    -fx-font-size: 14pt;
    -fx-text-fill: black;
}

.result-display .label {
    /* -fx-text-fill: black !important; */
}

.status-bar .label { /* Status bar font */
    -fx-font-family: sans-serif;
    -fx-text-fill: white;
}

.status-bar-with-border {
    -fx-background-color: green;
}

.status-bar-with-border .label {
    -fx-text-fill: white;
}

.grid-pane { /* Status bar border */
    -fx-background-color: white;
    -fx-background-radius: 0px;
}

.grid-pane .anchor-pane { /* Status bar background */
    -fx-background-color: white;
}

.context-menu { /* Used for menu expansion button background */
    -fx-background-color: #393E41;
}

.context-menu .label {
    -fx-text-fill: white;
}

.menu-bar { /* Used for menu bar colour */
    -fx-background-color: #FF69B4;
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: sans-serif;
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: black;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: red;
    -fx-border-width: 2;
    -fx-background-color: blue;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: black;
    -fx-border-radius: 20;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: red;
}

.button:pressed, .button:default:hover:pressed {
    -fx-background-color: white;
    -fx-text-fill: red;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: blue;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: black;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: black;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: green;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: white;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: black;
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: white;
    -fx-text-fill: white;
}

.scroll-bar { /* Scroll bar column background color */
    -fx-background-color: #CD69FF   ;
}

.scroll-bar .thumb { /* Scroll bar background color */
    -fx-background-color: #B3B3B3;
    -fx-background-insets: 3;
}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-padding: 0 0 0 0;
}

.scroll-bar .increment-arrow, .scroll-bar .decrement-arrow {
    -fx-shape: " ";
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 1 8 1 8;
}

.scroll-bar:horizontal .increment-arrow, .scroll-bar:horizontal .decrement-arrow {
    -fx-padding: 8 1 8 1;
}

#cardPane { /*Background of Listed contacts*/
    -fx-background-color: #CD69FF;
    -fx-border-width: 0;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: black;
}

#commandTextField { /* Command box */
    -fx-background-color: #FF69B4;
    -fx-background-insets: 0;
    -fx-font-family: sans-serif;
    -fx-font-size: 14pt;
    -fx-text-fill: purple;
    -fx-opacity: 0.9;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, black, 10, 0, 0, 0);
}

#resultDisplay .content { /* Result box background color and radius */
    -fx-background-color: #CD69FF;
    -fx-padding: 10px;

}
#resultDisplay {

    -fx-text-fill: white;
}
#listPanelPlaceholder{

    -fx-background-color: #CD69FF;

}
#tags {
    -fx-hgap: 7;
    -fx-vgap: 3;
}

#tags .label {
    -fx-text-fill: white;
    -fx-background-color: #4F6D7A;
    -fx-padding: 2 7 2 7;
    -fx-border-radius: 2;
    -fx-background-radius: 10;
    -fx-font-size: 11;
}
```
###### /resources/view/CalendarDisplay.fxml
``` fxml
<?import javafx.scene.control.TextArea?>
<?import javafx.scene.layout.StackPane?>

<StackPane fx:id="placeHolder" styleClass="pane-with-border" xmlns="http://javafx.com/javafx/9.0.4" xmlns:fx="http://javafx.com/fxml/1">
  <TextArea fx:id="resultDisplay" editable="false" prefHeight="416.0" prefWidth="796.0" style="-fx-background-color: yellow;" styleClass="result-display" />
</StackPane>
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Handles KeyPress Commands that are not keyed with Shift button held down.
     *
     * @param keyEvent Key event pressed by user.
     */
    private void handleStandardPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case UP:
            keyEvent.consume();
            navigateToPreviousInput();
            break;
        case DOWN:
            keyEvent.consume();
            navigateToNextInput();
            break;
        case ESCAPE:
            keyEvent.consume();
            commandTextField.setText("");
            break;
        case ALT:
            keyEvent.consume();
            shiftCaretLeftByWord();
            break;
        case CONTROL:
            keyEvent.consume();
            shiftCaretRightByWord();
            break;
        case RIGHT:
            boolean isCaretWithin = commandTextField.getCaretPosition() < commandTextField.getText().length();
            if (isCaretWithin) {
                break;
            }
            addsNextPrefix();
            break;
        default:
        }
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Adds prefix string to existing text input.
     *
     * @param prefix Prefix to add.
     * @return Text input concatenated with prefix.
     */
    private String concatPrefix(Prefix prefix) {
        return commandTextField.getText().concat(" ").concat(prefix.getPrefix());
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Adds the next prefix required for the input
     */
    private void addsNextPrefix() {
        String finalText;
        if (containsPrefix("name")) {
            finalText = concatPrefix(PREFIX_NAME);
        } else if (containsPrefix("phone")) {
            finalText = concatPrefix(PREFIX_PHONE);
        } else if (containsPrefix("email")) {
            finalText = concatPrefix(PREFIX_EMAIL);
        } else if (containsPrefix("address")) {
            finalText = concatPrefix(PREFIX_ADDRESS);
        } else if (containsPrefix("date")) {
            finalText = concatPrefix(PREFIX_DATE);
        } else if (containsPrefix("timetable")) {
            finalText = concatPrefix(PREFIX_TIMETABLE);
        } else if (containsPrefix("all")) {
            finalText = concatPrefix(PREFIX_TAG);
        } else {
            return;
        }
        commandTextField.setText(finalText);
        commandTextField.positionCaret(finalText.length());
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if add or edit KeyWord is in the input text. Also checks if prefix is in the input text.
     *
     * @param element String to be evaluated.
     * @return True if contains add or edit keyword and relevant prefixes.
     */
    private boolean containsPrefix(String element) {
        switch (element) {
        case "name":
            return (!containsName() && (addPollSuccessful() || editPollSuccessful()));
        case "phone":
            return (!containsPhone() && (addPollSuccessful() || editPollSuccessful()));
        case "email":
            return (!containsEmail() && (addPollSuccessful() || editPollSuccessful()));
        case "address":
            return (!containsAddress() && (addPollSuccessful() || editPollSuccessful()));
        case "timetable":
            return (!containsTimeTable() && (addPollSuccessful() || editPollSuccessful()));
        case "date":
            return (!containsDate() && (addPollSuccessful() || editPollSuccessful()));
        default:
            return (containsAllCompulsoryPrefix() && (addPollSuccessful() || editPollSuccessful()));
        }
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if the commandTextField all prefixes excluding tag.
     *
     * @return True if all prefixes are present.
     */
    private boolean containsAllCompulsoryPrefix() {
        return containsAddress() && containsEmail()
                && containsName() && containsPhone()
                && containsDate();
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if sentence starts with " edit " or " e " and is followed by a valid INDEX.
     * Accounts for blank spaces in front.
     *
     * @return True if sentence starts with " edit " or " e " and is followed by a valid INDEX.
     */
    private boolean editPollSuccessful() {
        String stringToEvaluate = commandTextField.getText().trim();
        if (stringToEvaluate.length() < 3 || !stringToEvaluate.contains(" ")) {
            return false;
        }
        String[] splittedString = stringToEvaluate.split(" ");
        boolean containsEditWord = splittedString[0].equalsIgnoreCase("edit");
        boolean containsEditShorthand = splittedString[0].equalsIgnoreCase("e");
        boolean containsEditCommand = containsEditShorthand || containsEditWord;
        String regex = "[0-9]+";
        boolean containsOnlyNumbers = splittedString[1].matches(regex);
        return containsEditCommand && containsOnlyNumbers;
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if sentence starts with " add " or " a ".
     * Accounts for blank space in front.
     *
     * @return True if if sentence starts with " add " or " a ".
     */
    private boolean addPollSuccessful() {
        String stringToEvaluate = commandTextField.getText().trim();
        if (stringToEvaluate.length() == 0) {
            return false;
        } else if (stringToEvaluate.length() == 1) {
            return stringToEvaluate.equalsIgnoreCase(AddCommand.COMMAND_ALIAS);
        } else if (stringToEvaluate.length() == 2) {
            return false;
        } else if (stringToEvaluate.length() == 3) {
            return containsAInFirstTwoChar(stringToEvaluate)
                    || stringToEvaluate.equalsIgnoreCase(AddCommand.COMMAND_WORD);
        } else {
            return containsAInFirstTwoChar(stringToEvaluate)
                    || containsAddInFirstFourChar(stringToEvaluate);
        }
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if the first two elements of the string are "a ".
     *
     * @param stringToEvaluate String to check.
     * @return True if the first two elements of the string are "a ".
     */
    private boolean containsAInFirstTwoChar(String stringToEvaluate) {
        return (Character.toString(stringToEvaluate.charAt(0)).equalsIgnoreCase(AddCommand.COMMAND_ALIAS)
                && Character.toString(stringToEvaluate.charAt(1)).equals(" "));
    }

```
###### /java/seedu/address/ui/CommandBox.java
``` java
    /**
     * Checks if the first four elements of the string are "add ".
     *
     * @param stringToEvaluate String to check.
     * @return True if the first four elements of the string are "add ".
     */
    private boolean containsAddInFirstFourChar(String stringToEvaluate) {
        return (stringToEvaluate.substring(0, 3).equalsIgnoreCase(AddCommand.COMMAND_WORD)
                && Character.toString(stringToEvaluate.charAt(3)).equals(" "));
    }

```
###### /java/seedu/address/ui/CommandBox.java
``` java
    /**
     * Shifts the caret right to the right of the last character of the next word
     * <p>
     * 1. If Caret is at far right, break
     * <p>
     * 2. If Char is present on right of Caret, shift right until
     * a) Caret is at far right or
     * b) "_" is found
     * <p>
     * 3. If "_" is present on right of Caret, shift right until 2. Condition holds
     * Run Step 2
     */
    private void shiftCaretRightByWord() {
        int newCaretPosition = commandTextField.getCaretPosition();
        int maxAchievablePosition = commandTextField.getText().length();
        if (newCaretPosition == maxAchievablePosition) {
            return;
        } else if (isEmptyAfter(newCaretPosition)) {
            newCaretPosition = shiftRightIgnoringSpaces(newCaretPosition, maxAchievablePosition);
        }
        newCaretPosition = shiftRightIgnoringWords(newCaretPosition, maxAchievablePosition);
        commandTextField.positionCaret(newCaretPosition);
    }

```
###### /java/seedu/address/ui/CommandBox.java
``` java
    /**
     * Shifts the caret right, ignoring all empty space.
     * <p>
     * Note: Will not implement exception throwing here as shiftCaretRightByWord is set up in such a way
     * that pre-conditions as follows are met. Do not want to write code which will affect test coverage
     * which is impossible to resolve.
     * <p>
     * Pre-Condition 1: Current caret position must have an empty space string on the right.
     * It must never be called if there is a possibility of the string after
     * it being not an empty space.
     * <p>
     * Pre-Condition 2: newCaretPosition should never be in the situation where there is a possibility
     * of it being at most right position.
     *
     * @param newCaretPosition      Current caret position.
     * @param maxAchievablePosition Right most bound of word.
     * @return New caret position.
     */
    private int shiftRightIgnoringSpaces(int newCaretPosition, int maxAchievablePosition) {
        int caretHolder = newCaretPosition;
        for (int i = caretHolder; i < maxAchievablePosition; i++) {
            if (!isEmptyAfter(caretHolder)) {
                break;
            }
            caretHolder += 1;
        }
        return caretHolder;
    }

```
###### /java/seedu/address/ui/CommandBox.java
``` java
    /**
     * Shifts the caret right, ignoring all char.
     * <p>
     * Note: Will not implement exception throwing here as shiftCaretRightByWord is set up in such a way
     * that pre-conditions as follows are met. Do not want to write code which will affect test coverage
     * which is impossible to resolve.
     * <p>
     * Pre-Condition 1: Current caret position must have an empty space string on the right.
     * It must never be called if there is a possibility of the string before
     * it being not an empty space.
     * <p>
     * Pre-Condition 2: newCaretPosition should never be in the situation where there is a possibility
     * of it being at most right position.
     *
     * @param newCaretPosition      Current caret position.
     * @param maxAchievablePosition Right most caret position.
     * @return New caret position.
     */
    private int shiftRightIgnoringWords(int newCaretPosition, int maxAchievablePosition) {
        int caretHolder = newCaretPosition;
        for (int i = caretHolder; i < maxAchievablePosition; i++) {
            if (isEmptyAfter(caretHolder)) {
                break;
            }
            caretHolder += 1;
        }
        return caretHolder;
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if string element after currentCaretPosition index is empty.
     *
     * @param currentCaretPosition Current caret position.
     * @return True if string element after currentCaretPosition index is empty.
     */
    private boolean isEmptyAfter(int currentCaretPosition) {
        Character charAfter = commandTextField.getText().charAt(currentCaretPosition);
        String convertToString = Character.toString(charAfter);
        return (" ".equals(convertToString));
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Checks if string element before currentCaretPosition index is empty.
     *
     * @param currentCaretPosition Current caret position.
     * @return True if string element before currentCaretPosition index is empty.
     */
    private boolean isEmptyBefore(int currentCaretPosition) {
        Character charBefore = commandTextField.getText().charAt(currentCaretPosition - 1);
        String convertToString = Character.toString(charBefore);
        return (" ".equals(convertToString));
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Shifts the caret left to the left of the first character of the next word
     * <p>
     * 1. If Caret is at far left, break
     * <p>
     * 2. If Char is present on left of Caret, shift left until
     * a) Caret is at far left or
     * b) "_" is found
     * <p>
     * 3. If "_" is present on left of Caret, shift left until 2. Condition holds
     * Run Step 2
     */
    private void shiftCaretLeftByWord() {
        int newCaretPosition = commandTextField.getCaretPosition();
        if (newCaretPosition == 0) {
            return;
        } else if (isEmptyBefore(newCaretPosition)) {
            newCaretPosition = shiftLeftIgnoringSpaces(newCaretPosition);
        }
        newCaretPosition = shiftLeftIgnoringWords(newCaretPosition);
        commandTextField.positionCaret(newCaretPosition);
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Shifts the caret left, ignoring all char.
     * <p>
     * Note: Will not implement exception throwing here as shiftCaretLeftByWord is set up in such a way
     * that pre-conditions as follows are met. Do not want to write code which will affect test coverage
     * which is impossible to resolve.
     * <p>
     * Pre-Condition 1: Current caret position must have an empty space string on the left.
     * It must never be called if there is a possibility of the string before
     * it being not an empty space.
     * <p>
     * Pre-Condition 2: newCaretPosition should never be in the situation where there is a possibility
     * of it being 0.
     *
     * @param newCaretPosition Current caret position.
     * @return New caret position.
     */
    private int shiftLeftIgnoringWords(int newCaretPosition) {
        int caretHolder = newCaretPosition;
        for (int i = caretHolder; i > 0; i--) {
            if (isEmptyBefore(caretHolder)) {
                break;
            }
            caretHolder -= 1;
        }
        return caretHolder;
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Shifts the caret left, ignoring all empty spaces.
     * <p>
     * Note: Will not implement exception throwing here as shiftCaretLeftByWord is set up in such a way
     * that pre-conditions as follows are met. Do not want to write code which will affect test coverage
     * which is impossible to resolve.
     * <p>
     * Pre-Condition 1: Current caret position must have an empty space string on the left.
     * It must never be called if there is a possibility of the string before.
     * it being not an empty space.
     * <p>
     * Pre-Condition 2: newCaretPosition should never be in the situation where there is a possibility
     * of it being 0.
     *
     * @param newCaretPosition Current caret position.
     * @return New caret position.
     */
    private int shiftLeftIgnoringSpaces(int newCaretPosition) {
        int caretHolder = newCaretPosition;
        for (int i = caretHolder; i > 0; i--) {
            if (!isEmptyBefore(caretHolder)) {
                break;
            }
            caretHolder -= 1;
        }
        return caretHolder;
    }


    /**
     * Updates the text field with the previous input in {@code historySnapshot},
     * if there exists a previous input in {@code historySnapshot}
     */
    private void navigateToPreviousInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasPrevious()) {
            return;
        }

        replaceText(historySnapshot.previous());
    }
```
###### /java/seedu/address/ui/CommandBox.java
``` java

    /**
     * Updates the text field with the next input in {@code historySnapshot},
     * if there exists a next input in {@code historySnapshot}
     */
    private void navigateToNextInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasNext()) {
            return;
        }

        replaceText(historySnapshot.next());
    }

```
###### /java/seedu/address/ui/CommandBox.java
``` java
    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandInputChanged() {
        try {
            CommandResult commandResult = logic.execute(commandTextField.getText());
            initHistory();
            historySnapshot.next();
            // process result of the command
            commandTextField.setText("");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser, false));

        } catch (CommandException | ParseException e) {
            initHistory();
            // handle command failure
            setStyleToIndicateCommandFailure();
            logger.info("Invalid command: " + commandTextField.getText());
            raise(new NewResultAvailableEvent(e.getMessage(), true));
        }
    }

```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    public BrowserPanel(ObservableList<ReadOnlyPerson> personList) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        //  getRoot().setOnKeyPressed(Event::consume);

        this.personList = personList;

        calendarView = new CalendarView();
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        updateCalendar();
        disableViews();
        registerAsAnEventHandler(this);

        //loadDefaultPage();
        //registerAsAnEventHandler(this);
    }

```
###### /java/seedu/address/ui/BrowserPanel.java
``` java

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
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
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
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        calendarView.getCalendarSources().clear();
    }

```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    private Calendar getCalendar(int styleNum, ReadOnlyPerson person) {
        Calendar calendar = new Calendar(person.getName().toString());
        calendar.setStyle(Calendar.Style.getStyle(styleNum));
        calendar.setLookAheadDuration(Duration.ofDays(365));
        return calendar;
    }

```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
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

```
###### /java/seedu/address/ui/BrowserPanel.java
``` java

    /**
     * Creates a new a calendar with the update information
     */
    private void updateCalendar() {
        setTime();
        CalendarSource calendarSource = new CalendarSource("Appointments");
        int styleNum = 0;
        for (ReadOnlyPerson person : personList) {
            Calendar calendar = getCalendar(styleNum, person);
            calendarSource.getCalendars().add(calendar);
            ArrayList<Entry> entries = getEntries(person);
            styleNum++;
            styleNum = styleNum % 5;
            for (Entry entry : entries) {
                calendar.addEntry(entry);
            }
        }
        calendarView.getCalendarSources().add(calendarSource);
    }
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    @Subscribe
    private void handleCalendarViewEvent(CalendarViewEvent event) {
        Character c = event.c;
        Platform.runLater(() -> showPage(c));
    }

```
###### /java/seedu/address/commons/events/ui/CalendarViewEvent.java
``` java

/**
 * Indicates a request to change calendar view
 */
public class CalendarViewEvent extends BaseEvent {

    public final Character c;

    public CalendarViewEvent(Character c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### /java/seedu/address/logic/Logic.java
``` java
    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<ReadOnlyPerson> getFilteredPersonList();

```
###### /java/seedu/address/logic/parser/CancelAppointmentParser.java
``` java

/**
 * Parse input arguments and create a new CancelAppointmentCommand Object
 */
public class CancelAppointmentParser implements Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        if (!userInput.contains("with")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CancelAppointmentCommand.MESSAGE_USAGE));
        }
        String description = userInput.substring(0, userInput.indexOf("with") - 1);
        String personName = userInput.substring(userInput.indexOf("with") + 5);

        return new CancelAppointmentCommand(personName, description);
    }


}
```
###### /java/seedu/address/logic/parser/CalendarViewParser.java
``` java

/**
 * Parser for CalendarViewCommand
 */
public class CalendarViewParser implements Parser {


    @Override
    public Command parse(String userInput) throws ParseException {
        userInput = userInput.trim();
        if (userInput.length() != 1 || !isValid(userInput)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalendarViewCommand.MESSAGE_USAGE));
        }
        return new CalendarViewCommand(userInput.charAt(0));
    }

    /**
     * Util method to check if the parameters is either w,d,y or m.
     */
    private boolean isValid(String str) {

        assert(str.length() == 1);
        switch (str.charAt(0)) {
        case('w'):
        case('d'):
        case('y'):
        case('m'):
            return true;
        default:
            return false;
        }
    }
}
```
###### /java/seedu/address/logic/parser/AddAppointmentParser.java
``` java

/**
 * Parse input arguments and creates a new AddAppointmentCommand Object
 */
public class AddAppointmentParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddAppointmentCommand parse(String userInput) throws ParseException {

        String[] args = userInput.split(" ");

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_DATE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        try {
            Index index = Index.fromOneBased(Integer.parseInt(args[1]));
            Appointment appointment = getAppointmentFromString(argumentMultimap.getValue(PREFIX_DATE).get());
            return new AddAppointmentCommand(index, appointment);
        } catch (NumberFormatException e) {
            throw new ParseException("Please input an index for Appointment.\n"
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Natty parser that takes in a string and returns an Appointment
     */
    public static Appointment getAppointmentFromString(String str) throws ParseException {
        String[] args = str.split(",");

        if (args.length != 2) {
            throw new ParseException("Please follow format for adding Appointment.\n"
                    + AddAppointmentCommand.MESSAGE_USAGE);
        }

        String description = args[0];

        com.joestelmach.natty.Parser parser = new com.joestelmach.natty.Parser();
        List<DateGroup> groups = parser.parse(args[1]);
        Calendar calendar = Calendar.getInstance();
        if (groups.size() == 0) {
            throw new ParseException("Please be more specific with your Appointment time");
        }

        //If there is a start and end time that is parsed
        if (groups.get(0).getDates().size() == 2) {
            calendar.setTime(groups.get(0).getDates().get(0));
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(groups.get(0).getDates().get(1));
            return new Appointment(description, calendar, calendarEnd);
        }

        //Only one date parsed
        calendar.setTime(groups.get(0).getDates().get(0));
        return new Appointment(description, calendar, null);
    }

}
```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java

/**
 * Command to add Appointment to a person in addressBook
 */
public class AddAppointmentCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "Appointment";
    public static final String COMMAND_ALIAS = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Appointment to a person in address book. \n"
            + COMMAND_ALIAS + ": Shorthand equivalent for add. \n"
            + "Parameters: " + COMMAND_WORD + " INDEX "
            + PREFIX_DATE + "DESCRIPTION, TIME" + "\n"
            + "Example 1:" + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "Lunch, Next Monday 3pm";

    public static final String MESSAGE_SUCCESS = "New Appointment added. ";
    public static final String INVALID_PERSON = "This person is not in your address book";
    public static final String INVALID_DATE = "Invalid Date. Please enter a valid date.";
    public static final String SORT_APPOINTMENT_FEEDBACK = "Rearranged contacts to show upcoming appointments.";


    private final Index index;
    private final Appointment appointment;

    public AddAppointmentCommand(Index index, Appointment appointment) {
        this.index = index;
        this.appointment = appointment;
    }
```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java
    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        requireNonNull(index);
        requireNonNull(appointment);

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToAddAppointment = lastShownList.get(index.getZeroBased());

        if (appointment.getDate() != null && !isDateValid()) {
            return new CommandResult(INVALID_DATE);
        }

        try {
            model.addAppointment(personToAddAppointment, appointment);
        } catch (PersonNotFoundException e) {
            return new CommandResult(INVALID_PERSON);
        }

        return new CommandResult(MESSAGE_SUCCESS);

    }
```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java
    /**
     * Checks if Appointment date set to after current time
     */
    private boolean isDateValid() {
        requireNonNull(appointment);
        Calendar calendar = Calendar.getInstance();
        return !appointment.getDate().before(calendar.getTime());
    }
```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java
    public Index getIndex() {
        return this.index;
    }

```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && (this.index.getZeroBased() == ((AddAppointmentCommand) other).index.getZeroBased())
                && (this.appointment.equals(((AddAppointmentCommand) other).appointment)));
    }

```
###### /java/seedu/address/logic/commands/AddAppointmentCommand.java
``` java
    /**
     * For testing purposes
     *
     */
    public void setData(Model model) {
        this.model = model;
    }

}
```
###### /java/seedu/address/logic/commands/CancelAppointmentCommand.java
``` java

/**
 * Command to cancel an existing appointment
 */
public class CancelAppointmentCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "cancel";
    public static final String NO_SUCH_PERSON_FOUND = "No such person found";
    public static final String NO_SUCH_APPOINTMENT = "No such appointment found";
    public static final String MESSAGE_SUCCESS = "Appointment canceled.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Cancels an appointment from a person. \n"
            + "Parameters: " + "DESCRIPTION with PERSON NAME \n"
            + "Example 1:" + COMMAND_WORD + " "
            + "Lunch with John Doe";
    public static final String REFER_PROMPT = "Please refer to the appointment description.";
    private String personString;
    private String appointmentString;

    public CancelAppointmentCommand(String person, String appointment) {
        this.personString = person;
        this.appointmentString = appointment;
    }

    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {
        try {
            ReadOnlyPerson person = getPersonFromName(personString);
            Appointment appointment = getAppointmentFromPerson(person, appointmentString);
            model.removeAppointment(person, appointment);
        } catch (PersonNotFoundException e) {
            throw new CommandException(NO_SUCH_PERSON_FOUND + "\n" + REFER_PROMPT);
        } catch (AppointmentNotFoundException e) {
            throw new CommandException(NO_SUCH_APPOINTMENT + "\n" + REFER_PROMPT);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Util method to search for the correct appointment from a person using only the description.
     * May have multiple appointments if there is same description under one person, but the first one will be deleted
     * @throws AppointmentNotFoundException if appointment not found
     */
    private Appointment getAppointmentFromPerson(ReadOnlyPerson person, String description)
            throws AppointmentNotFoundException {

        for (Appointment appointment : person.getAppointments()) {
            if (appointment.getDescription().equalsIgnoreCase(description.trim())) {
                return appointment;
            }
        }
        //Show Daily page for calendar
        EventsCenter.getInstance().post(new CalendarViewEvent('d'));
        throw new AppointmentNotFoundException();
    }

    /**
     * Extract person from address book using name. If there are more than one contact with the same name,
     * the first one will be extracted
     * @throws PersonNotFoundException if no such person is in the address book
     */
    private ReadOnlyPerson getPersonFromName(String personName) throws PersonNotFoundException {

        for (ReadOnlyPerson person : model.getAddressBook().getPersonList()) {
            if (person.getName().toString().equalsIgnoreCase(personName.trim())) {
                return person;
            }
        }

        throw new PersonNotFoundException();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CancelAppointmentCommand // instanceof handles nulls
                && (this.appointmentString.equals(((CancelAppointmentCommand) other).appointmentString))
                && (this.personString.equals(((CancelAppointmentCommand) other).personString)));
    }
}
```
###### /java/seedu/address/logic/commands/CalendarViewCommand.java
``` java
/**
 * Command to change calendar view
 */
public class CalendarViewCommand extends Command {

    public static final String COMMAND_WORD = "calendar";
    public static final String COMMAND_ALIAS = "cal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes calendar view. \n"
            + COMMAND_ALIAS + ": Short hand equivalent for calendar. \n"
            + "Parameter: \n"
            + "Day view: d\n"
            + "Week view: w\n"
            + "Month view: m\n"
            + "Year view: y\n";

    public static final String MESSAGE_SUCCESS = "View changed.";

    private Character arg;
```
###### /java/seedu/address/logic/commands/CalendarViewCommand.java
``` java
    public CalendarViewCommand(Character c) {
        this.arg = c;
    }
    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new CalendarViewEvent(arg));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
```
###### /java/seedu/address/logic/LogicManager.java
``` java
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

```
###### /java/seedu/address/model/person/Appointment/Appointment.java
``` java

/**
 *  Appointment class to hold all the start and end time of the Appointment and the description
 *  */
public class Appointment {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private String description;
    private Date date;
    private Date endDate;

    public Appointment(String description, Calendar calendar, Calendar calendarEnd) {
        requireNonNull(calendar);
        requireNonNull(description);
        this.description = description;
        this.date = calendar.getTime();
        if (calendarEnd != null) {
            this.endDate = calendarEnd.getTime();
        } else {
            calendar.add(Calendar.HOUR, 1);
            this.endDate = calendar.getTime();
        }
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    public Date getEndDate() {
        return endDate;
    }
    @Override
    public String toString() {
        if (date != null) {
            return "Appointment on " + DATE_FORMATTER.format(date);
        } else {
            return "No Appointment";
        }
    }

    /**
     * @return starting Appointment time in the format yyyy/MM/dd HH:mm
     */
    public String getDateInStringFormat() {
        return DATE_FORMATTER.format(date);
    }

    /**
     * @return ending Appointment time in the format yyyy/MM/dd HH:mm
     */
    public String getDateEndInStringFormat() {
        return DATE_FORMATTER.format(endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && this.getDateInStringFormat().equals(((Appointment) other).getDateInStringFormat()))
                && this.getDateEndInStringFormat().equals(((Appointment) other).getDateEndInStringFormat());
    }
}
```
###### /java/seedu/address/model/person/Appointment/AppointmentList.java
``` java

/**
 * A list of appointments of a person
 */
public class AppointmentList {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty Appointment list
     */
    public AppointmentList() {

    }

    /**
     * Contructs an Appointment list with new appointments
     */
    public AppointmentList(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        sortAppointmentsInChronologicalOrder(appointments);
        internalList.addAll(appointments);

    }

    /**
     * @param appointments list must be sorted
     */
    private void requireAllSorted(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            assert !appointments.get(i + 1).getDate().before(appointments.get(i).getDate());
        }
    }

    /**
     * Sorts all the appointments in the list before adding it to the internal list
     */
    private void sortAppointmentsInChronologicalOrder(List<Appointment> appointment) {
        requireNonNull(appointment);

        appointment.sort((o1, o2) -> {
            if (o1.getDate().toInstant().isBefore(o2.getDate().toInstant())) {
                return -1;
            } else {
                return 1;
            }
        });

        requireAllSorted(appointment);
    }

    /**
     * Returns true if list contains and equivalent Appointment
     */
    public boolean contains (Appointment appointment) {
        return internalList.contains(appointment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentList // instanceof handles nulls
                && this.internalList.equals(((AppointmentList) other).internalList));
    }

    /**
     * Returns all appointments in this list as a list.
     * This List is mutable and change-insulated against the internal list.
     */
    public List<Appointment> toList() {
        return new ArrayList<>(internalList);
    }

    @Override
    public String toString() {
        if (internalList.isEmpty()) {
            return "No Appointment set";
        } else {
            return internalList.size() == 1 ? "" + internalList.size() + " Appointment set"
                    : "" + internalList.size() + " appointments set";
        }
    }

}
```
###### /java/seedu/address/model/person/ReadOnlyPerson.java
``` java
/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    ObjectProperty<Name> nameProperty();
    Name getName();
    ObjectProperty<Phone> phoneProperty();
    Phone getPhone();
    ObjectProperty<Email> emailProperty();
    Email getEmail();
    ObjectProperty<Address> addressProperty();
    Address getAddress();
    ObjectProperty<Timetable> timeTableProperty();
    Timetable getTimetable();
    ObjectProperty<UniqueTagList> tagProperty();
    Set<Tag> getTags();
    ObjectProperty<AppointmentList> appointmentProperty();
    List<Appointment> getAppointments();
    /**Same state detected will return true.
     */
    default boolean isSameStateAs(ReadOnlyPerson rp) {
        return rp == this // short circuit if same object
                || (rp != null // this is first to avoid NPE below
                && rp.getName().equals(this.getName()) // state checks here onwards
                && rp.getPhone().equals(this.getPhone())
                && rp.getEmail().equals(this.getEmail())
                && rp.getAddress().equals(this.getAddress())
                && rp.getTimetable().equals((this.getTimetable())));
    }

```
###### /java/seedu/address/model/person/ReadOnlyPerson.java
``` java
    /**
     * Show all contact in detail as text
     */
    default String getAsText() {
        final StringBuilder b = new StringBuilder();
        b.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" TimeTable: ")
                .append(getTimetable())
                .append(" Tags: ");
        getTags().forEach(b::append);
        return b.toString();
    }

}

```
###### /java/seedu/address/model/person/UniquePersonList.java
``` java
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ReadOnlyPerson> asObservableList() {
        return FXCollections.unmodifiableObservableList(mappedList);
    }

```
###### /java/seedu/address/model/person/UniquePersonList.java
``` java
    /**
     * @return the list as an unmodifiable list and sorted by name in ascending order
     */
    public ObservableList<ReadOnlyPerson> asObservableListSortedByNameAsc() {
        internalList.sort((o1, o2) -> {
            int output = (o1.getName().fullName.compareToIgnoreCase(o2.getName().fullName) >= 0) ? 1 : -1;
            return output;
        });
        return FXCollections.unmodifiableObservableList(mappedList);
    }

```
###### /java/seedu/address/model/person/UniquePersonList.java
``` java
    /**
     * @return a unmodifiable list and will be sorted by name in descending order
     */
    public ObservableList<ReadOnlyPerson> asObservableListSortedByNameDsc() {
        internalList.sort((o1, o2) -> {
            int op = (o1.getName().fullName.compareToIgnoreCase(o2.getName().fullName) <= 0) ? 1 : -1;
            return op;
        });
        return FXCollections.unmodifiableObservableList(mappedList);
    }
```
###### /java/seedu/address/model/person/UniquePersonList.java
``` java
    /**
     * @return list is reversed
     */
    public ObservableList<ReadOnlyPerson> asObservableListReversed() {
        FXCollections.reverse(internalList);
        return FXCollections.unmodifiableObservableList(mappedList);
    }


    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                && this.internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }


    /**
     * Util method to extract person out from a list
     */
    private Person getPerson(ReadOnlyPerson target) throws PersonNotFoundException {
        requireNonNull(target);
        for (Person person : internalList) {
            if (person.equals(target)) {
                return person;
            }
        }
        throw new PersonNotFoundException();
    }

}

```
###### /java/seedu/address/model/person/exceptions/AppointmentNotFoundException.java
``` java
/**
 * Signals that the appointment cannot be found
 */
public class AppointmentNotFoundException extends Exception {
}
```
###### /java/seedu/address/model/person/Person.java
``` java
/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements ReadOnlyPerson {

    public static final int PARTNER_INDEX = 0;

    private ObjectProperty<Name> name;
    private ObjectProperty<Phone> phone;
    private ObjectProperty<Email> email;
    private ObjectProperty<Address> address;
    private ObjectProperty<Timetable> timetable;
    private ObjectProperty<UniqueTagList> tags;
    private ObjectProperty<AppointmentList> appointments;

```
###### /java/seedu/address/model/person/Person.java
``` java
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Timetable timetable, Set<Tag> tags, List<Appointment> appointments) {

        requireAllNonNull(name, phone, email, address, timetable, tags, appointments);

        this.name = new SimpleObjectProperty<>(name);
        this.phone = new SimpleObjectProperty<>(phone);
        this.email = new SimpleObjectProperty<>(email);
        this.address = new SimpleObjectProperty<>(address);
        this.timetable = new SimpleObjectProperty<>(timetable);
        // protect internal tags from changes in the arg list
        this.tags = new SimpleObjectProperty<>(new UniqueTagList(tags));
        this.appointments = new SimpleObjectProperty<>(new AppointmentList(appointments));

    }

```
###### /java/seedu/address/model/person/Person.java
``` java
    /**
     * Creates a copy of the given ReadOnlyPerson.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(),
                source.getTimetable(), source.getTags(), source.getAppointments());
    }
    public void setName(Name name) {
        this.name.set(requireNonNull(name));
    }

    @Override
    public ObjectProperty<Name> nameProperty() {
        return name;
    }

    @Override
    public Name getName() {
        return name.get();
    }

    public void setPhone(Phone phone) {
        this.phone.set(requireNonNull(phone));
    }

    @Override
    public ObjectProperty<Phone> phoneProperty() {
        return phone;
    }

    @Override
    public Phone getPhone() {
        return phone.get();
    }

    public void setEmail(Email email) {
        this.email.set(requireNonNull(email));
    }

    @Override
    public ObjectProperty<Email> emailProperty() {
        return email;
    }

    @Override
    public Email getEmail() {
        return email.get();
    }

    public void setAddress(Address address) {
        this.address.set(requireNonNull(address));
    }

    @Override
    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public Address getAddress() {
        return address.get();
    }

    public void setTimetable(Timetable timetable) {
        this.timetable.set(requireNonNull(timetable));
    }

    @Override
    public ObjectProperty<Timetable> timeTableProperty() {
        return timetable;
    }

    @Override
    public Timetable getTimetable() {
        return timetable.get();
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get().toSet());
    }

    public ObjectProperty<UniqueTagList> tagProperty() {
        return tags;
    }

    @Override
    public ObjectProperty<AppointmentList> appointmentProperty() {
        return appointments;
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointments.get().toList();
    }

    public void setAppointment(List<Appointment> appointments) {
        this.appointments.set(new AppointmentList(appointments));
    }

    /**
     * Replaces this person's tags with the tags in the argument tag set.
     */
    public void setTags(Set<Tag> replacement) {
        tags.set(new UniqueTagList(replacement));
    }

    public boolean hasTag(Tag tag) {
        return tags.get().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, timetable, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

```
###### /java/seedu/address/model/AddressBook.java
``` java

    /**
     * Help to remove unwanted tags
     */
    public void removeUnusedTags(Set<Tag> tagToRemove) {
        Set<Tag> newTags = getTagsExcluding(tagToRemove);
        tags.setTags(newTags);
        syncMasterTagListWith(persons);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java

    /**
     * Help to exclude unwanted tags
     */
    public Set<Tag> getTagsExcluding(Set<Tag> tagsToExclude) {
        Set<Tag> output = tags.toSet();
        for (Tag tagExcluded : tagsToExclude) {
            output.remove(tagExcluded);
        }
        return output;
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java

    /**
     * Make sure that these people:
     * - appear in the master list {@link #tags}
     * - Tag objects are pointed in the master list
     *
     * @see #syncMasterTagListWith(Person)
     */
    private void syncMasterTagListWith(UniquePersonList persons) {
        persons.forEach(this::syncMasterTagListWith);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java

    /**
     * Make sure this person:
     * - Appear in the master list {@link #tags}
     * - Tag object is pointed in the master list
     */
    private void syncMasterTagListWith(Person person) {
        final UniqueTagList tags = new UniqueTagList(person.getTags());
        this.tags.mergeFrom(tags);

        // Create map with values = tag object references in the master list
        // used for checking person tag references
        final Map<Tag, Tag> mainTagObjects = new HashMap<>();
        this.tags.forEach(tag -> mainTagObjects.put(tag, tag));

        // Rebuild the list of person tags to point to the relevant tags in the master tag list.
        final Set<Tag> correctTagReferences = new HashSet<>();
        tags.forEach(tag -> correctTagReferences.add(mainTagObjects.get(tag)));
        person.setTags(correctTagReferences);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     *
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removePerson(ReadOnlyPerson key) throws PersonNotFoundException {
        if (persons.remove(key)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, " + tags.asObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        return persons.asObservableList();
    }

    //@Override
    //public ObservableList<Tag> getTagList() {
    //  return tags.asObservableList();
    // }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.persons.equals(((AddressBook) other).persons)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons, tags);
    }
}
```
###### /java/seedu/address/model/Model.java
``` java
    /**
     * Adds Appointment to a person
     */
    void addAppointment(ReadOnlyPerson target, Appointment appointment) throws PersonNotFoundException;

    /**
     * Removes appointment from a person
     */
    void removeAppointment(ReadOnlyPerson target, Appointment appointment) throws PersonNotFoundException;

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate);

}
```

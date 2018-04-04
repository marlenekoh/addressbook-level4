package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;


/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#equals(Object)
 * @see
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    // used by asObservableList()
    private final ObservableList<ReadOnlyPerson> mappedList = EasyBind.map(internalList, (person) -> person);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(ReadOnlyPerson toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a person to the list.
     *
     * @throws DuplicatePersonException if the person to add is a duplicate of an existing person in the list.
     */
    public void add(ReadOnlyPerson toAdd) throws DuplicatePersonException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(new Person(toAdd));
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if the replacement is equivalent to another existing person in the list.
     * @throws PersonNotFoundException  if {@code target} could not be found in the list.
     */
    public void setPerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedPerson) && internalList.contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, new Person(editedPerson));
    }

    /**
     * Removes the equivalent person from the list.
     *
     * @throws PersonNotFoundException if no such person could be found in the list.
     */
    public boolean remove(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        requireNonNull(toRemove);
        final boolean personFoundAndDeleted = internalList.remove(toRemove);
        if (!personFoundAndDeleted) {
            throw new PersonNotFoundException();
        }
        return personFoundAndDeleted;
    }

    public void setPersons(UniquePersonList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setPersons(List<? extends ReadOnlyPerson> persons) throws DuplicatePersonException {
        final UniquePersonList replacement = new UniquePersonList();
        for (final ReadOnlyPerson person : persons) {
            replacement.add(new Person(person));
        }
        setPersons(replacement);
    }

    //@@author chenxing1992
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ReadOnlyPerson> asObservableList() {
        return FXCollections.unmodifiableObservableList(mappedList);
    }

    //@@author chenxing1992
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

    //@@author chenxing1992
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
    //@@author chenxing1992
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


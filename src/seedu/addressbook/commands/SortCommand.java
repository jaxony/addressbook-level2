package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user in ascending alphabetical (case-insensitive) order of names.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a sorted list using alphabetical (case-insensitive) order of names.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> mutableAllPersons = new ArrayList<>(allPersons);
        Collections.sort(mutableAllPersons, new Comparator<ReadOnlyPerson>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                return o1.getName().fullName.toLowerCase().compareTo(o2.getName().fullName.toLowerCase());
            }
        });
        return new CommandResult(getMessageForPersonListShownSummary(mutableAllPersons), mutableAllPersons);
    }
}

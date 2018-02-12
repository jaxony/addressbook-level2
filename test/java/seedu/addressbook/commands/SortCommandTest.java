package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.*;

import static org.junit.Assert.*;

public class SortCommandTest {
    private Person aliceBetsy;
    private Person bobChaplin;

    private Tag tagMathematician;

    private AddressBook addressBook;

    @Before
    public void setUp() throws Exception {
        tagMathematician = new Tag("mathematician");
        aliceBetsy     = new Person(new Name("Alice Betsy"),
                new Phone("91235468", false),
                new Email("alice@nushackers.org", false),
                new Address("8 Computing Drive, Singapore", false),
                new UniqueTagList(tagMathematician));
        bobChaplin     = new Person(new Name("Bob Chaplin"),
                new Phone("94321500", false),
                new Email("bob@nusgreyhats.org", false),
                new Address("9 Computing Drive", false),
                new UniqueTagList(tagMathematician));


        addressBook = new AddressBook(new UniquePersonList(bobChaplin, aliceBetsy),
                new UniqueTagList(tagMathematician));
    }

    @Test
    public void sortCommand_addressBook_isSorted() {
        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        CommandResult result = command.execute();

        Optional<List<? extends ReadOnlyPerson>> relevantPersons = result.getRelevantPersons();
        assertTrue(relevantPersons.isPresent());
        assertTrue(relevantPersons.get().size() > 1);

        for (int i = 1; i < relevantPersons.get().size(); i++) {
            String prevName = relevantPersons.get().get(i-1).getName().fullName.toLowerCase();
            String currName = relevantPersons.get().get(i).getName().fullName.toLowerCase();
            assertTrue(prevName.compareTo(currName) <= 0);
        }
    }
}

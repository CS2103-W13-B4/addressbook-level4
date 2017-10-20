package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListingUnit;
<<<<<<< HEAD
import seedu.address.model.module.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.predicates.NameContainsKeywordsPredicate;
=======
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.AllAttributesContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneNumberContainsKeywordsPredicate;
>>>>>>> master

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ListingUnit currentListingUnit = ListingUnit.getCurrentListingUnit();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        switch (ListingUnit.getCurrentListingUnit()) {
<<<<<<< HEAD
        case LOCATION:
            return new FindCommand(new LocationContainsKeywordsPredicate(Arrays.asList(keywords)));
        default:
            return new FindCommand(new ModuleContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
=======
        case EMAIL:
            return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        case PHONE:
            return new FindCommand(new PhoneNumberContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        case ADDRESS:
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        default:
            return new FindCommand(new AllAttributesContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

>>>>>>> master
    }

}

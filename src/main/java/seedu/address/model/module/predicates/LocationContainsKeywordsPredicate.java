package seedu.address.model.module.predicates;

import seedu.address.model.module.ReadOnlyLesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Phone Number} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate extends FindPredicate {
    private final List<String> keywords;
    private ArrayList<String> duplicateLocation = new ArrayList<String>();

    public LocationContainsKeywordsPredicate(List<String> keywords) {

        this.keywords = keywords;

    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(ReadOnlyLesson lesson) {

        for (int i = 0; i < keywords.size(); i++) {
            if (lesson.getLocation().value.toLowerCase().contains(keywords.get(i).toLowerCase())) {

                if (duplicateLocation.contains(lesson.getLocation().value)) {
                    return false;
                } else {
                    duplicateLocation.add(lesson.getLocation().value);
                    return true;
                }

            }
        }
        return false;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}

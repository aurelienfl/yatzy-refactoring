import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class YatzyGame {

    /** Use to sort category by probility asc. In equal case, by score for roll desc */
    private static final Comparator<Category> CATEGORY_PROBA_SCORE_COMPARATOR = (category1, category2) -> {
        int compareProba = Double.compare(category1.getProbability(), category2.getProbability());
        if (compareProba != 0) {
            return compareProba;
        } else {
            return Integer.compare(category2.getScoreForRoll(), category1.getScoreForRoll());
        }
    };

    /**
     * Allow to select the best category for a roll, by looking at the one that is available and have the minimum probability
     * @param roll the roll to score
     * @param playerCategories the player categories
     * @return the score for the roll that will be the result of the selected category rule
     */
    public static int chooseBestCategoryForRoll(Roll roll, List<Category> playerCategories) {
        List<Category> availablesCategories = playerCategories.stream()
            .filter(Category::isAvailable)
            .toList();
        for (Category availableCategory : availablesCategories) {
            availableCategory.setScoreForRoll(availableCategory.getScoreFunction().apply(roll));
        }
        Optional<Category> selectedCategory = availablesCategories.stream()
            .filter(category -> category.getScoreForRoll() > 0)
            .min(CATEGORY_PROBA_SCORE_COMPARATOR);
        if (selectedCategory.isPresent()) {
            Category presentSelectedCategory = selectedCategory.get();
            presentSelectedCategory.setAvailable(false);
            return presentSelectedCategory.getScoreForRoll();
        }
        return 0;
    }

}

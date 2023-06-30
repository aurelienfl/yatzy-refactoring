

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class YatzyGameTest {

    private static Optional<Category> getCategory (String categoryName, List<Category> categories) {
        return categories.stream().filter(category -> categoryName.equals(category.getName())).findFirst();
    }

    @Test
    public void testSelectYatzi() {
        List<Category> categories = Category.categories();
        assertEquals(50, YatzyGame.chooseBestCategoryForRoll(new Roll(5,5,5,5,5), categories));
        Optional<Category> yatzyCategory = getCategory(Category.YATZY, categories);
        yatzyCategory.ifPresent(category -> assertFalse(category.isAvailable()));
    }

    @Test
    public void testSelectPairInsteadHigherScore() {
        List<Category> categories = Category.categories();
        assertEquals(2, YatzyGame.chooseBestCategoryForRoll(new Roll(1,1,3,4,5), categories));
        Optional<Category> pairCategory = getCategory(Category.PAIR, categories);
        pairCategory.ifPresent(category -> assertFalse(category.isAvailable()));
    }

    @Test
    public void testSelectPairTwoPairsUnvailable() {
        List<Category> categories = Category.categories();
        Optional<Category> twosPairsCategory = getCategory(Category.TWO_PAIRS, categories);
        twosPairsCategory.ifPresent(category -> category.setAvailable(false));
        assertEquals(12, YatzyGame.chooseBestCategoryForRoll(new Roll(1,1,6,6,5), categories));
        Optional<Category> pairCategory = getCategory(Category.PAIR, categories);
        pairCategory.ifPresent(category -> assertFalse(category.isAvailable()));
    }

    @Test
    public void testSelectSmallStraightInsteadOfChance() {
        List<Category> categories = Category.categories();
        assertEquals(15, YatzyGame.chooseBestCategoryForRoll(new Roll(1,2,3,4,5), categories));
        Optional<Category> stCategory = getCategory(Category.SMALL_STRAIGHT, categories);
        stCategory.ifPresent(category -> assertFalse(category.isAvailable()));
        Optional<Category> chanceCategory = getCategory(Category.CHANCE, categories);
        chanceCategory.ifPresent(category -> assertTrue(category.isAvailable()));
    }

}

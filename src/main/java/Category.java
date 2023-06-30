import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;

@Builder
@Getter
@Setter
public class Category {
    static final String CHANCE = "chance";
    static final String YATZY = "yatzy";
    static final String ONES = "ones";
    static final String TWOS = "twos";
    static final String THREES = "threes";
    static final String FOURS = "fours";
    static final String FIVES = "fives";
    static final String SIXES = "sixes";
    static final String PAIR = "pair";
    static final String TWO_PAIRS = "twoPair";
    static final String THREE_OF_A_KIND = "threeOfAKind";
    static final String FOUR_OF_A_KIND = "fourOfAKind";
    static final String SMALL_STRAIGHT = "smallStraight";
    static final String LARGE_STRAIGHT = "largeStraight";
    static final String FULL_HOUSE = "fullHouse";

    public static List<Category> categories () {
        return List.of(
            Category.builder().name(CHANCE).scoreFunction(YatzyCategoriesScoringRules::chance).probability(100).isAvailable(true).build(),
            Category.builder().name(YATZY).scoreFunction(YatzyCategoriesScoringRules::yatzy).probability(0.1).isAvailable(true).build(),
            Category.builder().name(ONES).scoreFunction(YatzyCategoriesScoringRules::ones).probability(100).isAvailable(true).build(),
            Category.builder().name(TWOS).scoreFunction(YatzyCategoriesScoringRules::twos).probability(100).isAvailable(true).build(),
            Category.builder().name(THREES).scoreFunction(YatzyCategoriesScoringRules::threes).probability(100).isAvailable(true).build(),
            Category.builder().name(FOURS).scoreFunction(YatzyCategoriesScoringRules::fours).probability(100).isAvailable(true).build(),
            Category.builder().name(FIVES).scoreFunction(YatzyCategoriesScoringRules::fives).probability(100).isAvailable(true).build(),
            Category.builder().name(SIXES).scoreFunction(YatzyCategoriesScoringRules::sixes).probability(100).isAvailable(true).build(),
            Category.builder().name(PAIR).scoreFunction(YatzyCategoriesScoringRules::pair).probability(46.3).isAvailable(true).build(),
            Category.builder().name(TWO_PAIRS).scoreFunction(YatzyCategoriesScoringRules::twoPair).probability(23.1).isAvailable(true).build(),
            Category.builder().name(THREE_OF_A_KIND).scoreFunction(YatzyCategoriesScoringRules::threeOfAKind).probability(15.4).isAvailable(true).build(),
            Category.builder().name(FOUR_OF_A_KIND).scoreFunction(YatzyCategoriesScoringRules::fourOfAKind).probability(1.9).isAvailable(true).build(),
            Category.builder().name(SMALL_STRAIGHT).scoreFunction(YatzyCategoriesScoringRules::smallStraight).probability(3.9).isAvailable(true).build(),
            Category.builder().name(LARGE_STRAIGHT).scoreFunction(YatzyCategoriesScoringRules::largeStraight).probability(1.9).isAvailable(true).build(),
            Category.builder().name(FULL_HOUSE).scoreFunction(YatzyCategoriesScoringRules::fullHouse).probability(3.9).isAvailable(true).build()
            );
    }

    private String name;
    private double probability;
    private Function<Roll, Integer> scoreFunction;

    private boolean isAvailable;

    private int scoreForRoll;

}

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YatzyCategoriesScoringRules {

    private static final List<Integer> SMALL_STRAIGHT_LIST = List.of(1, 2, 3, 4, 5);

    private static final List<Integer> LARGE_STRAIGHT_LIST = List.of(2, 3, 4, 5, 6);

    private static Map<Integer, Long> dicesGroupByCount(Roll roll) {
        return roll.createDicesList().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static int chance(Roll roll) {
        if (roll == null) {
            return 0;
        }
        return roll.createDicesList().stream().reduce(0, Integer::sum);
    }

    public static int yatzy(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        Optional<Long> nb5 = dicesGroupByCount.values().stream()
            .filter(nb -> nb == 5).findFirst();
        return nb5.isPresent() ? 50 : 0;
    }

    private static int scoreForSameNumber(Roll roll, int number) {
        if (roll == null) {
            return 0;
        }
        return (int) roll.createDicesList().stream().filter(d -> d == number).count() * number;
    }

    public static int ones(Roll roll) {

        return scoreForSameNumber(roll, 1);
    }

    public static int twos(Roll roll) {
        return scoreForSameNumber(roll, 2);
    }

    public static int threes(Roll roll) {
        return scoreForSameNumber(roll, 3);
    }

    public static int fours(Roll roll) {
        return scoreForSameNumber(roll, 4);
    }

    public static int fives(Roll roll) {
        return scoreForSameNumber(roll, 5);
    }

    public static int sixes(Roll roll) {
        return scoreForSameNumber(roll, 6);
    }

    public static int pair(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        Optional<Integer> firstPairValuesDice = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() >= 2)
            .map(Map.Entry::getKey)
            .min(Collections.reverseOrder());
        return firstPairValuesDice.isPresent() ? firstPairValuesDice.get() * 2 : 0;
    }


    public static int twoPair(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        List<Integer> pairValuesDices = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() >= 2)
            .map(Map.Entry::getKey)
            .toList();
        if (pairValuesDices.size() >= 2) {
            int score = 0;
            for (Integer pairValueDice : pairValuesDices) {
                score += pairValueDice * 2;
            }
            return score;
        }
        return 0;
    }

    public static int fourOfAKind(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        Optional<Integer> firstFourKindValuesDice = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() >= 4)
            .map(Map.Entry::getKey).findFirst();
        return firstFourKindValuesDice.isPresent() ? firstFourKindValuesDice.get() * 4 : 0;
    }

    public static int threeOfAKind(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        Optional<Integer> firstThreeKindValuesDice = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() >= 3)
            .map(Map.Entry::getKey).findFirst();
        return firstThreeKindValuesDice.isPresent() ? firstThreeKindValuesDice.get() * 3 : 0;
    }

    public static int smallStraight(Roll roll) {
        if (roll == null) {
            return 0;
        }
        List<Integer> sortedRollDicesList = roll.createDicesList().stream().sorted().toList();
        return SMALL_STRAIGHT_LIST.equals(sortedRollDicesList) ? 15 : 0;
    }

    public static int largeStraight(Roll roll) {
        if (roll == null) {
            return 0;
        }
        List<Integer> sortedRollDicesList = roll.createDicesList().stream().sorted().toList();
        return LARGE_STRAIGHT_LIST.equals(sortedRollDicesList) ? 20 : 0;
    }

    public static int fullHouse(Roll roll) {
        if (roll == null) {
            return 0;
        }
        Map<Integer, Long> dicesGroupByCount = dicesGroupByCount(roll);
        Optional<Integer> threeKindValuesDice = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() == 3)
            .map(Map.Entry::getKey).findFirst();
        Optional<Integer> pairValuesDice = dicesGroupByCount.entrySet().stream()
            .filter(diceValue -> diceValue.getValue() == 2)
            .map(Map.Entry::getKey).findFirst();
        if (threeKindValuesDice.isPresent() && pairValuesDice.isPresent()) {
            return threeKindValuesDice.get() * 3 + pairValuesDice.get() * 2;
        }
        return 0;
    }
}




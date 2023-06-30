
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YatzyCategoriesScoringRulesTest {

    @Test
    public void testChance() {
        assertEquals(15, YatzyCategoriesScoringRules.chance(new Roll(2,3,4,5,1)));
        assertEquals(16, YatzyCategoriesScoringRules.chance(new Roll(3,3,4,5,1)));
    }

    @Test
    public void testYatzi() {
        assertEquals(50, YatzyCategoriesScoringRules.yatzy(new Roll(4,4,4,4,4)));
        assertEquals(50, YatzyCategoriesScoringRules.yatzy(new Roll(6,6,6,6,6)));
        assertEquals(0, YatzyCategoriesScoringRules.yatzy(new Roll(6,6,6,6,3)));
    }

    @Test
    public void testOne() {
        assertEquals(1, YatzyCategoriesScoringRules.ones(new Roll(1,2,3,4,5)));
        assertEquals(2, YatzyCategoriesScoringRules.ones(new Roll(1,2,1,4,5)));
        assertEquals(0, YatzyCategoriesScoringRules.ones(new Roll(6,2,2,4,5)));
        assertEquals(4, YatzyCategoriesScoringRules.ones(new Roll(1,2,1,1,1)));
    }

    @Test
    public void testTwo() {
        assertEquals(4, YatzyCategoriesScoringRules.twos(new Roll(1,2,3,2,6)));
        assertEquals(10, YatzyCategoriesScoringRules.twos(new Roll(2,2,2,2,2)));
    }

    @Test
    public void testThrees() {
        assertEquals(6, YatzyCategoriesScoringRules.threes(new Roll(1,2,3,2,3)));
        assertEquals(12, YatzyCategoriesScoringRules.threes(new Roll(2,3,3,3,3)));
    }

    @Test
    public void testFours()
    {
        assertEquals(12, YatzyCategoriesScoringRules.fours(new Roll(4,4,4,5,5)));
        assertEquals(8, YatzyCategoriesScoringRules.fours(new Roll(4,4,5,5,5)));
        assertEquals(4, YatzyCategoriesScoringRules.fours(new Roll(4,5,5,5,5)));
    }

    @Test
    public void testFives() {
        assertEquals(10, YatzyCategoriesScoringRules.fives(new Roll(4,4,4,5,5)));
        assertEquals(15, YatzyCategoriesScoringRules.fives(new Roll(4,4,5,5,5)));
        assertEquals(20, YatzyCategoriesScoringRules.fives(new Roll(4,5,5,5,5)));
    }

    @Test
    public void testSixes() {
        assertEquals(0, YatzyCategoriesScoringRules.sixes(new Roll(4,4,4,5,5)));
        assertEquals(6, YatzyCategoriesScoringRules.sixes(new Roll(4,4,6,5,5)));
        assertEquals(18, YatzyCategoriesScoringRules.sixes(new Roll(6,5,6,6,5)));
    }

    @Test
    public void testOnePair() {
        assertEquals(6, YatzyCategoriesScoringRules.pair(new Roll(3,4,3,5,6)));
        assertEquals(10, YatzyCategoriesScoringRules.pair(new Roll(5,3,3,3,5)));
        assertEquals(12, YatzyCategoriesScoringRules.pair(new Roll(5,3,6,6,5)));
    }

    @Test
    public void testTwoPair() {
        assertEquals(16, YatzyCategoriesScoringRules.twoPair(new Roll(3,3,5,4,5)));
        assertEquals(16, YatzyCategoriesScoringRules.twoPair(new Roll(3,3,5,5,5)));
    }

    @Test
    public void testThreeOfAKind()
    {
        assertEquals(9, YatzyCategoriesScoringRules.threeOfAKind(new Roll(3,3,3,4,5)));
        assertEquals(15, YatzyCategoriesScoringRules.threeOfAKind(new Roll(5,3,5,4,5)));
        assertEquals(9, YatzyCategoriesScoringRules.threeOfAKind(new Roll(3,3,3,3,5)));
        assertEquals(9, YatzyCategoriesScoringRules.threeOfAKind(new Roll(3,3,3,3,3)));
    }

    @Test
    public void testFourOfAKnd() {
        assertEquals(12, YatzyCategoriesScoringRules.fourOfAKind(new Roll(3,3,3,3,5)));
        assertEquals(20, YatzyCategoriesScoringRules.fourOfAKind(new Roll(5,5,5,4,5)));

    }

    @Test
    public void testSmallStraight() {
        assertEquals(15, YatzyCategoriesScoringRules.smallStraight(new Roll(1,2,3,4,5)));
        assertEquals(15, YatzyCategoriesScoringRules.smallStraight(new Roll(2,3,4,5,1)));
        assertEquals(0, YatzyCategoriesScoringRules.smallStraight(new Roll(1,2,2,4,5)));
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20, YatzyCategoriesScoringRules.largeStraight(new Roll(6,2,3,4,5)));
        assertEquals(20, YatzyCategoriesScoringRules.largeStraight(new Roll(2,3,4,5,6)));
        assertEquals(0, YatzyCategoriesScoringRules.largeStraight(new Roll(1,2,2,4,5)));
    }

    @Test
    public void testFullHouse() {
        assertEquals(18, YatzyCategoriesScoringRules.fullHouse(new Roll(6,2,2,2,6)));
        assertEquals(0, YatzyCategoriesScoringRules.fullHouse(new Roll(2,3,4,5,6)));
    }
}

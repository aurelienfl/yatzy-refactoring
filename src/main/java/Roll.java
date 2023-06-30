import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Roll {
    private int dice1;
    private int dice2;
    private int dice3;
    private int dice4;
    private int dice5;

    public List<Integer> createDicesList () {
        return List.of(this.dice1, this.dice2, this.dice3,this.dice4, this.dice5);
    }
}

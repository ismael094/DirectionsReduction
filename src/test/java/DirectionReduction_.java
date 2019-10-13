import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(org.junit.runners.Parameterized.class)
public class DirectionReduction_ {

    private final String[] number;
    private final String[] value;

    public DirectionReduction_(String[] directions, String[] value) {
        this.number = directions;
        this.value = value;
    }

    @Parameterized.Parameters
    public static Object[][] cases() {
        return new Object[][]{
                {new String[]{}, new String[]{}},
                {new String[]{"NORTH"}, new String[]{"NORTH"}},
                {new String[]{"WEST"}, new String[]{"WEST"}},
                {new String[]{"WEST", "WEST"}, new String[]{"WEST", "WEST"}},
                {new String[]{"WEST", "EAST"}, new String[]{}}
        };
    }

    @Test
    public void execute() {
        assertThat(reduceDirectionOf(number)).isEqualTo(value);
    }

    private String[] reduceDirectionOf(String[] directions) {
        if (directions.length == 0) return new String[]{};
        List<String> list = new ArrayList<>();
        for (int i = 0; i<directions.length;i++) {
            String direction = directions[i];
            if (direction.equals("WEST") && !search(directions,"EAST",i))
                list.add(direction);
            else if (direction.equals("NORTH") && !search(directions,"SOUTH",i))
                list.add(direction);
        }
        return list.toArray(new String[list.size()]);
    }

    private boolean search(String[] directions, String directionLook, int i) {
        for (int j = i+1;j<directions.length;j++) {
            if (directions[j].equals(directionLook))
                return true;
        }
        return false;
    }


}

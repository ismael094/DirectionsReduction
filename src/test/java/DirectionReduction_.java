import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(org.junit.runners.Parameterized.class)
public class DirectionReduction_ {

    public static final String WEST = "WEST";
    public static final String EAST = "EAST";
    public static final String NORTH = "NORTH";
    public static final String SOUTH = "SOUTH";
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
                {new String[]{NORTH}, new String[]{NORTH}},
                {new String[]{WEST}, new String[]{WEST}},
                {new String[]{WEST, WEST}, new String[]{WEST, WEST}},
                {new String[]{NORTH, SOUTH}, new String[]{}},
                {new String[]{NORTH, SOUTH, SOUTH, EAST, WEST, NORTH, WEST}, new String[]{WEST}}
        };
    }

    @Test
    public void execute() {
        assertThat(reduceDirectionOf(number)).isEqualTo(value);
    }

    private String[] reduceDirectionOf(String[] directions) {
        List<String> list = getList(directions);
        for (int i = 0; i<list.size()-1;i++) {
            String direction = list.get(i);
            if (searchOpposite(list, getOpposite(direction),i+1))
                i = -1;

        }
        return list.toArray(new String[list.size()]);
    }

    private boolean searchOpposite(List<String> list, String directionLook, int i) {

        if (list.get(i).equals(directionLook)){
            list.remove(i);
            list.remove(i-1);
            return true;
        }
        return false;
    }

    private String getOpposite(String direction) {
        if (direction.equals(NORTH)) return SOUTH;
        else if (direction.equals(SOUTH)) return NORTH;
        else if (direction.equals(WEST)) return EAST;
        else return WEST;
    }

    private List<String> getList(String[] directions) {
        return iterate(0,l -> l+1)
                .limit(directions.length)
                .map(i -> directions[i])
                .collect(toList());
    }


}

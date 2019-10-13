import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
                {new String[]{"NORTH"}, new String[]{"NORTH"}},
                {new String[]{"WEST"}, new String[]{"WEST"}}
        };
    }

    @Test
    public void execute() {
        assertThat(reduceDirectionOf(number)).isEqualTo(value);
    }

    private String[] reduceDirectionOf(String[] directions) {
        return new String[]{directions[0]};
    }
}

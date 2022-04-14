package baseball.domain;

import baseball.exception.PlaceValueIndexOutOfException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlaceValueFactoryTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 5})
    void createPlaceValues_GiveCount_ReturnWithoutDuplicates(int count) {
        List<PlaceValue> placeValueList = PlaceValueFactory.createPlaceValues(count);

        assertThat(placeValueList).isNotNull();
        assertThat(placeValueList).isNotEmpty();
        assertThat(placeValueList.size()).isEqualTo(count);
        for (int i = 0; i < count; i ++){
            assertThat(placeValueList).containsAnyOf(placeValueList.get(i));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10 , 20})
    void createPlaceValues_PlaceValueCountMoreThan10_ExceptionThrown(int count) {
        assertThatThrownBy(() -> {
            PlaceValueFactory.createPlaceValues(count);
        }).isInstanceOf(PlaceValueIndexOutOfException.class)
                        .hasMessageContaining(PlaceValueFactory.INDEX_OUT_OF_ERROR_MSG);
    }
}

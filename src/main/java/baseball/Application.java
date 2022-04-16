package baseball;

import baseball.domain.PlaceValueFactory;
import baseball.game.BaseBallGame;
import baseball.game.GameProvider;

public class Application {
    public static void main(String[] args) {
        GameProvider gameProvider = GameProvider.from(BaseBallGame.newInstance());
        gameProvider.start();
        PlaceValueFactory.createPlaceValues(3, "123");
    }
}

package demo;

import model.Actor;
import model.Game;
import ui.Board;

/**
 * Created by LionC on 07.11.2014.
 */
public class ClickParticler implements Actor {
    protected Board inputBoard;
    protected Game game;

    public ClickParticler(Game aGame) {
        this.inputBoard = aGame.getBoard();
        this.game = aGame;
    }

    @Override
    public void update() {
        if(inputBoard.mouseKeyDown(1)) {
            for(int i = 0; i < Math.random() * 50; i++) {
                DogCollisionParticle particle = new DogCollisionParticle(this.game, inputBoard.getMouseX(), inputBoard.getMouseY());
                game.addActor(particle);
                game.getBoard().add(particle);
            }
        }
    }
}

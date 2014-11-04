package model;

import ui.Board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    protected int fps = 30;
    protected int frameLength = 1000 / fps;
    protected long lastFrame;

    protected List<Board> boards = new ArrayList<>();
    protected List<Actor> actors = new LinkedList<>();

    public Game() {
        //TODO: Construct and add default Board
    }

    public Game(Board aBoard) {
        this.boards.add(aBoard);
    }

    public void addBoard(Board aBoard) {
        this.boards.add(aBoard);
    }

    public boolean removeBoard(Board aBoard) {
        return this.boards.remove(aBoard);
    }

    public List<Board> getBoards() {
        //TODO: This is kinda mutable, someGame.getBoards().remove(someBoard) - is this ok?
        return this.boards;
    }

    public Board getBoard(int aIndex) {
        return this.boards.get(aIndex);
    }

    public Board getBoard() {
        return this.getBoard(0);
    }

    public void addActor(Actor aActor) {
        this.actors.add(aActor);
    }

    public boolean removeActor(Actor aActor) {
        return this.actors.remove(aActor);
    }

    public void setFps(int aFps) {
        this.fps = aFps;
        this.frameLength = 1000 / aFps;
    }

    public void run() {
        long cur = System.currentTimeMillis();
        this.lastFrame = cur;

        for(;;) {
            for(Actor act : this.actors)
                act.update();

            for(Board act : this.boards)
                act.update();

            cur = System.currentTimeMillis();
            this.lastFrame = cur;
            this.busyWait(this.frameLength - (int)(cur - this.lastFrame));
        }
    }

    protected void busyWait(int milis) {
        if(milis > 0) {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() < (start + milis))
                Thread.yield();
        }
    }
}

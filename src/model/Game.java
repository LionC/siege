package model;

import ui.Board;
import utility.Average;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    protected int fps = 30;
    protected int frameLength = 1000 / fps;
    protected long lastFrame;

    protected List<Board> boards = new ArrayList<>();
    protected List<Actor> actors = new LinkedList<>();
    protected List<Actor> actorsToRemove = new LinkedList<>();
    protected List<Actor> actorsToAdd = new LinkedList<>();

    protected CollisionChecker collider = new CollisionChecker();

    public Game() {
        this.boards.add(new Board());
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

    public void clearBoards() {
        this.boards.clear();
    }

    public List<Board> getBoards() {
        //TODO: This is kinda mutable, someGame.getBoards().remove(someBoard) - is this ok?
        return this.boards;
    }

    public CollisionChecker getCollisionChecker() {
        return this.collider;
    }

    public Board getBoard(int aIndex) {
        return this.boards.get(aIndex);
    }

    public Board getBoard() {
        return this.getBoard(0);
    }

    public void addActor(Actor aActor) {
        this.actorsToAdd.add(aActor);
    }

    public void removeActor(Actor aActor) {
        this.actorsToRemove.add(aActor);
    }

    public void clearActors() {
        this.actors.clear();
    }

    public void clear() {
        for(Board act : this.boards) {
            act.clear();
        }

        this.getCollisionChecker().clear();
        this.clearActors();
    }

    public void setFps(int aFps) {
        this.fps = aFps;
        this.frameLength = 1000 / aFps;
    }


    /**
     * updates tha internal acctor lists
     */
    protected void updateActorList() {
        actors.removeAll(actorsToRemove);
        actors.addAll(actorsToAdd);

        actorsToAdd.clear();
        actorsToRemove.clear();
    }

    public void run() {
        long cur = System.currentTimeMillis();
        this.lastFrame = cur;

        Average avg = new Average();

        for(;;) {
            this.collider.checkCollisions();

            this.updateActorList();
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

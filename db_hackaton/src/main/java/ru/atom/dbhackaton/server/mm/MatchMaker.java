package ru.atom.dbhackaton.server.mm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 19.04.17.
 */
public class MatchMaker implements Runnable {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);


    @Override
    public void run() {
        log.info("Match Maker Started");
        List<Connection> candidates = new ArrayList<>(GameSession.PLAYERS_IN_GAME);
        while (!Thread.currentThread().isInterrupted()) {

            try {
                candidates.add(
                        ThreadSafeQueue.getInstance().poll(10_000, TimeUnit.SECONDS)
                );


                /* Check for null
            Connection connection = ThreadSafeQueue.getInstance().poll();
            if (connection != null) {
                candidates.add(connection);
                }
                 */


            } catch (InterruptedException e) {
                log.warn("Timeout reached");
            }

            if (candidates.size() == GameSession.PLAYERS_IN_GAME) {
                GameSession session = new GameSession(candidates.toArray(new Connection[0]));
                log.info("create new session! {}", session);
                ThreadSafeStorage.put(session);
                candidates.clear();
            }
        }
    }
}
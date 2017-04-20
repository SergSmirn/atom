package ru.atom.dbhackaton.server.mm;

import ru.atom.dbhackaton.server.model.Token
import java.util.concurrent.atomic.AtomicLong;
/**
 * Created by konstantin on 19.04.17.
 */

public class Connection {
    private final Token token;
    private AtomicLong sessionId = new AtomicLong(-1);

    public Connection(Token token) {
        this.token = token;
    }


    public Token getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        return token.equals(that.token);

    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    public void setSessionId(long sessionId) {
        this.sessionId.set(sessionId);
    }

    public long getSessionIdValue() {
        return sessionId.get();
    }

    public boolean idNull() {
        return sessionId.equals(-1);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "token='" + token.toString() + '\'' +
                ", sessionId=" + sessionId +
                '}';
    }

}
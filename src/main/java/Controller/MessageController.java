package Controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


@Controller
@ServerEndpoint("/chat")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Set<Session> users = new CopyOnWriteArraySet<>();

    @OnOpen
    public void OnOpen(Session session) {
        users.add(session);
        logger.info("Connected: " + session.getId());
    }

    @OnClose
    public void OnClose(Session session) {
        users.remove(session);
        logger.info("Disconnected: " + session.getId());
    }

    @OnMessage
    public void OnMessage(Session session, String message) {

    }

    @OnError
    public void OnError(Session session, Throwable throwable) {
        logger.warn("OnError: " + throwable.getMessage());
        users.remove(session);
    }
}

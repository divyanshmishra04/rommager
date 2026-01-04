package com.roomate.websocket;



import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



/**
 * WebSocket endpoint for sending real-time notifications to connected users.
 * This class creates a persistent connection between the server and clients (JSPs).
 *
 * When a chore is marked done in ChoreServlet, it can call:
 *     NotificationEndpoint.broadcast("✅ Divyansh completed 'Water Can'. Next: Arpit.");
 *
 * All connected clients will instantly receive this message without refreshing.
 */

@ServerEndpoint("/notifications")  // URL that clients (JSP) will connect to
public class NotificationEndpoint {

    // A synchronized set to track all connected client sessions
    private static final Set<Session> userSessions = Collections.synchronizedSet(new HashSet<>());

    /**
     * Called automatically when a new client (browser) connects.
     */
    @OnOpen
    public void onOpen(Session session) {
        userSessions.add(session);
        System.out.println("✅ New WebSocket connection opened: " + session.getId());
    }

    /**
     * Called when the client sends a message to the server.
     * (Not needed for simple notifications, but useful if you want two-way chat.)
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("📩 Message received from client " + session.getId() + ": " + message);
    }

    /**
     * Called when a client disconnects.
     */
    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session);
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    /**
     * Called if there’s an error during the connection.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("⚠️ WebSocket error on session " + session.getId() + ": " + throwable.getMessage());
    }

    /**
     * Utility method that lets other servlets broadcast messages to all clients.
     * Example use (in ChoreServlet after updating DB):
     *
     *     NotificationEndpoint.broadcast("✅ Divyansh completed 'Water Can'. Next: Arpit.");
     */
    public static void sendNotification(String message) {
        synchronized (userSessions) {
            for (Session session : userSessions) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.wiichat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * 聊天服务器类
 *
 */
@ServerEndpoint(value = "/websocket")
public class ChatServer {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 日期格式化

	private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);
	static Set<Session> sessionSet = new HashSet<>();

	@OnOpen
	public void open(Session session) {
		// 添加初始化操作
		sessionSet.add(session);
	}

	/**
	 * 接受客户端的消息，并把消息发送给所有连接的会话
	 * 
	 * @param message
	 *            客户端发来的消息
	 * @param session
	 *            客户端的会话
	 */
	@OnMessage
	public void getMessage(String message, Session session) {
		// 把客户端的消息解析为JSON对象
		JSONObject jsonObject = JSONObject.fromObject(message);
		// 在消息中添加发送日期
		jsonObject.put("date", DATE_FORMAT.format(new Date()));
		// 把消息发送给所有连接的会话
		logger.info("session Set : " + sessionSet);
		Iterator<Session> it = sessionSet.iterator();
		while (it.hasNext()) {
			Session currentSession = it.next();
			// 添加本条消息是否为当前会话本身发的标志
			jsonObject.put("isSelf", currentSession.equals(session));
			try {
				if (currentSession.isOpen()) {
					// 发送JSON格式的消息
					currentSession.getBasicRemote().sendText(jsonObject.toString());
				}else{
					// session 已经关闭则移出
					logger.info("remove closed session " + currentSession + "");
					it.remove();
				}
			} catch (IOException e) {
			}
		}
	}

	@OnClose
	public void close(Session session) {
		// 添加关闭会话时的操作
		logger.info(session + "session closed .");
	}

	@OnError
	public void error(Throwable t) {
		// 添加处理错误的操作
		logger.error("session error : " + t);
	}
}
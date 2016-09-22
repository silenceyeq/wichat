package com.wiichat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

/**
 * 聊天服务器类
 *
 */
@ServerEndpoint(value="/websocket")
public class ChatServer {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
	
	static Set<Session> sessionSet = new HashSet<>();
	
	Session session;
	
	@OnOpen
	public void open(Session session) {
		// 添加初始化操作
		sessionSet.add(session);
		this.session = session;
	}
	
	/**
	 * 接受客户端的消息，并把消息发送给所有连接的会话
	 * @param message 客户端发来的消息
	 * @param session 客户端的会话
	 */
	@OnMessage
	public void getMessage(String message,Session session) {
		// 把客户端的消息解析为JSON对象
		JSONObject jsonObject = JSONObject.fromObject(message);
		// 在消息中添加发送日期
		jsonObject.put("date", DATE_FORMAT.format(new Date()));
		// 把消息发送给所有连接的会话
		System.out.println(sessionSet);
		for (Session openSession : sessionSet) {
			System.out.println("openSession: "+openSession);
			System.out.println("session: "+session);
			// 添加本条消息是否为当前会话本身发的标志
			jsonObject.put("isSelf", openSession.equals(session));
			// 发送JSON格式的消息
			try {
				openSession.getBasicRemote().sendText(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void close() {
		// 添加关闭会话时的操作
	}

	@OnError
	public void error(Throwable t) {
		// 添加处理错误的操作
	}
}
package com.huatech.messageremind.service;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class DwrScriptSessionManagerUtil extends DwrServlet{
	
	private static final long serialVersionUID = -7504612622407420071L;  

	
	public void init() throws ServletException {
		
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		
		//ά��һ��Map keyΪsession��Id�� valueΪScriptSession����
	    
		
		ScriptSessionListener listener = new ScriptSessionListener() {
			
			
			public void sessionCreated(ScriptSessionEvent ev) {
				HttpSession session = WebContextFactory.get().getSession();

				String userId =  (String) session.getAttribute("userId");
				System.out.println("a ScriptSession is created!");
				
				ev.getSession().setAttribute("userId", userId);
				
				//scriptSessionMap.put(session.getId(), scriptSession); 
			}

			public void sessionDestroyed(ScriptSessionEvent ev) {
				//��֪���������������ʱ��srciptSession�ǲ����Ѿ�����������
				//Collection<ScriptSession> sessions = Browser.getTargetSessions();
				//sessions.remove(ev.getSession());
				System.out.println("a ScriptSession is distroyed");
			}
		};
		manager.addScriptSessionListener(listener);
	}
}

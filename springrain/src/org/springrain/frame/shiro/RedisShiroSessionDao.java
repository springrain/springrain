package org.springrain.frame.shiro;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springrain.frame.cached.ICached;
public class RedisShiroSessionDao extends AbstractSessionDAO {
	public RedisShiroSessionDao (){
	}
	private ICached cached;
	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			cached.updateCached(session.getId().toString(),session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Session session) {
		try {
			cached.deleteCached(session.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
	
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId=session.getId();
		try {
			super.assignSessionId(session, super.generateSessionId(session));
			update(session);
			sessionId=session.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionId;
		
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session=null;
		try {
			session=	(Session) cached.getCached(sessionId.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
		
	
	}

	public ICached getCached() {
		return cached;
	}

	public void setCached(ICached cached) {
		this.cached = cached;
	}
	
}

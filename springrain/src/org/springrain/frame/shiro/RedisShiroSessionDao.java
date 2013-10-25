package org.springrain.frame.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springrain.frame.cached.ICached;
public class RedisShiroSessionDao extends AbstractSessionDAO {
	public Logger logger=Logger.getLogger(getClass());
	private String sessionprefix="ss-";
	public RedisShiroSessionDao (){
	}
	private ICached cached;
	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			cached.updateCached(session.getId().toString(),session);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	public void delete(Session session) {
		try {
			cached.deleteCached(session.getId().toString());
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		String keys=sessionprefix+"*";
		List<Session> list=null;
		try {
		 list=	cached.getKeys(keys);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId=session.getId();
		try {
			super.assignSessionId(session,sessionprefix+ super.generateSessionId(session));
			update(session);
			sessionId=session.getId();
		} catch (Exception e) {
			logger.error(e);
		}
		return sessionId;
		
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session=null;
		try {
			session=	(Session) cached.getCached(sessionId.toString());
		} catch (Exception e) {
			logger.error(e);
		}
		return session;
		
	
	}

	public ICached getCached() {
		return cached;
	}

	public void setCached(ICached cached) {
		this.cached = cached;
	}

	public String getSessionprefix() {
		return sessionprefix;
	}

	public void setSessionprefix(String sessionprefix) {
		this.sessionprefix = sessionprefix;
	}
	
}

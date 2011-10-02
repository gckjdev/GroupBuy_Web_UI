package com.orange.groupbuy.web.server.handler;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.sf.json.JSONObject;

import com.orange.groupbuy.web.client.dispatch.GetUser;
import com.orange.groupbuy.web.client.dispatch.RegisterEmail;
import com.orange.groupbuy.web.client.model.UserInfo;
import com.orange.groupbuy.web.server.util.ProxyUtil;
import com.orange.groupbuy.web.server.util.SessionUtil;
import com.orange.groupbuy.web.server.util.StringUtil;
import com.orange.groupbuy.web.shared.ServiceConstant;

public class RegisterEmailHandler  implements ActionHandler<RegisterEmail, UserInfo> {
	private Logger log = Logger.getLogger(RegisterEmailHandler.class.getName());
	@Override
	public UserInfo execute(RegisterEmail action, ExecutionContext arg1)
			throws DispatchException {
			String apiServerUrl = ProxyUtil.getSearchGroupBuyUrl();
	        String requestUrl = apiServerUrl + "m=ru&rt=1&app=GROUPBUYWEB&em=" + action.getEmail()+ "&pwd=" + action.getPassword();
	        UserInfo user = null;

	        try {
	            InputStream inputStream = ProxyUtil.getResponse(requestUrl);

	            StringWriter sw = new StringWriter();
	            StringUtil.copyLarge(inputStream, sw);

	            JSONObject dataObject = JSONObject.fromObject(sw.toString());
	            JSONObject result = dataObject.getJSONObject("dat");

				if (result != null && !result.isNullObject()) {
	                user = jsonToResult(result);
					// save in session
					SessionUtil.get().setAttribute(user.getUserId(), user);
	            }

	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        }
	        return user;
	}
	
	 private UserInfo jsonToResult(JSONObject object) {
	        UserInfo user = new UserInfo();
	        user.setUserId(getString(object, ServiceConstant.PARA_USERID));
	        return user;
	    }

	    private String getString(JSONObject object, String key) {
	        String returnValue = "";
	        Object value = object.get(key);

	        if (value != null) {
	            returnValue = String.valueOf(value);
	        }
	        return returnValue;
	    }

	@Override
	public Class<RegisterEmail> getActionType() {
		return RegisterEmail.class;
	}

	@Override
	public void rollback(RegisterEmail arg0, UserInfo arg1,
			ExecutionContext arg2) throws DispatchException {
		// TODO Auto-generated method stub
		
	}



}

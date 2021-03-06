package com.sanmina.tk.config;

import java.util.Hashtable;

import javax.naming.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class LoginInActiveDirectory {
	
	public boolean login(String userName, String userPassword){
		boolean isValid = false;
		LdapContext ctx = null;	
		try{
			Hashtable hashtable = new Hashtable();
			hashtable.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			hashtable.put(Context.SECURITY_AUTHENTICATION, "Simple");
			hashtable.put(Context.SECURITY_PRINCIPAL, "am\\" + userName);
			hashtable.put(Context.SECURITY_CREDENTIALS, userPassword);
			hashtable.put(Context.PROVIDER_URL, "ldap://gdl1amdcw01.am.sanm.corp");
			ctx = new InitialLdapContext(hashtable, null);
			isValid = true;
			ctx.close();
		}catch(NamingException ex){
			isValid = false;
		}
		return isValid;
	}
}

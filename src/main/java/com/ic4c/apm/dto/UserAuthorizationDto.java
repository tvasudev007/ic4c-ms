/**
 * 
 */
package com.ic4c.apm.dto;

/**
 * @author 10620506
 *
 */
public class UserAuthorizationDto {
	
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String jti;
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}
	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	/**
	 * @return the expires_in
	 */
	public String getExpires_in() {
		return expires_in;
	}
	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the jti
	 */
	public String getJti() {
		return jti;
	}
	/**
	 * @param jti the jti to set
	 */
	public void setJti(String jti) {
		this.jti = jti;
	}
	/**
	 * 
	 */
	public UserAuthorizationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param access_token
	 * @param token_type
	 * @param refresh_token
	 * @param expires_in
	 * @param scope
	 * @param jti
	 */
	public UserAuthorizationDto(String access_token, String token_type, String refresh_token, String expires_in,
			String scope, String jti) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.refresh_token = refresh_token;
		this.expires_in = expires_in;
		this.scope = scope;
		this.jti = jti;
	}
	
	
	

}

package com.goopal.blockchain.tools.exception;

public class GoopalException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private String retCode;
    
    private String retMsg;
    
    public GoopalException()
    {
    	super();
    }
    
    public GoopalException(String retMsg)
    {
    	super();
    	this.retMsg = retMsg;
    }
    
    public GoopalException(String retCode, String retMsg)
    {
    	super();
    	this.retCode = retCode;
    	this.retMsg = retMsg;
    }

	public String getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}
    
}

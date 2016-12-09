/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 * Exception that is thrown when a user is not allowed to do a requested action!
 * 
 * @author Friedrich
 */
public class NotAuthorizedException extends Exception {

    /**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException() {
        super();
    }
    
    public NotAuthorizedException(String message) {
        super(message);
    }
    
}

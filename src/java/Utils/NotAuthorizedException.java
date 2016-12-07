/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Friedrich
 */
public class NotAuthorizedException extends Exception {

    public NotAuthorizedException() {
        super();
    }
    
    public NotAuthorizedException(String message) {
        super(message);
    }
    
}

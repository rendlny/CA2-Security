/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author Ren
 */
public class CommandFactory {
    public Command createCommand(String action){
        Command command = null;
        if((action.toLowerCase()).equals("login_user")){
            command =  new UserLoginCommand();
        }else if((action.toLowerCase()).equals("create_user")){
            command =  new UserSignUpCommand();
        }else if((action.toLowerCase()).equals("home")){
            command =  new TitleListCommand();
        }else if((action.toLowerCase()).equals("check_out")){
            command = new TitleCheckOutCommand();
        }else if((action.toLowerCase()).equals("user_loans")){
            command =  new UserLoanCommand();
        }
        return command;
    }
}

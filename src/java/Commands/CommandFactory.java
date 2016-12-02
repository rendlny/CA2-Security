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
        action = action.toLowerCase();
        if(action.equals("login_user")){
            command =  new UserLoginCommand();
        }else if(action.equals("create_user")){
            command =  new UserSignUpCommand();
        }else if(action.equals("home")){
            command =  new TitleListCommand();
        }else if(action.equals("check_out")){
            command = new TitleCheckOutCommand();
        }else if(action.equals("user_loans")){
            command =  new UserLoanCommand();
        }else if(action.equals("update_password")){
            command =  new UserUpdatePasswordCommand();
        }else if(action.equals("update_email")){
            command =  new UserUpdateEmailCommand();
        } else if(action.equals("title_search")) {
            command = new TitleSearchCommand();
        }
        return command;
    }
}

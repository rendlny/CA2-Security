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
    public Command createCommand(String action) {
        Command command = null;
        action = action.toLowerCase();
        if (action.equals("login_user")) {
            command = new UserLoginCommand();
        } else if (action.equals("create_user")) {
            command = new UserSignUpCommand();
        } else if (action.equals("home")) {
            command = new TitleListCommand();
        } else if (action.equals("check_out")) {
            command = new TitleCheckOutCommand();
        } else if(action.equals("user_loans")) {
            command = new UserLoanCommand();
        } else if (action.equals("update_password")) {
            command = new UserUpdatePasswordCommand();
        } else if (action.equals("update_email")) {
            command = new UserUpdateEmailCommand();
        } else if (action.equals("force_update_password")) {
            command = new UserForceUpdatePasswordCommand();
        } else if (action.equals("title_search")) {
            command = new TitleSearchCommand();
        } else if (action.equals("user_search")) {
            command = new UserSearchCommand();
        } else if(action.equals("return_title")){
            command = new TitleReturnCommand();
        }else if(action.equals("get_users_sq")){
            command = new SecurityQuestionsForUserCommand();
        }else if(action.equals("check_security_answers")){
            command = new SecurityQuestionAnswersCheck();
        }else if(action.equals("reset_forgotten_password")){
            command = new UserResetForgottenPasswordCommand();
        }else if(action.equals("update_user_question")){
        	// TODO Auto-generated constructor stub
            //command = new UpdateUserQuestionAnswerCommand();
        } else if (action.equals("add_title")) {
            command = new CreateTitleCommand();
        }
        return command;
    }
}

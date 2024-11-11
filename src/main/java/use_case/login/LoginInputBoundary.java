package use_case.login;

import use_case.signup.SignupInputData;

/**
 * Input boundary for actions related to logging in.
 */
public interface LoginInputBoundary {
    /**
     * Executes the login use case.
     * @param loginInputData the input data
     */
    void execute(LoginInputData loginInputData);

}

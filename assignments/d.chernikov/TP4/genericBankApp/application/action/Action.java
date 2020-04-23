package application.action;

import bank.BankAgency;

/**
 * An Action is an object that implements some action of a user's menu.<BR>
 * It is defined by a message, an optional code, an execute method to "do" the action.
 */
public interface Action {
    /**
     * Message of the action (to show on screen).
     *
     * @return the message of the action
     */
    String actionMessage();

    /**
     * Code of the action (may be used to identify the action among other ones).
     *
     * @return the code of the action
     */
    String actionCode();

    /**
     * The method to call in order to "execute" the action on <code>ag</code>.
     *
     * @param ag the BankAgency on which the action may act on
     * @throws Exception when an uncaught exception occurs during execution
     */
    void execute(BankAgency ag) throws Exception;
}

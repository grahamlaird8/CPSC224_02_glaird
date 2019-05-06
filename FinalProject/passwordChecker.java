/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Finni
 */
public class passwordChecker {
    private String passGuess;
    private String pass;
    private String user;
    void passwordChecker(String passGuess, String pass, String user)
    {
        this.passGuess = passGuess;
        this.pass = pass;
        this.user = user;
    }
    
    void setGuess(String passGuess)
    {
        this.passGuess = passGuess;
    }
    
    void setUser(String user)
    {
        this.user = user;
    }
    
    void setPass(String pass)
    {
        this.pass = pass;
    }
    
    String getPass()
    {
        return this.pass;
    }
    
    String getUser()
    {
        return this.user;
    }
    
    String getPassGuess()
    {
        return this.passGuess;
    }
    
}

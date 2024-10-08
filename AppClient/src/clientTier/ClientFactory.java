/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTier;

import model.MainWindowClousable;
import model.SignInable;
import model.SignUpable;
import view.ClientApplication;
import view.SignInController;

/**
 *
 * @author 2dam
 */
public class ClientFactory extends ClientApplication{
    
    public static SignInable signIn(){
        return   new Client();
        
    }
    public static SignUpable signUp(){
        return null;
        
    }
    public static MainWindowClousable closeApplication(){
        return null;
        
    }
}

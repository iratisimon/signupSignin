/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientBusinessLogic;

import logicalModel.interfaces.Signable;

/**
 *
 * @author 2dam
 */
public class ClientFactory {
    
    public static Signable getSignable(){
        return new Client();
    }
}

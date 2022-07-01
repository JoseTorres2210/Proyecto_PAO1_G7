/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Det-Pc
 */
public class EmptyFieldsException extends NullPointerException{
    
    public EmptyFieldsException(){
        super("No se llenaron los campos");
    }
    
}

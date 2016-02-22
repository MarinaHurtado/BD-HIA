/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiwiki;

/**
 *
 * @author sdist
 */
import java.rmi.Naming;

public class RmiClient { 
    public static void main(String args[]) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
        for (int i=1; i<=50; i++){
            int a =(int)(Math.random()*1000);
            int b =(int)(Math.random()*1000);
            System.out.println(obj.getMessage() + ", soy el cliente " + i);
            System.out.println("La suma de " + a + " más " + b + " es " + obj.suma(a, b));
        }
    }
}
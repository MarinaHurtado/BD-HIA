//package tstrmi;

import java.rmi.Naming;

public class Client { 
    public static void main(String args[]) throws Exception {
        Hello obj = (Hello)Naming.lookup("//localhost/RmiServer");
        for (int i=1; i<=10; i++){
            int a =(int)(Math.random()*1000);
            int b =(int)(Math.random()*1000);
            System.out.println(obj.getMessage() + ", soy el cliente " + i);
            System.out.println("La suma de " + a + " más " + b + " es " + obj.suma(a, b));
        }
    }
}
}
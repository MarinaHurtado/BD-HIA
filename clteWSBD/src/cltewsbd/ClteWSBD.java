/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cltewsbd;

/**
 *
 * @author sdist
 */
public class ClteWSBD {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        for(int i=0; i<=50; i++){
            System.out.println(consultAlumnos());
        }
    }

    private static String consultAlumnos() {
        wsbd.StrBD_Service service = new wsbd.StrBD_Service();
        wsbd.StrBD port = service.getStrBDPort();
        return port.consultAlumnos();
    }
    
}

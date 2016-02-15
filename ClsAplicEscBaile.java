/*
 * ClsAplicEscBaile.java
 *
 * Created on 6 de diciembre de 2004, 03:05 PM
 */

/**
 *
 * @author  Administrador
 */
public class ClsAplicEscBaile
{
    /* Esta clase representa la aplicaci�n y es la que se ejecuta como
     * clase inicial. Solamente se utiliza el main, ya que solamente hay
     * una aplicaci�n por m�quina virtual.
     *
     * La secuencia de ejecuci�n es:
     * El main de esta clase crea al objeto de gesti�n de la(s) clase(s) que
     * contienen los casos de negocio.
     * El gestor crea a la conexi�n de negocio ( a�n desconectada ) a la Base de Datos.
     * La aplicaci�n crea la ventana de login y le notifica quien es el gestor.
     * La ventana de login solicita la verificaci�n de los datos de usuario
     * y contrase�a al gestor. En caso procedente, la conexi�n se establece
     * a la base de datos y e� objeto conexi�n de negocio se queda conectado.
     * De proceder la ejecuci�n se crea la ventana de men� principal y ah� se aloja
     * el gestor para ser asignado a cada una de la ventana que son invocadas por 
     * la funcionalidad del men�.
     *
     * Por: Rafael Gregorio Gamboa Hirales.
     * Para: Examen Final de Laboratorio de Software II
     * Per�odo: Agosto - Diciembre de 2004.
    */
    
    /** Creates a new instance of ClsAplicEscBaile */
    public ClsAplicEscBaile() 
    {
    }
    
    /**
     * @param args the command line arguments
     */
   
    // Ejemplo de uso de la ventana de mensajes: 
    //new DlgMensajes(new javax.swing.JFrame(),"Hola","Mundo...").show(); 
    
    public static void main(String[] args) 
    {
      ClsGestorEscBaile gestor = new ClsGestorEscBaile();
      
      DlgLogin dlgLogin = new DlgLogin(new javax.swing.JFrame(),gestor);
      dlgLogin.show();
      
      if( gestor.conectado() )
      {
        //new DlgMensajes(new javax.swing.JFrame(),"Escuela de Baile","Se conect� a la BD").show();
          new FrmMenuPrincipal(gestor).show();
      }
      else
       new DlgMensajes(new javax.swing.JFrame(),"Escuela de Baile","No hay conexi�n a la BD...").show();    
    }
    
}

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
    /* Esta clase representa la aplicación y es la que se ejecuta como
     * clase inicial. Solamente se utiliza el main, ya que solamente hay
     * una aplicación por máquina virtual.
     *
     * La secuencia de ejecución es:
     * El main de esta clase crea al objeto de gestión de la(s) clase(s) que
     * contienen los casos de negocio.
     * El gestor crea a la conexión de negocio ( aún desconectada ) a la Base de Datos.
     * La aplicación crea la ventana de login y le notifica quien es el gestor.
     * La ventana de login solicita la verificación de los datos de usuario
     * y contraseña al gestor. En caso procedente, la conexión se establece
     * a la base de datos y eñ objeto conexión de negocio se queda conectado.
     * De proceder la ejecución se crea la ventana de menú principal y ahí se aloja
     * el gestor para ser asignado a cada una de la ventana que son invocadas por 
     * la funcionalidad del menú.
     *
     * Por: Rafael Gregorio Gamboa Hirales.
     * Para: Examen Final de Laboratorio de Software II
     * Período: Agosto - Diciembre de 2004.
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
        //new DlgMensajes(new javax.swing.JFrame(),"Escuela de Baile","Se conectó a la BD").show();
          new FrmMenuPrincipal(gestor).show();
      }
      else
       new DlgMensajes(new javax.swing.JFrame(),"Escuela de Baile","No hay conexión a la BD...").show();    
    }
    
}

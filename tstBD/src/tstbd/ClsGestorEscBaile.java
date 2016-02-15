package tstBD;

/*
 * ClsGestorEscBaile.java
 *
 * Created on 6 de diciembre de 2004, 03:29 PM
 */

/**
 *
 * @author  Administrador
 */
public class ClsGestorEscBaile
{
    ClsConexion conexion;
// ---------------------------------------------------------------------------    
    public ClsGestorEscBaile ()
    {
       conexion = new ClsConexion("EscBaileExFin");
    }
// ---------------------------------------------------------------------------        
    public boolean conectaBD(String strUsuario, String strContrasenha )
    {
        return conexion.conectate (strUsuario, strContrasenha );
    }
// ---------------------------------------------------------------------------        
    public boolean conectado() 
    {
        return conexion.conectado();
    } 
// --------------------------------------------------------------------------- 
   public void desconecta()
   {
     conexion.cierraCon();  
   }
// --------------------------------------------------------------------------- 
    public java.sql.ResultSet obtenAlumnos()
    {
      return conexion.obtenRS ("tblAlumnos");
    }
    
// ---------------------------------------------------------------------------
    public String obtenAlumnosStr()
    {
      return conexion.impRS(this.obtenAlumnos());
    }
// ---------------------------------------------------------------------------
    public java.sql.ResultSet obtenProfesores()
    {
        return conexion.obtenRS ("tblProfesores");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenBailes()
    {
        return conexion.obtenRS ("tblBailes");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenNiveles()
    {
        return conexion.obtenRS ("tblNiveles");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenGrupos()
    {
        return conexion.obtenRS ("tblGrupos");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenAlumnosPorGrupo(String numGpo )
    { 
       return null;
    }
// ---------------------------------------------------------------------------
//                           Obtener un solo campo
// ---------------------------------------------------------------------------
   public java.sql.ResultSet obtenCampoParaLista(String strTV, String strCampo )
   {
     String strSelect = "Select " + strCampo + " From " + strTV; 
     return conexion.obtenRegSelect(strSelect);  
   }
// ---------------------------------------------------------------------------    
//                            Altas de registros 
// ---------------------------------------------------------------------------
    public boolean altaAlumno()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaProfesor()
    {
     return true;   
    }  
// ---------------------------------------------------------------------------    
    public boolean altaBaile()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaNivel()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaGrupo()
    {
     return true;   
    }    
// ---------------------------------------------------------------------------    
    public boolean altaAlumnoEnGrupo(String numGpo, String clvAlumno )
    {
     return true;   
    }    
// --------------------------------------------------------------------------- 
    public static void main(String args[])
    {
        ClsGestorEscBaile gbd = new ClsGestorEscBaile();
        gbd.conectaBD("rafa", "rafa");
        if( gbd.conectado())
        {
            System.out.println(gbd.obtenAlumnosStr());
            gbd.desconecta();
        }else {
            System.out.println("No se pudo conectar a la base de datos");
        }
    }
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
    
}
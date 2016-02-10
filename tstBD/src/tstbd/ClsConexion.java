package tstBD;
//import net.ucanaccess.util.*;
//import net.ucanaccess.jdbc.*;

import java.sql.*;
import java.util.*;

/*
 * ClsConexion.java
 *
 * Created on 20 de noviembre de 2004, 01:23 PM
 */

/**
 *
 * @author  Rafael G. Gamboa Hirales
 */
public class ClsConexion
{
    java.sql.Connection con    = null;
    String              strUID = null;
    String              strURL = null;
    String              strDSN = null;
// ---------------------------------------------------------------------------    
    /** Creates a new instance of ClsConexion */
    public ClsConexion(String unDSN )
    {
        // no se conecta, solamente crea el objeto
        strDSN = unDSN;
    }
    
    public boolean conectate( String unUID, String unPwd )
    {
        strUID = unUID;
        String strSel = "Select contrasenha from tblUsuarios where clvUsuario = '" 
                        + unUID + "'";
        boolean res = false;
        
        try
        {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //con = DriverManager.getConnection( "jdbc:odbc:" + 
            //                                    strDSN, "", "");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sdist/Documents/BD - HIA/TstBD_EscBaileExFin.mdb");
            // Si todo va bien, verifica el unID y unPwd
            
            java.sql.ResultSet rs = this.obtenRegSelect(strSel);
            if( rs.next() )
            {
              String strContrasenha = rs.getString(1);  
              if( strContrasenha.compareTo(unPwd ) == 0 )
                  res = true;
              else
              {
               con.close();
               con = null;
              }
            }
            else
            {
              con.close();
              con = null;
            }
        }
        catch( Exception e )
        { 
           e.printStackTrace();
           con = null;
        }
        return res;
    }
// ---------------------------------------------------------------------------    
    public boolean conectado() { return con != null; }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenRS( String strTV )
    {
        return obtenRegSelect( "Select * FROM " + strTV );
    }
// ---------------------------------------------------------------------------    
   public java.sql.ResultSet obtenRS( String strTV, 
                                             java.util.TreeMap colCampos)
   {
      String strSelect = "",
             strWhere  = "";
      ClsCampoBD campo;
     // java.util.Iterator it = colCampos.entrySet().iterator();
      
      java.sql.ResultSet rs = null;
      
      strSelect = ClsConexion.cadenaSelect(colCampos) + " FROM " + strTV;
      strWhere  = ClsConexion.cadenaWhere(colCampos);
      
      if( strWhere.length() > 0 )
          strSelect = strSelect + " Where " + strWhere;
      
      rs = obtenRegSelect( strSelect );
      
      return rs; 
   }
// ---------------------------------------------------------------------------    
   public java.sql.ResultSet obtenRegSelect( String strSelect )
   {
       java.sql.ResultSet rs = null;
       java.sql.Statement st = null;
       try
       {
           st = con.createStatement();  
           rs = st.executeQuery( strSelect );
       }
       catch(Exception e )
       {
           e.printStackTrace();
           rs = null;
       }
       
       return rs;
   }
// ---------------------------------------------------------------------------    
   public String impRS( java.sql.ResultSet rs )
   {
       String                     strRes = "";
       java.sql.ResultSetMetaData rsmd   = null;
       int n,nCol,i;
       try
       {
           rsmd = rs.getMetaData();
           nCol = rsmd.getColumnCount();
           for( i = 1; i<=nCol; i++ )
               strRes += rsmd.getColumnName(i) + " ";
           strRes += '\n';
           n = 0;
           while( rs.next() )
           {
               n++;
               strRes += n + " ";
               for( i = 1; i<=nCol; i++ )
                  strRes += rs.getString(i) + " ";
               strRes += '\n';
           }
       }
       catch(Exception e )
       {
           e.printStackTrace();
       }
       
       return strRes;
   }
// --------------------------------------------------------------------------- 
   public java.util.TreeMap obtenMapaCampos( java.sql.ResultSet rs )
   {
       java.util.TreeMap arbolCampos = new java.util.TreeMap();
       java.sql.ResultSetMetaData rsmd   = null;
       ClsCampoBD campo     = null;
       String strNomCampo   = null, 
              strTipoCampo  = null;
       
       int nCol,i;
       
       try
       {
           rsmd = rs.getMetaData();
           nCol = rsmd.getColumnCount();
           for( i = 1; i<=nCol; i++ )
           { 
              strNomCampo   = rsmd.getColumnName(i);
              strTipoCampo  = rsmd.getColumnTypeName(i);
              campo = new ClsCampoBD(strNomCampo, "", strTipoCampo, "" );
              arbolCampos.put(strNomCampo, campo );
           }
              
       }
       catch(Exception e )
       {
           e.printStackTrace();
       }
       
       return arbolCampos;
  
   }
// --------------------------------------------------------------------------- 
   public static String cadenaWhere( java.util.TreeMap colCampos )
   {
       String strWhere = "";
       ClsCampoBD campo;
       java.util.Iterator it = colCampos.entrySet().iterator();
       boolean primero = true;
          
       while( it.hasNext())
       {
          campo =  (ClsCampoBD)((Map.Entry) (it.next())).getValue();
          if( campo.valWhere.length() > 0 )
          {
             if( !primero ) strWhere += " AND "; 
             strWhere += "(" + campo.nombre + " = " + 
                          campo.codificaDato( campo.valWhere ) + ")";
             primero = false;
          }
       }
       return strWhere;
   }
// ---------------------------------------------------------------------------        
   public static String cadenaSelect( java.util.TreeMap colCampos )
   {
       String strSelect = "";
       ClsCampoBD campo;
       java.util.Iterator it = colCampos.entrySet().iterator();
       boolean primero = true;
      
       while( it.hasNext())
       {
          //Object obj = it.next();
          //System.out.println( obj.getClass().getName() );
          campo =  (ClsCampoBD)((Map.Entry) (it.next())).getValue();
          if( campo.nombre.length() > 0 )
          {
             if( campo.nombre == "*" )
             {
                 strSelect = "*";
                 break;
             }
             if( !primero ) strSelect += ','; 
             strSelect += campo.nombre;
             primero = false;
          }
       }
 
        return "Select " + strSelect;
   }
// ---------------------------------------------------------------------------    
   public boolean insertaReg(String strTV, java.util.TreeMap colCampos )
   {
       String      strCampos =  "Insert into " + strTV + " (";
       String      strValores  = " Values (";
       String      strInsertar;
       int          k = 0;
       boolean blnRes        = false;
       
       ClsCampoBD campo;
       java.util.Iterator it = colCampos.entrySet().iterator();
       boolean primero = true;
      
       while( it.hasNext())
       {
          campo =  (ClsCampoBD)((Map.Entry) (it.next())).getValue();
          if( campo.valor.length() > 0 )
          {
             if( !primero )
             { 
                 strCampos += ", ";
                 strValores += ", ";
             }
             strCampos += campo.nombre;
             strValores += campo.codificaDato( campo.valor );
             primero = false;
          }
       }
       strCampos  += ")";
       strValores += ")";
       
       strInsertar = strCampos + strValores;
       System.out.println( strInsertar );
       
       try
       {
          java.sql.Statement s = con.createStatement();
         k = s.executeUpdate(strInsertar);
          s.close();
       }
       catch( Exception e )
       {
           e.printStackTrace();
       }
       return  k > 0 ;
    
   }
// --------------------------------------------------------------------------  
   public boolean modificaReg(String strTV, java.util.TreeMap colCampos )
   {
       String      strCampos   = "Update " + strTV + " set ";
       
       String      strWhere    = " Where ( ";
       String      strModiReg;
       int          k = 0;
       boolean blnRes        = false;
       
       ClsCampoBD campo;
       java.util.Iterator it = colCampos.entrySet().iterator();
       boolean primero = true;
      
       while( it.hasNext())
       {
          campo =  (ClsCampoBD)((Map.Entry) (it.next())).getValue();
          if( campo.valor.length() > 0 )
          {
             if( !primero )
             { 
                 strCampos += ", ";
             }
             strCampos += campo.nombre + "=";
             strCampos += campo.codificaDato( campo.valor );
             primero = false;
          }
       }
     
       strWhere   += ClsConexion.cadenaWhere(colCampos) + ")";
      
      if( strWhere.length() > 0 )
          strCampos += strWhere; 
       
       System.out.println( strCampos );
       
       try
       {
          java.sql.Statement s = con.createStatement();
         k = s.executeUpdate(strCampos);
          s.close();
       }
       catch( Exception e )
       {
           e.printStackTrace();
       }
       return  k > 0 ;
    
   }
// --------------------------------------------------------------------------  
   public boolean eliminaRegs( String strTV, 
                                             java.util.TreeMap colCampos)
   {
      String strDelete = "",
             strWhere  = "";
      ClsCampoBD campo;
      int k = 0;
            
      strDelete = "Delete  FROM " + strTV;
      strWhere  = ClsConexion.cadenaWhere(colCampos);
      
      if( strWhere.length() > 0 )
          strDelete += " Where " + strWhere;
          
      System.out.println( strDelete);    
       try
       {
          java.sql.Statement s = con.createStatement();
          k = s.executeUpdate(strDelete);
          s.close();
       }
       catch( Exception e )
       {
           e.printStackTrace();
       }
       return  k > 0 ;
    
   }
// ---------------------------------------------------------------------------    
  public void cierraCon()
   {
       try
       {
           con.close();
       }
       catch( Exception e )
       {
           e.printStackTrace();
       }
   }
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
    public static void main(String[] args)
    {
       //System.out.println("Hola...");
        int intPrueba[] = {1,5,4,6,2,7,2};
        int i,n = 7;
        java.sql.ResultSet r = null;
        java.util.TreeMap colCampos = null;
        
        try
        {
           ClsConexion c = new ClsConexion("TstBD");
           c.conectate("rafa","rafa");
           if( c.conectado() )    
           {
           	  for( i = 0; i < n; i++ )
                switch( intPrueba[i] )
                {
                  case 1:  
                         r = c.obtenRS("tblAlumnos");
                         if( r != null )
                         {
                           System.out.println(c.impRS(r));
                         }
                         else
                             System.out.println(
                                "No se pudo recuperar el ResultSet...");  
                         break;
                  case 2:
                         r = c.obtenRS("tblAlumnos");
                         colCampos = c.obtenMapaCampos(r);
                         r = c.obtenRS("tblAlumnos",colCampos );
                         System.out.println(c.impRS(r));
                         break;
                  case 3:
                         r = c.obtenRS("tblAlumnos");
                         colCampos = c.obtenMapaCampos(r);
                         colCampos.put("*",new ClsCampoBD("*","","","")); 
                         r = c.obtenRS("tblAlumnos",colCampos );
                         System.out.println(c.impRS(r));
                         break;
                 case 4:
                         colCampos = new TreeMap();
                         colCampos.put("2_apPaterno",
                                                    new ClsCampoBD("apPaterno","",ClsCampoBD.TIPO_VARCHAR,"")); 
                         colCampos.put("1_cu",new ClsCampoBD("cu","","","")); 
                         r = c.obtenRS("tblAlumnos",colCampos );
                         System.out.println(c.impRS(r));
                         break;
                   case 5:
                         colCampos = new TreeMap();
                         colCampos.put("apPaterno",
                                        new ClsCampoBD("apPaterno","apPaternoNo. " + i,ClsCampoBD.TIPO_VARCHAR,"")); 
                         colCampos.put("apMaterno",
                                        new ClsCampoBD("apMaterno","apMaternoNo. " + i,ClsCampoBD.TIPO_VARCHAR,"")); 
                         colCampos.put("nombre",
                                        new ClsCampoBD("nombre","N�mero "+ i,ClsCampoBD.TIPO_VARCHAR,"")); 
                         colCampos.put("cu",new ClsCampoBD("cu","1000"+i,ClsCampoBD.TIPO_INTEGER,"")); 
                         c.insertaReg("tblAlumnos",colCampos );
                         //System.out.println(c.impRS(r));
                         break;
                   case 6:
                         colCampos = new TreeMap();
                         colCampos.put("apPaterno",
                                        new ClsCampoBD("apPaterno","apPaternoModificado" ,ClsCampoBD.TIPO_VARCHAR,"apPaternoNo. 1")); 
                         colCampos.put("apMaterno",
                                        new ClsCampoBD("apMaterno","apMaternoModificado" ,ClsCampoBD.TIPO_VARCHAR,"apMaternoNo. 1")); 
                         colCampos.put("nombre",
                                        new ClsCampoBD("nombre","",ClsCampoBD.TIPO_VARCHAR,"N�mero 1")); 
                         colCampos.put("cu",new ClsCampoBD("cu","2525",ClsCampoBD.TIPO_INTEGER,"10001")); 
                         c.modificaReg("tblAlumnos",colCampos );
                         //System.out.println(c.impRS(r));
                         break;
                   case 7:
                         colCampos = new TreeMap();
                         colCampos.put("apPaterno",
                                        new ClsCampoBD("apPaterno", "",ClsCampoBD.TIPO_VARCHAR,"apPaternoModificado")); 
                         colCampos.put("apMaterno",
                                        new ClsCampoBD("apMaterno","" ,ClsCampoBD.TIPO_VARCHAR,"apMaternoModificado")); 
                         colCampos.put("nombre",
                                        new ClsCampoBD("nombre","",ClsCampoBD.TIPO_VARCHAR,"N�mero 1")); 
                         colCampos.put("cu",new ClsCampoBD("cu","",ClsCampoBD.TIPO_INTEGER,"2525")); 
                         c.eliminaRegs("tblAlumnos",colCampos );
                         //System.out.println(c.impRS(r));
                         break;
                       
               }
               
               
             c.cierraCon();
           }
           else
           {
              System.out.println("No se pudo conectar..."); 
           }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
       
    }
// ---------------------------------------------------------------------------        
}

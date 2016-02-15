/*
 * ModeloLista.java
 *
 * Created on 5 de diciembre de 2004, 04:35 PM
 */

/**
 *
 * @author  Administrador
 */
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ModeloLista implements ComboBoxModel
{
    Vector renglones;
    String cadSel;
    
    /** Creates a new instance of ModeloLista */
    public ModeloLista(java.sql.ResultSet rs) 
    {
      renglones = new Vector();
      try
      {
       while(rs.next())
        {
          renglones.add(  new String(rs.getString(1)) );  
        }
      }
      catch( Exception e)
      {
          e.printStackTrace();
      } 
      if( renglones.size() > 0 ) 
          cadSel = (String) renglones.elementAt(0);
      else
          cadSel = "";
    }
    
    public void addListDataListener(javax.swing.event.ListDataListener listDataListener) 
    {
    }
    
    public Object getElementAt(int param) 
    {
      return renglones.elementAt(param);  
    }
    
    public int getSize() 
    {
        return renglones.size();
    }
    
    public void removeListDataListener(javax.swing.event.ListDataListener listDataListener) 
    {
    }
    
    public Object getSelectedItem()
    {
        return (String) cadSel;
    }
    
    public void setSelectedItem(Object obj)
    {
        //System.out.println("el objeto es " +obj);
        cadSel = (String) obj;
    }
    
}

/*
 * MiModelo.java
 *
 * Created on 3 de diciembre de 2004, 01:21 AM
 */

/**
 *
 * @author  Administrador
 */
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

public class MiModelo extends AbstractTableModel implements TableModel
{
    Vector titulos;

    Vector renglones;
    
    /** Creates a new instance of MiModelo */
    public MiModelo( ResultSet rs )
    {
      int i,n;
      Vector renglon;
      
      try
      {
        titulos = new Vector();
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();  
        n = rsmd.getColumnCount();
        for(i=1;i<=n;i++)
          titulos.add( new String(rsmd.getColumnLabel(i)));
        renglones = new Vector();
        while(rs.next())
        {
          renglon = new Vector();
          for(i=1;i<=n;i++)
            renglon.add( new String(rs.getString(i)) );
          renglones.add( renglon );  
        }
      }
      catch( Exception e)
      {
          e.printStackTrace();
      } 
    }
    
    public String getColumnName(int i)
    {
        return (String) titulos.elementAt(i);
    }
    
    
    public int getColumnCount()
    {
        return titulos.size();
    }
    
    public int getRowCount()
    {
        return renglones.size();
    }
    
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Vector renglon = (Vector) renglones.elementAt(rowIndex);
        return renglon.elementAt(columnIndex);
    }
    
    public boolean isCellEditable(int row, int col)
    { 
      return true;
    }
    
    public void setValueAt(Object value, int row, int col) 
    {             
      fireTableCellUpdated(row, col);
    }

    
}

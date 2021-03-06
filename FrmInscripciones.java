/*
 * FrmInscripciones.java
 *
 * Created on 7 de diciembre de 2004, 02:37 PM
 */

/**
 *
 * @author  Administrador
 */
public class FrmInscripciones extends javax.swing.JFrame
{
    ClsGestorEscBaile objGestor;
    
    /** Creates new form FrmInscripciones */
    public FrmInscripciones ( ClsGestorEscBaile unGestor )
    {
        objGestor = unGestor;
        initComponents ();
    }
        private MiModelo obtenModelo()
    {
        java.sql.ResultSet r;
        r = objGestor.obtenAlumnos();
        //System.out.println(con.impRS(r));
        MiModelo mm = new MiModelo( r );
        try
        {
            r.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return mm;
    }
    
    private ModeloLista obtenModeloLista(String strTV,String strCampo)
    {
        java.sql.ResultSet r = objGestor.obtenCampoParaLista (strTV, strCampo);
        //System.out.println(con.impRS(r));
        ModeloLista ml = new ModeloLista( r );
        try
        {
            r.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    
        return ml;        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBoxGrupos = new javax.swing.JComboBox();
        jBtnInscribir = new javax.swing.JButton();
        jBtnSalir = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTable1.setModel(obtenModelo());
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 200));

        jComboBoxGrupos.setModel(obtenModeloLista("tblGrupos","numGpo"));
        getContentPane().add(jComboBoxGrupos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 150, -1));

        jBtnInscribir.setText("Inscribir");
        jBtnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInscribirActionPerformed(evt);
            }
        });

        getContentPane().add(jBtnInscribir, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        jBtnSalir.setText("Salir");
        jBtnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSalirMouseClicked(evt);
            }
        });

        getContentPane().add(jBtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, -1, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-437)/2, (screenSize.height-346)/2, 437, 346);
    }//GEN-END:initComponents

    private void jBtnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalirMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtnSalirMouseClicked

    private void jBtnInscribirActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnInscribirActionPerformed
    {//GEN-HEADEREND:event_jBtnInscribirActionPerformed
        // TODO add your handling code here:
        new DlgMensajes(new javax.swing.JFrame(),"Inscribir","Implantar la inscripción del alumno al grupo").show();
    }//GEN-LAST:event_jBtnInscribirActionPerformed
    
    /** Exit the Application */
    private void exitForm (java.awt.event.WindowEvent evt)//GEN-FIRST:event_exitForm
    {
        dispose();
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main (String args[])
    {
        new FrmInscripciones (null).show ();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnInscribir;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JComboBox jComboBoxGrupos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}

/*
 * DlgMensajes.java
 *
 * Created on 5 de diciembre de 2004, 07:25 PM
 */

/**
 *
 * @author  Administrador
 */
public class DlgMensajes extends javax.swing.JDialog {
    
    /** Creates new form DlgMensajes */
    public DlgMensajes(java.awt.Frame parent, String strTitulo, String strMsg)
    {
        super(parent, true);
        this.setTitle(strTitulo);
        initComponents();
        jLabel1.setText(strMsg);
      
    }
     public DlgMensajes(java.awt.Dialog parent, String strTitulo, String strMsg)
    {
        super(parent, true);
        this.setTitle(strTitulo);
        initComponents();
        jLabel1.setText(strMsg);
      
    }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusableWindowState(false);
        setFont(new java.awt.Font("Arial Black", 0, 18));
        setModal(true);
        setResizable(false);
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 70, 30));

        jLabel1.setText("Aqu\u00ed va el mensaje a desplegar.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 390, 20));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-433)/2, (screenSize.height-137)/2, 433, 137);
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //System.out.println("Hola");
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        new DlgMensajes(new javax.swing.JFrame(),"Este es el T�tulo","Y este el mensaje").show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    
}

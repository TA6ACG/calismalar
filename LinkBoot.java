/*
*Cagri Seyfeli 12.06.2017 => Kayseri
*Bu program verilen URL adresini Aç kapa yaparak linkleri otomatik test etmek için yapılmıştır 
 */
package deneme.tahtasi;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.misc.IOUtils;

public class NewJFrame extends javax.swing.JFrame {

    boolean durum = true;

    public NewJFrame() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
 
    private void initComponents() {

        denemeTahtasi1 = new deneme.tahtasi.DenemeTahtasi();
        jPanel1 = new javax.swing.JPanel();
        jtAdres = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBasla = new javax.swing.JButton();
        btnBitir = new javax.swing.JButton();
        jlSayac = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
 
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Link Boot");

        jtAdres.setText("http://www.cagriseyfeli.com/");

        jLabel1.setText("ADRES: ");

        btnBasla.setText("BAŞLA");
        btnBasla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaslaActionPerformed(evt);
            }
        });

        btnBitir.setText("BİTİR");
        btnBitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitirActionPerformed(evt);
            }
        });

        jlSayac.setText("00");

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jtAdres)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnBasla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jlSayac, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBitir))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtAdres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBasla)
                    .addComponent(jlSayac)
                    .addComponent(btnBitir)))
        );

        pack();
    }

    private void btnBaslaActionPerformed(java.awt.event.ActionEvent evt) {
        durum = true;
        Thread a = new Thread(() -> {
            long sayac = 0;
            String URL = jtAdres.getText();
            while (durum) {

                try {
                    site(URL);
                } catch (IOException ex) {
                    jlSayac.setText("Hata");
                    JOptionPane.showMessageDialog(this, ex, "Hata Tespit Edildi", 2);
                    break;
                }
                sayac++;
                jlSayac.setText(sayac + "");
                System.out.println("deneme= " + sayac);
            }
        });
        a.start();
    }

    private void btnBitirActionPerformed(java.awt.event.ActionEvent evt) {durum = false;}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {JOptionPane.showMessageDialog(this, "Programın Amacı verilen linki sürekli açıp kapamak", "İnfo", 1);}
    public static void site(String aa) throws MalformedURLException, IOException {URL url = new URL(aa);InputStream response = url.openStream();response.close();}
    public static void main(String args[]) {
           try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
             java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    public static javax.swing.JButton btnBasla;
    public static javax.swing.JButton btnBitir;
    private deneme.tahtasi.DenemeTahtasi denemeTahtasi1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlSayac;
    private javax.swing.JTextField jtAdres;
  }

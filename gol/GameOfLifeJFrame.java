/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author loux
 */
public class GameOfLifeJFrame extends javax.swing.JFrame {

    private final class GameOfLifeJPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setPaint(Color.GRAY);
            int cellHeight = this.getHeight() / geometry.getHeight();
            int cellWidth = this.getWidth() / geometry.getWidth();
            for (int layer = 0; layer < geometry.getDepth(); layer++) {
                for (int row = 0; row < geometry.getHeight(); row++) {
                    int y = row * cellHeight;
                    for (int column = 0; column < geometry.getWidth(); column++) {
                        int x = column * cellWidth;
                        Cell cell = geometry.getCells()[layer][row][column];
                        if (cell.isAlive()) {
                            g2D.setPaint(Color.WHITE);
                        } else {
                            g2D.setPaint(Color.BLACK);
                        }
                        g2D.fillRect(x, y, cellWidth, cellHeight);
                    }
                }
            }
        }
    }

    private Geometry geometry;
    private int it_;
    private GameOfLifeSwingWorker gameOfLifeSwingWorker;

    /**
     * Creates new form GameOfLifeJFrame
     */
    public GameOfLifeJFrame() {
        geometry = new Geometry.Rectangle(200, 200);
        GameOfLife.randomInitialize(geometry);
        it_ = 0;
        gameOfLifeSwingWorker = new GameOfLifeSwingWorker(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("This is the game of life");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(new GameOfLifeJPanel(), BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyChar() == 'p') {
            gameOfLifeSwingWorker.cancel(true);
        } else if (evt.getKeyChar() == 's') {
            java.awt.EventQueue.invokeLater(() -> {
                if (gameOfLifeSwingWorker.isCancelled()) {
                    gameOfLifeSwingWorker = new GameOfLifeSwingWorker(this);
                }
                gameOfLifeSwingWorker.execute();
            });
        } else if (evt.getKeyChar() == 'i') {
            java.awt.EventQueue.invokeLater(() -> {
                gameOfLifeSwingWorker.cancel(true);
                geometry = new Geometry.Rectangle(200, 200);
                GameOfLife.randomInitialize(geometry);
                gameOfLifeSwingWorker = new GameOfLifeSwingWorker(this);
                jPanel1.repaint();
            });
        }
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            GameOfLifeJFrame gameOfLifeJFrame = new GameOfLifeJFrame();
            gameOfLifeJFrame.setVisible(true);
        });
    }

    private static class GameOfLifeSwingWorker extends SwingWorker<Integer, Geometry> {

        protected final GameOfLifeJFrame gameOfLifeJFrame;

        public GameOfLifeSwingWorker(GameOfLifeJFrame gameOfLifeJFrame) {
            this.gameOfLifeJFrame = gameOfLifeJFrame;
        }

        @Override
        protected void process(java.util.List<Geometry> chunks) {
            for (Geometry geometry : chunks) {
//                this.gameOfLifeJFrame.jPanel1.repaint();
            }
            this.gameOfLifeJFrame.jPanel1.repaint();
        }

        @Override
        protected void done() {
            try {
                if (!isCancelled()) {
                    System.out.println(get());
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(GameOfLifeJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        protected Integer doInBackground() throws Exception {
            boolean isEvolving = true;
            while (!isCancelled() && isEvolving) {
                isEvolving = GameOfLife.nextGeneration(this.gameOfLifeJFrame.geometry, new Strategy.Default());
                publish(this.gameOfLifeJFrame.geometry);
                TimeUnit.MILLISECONDS.sleep(100);
                this.gameOfLifeJFrame.it_++;
            }
            return this.gameOfLifeJFrame.it_;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
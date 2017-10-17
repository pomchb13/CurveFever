package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marco on 10.10.2017.
 */
public class StartUI extends JFrame {

 /*   public StartUI(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
*/

    private JPanel pnButtons;
    private JLabel lbTitle;
    private JLabel lbRightPic;
    private JLabel lbLeftPic;
    private JButton btStart;
    private JButton btSettings;
    private JButton btCredits;
    private JButton btExit;
    private File file;

    public StartUI() throws HeadlessException {
        super("CurveFever");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(625, 800);
        this.setLocationRelativeTo(this);
        this.setTitle("CurveFever");
        initComponents();
    }

    private void initComponents() {
        //create a Container and set the layout
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout(5,5));

        //add the title to the container
        lbTitle = new JLabel("CurveFever", JLabel.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 80));
        lbTitle.setForeground(Color.BLUE);
        lbTitle.setOpaque(true);
        cont.add(lbTitle, BorderLayout.NORTH);



        lbLeftPic = new JLabel();
        lbRightPic = new JLabel();
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "res" + File.separator + "startPic4.png");
        lbLeftPic.setIcon(icon);
        lbRightPic.setIcon(icon);
        cont.add(lbLeftPic, BorderLayout.WEST);
        cont.add(lbRightPic, BorderLayout.EAST);

        pnButtons = new JPanel();
        pnButtons.setLayout(new GridLayout(4,1,15,15));

        btStart = new JButton("Starten");
        btSettings = new JButton("Einstellungen");
        btCredits = new JButton("Über");
        btExit = new JButton("Schließen");

        pnButtons.add(btStart);
        pnButtons.add(btSettings);
        pnButtons.add(btCredits);
        pnButtons.add(btExit);

        cont.add(pnButtons, BorderLayout.CENTER);

    }


    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartUI().setVisible(true);
            }
        });
}
}


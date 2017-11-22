package gui;

import border.RoundedBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marco on 10.10.2017.
 */
public class StartGUI extends JFrame {

 /*   public StartGUI(int width, int height, String title, Game game)
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
    private MainStarter ms;

    public StartGUI(MainStarter ms) throws HeadlessException {
        super("CurveFever");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 900);
        this.setLocationRelativeTo(this);
        this.ms = ms;
        this.setTitle("CurveFever");
        initComponents();
    }

    private void initComponents() {
        //create a Container and set the layout
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout(5, 5));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle displaySize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();


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
        pnButtons.setLayout(new GridLayout(4, 1, 15, 15));

        btStart = new JButton("Starten");
        btSettings = new JButton("Einstellungen");
        btCredits = new JButton("Über");
        btExit = new JButton("Schließen");


        pnButtons.add(btStart);
        pnButtons.add(btSettings);
        pnButtons.add(btCredits);
        pnButtons.add(btExit);

        cont.add(pnButtons, BorderLayout.CENTER);

        btExit.addActionListener((e) -> onExit(e));
        btStart.addActionListener((e) -> onStartGame(e));
    }


    public void onExit(ActionEvent event) {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Wirklich beenden?",
                "Beenden",
                JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            this.dispose();
        }
    }

    public void onStartGame(ActionEvent event) {
        ms.onChange(this, GUIS.GAMEGUI);
      /*  JFrame frame = new JFrame("CurveFever");
        //frame.setLayout(new BorderLayout());

        frame.setSize(625, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        frame.add(new Game());
        this.dispose();
        */
    }
}


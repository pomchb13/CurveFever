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
public class StartGUI extends GUIObject {


    private JPanel pnButtons;
    private JLabel lbTitle;
    private JLabel lbRightPic;
    private JLabel lbLeftPic;
    private JButton btStart;
    private JButton btSettings;
    private JButton btCredits;
    private JButton btExit;
    private File file;

    public StartGUI(int width, int height, String title, Game game) {
        super(width, height, title, game);

        Init();
    }


    private void Init() {
        //create a Container and set the layout
        Container cont = _Frame.getContentPane();
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

        // Muss am Ende stehen
        _Frame.setVisible(true);
    }


    public void onExit(ActionEvent event) {
        int option = JOptionPane.showConfirmDialog(
                _Frame,
                "Wirklich beenden?",
                "Beenden",
                JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            _Frame.dispose();
        }
    }

    public void onStartGame(ActionEvent event) {
        _Game.onChange(_Frame, GUIS.GAMEGUI);
    }
}


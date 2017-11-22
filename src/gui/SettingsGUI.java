package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ewald on 17.10.2017.
 */
public class SettingsGUI extends JDialog {



    public SettingsGUI(Frame parent, boolean modal) throws HeadlessException {
        super(parent, modal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(parent);
        this.setTitle("Einstellungen");
    }


}

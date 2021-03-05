package client.View;

import javax.swing.*;
import java.awt.*;

public class ComposePanel extends JPanel {
    ComposePanel(){
        setPreferredSize(new Dimension(1920/6,1080/6));
        setBackground(Color.cyan);
        JLabel laeebael = new JLabel("Bra fin");
        add(laeebael);
    }
}

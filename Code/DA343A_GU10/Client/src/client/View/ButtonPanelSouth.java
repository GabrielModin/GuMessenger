package client.View;

import javax.swing.*;
import java.awt.*;

public class ButtonPanelSouth extends JPanel {
    ButtonPanelSouth(){
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        layout.setHgap(10);
        layout.setVgap(10);

        JPanel userButtons = new JPanel();
        JPanel readButtons = new JPanel();
        JPanel writeButtons = new JPanel();

        userButtons.setBackground(Color.red);
        readButtons.setBackground(Color.green);
        writeButtons.setBackground(Color.blue);

        userButtons.add(new JButton("add to contacts"));
        writeButtons.add(new JButton("attach image"));
        writeButtons.add(new JButton("send"));

        readButtons.setPreferredSize(new Dimension(1920/6,30));

        add(userButtons, BorderLayout.WEST);
        add(readButtons, BorderLayout.CENTER);
        add(writeButtons, BorderLayout.EAST);

    }
}

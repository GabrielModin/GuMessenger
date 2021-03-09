package client.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReadPanel extends JPanel {

    BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

    GUI gui;
    JScrollPane scrollPane;
    JPanel readPanelItem;

    ReadPanel(GUI gui){
        setPreferredSize(new Dimension(500,1080/6));
        setBackground(Color.BLACK);
        setLayout(layout);

        readPanelItem = new JPanel();
        BoxLayout userItemPanelLayout = new BoxLayout(readPanelItem,BoxLayout.PAGE_AXIS);

        readPanelItem.setLayout(userItemPanelLayout);
        scrollPane = new JScrollPane(readPanelItem);
        add(scrollPane);

        this.gui = gui;
    }

    public void addMessage(String name, String messageText, ImageIcon messageIcon, String timeStamp) {
        readPanelItem.add(new ReadPanelItem(name, messageText, messageIcon, timeStamp));
        revalidate();
        repaint();
    }

    public void reset() {

        for (Component component: readPanelItem.getComponents()) {
            readPanelItem.remove(component);
        }
    }
}

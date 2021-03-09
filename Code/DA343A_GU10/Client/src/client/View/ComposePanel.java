package client.View;

import javax.swing.*;
import java.awt.*;

public class ComposePanel extends JPanel {

    JTextArea textArea;

    ImageIcon messageIcon = null;

    ComposePanel(){
        setPreferredSize(new Dimension(500,Integer.MAX_VALUE));

        textArea = new JTextArea(1,1);
        textArea.setPreferredSize(new Dimension(400,Integer.MAX_VALUE));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        add(textArea);
    }

    public String getTextFromTextArea() {
        return textArea.getText();
    }

    public void attatchImage(ImageIcon icon) {
        messageIcon = icon;
    }

    public ImageIcon getImage() {
        ImageIcon retImg = messageIcon;
        messageIcon = null;
        return retImg;
    }
}

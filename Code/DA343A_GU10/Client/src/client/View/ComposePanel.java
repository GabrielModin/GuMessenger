package client.View;

import javax.swing.*;
import java.awt.*;

public class ComposePanel extends JPanel {

    JTextArea textArea;

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
}

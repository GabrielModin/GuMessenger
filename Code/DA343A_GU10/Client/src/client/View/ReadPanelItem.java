package client.View;

import javax.swing.*;
import java.awt.*;

public class ReadPanelItem extends JPanel {

    String name;
    String msg;
    String timestamp;
    ImageIcon img;

    JLabel nameTimeLabel;
    JLabel imgLabel;
    JTextArea messageLabel;

    public ReadPanelItem(String name, String msg, ImageIcon img, String timestamp){

        this.name = name;
        this.msg = msg;
        this.img = img;
        this.timestamp = timestamp;

        int width = 450;

        JPanel senderPanel = new JPanel();
        JPanel imagePanel = new JPanel();
        JPanel textPanel = new JPanel();

        imagePanel.setPreferredSize(new Dimension(200,200));
        imagePanel.setMaximumSize(new Dimension(200,200));
        imagePanel.setSize(new Dimension(200,200));

        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        String nameTime = String.format("%s , %s",name, timestamp);

        nameTimeLabel = new JLabel(nameTime);
        senderPanel.add(nameTimeLabel);
        if (img != null) {
            Image image = img.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            imgLabel = new JLabel(icon);
        }
        messageLabel = new JTextArea(5,30);
        messageLabel.setWrapStyleWord(true);
        messageLabel.setLineWrap(true);
        messageLabel.setText(msg);
        messageLabel.setOpaque(false);
        messageLabel.setEditable(false);
        messageLabel.setFocusable(false);
        imgLabel.setPreferredSize(new Dimension(200,200));
        imgLabel.setMaximumSize(new Dimension(200,200));


        if (img!=null && msg==null){
            setPreferredSize(new Dimension(width,200));
            setMaximumSize(new Dimension(width,200));
            imagePanel.add(imgLabel);
        }

        if (msg!=null && img==null){
            setPreferredSize(new Dimension(width,200));
            setMaximumSize(new Dimension(width,200));
            textPanel.add(messageLabel);
        }

        if (msg != null && img != null){
            setPreferredSize(new Dimension(width,400));
            setMaximumSize(new Dimension(width,400));

            imgLabel = new JLabel(img);
            imagePanel.add(imgLabel);
            textPanel.add(messageLabel);
        }

        add(senderPanel);
        add(imagePanel);
        add(textPanel);

        setBorder(BorderFactory.createBevelBorder(1));

    }

}

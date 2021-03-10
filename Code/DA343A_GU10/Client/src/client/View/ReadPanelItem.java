package client.View;

import javax.swing.*;
import java.awt.*;

public class ReadPanelItem extends JPanel {

    private String name;
    private String msg;
    private String timestamp;
    private ImageIcon img;
    private JLabel nameTimeLabel;
    private JLabel imgLabel;
    private JTextArea messageLabel;

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

        imgLabel = new JLabel();

        messageLabel = new JTextArea(5,30);
        messageLabel.setWrapStyleWord(true);
        messageLabel.setLineWrap(true);
        messageLabel.setText(msg);
        messageLabel.setOpaque(false);
        messageLabel.setEditable(false);
        messageLabel.setFocusable(false);

        imgLabel.setPreferredSize(new Dimension(200,200));
        imgLabel.setMaximumSize(new Dimension(200,200));
        imgLabel.setIcon(scaleImage(img));

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

    public ImageIcon scaleImage(ImageIcon imageIcon){
        Image image = imageIcon.getImage();
        Image imageScaled = image.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        return new ImageIcon(imageScaled);

    }

}

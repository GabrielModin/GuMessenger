package client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserList extends JPanel implements ActionListener {

    BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
    int numUsers = 0;
    GUI gui;
    JScrollPane scrollPane;
    JPanel userItemPanel;

    UserList(GUI gui){
        setPreferredSize(new Dimension(500,1080/6));
        setBackground(Color.BLACK);
        setLayout(layout);

        userItemPanel = new JPanel();
        BoxLayout userItemPanelLayout = new BoxLayout(userItemPanel,BoxLayout.PAGE_AXIS);

        userItemPanel.setLayout(userItemPanelLayout);
        scrollPane = new JScrollPane(userItemPanel);
        add(scrollPane);

        this.gui = gui;
    }

    public void addUserToOnlineList(String user, ImageIcon icon, boolean online) {
        UserListItem userListItem = new UserListItem(user,icon,online, this);
        numUsers++;
        userListItem.setPreferredSize(new Dimension(400,100));
        userListItem.setMaximumSize(new Dimension(400,100));
        userItemPanel.add(userListItem);
    }

    public void reset() {
        numUsers = 0;
        for (Component component: getComponents()) {
            remove(component);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String user = (actionEvent.getActionCommand());
        gui.getNewStringArrayForChat(user);
    }
}

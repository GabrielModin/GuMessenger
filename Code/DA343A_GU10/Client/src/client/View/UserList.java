package client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class UserList extends JPanel implements ActionListener {

    private int numUsers = 0;
    private GUI gui;
    private ArrayList<UserListItem> userListItems = new ArrayList<>();
    private BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
    private JScrollPane scrollPane;
    private JPanel userItemPanel;

    public UserList(GUI gui){
        this.gui = gui;

        setPreferredSize(new Dimension(500,1080/6));
        setBackground(Color.BLACK);
        setLayout(layout);

        userItemPanel = new JPanel();
        BoxLayout userItemPanelLayout = new BoxLayout(userItemPanel,BoxLayout.PAGE_AXIS);

        userItemPanel.setLayout(userItemPanelLayout);
        scrollPane = new JScrollPane(userItemPanel);
        add(scrollPane);
    }

    public void addUserToOnlineList(String user, ImageIcon icon, boolean online) {
        UserListItem userListItem = new UserListItem(user,icon,online, this,numUsers);
        numUsers++;
        userListItem.setPreferredSize(new Dimension(400,100));
        userListItem.setMaximumSize(new Dimension(400,100));
        userItemPanel.add(userListItem);
        userListItems.add(userListItem);
        revalidate();
        repaint();
    }

    public void reset() {
        numUsers = 0;
        for (Component component : userItemPanel.getComponents()) {
            if (component instanceof UserListItem){
                UserListItem uli = (UserListItem) component;
                uli.setIsSelected(false);
                userListItems.remove(uli);
            }
            userItemPanel.remove(component);
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String user = (actionEvent.getActionCommand());
        gui.getMessage(user);
    }

    public int[] getSelected() {
        ArrayList<UserListItem> uli = new ArrayList<>();
        for (UserListItem item: userListItems) {
            if (item.isChecked()){
                uli.add(item);
            }
        }

        int[] selectedUsers = new int[uli.size()];

        for (int i = 0; i < selectedUsers.length; i++) {
            selectedUsers[i] = uli.get(i).getIndex();
        }
        return selectedUsers;
    }
}
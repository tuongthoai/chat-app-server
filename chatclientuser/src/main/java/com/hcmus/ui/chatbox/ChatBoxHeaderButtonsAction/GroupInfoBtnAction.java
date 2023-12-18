package com.hcmus.ui.chatbox.ChatBoxHeaderButtonsAction;

import com.hcmus.models.GroupChatMember;
import com.hcmus.ui.chatbox.ChatBox;
import com.hcmus.ui.chatbox.GroupInfoDialog;
import okhttp3.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GroupInfoBtnAction implements ActionListener {
    private ChatBox parent;

    public GroupInfoBtnAction() {}

    public GroupInfoBtnAction(ChatBox parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // CALL API FOR CHAT INFO

        List<String> membersName = new ArrayList<>();
        List<String> roles = new ArrayList<>();



        GroupInfoDialog memListDialog = new GroupInfoDialog(parent, membersName, roles);
        memListDialog.setVisible(true);
    }
}

package com.hcmus.chatserver.service;

import com.hcmus.chatserver.entities.groupchat.GroupChat;
import com.hcmus.chatserver.entities.user.User;
import com.hcmus.chatserver.repository.GroupChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupChatService {
    private final GroupChatRepository repository;

    public GroupChatService(GroupChatRepository repository) {
        this.repository = repository;
    }

    public List<GroupChat> findGroupChatOfUser(int userId) {
        return repository.findByMemberId(userId);
    }
    public List<GroupChat> findAllGroupChat() throws Exception {
        return repository.findAll();
    }

    public List<User> findAllMembers(int groupId) throws Exception {
        return repository.findAllMembers(groupId);
    }
    public List<User> findAllAdmins(int groupId) throws Exception {
        return repository.findAllAdmins(groupId);
    }
}
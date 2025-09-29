package com.example.SparkDesk.service;

import com.example.SparkDesk.model.Message;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.MessageRepository;
import com.example.SparkDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository = null;
    private final UserRepository userRepository = null;

    @Override
    public void sendMessageToUser(Long senderId, Long receiverId, String content) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message msg = new Message();
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(content);
        msg.setRoleTarget(null);  // individual message

        messageRepository.save(msg);
    }

    @Override
    public void sendBroadcast(Long senderId, String roleTarget, String content) {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));

        Message msg = new Message();
        msg.setSender(sender);
        msg.setReceiver(null); // broadcast
        msg.setRoleTarget(roleTarget);
        msg.setContent(content);

        messageRepository.save(msg);
    }
}

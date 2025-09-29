package com.example.SparkDesk.service;

public interface MessageService {
    void sendMessageToUser(Long senderId, Long receiverId, String content);
    void sendBroadcast(Long senderId, String roleTarget, String content);
}

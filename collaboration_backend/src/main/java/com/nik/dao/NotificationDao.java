package com.nik.dao;

import java.util.List;

import com.nik.model.Notification;

public interface NotificationDao {
List<Notification> getAllNotification(String email);//login id
void updateViewedStatus(int notificationId);
Notification getNotification(int id);
}

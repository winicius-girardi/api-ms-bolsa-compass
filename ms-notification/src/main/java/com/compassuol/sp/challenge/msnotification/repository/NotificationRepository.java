package com.compassuol.sp.challenge.msnotification.repository;

import com.compassuol.sp.challenge.msnotification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
}

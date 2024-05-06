package com.compassuol.sp.challenge.msnotification.common;

import com.compassuol.sp.challenge.msnotification.dto.NotificationRequestDto;
import com.compassuol.sp.challenge.msnotification.entity.Notification;

import java.time.ZonedDateTime;

public class NotificationConstraints {

    public static final NotificationRequestDto NOTIFICATION_REQUEST_DTO_VALID = new NotificationRequestDto("teste@teste.com", "LOGIN", "2021-08-01T00:00:00Z");

    public static final Notification NOTIFICATION_VALID = new Notification("teste@teste.com", Notification.Event.valueOf("LOGIN"), ZonedDateTime.parse("2021-08-01T00:00:00Z").toLocalDateTime());


    public static final Notification NOTIFICATION_INVALID = new Notification("", Notification.Event.valueOf(""),null);
}

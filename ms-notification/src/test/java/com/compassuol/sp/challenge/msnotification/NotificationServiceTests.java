package com.compassuol.sp.challenge.msnotification;


import com.compassuol.sp.challenge.msnotification.repository.NotificationRepository;
import com.compassuol.sp.challenge.msnotification.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.compassuol.sp.challenge.msnotification.common.NotificationConstraints.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTests {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;


//    @Test
//    public void saveValidNotification() {
//        when(notificationRepository.save(NOTIFICATION_VALID)).thenReturn(NOTIFICATION_VALID);
//
//        notificationService.saveNotification(NOTIFICATION_REQUEST_DTO_VALID);
//        assertThat(notificationRepository.save(NOTIFICATION_VALID)).isEqualTo(NOTIFICATION_VALID);
//    }
//}
}
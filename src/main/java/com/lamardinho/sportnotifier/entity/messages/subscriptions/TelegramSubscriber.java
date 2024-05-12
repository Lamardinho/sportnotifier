package com.lamardinho.sportnotifier.entity.messages.subscriptions;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "telegram_subscriber")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class TelegramSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = null;

    @Column(name = "chat_id", nullable = false, unique = true)
    private Long chatId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "active")
    private boolean active = false;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_creation", nullable = false, updatable = false, columnDefinition = "timestamp")
    @ColumnDefault("current_timestamp")
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column(name = "date_last_subscription", nullable = false, columnDefinition = "timestamp")
    @ColumnDefault("current_timestamp")
    private LocalDateTime dateLastSubscription = LocalDateTime.now();

    @Column(name = "date_last_unsubscription", nullable = false, columnDefinition = "timestamp")
    @ColumnDefault("current_timestamp")
    private LocalDateTime dateLastUnSubscription = LocalDateTime.now();
}

package com.example.reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 会員予約なら　userId をセット
    private Long userId;

    // 非会員予約なら userName をセット
    private String userName;

    @ManyToOne
    private Facility facility;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
}

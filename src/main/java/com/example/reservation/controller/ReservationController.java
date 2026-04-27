package com.example.reservation.controller;

import com.example.reservation.entity.Reservation;
import com.example.reservation.service.ReservationService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // -----------------------
    // 予約作成
    // -----------------------
    @PostMapping
    public Reservation createReservation(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam("facilityId") Long facilityId,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);

        // 会員 or 非会員チェック
        if (userId == null && (userName == null || userName.isBlank())) {
            throw new RuntimeException("userId か userName のどちらかが必要です");
        }
        return reservationService.createReservation(
            userId, userName, facilityId, startTime, endTime
        );
    }

    // ---------------------
    // 予約取得
    // ---------------------
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    // ---------------------
    // 施設ごとの予約一覧
    // ---------------------
    @GetMapping("/facility/{facilityId}")
    public List<Reservation> getReservationsByFacility(
            @PathVariable("facilityId") Long facilityId) {
        return reservationService.getReservationsByFacility(facilityId);
    }

    // ---------------------
    // ユーザーごとの予約一覧
    // ---------------------
    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsByUser(@PathVariable Long userId) {
        return reservationService.getReservationsByUser(userId);
    }

    // ----------------------
    // 全予約一覧
    // ----------------------
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // ----------------------
    // 予約更新
    // ----------------------
    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable Long id,
            @RequestParam("facilityId") Long facilityId,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
                
        LocalDateTime newStart = LocalDateTime.parse(start);
        LocalDateTime newEnd = LocalDateTime.parse(end);

        return reservationService.updateReservation(id, facilityId, newStart, newEnd);
    }

    // -----------------------
    // 予約削除
    // -----------------------
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}

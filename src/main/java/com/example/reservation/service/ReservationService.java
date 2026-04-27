package com.example.reservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.entity.Facility;
import com.example.reservation.entity.Reservation;
import com.example.reservation.repository.FacilityRepository;
import com.example.reservation.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FacilityRepository facilityRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            FacilityRepository facilityRepository) {
        this.reservationRepository = reservationRepository;
        this.facilityRepository = facilityRepository;
    }

    // ----------------------
    // 予約作成（会員／非会員対応）
    // ----------------------
    public Reservation createReservation(
            Long userId, 
            String userName,
            Long facilityId,
            LocalDateTime start, 
            LocalDateTime end) {

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        
        // 重複チェック
        boolean exists = reservationRepository.existsByFacilityIdAndTimeRange(
                facilityId, start, end
        );
        if (exists) {
            throw new RuntimeException("Facility is already reserved in this time range");
        }

        Reservation reservation = new Reservation();
        reservation.setFacility(facility);
        reservation.setStartTime(start);
        reservation.setEndTime(end);

        // 会員予約
        if (userId != null) {
            reservation.setUserId(userId);
        }

        // 非会員予約
        if (userName != null && !userName.isBlank()) {
            reservation.setUserName(userName);
        }

        return reservationRepository.save(reservation);
    }

    // ----------------
    // 予約取得
    // ----------------
    public Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // ----------------
    // 施設ごとの予約一覧
    // ----------------
    public List<Reservation> getReservationsByFacility(Long facilityId) {
        return reservationRepository.findByFacilityId(facilityId);
    }

    // --------------------
    // ユーザーごとの予約一覧（会員のみ）
    // --------------------
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    // ---------------------
    // 全予約一覧
    // ---------------------
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // ---------------------
    // 予約更新（会員／非会員両対応）
    // ---------------------
    public Reservation updateReservation(
            Long id, 
            Long facilityId,
            LocalDateTime start,
            LocalDateTime end) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));                           
        
        // 重複チェック（自分自身は除外）
        boolean exists = reservationRepository.existsByFacilityIdAndTimeRangeExceptId(
                facilityId, start, end, id
        );
        if (exists) {
            throw new RuntimeException("Facility is already reserved in this time range");
        }

        // ここで userId / userName は変更しない
        reservation.setFacility(facility);
        reservation.setStartTime(start);
        reservation.setEndTime(end);

        return reservationRepository.save(reservation);
    }

    // ---------------------
    // 予約削除
    // ---------------------
    public void deleteReservation(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new RuntimeException("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }
}


package com.example.reservation.repository;

import com.example.reservation.entity.Reservation;

//import org.hibernate.validator.internal.util.actions.LoadClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // -------------------------------
    // 重複チェック（新規予約用）
    // -------------------------------
     @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reservation r
        WHERE r.facility.id = :facilityId
        AND r.startTime < :end
        AND r.endTime > :start
    """)
    boolean existsByFacilityIdAndTimeRange(Long facilityId,
                                              LocalDateTime start,
                                              LocalDateTime end);
    
    // -------------------------------
    // 重複チェック（更新時：自分自身を除外）
    // -------------------------------
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reservation r
        WHERE r.facility.id = :facilityId
        AND r.startTime < :end
        AND r.endTime > :start
        AND r.id <> :id
    """)
    boolean existsByFacilityIdAndTimeRangeExceptId(
        Long facilityId,
        LocalDateTime start,
        LocalDateTime end,
        Long id
    );
    
    // -------------------------------
    // 施設ごとの予約一覧
    // -------------------------------
    List<Reservation> findByFacilityId(Long facilityId);

    // -------------------------------
    // ユーザーごとの予約一覧
    // -------------------------------
    List<Reservation> findByUserId(Long userId);
    
}

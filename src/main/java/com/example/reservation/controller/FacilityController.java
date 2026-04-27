package com.example.reservation.controller;

import com.example.reservation.entity.Facility;
import com.example.reservation.repository.FacilityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityRepository facilityRepository;

    public FacilityController(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @GetMapping
    public List<Facility> getFacilities() {
        return facilityRepository.findAll();
    }


    @PostMapping
    public Facility creatFacility(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer capacity) {

        Facility facility = new Facility();
        facility.setName(name);
        facility.setDescription(description);
        facility.setCapacity(capacity);

        return facilityRepository.save(facility);
    }
}

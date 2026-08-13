package com.javalife365.javalife365api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="membership_plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String durationDays;

    private String price;

    private boolean isActive;

    @Column(updatable = false)
    private LocalDateTime createdAt;

}

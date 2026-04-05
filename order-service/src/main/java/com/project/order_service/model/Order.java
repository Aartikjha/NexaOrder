package com.project.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "orders")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String userEmail;
        private String productName;
        private int quantity;
        private double price;
        private String status;
        private LocalDateTime createdAt;

        @PrePersist
        public void prePersist() {
            this.createdAt = LocalDateTime.now();
            this.status = "PENDING";
        }

    }


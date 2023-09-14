package com.neshan.navigate_neshan.Model;

import com.neshan.navigate_neshan.Enum.ReportType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Report{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private ReportType type;//that contains type of report
        private String description;
        private Date createdDate;
        private Date expirationDate;//in this time report is deleted

    }


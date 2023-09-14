package com.neshan.navigate_neshan.Model;

import com.neshan.navigate_neshan.Enum.ReportType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.logging.Level;

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


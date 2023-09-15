package com.neshan.navigate_neshan.Model;

import com.neshan.navigate_neshan.Enum.ReportType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    ReportType type;//that contains type of report
    String description;
    LocalDateTime createdDate;
    LocalDateTime expirationDate;//in this time report is deleted
    int likes;
    @ManyToOne
    UserInfo user;
    @ManyToOne
    Rout rout;
}


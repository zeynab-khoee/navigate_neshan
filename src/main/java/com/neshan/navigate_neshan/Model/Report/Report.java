package com.neshan.navigate_neshan.Model.Report;

import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Rout;
import com.neshan.navigate_neshan.Model.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    LocalDateTime createdDate;
    LocalDateTime expirationDate;//in this time report is deleted
    boolean requiredConfirm = true; //default is true
    boolean isAccepted;
    int likes;
    @ManyToOne
    UserInfo user;
    @ManyToMany(mappedBy = "reportListLiked")
    List<UserInfo> userInfoLiked;
    @ManyToOne
    Rout rout;
    ReportType reportType;
}


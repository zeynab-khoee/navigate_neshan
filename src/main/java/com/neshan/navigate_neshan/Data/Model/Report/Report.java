package com.neshan.navigate_neshan.Data.Model.Report;

import com.neshan.navigate_neshan.Data.Enum.ReportType;
import com.neshan.navigate_neshan.Data.Model.Rout;
import com.neshan.navigate_neshan.Data.Model.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@ToString
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
    LocalDateTime expirationDate;//in this time report dost show
    boolean requiredConfirm = true; //default is true
    boolean isAccepted;
    int likes;
    @ManyToOne
    UserInfo user;
    @ManyToMany(mappedBy = "reportListLiked", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<UserInfo> userInfoLiked;
    @ManyToOne
    @JoinColumn(name = "rout_id")
    Rout rout;
    ReportType reportType;
}


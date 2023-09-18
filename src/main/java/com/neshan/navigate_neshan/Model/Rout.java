package com.neshan.navigate_neshan.Model;

import com.neshan.navigate_neshan.Model.Report.Report;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Rout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String wktString;
    @OneToMany(mappedBy = "rout")
    List<Report> reportList;
}

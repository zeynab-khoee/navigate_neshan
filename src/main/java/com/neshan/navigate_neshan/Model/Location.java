package com.neshan.navigate_neshan.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Point;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String address;
    Point coordinates;
    @OneToMany(mappedBy = "startLocation")
    List<Rout> routStartLocation;
    @OneToMany(mappedBy = "endLocation")
    List<Rout> routEndLocation;

}

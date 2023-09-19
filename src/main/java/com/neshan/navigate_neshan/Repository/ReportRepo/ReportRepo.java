package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.Report;
import io.lettuce.core.dynamic.annotation.Param;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    @Query(value ="SELECT * FROM Report r WHERE EXISTS ( SELECT 1 FROM Rout rt WHERE ST_Intersects(rt.wkt_string::geometry,:buffer) = true)", nativeQuery = true)
    List<Report> findReportsIntersectingWithBuffer(@Param("buffer") Geometry buffer);
    List<Report> getReportListByRoutId(Long rout_id);
}

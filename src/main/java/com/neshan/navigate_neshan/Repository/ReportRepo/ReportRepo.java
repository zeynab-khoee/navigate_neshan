package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.Report;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r WHERE ST_Intersects(r.rout.wktString, ?1)= true and r.isAccepted=true")
    List<Report> findReportsIntersectingWithBuffer(Geometry buffer);
    List<Report> getReportListByRoutId(Long rout_id);
}

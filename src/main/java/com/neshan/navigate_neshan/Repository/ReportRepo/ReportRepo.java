package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.Report;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    //@Query(value = "SELECT r.* FROM Report r JOIN Rout rt ON r.rout_id = rt.id WHERE ST_Intersects(ST_GeomFromText(rt.wkt_string, 4326), :buffer) = true", nativeQuery = true)
    @Query("SELECT r FROM Report r JOIN r.rout rt WHERE FUNCTION('ST_Intersects', FUNCTION('ST_GeomFromText', rt.wktString), :buffer) = true AND r.isAccepted = true")
    List<Report> findReportsIntersectingWithBuffer(Geometry buffer);
    List<Report> getReportListByRoutId(Long rout_id);
}

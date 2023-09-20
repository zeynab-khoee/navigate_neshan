package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.Report;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    @Query(value = "SELECT r.description " +
            "FROM Report r " +
            "WHERE EXISTS " +
            "(SELECT 1 " +
            "FROM Rout rt " +
            "WHERE ST_Intersects(ST_GeomFromText(rt.wkt_string, 4326), ?1))" +
            "AND r.expiration_date > CURRENT_TIMESTAMP " +
            "AND r.is_accepted=true"
            , nativeQuery = true)
    List<String> findReportsIntersectingWithBuffer(Geometry buffer);


    List<Report> getReportListByRoutId(Long rout_id);

    @Query(value = "SELECT EXTRACT(HOUR FROM report.created_date) || ' - ' || COUNT(*) AS hour_and_accident_count " +
            "FROM Report " +
            "WHERE report.report_type = 2 " +
            "GROUP BY EXTRACT(HOUR FROM report.created_date) " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 1", nativeQuery = true)
    String findHourAndAccidentCountWithMostAccidents();

}

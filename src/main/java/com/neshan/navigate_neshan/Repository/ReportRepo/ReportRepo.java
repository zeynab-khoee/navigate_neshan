package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    List<Report> getReportListByRoutId(Long rout_id);
}

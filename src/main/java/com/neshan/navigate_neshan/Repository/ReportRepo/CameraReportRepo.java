package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.CameraReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraReportRepo extends JpaRepository<CameraReport, Long> {

}

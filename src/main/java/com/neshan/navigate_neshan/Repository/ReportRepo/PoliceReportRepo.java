package com.neshan.navigate_neshan.Repository.ReportRepo;

import com.neshan.navigate_neshan.Model.Report.AccidentReport;
import com.neshan.navigate_neshan.Model.Report.PoliceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceReportRepo extends JpaRepository<PoliceReport, Long> {

}

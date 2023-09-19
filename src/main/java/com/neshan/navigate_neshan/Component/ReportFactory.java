package com.neshan.navigate_neshan.Component;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.Rout;
import com.neshan.navigate_neshan.Service.Report.AccidentReportService;
import com.neshan.navigate_neshan.Service.Report.CameraReportService;
import com.neshan.navigate_neshan.Service.Report.ReportHandler;
import com.neshan.navigate_neshan.Service.Report.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFactory {
    CameraReportService cameraReportService;
    AccidentReportService accidentReportService;
    ReportService reportService;


    public void createReport(Report report, ReportType type,Rout rout) {
        switch (type) {
            case CAMERA -> cameraReportService.createReport(report, type,rout);
            case ACCIDENT -> accidentReportService.createReport(report, type,rout);
        }

        // throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    }


    public Report like(Long reportId) {
        return reportService.like(reportId);

    }
    public Report disLike(Long reportId) {
        return reportService.disLike(reportId);
    }


    public List<ReportDto> getAllReportByRoutId(Long routId) {
        return reportService.getAllReportByRoutId(routId);
    }

    public void confirmByOperator(Long reportId) {
        reportService.confirmByOperator(reportId);
    }
}

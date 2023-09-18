package com.neshan.navigate_neshan.Component;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Service.Report.AccidentReportService;
import com.neshan.navigate_neshan.Service.Report.CameraReportService;
import com.neshan.navigate_neshan.Service.Report.ReportHandler;
import com.neshan.navigate_neshan.Service.Report.ReportService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFactory {
    private final List<ReportHandler> reportHandlers;

    @Autowired
    public ReportFactory(List<ReportHandler> reportHandlers) {
        this.reportHandlers = reportHandlers;
    }

    public void createReport(Report report, ReportType type) {
        switch (type) {
            case CAMERA -> reportHandlers.stream()
                    .filter(handler -> handler instanceof CameraReportService)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                    .createReport(report, type);


            case ACCIDENT -> reportHandlers.stream()
                    .filter(handler -> handler instanceof AccidentReportService)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                    .createReport(report, type);
        }

        throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    }


    public ReportDto like(Long reportId) {
        return reportHandlers.stream()
                .filter(handler -> handler instanceof ReportService)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                .like(reportId);

    }
    public ReportDto disLike(Long reportId) {
        return reportHandlers.stream()
                .filter(handler -> handler instanceof ReportService)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                .disLike(reportId);
    }


    public List<ReportDto> getAllReportByRoutId(Long routId) {
        return reportHandlers.stream()
                .filter(handler -> handler instanceof ReportService)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                .getAllReportByRoutId(routId);
    }

    public void confirmByOperator(Long reportId) {
        reportHandlers.stream()
                .filter(handler -> handler instanceof ReportService)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("CameraReportService not found"))
                .confirmByOperator(reportId);
    }
}

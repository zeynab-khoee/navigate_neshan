package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.Rout;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ReportHandler {
    default Report like(Long reportId) {
        return null;
    }

    default Report disLike(Long reportId) {
        return null;
    }

    default List<ReportDto> getAllReportByRoutId(Long routId) {
        return null;
    }

    default void confirmByOperator(Long reportId) {}

    default void createReport(Report report, ReportType type, Rout rout) {
    }
}
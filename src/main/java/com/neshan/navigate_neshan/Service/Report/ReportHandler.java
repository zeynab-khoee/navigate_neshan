package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Data.Dto.ReportDto;
import com.neshan.navigate_neshan.Data.Enum.ReportType;
import com.neshan.navigate_neshan.Data.Model.Report.Report;
import com.neshan.navigate_neshan.Data.Model.Rout;
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
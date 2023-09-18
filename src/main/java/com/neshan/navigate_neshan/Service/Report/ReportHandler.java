package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Model.Report.Report;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportHandler {


    default ReportDto like(Long reportId) {
        return null;
    }

    default ReportDto disLike(Long reportId) {
        return null;
    }

    default List<ReportDto> getAllReportByRoutId(Long routId) {
        return null;
    }

    default ReportDto findReportById(Long reportId) {
        return null;
    }

    default void confirmByOperator(Long reportId) {
    }

    default void createReport(Report report, Long routId) {
    }

}
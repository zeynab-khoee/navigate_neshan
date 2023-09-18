package com.neshan.navigate_neshan.Controller.Report;

import com.neshan.navigate_neshan.Component.ReportFactory;
import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Model.Report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Report")
public class ReportController {
    ReportFactory reportFactory;
    @PostMapping("/{routId}")
    public void createItem(@RequestBody Report report,@PathVariable Long routId) {
        reportFactory.createReport(report, routId);
    }

    @GetMapping("{routId}")
    public List<ReportDto> getAllReportByRoutId(@PathVariable Long routId){
        return reportFactory.getAllReportByRoutId(routId);
    }

    @PutMapping("{reportId}/like")
    public ReportDto like(@PathVariable Long reportId) {
        return reportFactory.like(reportId);
    }

    @PutMapping("{reportId}/disLike")
    public ReportDto disLike(@PathVariable Long reportId) {
        return reportFactory.disLike(reportId);
    }

    @PutMapping("{reportId}/confirm")
    public void confirmByOperator(@PathVariable Long reportId) {
        reportFactory.confirmByOperator(reportId);
    }

}

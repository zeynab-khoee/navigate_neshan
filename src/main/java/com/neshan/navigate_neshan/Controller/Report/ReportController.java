package com.neshan.navigate_neshan.Controller.Report;

import com.neshan.navigate_neshan.Component.ReportFactory;
import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Report")
public class ReportController {
    private final ReportFactory reportFactory;

    @PostMapping("/{type}")
    public void createReport(@RequestBody Report report, @PathVariable ReportType type) {
        reportFactory.createReport(report, type, report.getRout());
    }
    @GetMapping("/most")
    public String mostAccident() {
       return reportFactory.mostAccident();
    }

    @GetMapping("{routId}")
    public List<ReportDto> getAllReportByRoutId(@PathVariable Long routId) {
        return reportFactory.getAllReportByRoutId(routId);
    }
    @PutMapping("{reportId}/like")
    public ResponseEntity<String> like(@PathVariable Long reportId) {
        Report likedReport = reportFactory.like(reportId);
        if (likedReport != null) {
            return ResponseEntity.ok("Report liked successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{reportId}/disLike")
    public ResponseEntity<String> disLike(@PathVariable Long reportId) {
        Report dislikedReport = reportFactory.disLike(reportId);
        if (dislikedReport != null) {
            return ResponseEntity.ok("Report disliked successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{reportId}/confirm")
    public void confirmByOperator(@PathVariable Long reportId) {
        reportFactory.confirmByOperator(reportId);
    }
}

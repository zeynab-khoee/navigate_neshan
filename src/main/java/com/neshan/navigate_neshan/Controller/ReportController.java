package com.neshan.navigate_neshan.Controller;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Model.Report;
import com.neshan.navigate_neshan.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Report")
public class ReportController {
    ReportService reportService;
    @PostMapping("/{routId}")
    public void createItem(@RequestBody Report report,@PathVariable Long routId) {
        reportService.createReport(report,routId);
    }
    @GetMapping("{reportId}")
   public ReportDto findReportById(@PathVariable Long reportId){
        return reportService.findReportById(reportId);
    }
    @GetMapping("{routId}")
    public List<ReportDto> getAllReportByRoutId(@PathVariable Long routId){
        return reportService.getAllReportByRoutId(routId);
    }
    @PutMapping("{reportId}/like")
    public ReportDto like(@PathVariable Long reportId){
        return reportService.like(reportId);
    }
    @PutMapping("{reportId}/disLike")
    public ReportDto disLike(@PathVariable Long reportId){
        return reportService.disLike(reportId);
    }

}

package com.neshan.navigate_neshan.Controller.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Service.Report.AccidentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accidentReport")
public class AccidentReportController {
    private final AccidentReportService accidentReportService;
    @PutMapping("{reportId}/confirm")
    public ReportDto ConfirmByOperator(@PathVariable Long reportId) {
        return accidentReportService.confirmByOperator(reportId);
    }
    @GetMapping("{reportId}")
    public ReportDto findReportById(@PathVariable Long reportId){
        return accidentReportService.findReportById(reportId);
    }
}

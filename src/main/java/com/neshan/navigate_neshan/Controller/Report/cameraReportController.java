package com.neshan.navigate_neshan.Controller.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Service.Report.CameraReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cameraReport")
public class cameraReportController {
    private final CameraReportService cameraReportService;

    @PutMapping("{reportId}/confirm")
    public ReportDto ConfirmByOperator(@PathVariable Long reportId) {
        return cameraReportService.confirmByOperator(reportId);
    }

    @GetMapping("{reportId}")
    public ReportDto findReportById(@PathVariable Long reportId) {
        return cameraReportService.findReportById(reportId);
    }
}


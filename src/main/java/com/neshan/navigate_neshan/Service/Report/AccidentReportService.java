package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import com.neshan.navigate_neshan.Repository.RoutRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccidentReportService implements ReportHandler {
    RoutRepo routRepo;
    ReportRepo reportRepo;

    public void createReport(Report report, Long routId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        report.setUser(user);
        report.setReportType(ReportType.CAMERA);
        report.setRout(routRepo.findById(routId)
                .orElse(null));
        reportRepo.save(report);
    }

}

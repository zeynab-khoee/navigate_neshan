package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.Rout;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import com.neshan.navigate_neshan.Repository.RoutRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccidentReportService implements ReportHandler {
    ReportRepo reportRepo;
    RoutRepo routRepo;

    public void createReport(Report report, ReportType type, Rout rout) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        report.setUser(user);
        report.setReportType(ReportType.ACCIDENT);
        report.setCreatedDate(LocalDateTime.now());
        report.setExpirationDate(report.getCreatedDate().plusMinutes(2));
        routRepo.save(rout);
        report.setRout(rout);
        reportRepo.save(report);
    }

}

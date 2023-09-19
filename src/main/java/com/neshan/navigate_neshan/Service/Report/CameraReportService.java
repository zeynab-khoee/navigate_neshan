package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Enum.ReportType;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.Rout;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import com.neshan.navigate_neshan.Repository.RoutRepo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CameraReportService implements ReportHandler {
    ReportRepo reportRepo;
    RoutRepo routRepository;

    public void createReport(Report report, ReportType type, Rout rout) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        report.setUser(user);
        report.setReportType(ReportType.CAMERA);
        report.setCreatedDate(LocalDateTime.now());
        report.setExpirationDate(report.getCreatedDate().plusMinutes(2));
        routRepository.save(rout);
        report.setRout(rout);
        reportRepo.save(report);
    }

}



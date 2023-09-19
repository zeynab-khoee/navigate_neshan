package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.RoleType;
import com.neshan.navigate_neshan.Mapper.ReportMapper;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@ToString
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService implements ReportHandler {
    ReportRepo reportRepo;

    public void confirmByOperator(Long reportId) {
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            if (report.isRequiredConfirm()) {
                report.setAccepted(true);
            }
            reportRepo.save(report);
        } else {
            throw new NullPointerException("Report is null");
        }
    }

    public List<ReportDto> getAllReportByRoutId(Long routId) {
        List<Report> reportList = reportRepo.getReportListByRoutId(routId);
        return reportList.stream().map(ReportMapper.INSTANCE::reportToReportDTO)
                .toList();
    }

    public Report like(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int minutesToPlus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);

        if (report != null) {
            if (!report.getUserInfoLiked().contains(user)) {
                report.getUserInfoLiked().add(user);
                user.getReportListLiked().add(report);
            }

            report.setLikes(report.getLikes() + 1);
            report.setExpirationDate(report.getExpirationDate().plusMinutes(minutesToPlus));

            // Save the report (which will also update the relationships)
            return reportRepo.save(report);
        }

        return null;
    }

    public Report disLike(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int minutesToMinus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            report.setLikes(report.getLikes() - 1);
            report.getUserInfoLiked().remove(user);
            report.setExpirationDate(report.getExpirationDate().minusMinutes(minutesToMinus));
            return reportRepo.save(report);
        }
        return null;
    }

}

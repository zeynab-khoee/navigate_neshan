package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.RoleType;
import com.neshan.navigate_neshan.Mapper.ReportMapper;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService implements ReportHandler {
    ReportRepo reportRepo;

    public void confirmByOperator(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReportDto reportDto = findReportById(reportId);
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        if (user.getRole() == RoleType.ADMIN && report.isRequiredConfirm()) {
            report.setAccepted(true);
        }
    }

    public ReportDto findReportById(Long reportId) {
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }

    public List<ReportDto> getAllReportByRoutId(Long routId) {
        List<Report> reportList = reportRepo.getReportListByRoutId(routId);
        return reportList.stream().map(ReportMapper.INSTANCE::reportToReportDTO)
                .toList();
    }

    public ReportDto like(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int minutesToPlus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            report.setLikes(report.getLikes() + 1);
            report.getUserInfoLiked().remove(user);
            report.setExpirationDate(report.getExpirationDate().plusMinutes(minutesToPlus));
            reportRepo.save(report);
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }

    public ReportDto disLike(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int minutesToMinus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            report.setLikes(report.getLikes() + 1);
            report.getUserInfoLiked().add(user);
            report.setExpirationDate(report.getExpirationDate().minusMinutes(minutesToMinus));
            reportRepo.save(report);
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }

}

package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Mapper.ReportMapper;
import com.neshan.navigate_neshan.Model.Report;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo;
import com.neshan.navigate_neshan.Repository.RoutRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {
    ReportRepo reportRepo;
    private final RoutRepo routRepo;

    public void createReport(Report report, Long routId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        report.setUser(user);
        report.setRout(routRepo.findById(routId)
                .orElse(null));
        reportRepo.save(report);
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

    public ReportDto Like(Long reportId) {
        int minutesToPlus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            report.setLikes(report.getLikes() + 1);
            report.setExpirationDate(report.getExpirationDate().plusMinutes(minutesToPlus));
            reportRepo.save(report);
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }

    public ReportDto disLike(Long reportId) {
        int minutesToMinus = 2;
        Report report = reportRepo.findById(reportId).orElse(null);
        if (report != null) {
            report.setLikes(report.getLikes() + 1);
            report.setExpirationDate(report.getExpirationDate().minusMinutes(minutesToMinus));
            reportRepo.save(report);
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }

}

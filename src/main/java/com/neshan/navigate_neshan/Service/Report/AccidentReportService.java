package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Enum.RoleType;
import com.neshan.navigate_neshan.Mapper.ReportMapper;
import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.AccidentReportRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccidentReportService {
    AccidentReportRepo accidentReportRepo;
    public ReportDto findReportById(Long reportId) {
        Report report = accidentReportRepo.findById(reportId).orElse(null);
        if (report != null) {
            return ReportMapper.INSTANCE.reportToReportDTO(report);
        }
        return null;
    }
    public ReportDto confirmByOperator(Long reportId) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReportDto reportDto = findReportById(reportId);
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        if (user.getRole() == RoleType.ADMIN && report.isRequiredConfirm()) {
            report.setAccepted(true);
        }
        return ReportMapper.INSTANCE.reportToReportDTO(report);
    }
}

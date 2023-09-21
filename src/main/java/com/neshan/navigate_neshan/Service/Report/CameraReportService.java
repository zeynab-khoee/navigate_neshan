package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Data.Enum.ReportType;
import com.neshan.navigate_neshan.Data.Model.Report.Report;
import com.neshan.navigate_neshan.Data.Model.Rout;
import com.neshan.navigate_neshan.Data.Model.UserInfo;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import com.neshan.navigate_neshan.Repository.RoutRepo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CameraReportService implements ReportHandler {
    ReportRepo reportRepo;
    RoutRepo routRepository;
    RedissonClient redissonClient;

    public void createReport(Report report, ReportType type, Rout rout) {

        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        report.setUser(user);
        int repeat = reportRepo.findReportsByUserAndWktStringNative(user.getId(), rout.getWktString());
        System.out.println(repeat);
        String lockKey = report.getReportType() + "_report_creation_lock_" + user.getId();
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean isLocked = lock.tryLock(40, TimeUnit.SECONDS);
            if (isLocked) {
                //if dosnt exist repeat report with same user and same rout
                if (repeat == 0) {
                    report.setReportType(ReportType.CAMERA);
                    report.setCreatedDate(LocalDateTime.now());
                    report.setExpirationDate(report.getCreatedDate().plusMinutes(2));
                    routRepository.save(rout);
                    report.setRout(rout);
                    reportRepo.save(report);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}


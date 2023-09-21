package com.neshan.navigate_neshan.Service.Report;

import com.neshan.navigate_neshan.Data.Model.Report.Report;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepo reportRepo;

    @Test
    public void testConfirmByOperator_Success() {
        Long reportId = 1L;
        Report report = new Report();
        report.setRequiredConfirm(true); // Simulating that confirmation is required

        // Mock the behavior of reportRepo.findById()
        when(reportRepo.findById(reportId)).thenReturn(Optional.of(report));

        // Call the confirmByOperator method
        reportService.confirmByOperator(reportId);

        // Verify that the report is accepted
        assertTrue(report.isAccepted());

        // Verify that reportRepo.save() was called
        verify(reportRepo, times(1)).save(report);
    }

}
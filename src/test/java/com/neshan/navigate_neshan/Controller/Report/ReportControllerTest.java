package com.neshan.navigate_neshan.Controller.Report;

import com.neshan.navigate_neshan.Component.ReportFactory;
import com.neshan.navigate_neshan.Data.Enum.ReportType;
import com.neshan.navigate_neshan.Data.Model.Report.Report;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportFactory reportFactory;


    @Test
    public void testLike() throws Exception {
        Long reportId = 1L; // Define a reportId for testing

        Report likedReport = new Report(); // Create a mocked liked report
        when(reportFactory.like(reportId)).thenReturn(likedReport);

        // Perform the test using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.put("/Report/{reportId}/like", reportId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Report liked successfully"));
    }

    @Test
    public void testLikeNotFound() throws Exception {
        Long reportId = 1L;

        when(reportFactory.like(reportId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/Report/{reportId}/like", reportId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateReport() throws Exception {
        Report report = new Report();
        ReportType type = ReportType.CAMERA;

        // Mock the behavior of reportFactory.createReport()
        doNothing().when(reportFactory).createReport(report, type, report.getRout());

        // Perform a POST request to create a report
        mockMvc.perform(MockMvcRequestBuilders.post("/Report/{type}", type)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // You can provide a JSON payload here
                .andExpect(status().isOk());
    }

}

package com.neshan.navigate_neshan.Controller;

import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Service.RoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rout")
public class RoutController {
    RoutService routService;

    @PostMapping("/getReport")
    public List<Report> generateLineString(@RequestBody String wktString) {
        return routService.getReportsWithin10Units(wktString);
    }
}

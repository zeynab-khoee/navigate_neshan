package com.neshan.navigate_neshan.Controller;

import com.neshan.navigate_neshan.Service.RoutService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/rout")
public class RoutController {
    RoutService routService;

    @PostMapping("/getReport")
    public ResponseEntity<List<String>> generateLineString(@RequestBody String wktString) {
        try {
            List<String> reportDescriptions = routService.findReportsWithinDistance(wktString);
            return new ResponseEntity<>(reportDescriptions, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

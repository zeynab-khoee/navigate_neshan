package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoutService {
    ReportRepo reportRepo;

    @Async // Add the @Async annotation to enable asynchronous execution
    public CompletableFuture<List<Report>> getReportsWithin10Units(String wktString) {
        try {
            GeometryFactory geometryFactory = new GeometryFactory();
            WKTReader wktReader = new WKTReader(geometryFactory);
            Geometry inputGeometry = wktReader.read(wktString);
            double bufferDistance = 10.0;
            Geometry buffer = inputGeometry.buffer(bufferDistance);

            List<Report> reports = reportRepo.findReportsIntersectingWithBuffer(buffer);
            return CompletableFuture.completedFuture(reports);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }
}


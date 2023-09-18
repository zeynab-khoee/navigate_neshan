package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Model.Report.Report;
import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoutService {
    ReportRepo reportRepo;

    public List<Report> getReportsWithin10Units(String wktString) {
        try {
            GeometryFactory geometryFactory = new GeometryFactory();
            WKTReader wktReader = new WKTReader(geometryFactory);
            Geometry inputGeometry = wktReader.read(wktString);
            double bufferDistance = 10.0;
            Geometry buffer = inputGeometry.buffer(bufferDistance);

            return reportRepo.findReportsIntersectingWithBuffer(buffer);
        } catch (Exception e) {
            e.printStackTrace();

            return Collections.emptyList();
        }
    }
}



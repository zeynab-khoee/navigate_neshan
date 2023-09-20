package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Repository.ReportRepo.ReportRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoutService {
    ReportRepo reportRepo;

    public List<String> findReportsWithinDistance(String wktGeometry) throws Exception {
        Geometry bufferGeometry = createBufferFromWKT(wktGeometry);

        return reportRepo.findReportsIntersectingWithBuffer(bufferGeometry);
    }

    private Geometry createBufferFromWKT(String wktGeometry) throws Exception {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        WKTReader wktReader = new WKTReader(geometryFactory);
        Geometry geometry = wktReader.read(wktGeometry);
        return geometry.buffer(10);
    }
}



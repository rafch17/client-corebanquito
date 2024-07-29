package com.banquito.corecobros.clientdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.banquito.corecobros.clientdoc.model.Segment;
import java.util.List;

public interface SegmentRepository extends MongoRepository<Segment, String> {
    Segment findByCode(String id);

    List<Segment> findAllByOrderByName();
}

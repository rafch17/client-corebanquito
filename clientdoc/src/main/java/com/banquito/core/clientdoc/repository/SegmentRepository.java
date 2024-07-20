package com.banquito.core.clientdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.banquito.core.clientdoc.model.Segment;
import java.util.List;

public interface SegmentRepository extends MongoRepository<Segment, String> {
    Segment findByCode(String code);

    List<Segment> findAllByOrderByName();
}

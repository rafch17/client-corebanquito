package com.banquito.corecobros.clientdoc.repository;

import com.banquito.corecobros.clientdoc.model.Segment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SegmentRepository extends MongoRepository<Segment, String> {
    Optional<Segment> findByUniqueId(String uniqueId);

    List<Segment> findAllByOrderByName();
}

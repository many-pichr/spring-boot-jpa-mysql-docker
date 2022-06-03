package com.many.exercise.repository;

import com.many.exercise.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    Optional<Track> findByOldId(Long id);
}

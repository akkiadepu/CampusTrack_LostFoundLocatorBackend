package com.campusTrack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusTrack.entity.MatchItem;
import com.campusTrack.entity.MatchItemId;

public interface MatchItemRepository extends JpaRepository<MatchItem, MatchItemId> {

}

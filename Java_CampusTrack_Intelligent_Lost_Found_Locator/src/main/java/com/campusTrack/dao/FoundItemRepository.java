package com.campusTrack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.LostItem;

@Repository
public interface FoundItemRepository extends JpaRepository<FoundItem, String> {

	@Query("select max(foundItemId) from FoundItem")
	public String getLastId();

	@Query("select a from FoundItem a where a.username = ?1")
	public List<FoundItem> getfoundItemsByUsername (String username);
}

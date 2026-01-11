package com.campusTrack.dao;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.campusTrack.entity.LostItem;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, String> {

	@Query("select max(lostItemId) from LostItem")
	public String getLastId();

	@Query("select a from LostItem a where a.username = ?1 and a.status = false")
	public List<LostItem> getLostItemsByUsername (String username);
	


}

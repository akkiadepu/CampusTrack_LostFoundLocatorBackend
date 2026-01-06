package com.campusTrack.dao;

import java.util.List;

import com.campusTrack.entity.MatchItem;

public interface MatchItemDao {

	public void saveMatchItem(MatchItem matchItem);
	public List<MatchItem> getAllMatchItems();
	
}

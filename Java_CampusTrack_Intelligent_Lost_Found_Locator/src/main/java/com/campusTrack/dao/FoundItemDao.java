package com.campusTrack.dao;

import java.util.List;

import com.campusTrack.entity.FoundItem;


public interface FoundItemDao {

	
	public void saveFoundItem(FoundItem foundItem);
	public List<FoundItem> getAllFoundItems();
	public FoundItem getFoundItemById(String foundItemId);
	public void deleteFoundItemById(String foundItemId);
	public void updateFoundItem(FoundItem foundItem);
	public String getLastId();
	public List<FoundItem> getFoundItemsByUsername (String username);
	public List<FoundItem> matchFoundItems(String lostItemName, String category, String color, String brand,String location);
}

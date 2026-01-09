package com.campusTrack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.FoundItemDao;
import com.campusTrack.dao.LostItemDao;
import com.campusTrack.dao.MatchItemDao;
import com.campusTrack.dao.MatchItemRepository;
import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.FoundItemDTO;
import com.campusTrack.entity.LostItem;
import com.campusTrack.entity.MatchItem;
import com.campusTrack.entity.MatchItemDTO;

@Service
public class MatchItemService {

	@Autowired
	private MatchItemRepository repository;
	
	@Autowired
	private LostItemDao lostItemDao;
	
	
	@Autowired
	private FoundItemDao foundItemDao;
	
	public void updateLostFoundItems(MatchItemDTO matchItemDTO) {
		
		String lostItemId = matchItemDTO.getLostItemId();
		String foundItemId = matchItemDTO.getFoundItemId();
		
		LostItem lostItem = lostItemDao.getLostItemById(lostItemId);
		FoundItem foundItem = foundItemDao.getFoundItemById(foundItemId);
		
		lostItem.setStatus(true);
		foundItem.setStatus(true);
		
		lostItemDao.saveLostItem(lostItem);
		foundItemDao.saveFoundItem(foundItem);
		
		
	}
	
//	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {
//
//	    LostItem lost = lostItemDao.getLostItemById(lostItemId);
//
//	    List<FoundItem> results = foundItemDao.matchFoundItems(
//	        lost.getLostItemName(),
//	        lost.getCategory(),
//	        lost.getColor(),
//	        lost.getBrand()
//	    );
//
//	    return results.stream()
//	        .map(FoundItemDTO::new)
//	        .collect(Collectors.toList());
//	}

	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {

	    LostItem lost = lostItemDao.getLostItemById(lostItemId);

	    List<FoundItem> candidates =
	        foundItemDao.matchFoundItems(
	            lost.getLostItemName(),
	            lost.getCategory(),
	            lost.getColor(),
	            lost.getBrand(),
	            lost.getLocation()
	        );

	    List<FoundItem> finalMatches = new ArrayList<>();

	    for (FoundItem found : candidates) {

	        // -------- Rule 1: Item name match (TOP PRIORITY) --------
	        if (isNameMatch(lost.getLostItemName(), found.getFoundItemName())) {
	            finalMatches.add(found);
	            continue;
	        }

	        // -------- Rule 2: 2 or 3 other fields match --------
	        int matchCount = 0;

	        if (equalsIgnoreCase(lost.getCategory(), found.getCategory()))
	            matchCount++;

	        if (equalsIgnoreCase(lost.getColor(), found.getColor()))
	            matchCount++;

	        if (equalsIgnoreCase(lost.getBrand(), found.getBrand()))
	            matchCount++;

	        if (equalsIgnoreCase(lost.getLocation(), found.getLocation()))
	            matchCount++;

	        if (matchCount >= 2) {
	            finalMatches.add(found);
	        }
	    }

	    return finalMatches.stream()
	            .map(FoundItemDTO::new)
	            .collect(Collectors.toList());
	}
	
	private boolean isNameMatch(String lostName, String foundName) {
	    if (lostName == null || foundName == null) return false;

	    lostName = lostName.trim().toLowerCase();
	    foundName = foundName.trim().toLowerCase();

	    return foundName.contains(lostName) ||
	           lostName.contains(foundName);
	}

	private boolean equalsIgnoreCase(String a, String b) {
	    if (a == null || b == null) return false;
	    return a.trim().equalsIgnoreCase(b.trim());
	}


	
}

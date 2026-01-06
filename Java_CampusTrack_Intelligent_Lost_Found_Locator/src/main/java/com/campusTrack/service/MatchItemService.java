package com.campusTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.FoundItemDao;
import com.campusTrack.dao.LostItemDao;
import com.campusTrack.dao.MatchItemDao;
import com.campusTrack.dao.MatchItemRepository;
import com.campusTrack.entity.FoundItem;
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
	
}

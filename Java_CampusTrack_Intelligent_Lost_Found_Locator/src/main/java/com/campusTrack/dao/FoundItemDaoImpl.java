package com.campusTrack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.campusTrack.entity.FoundItem;
import com.campusTrack.service.FoundItemService;

@Service
@Repository
public class FoundItemDaoImpl implements FoundItemDao {

	@Autowired
	private FoundItemRepository repository;

	
	@Override
	public void saveFoundItem(FoundItem foundItem) {
		repository.save(foundItem);

	}

	@Override
	public List<FoundItem> getAllFoundItems() {
		return repository.findAll();
	}

	@Override
	public FoundItem getFoundItemById(String foundItemId) {
		 return repository.findById(foundItemId).orElse(null);
	}

	@Override
	public void deleteFoundItemById(String foundItemId) {
		 repository.deleteById(foundItemId);

	}

	@Override
	public void updateFoundItem(FoundItem foundItem) {
		 repository.save(foundItem);

	}

	@Override
	public String getLastId() {
		return repository.getLastId();
	}

	@Override
	public List<FoundItem> getFoundItemsByUsername(String username) {
		return repository.getfoundItemsByUsername(username);
	}

	@Override
	public List<FoundItem> matchFoundItems(String ItemName, String category, String color, String brand,String location) {
		// TODO Auto-generated method stub
		 return repository.findPossibleMatches(ItemName, category, color, brand,location);
	}

}

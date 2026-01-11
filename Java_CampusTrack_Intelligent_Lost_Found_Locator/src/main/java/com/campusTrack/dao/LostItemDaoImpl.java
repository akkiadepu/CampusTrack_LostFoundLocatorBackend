package com.campusTrack.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.campusTrack.entity.LostItem;

@Service
@Repository
public class LostItemDaoImpl implements LostItemDao {

	@Autowired
	private LostItemRepository repository;

	@Override
	public void saveLostItem(LostItem lostItem) {
		repository.save(lostItem);
	}

	@Override
	public List<LostItem> getAllLostItems() {
		return repository.findAll();
	}

	@Override
	public LostItem getLostItemById(String lostItemId) {
		return repository.findById(lostItemId).orElse(null);
	}

	@Override
	public void deleteLostItemById(String lostItemId) {
		repository.deleteById(lostItemId);
	}

	@Override
	public void updateLostItem(LostItem lostItem) {
		repository.save(lostItem);
	}

//    @Override
//    public String generateId() {
//    	
//    	String newId="";
//        String Id = repository.getLastId();
//        
//        if (Id == null) {
//            newId= "L100001";
//        } else {
//            int num = Integer.parseInt(Id.substring(1)) + 1;
//            newId = "L" + num;
//        }
//        
//        return newId;
//    }

	@Override
	public List<LostItem> getLostItemsByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.getLostItemsByUsername(username);
	}

	@Override
	public String getLastId() {
		// TODO Auto-generated method stub
		return repository.getLastId();
	}

	

}
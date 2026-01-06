package com.campusTrack.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.campusTrack.entity.MatchItem;

@Service
@Repository
public class MatchItemDaoImp implements MatchItemDao {

	@Autowired
	private MatchItemRepository repository;
	
	@Override
	public void saveMatchItem(MatchItem matchItem) {
		repository.save(matchItem);
	}

	@Override
	public List<MatchItem> getAllMatchItems() {
		return repository.findAll();
	}
	
}

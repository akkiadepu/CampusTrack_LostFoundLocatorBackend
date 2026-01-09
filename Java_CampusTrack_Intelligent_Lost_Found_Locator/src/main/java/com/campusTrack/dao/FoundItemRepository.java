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
	
	// Keyword search (LIKE for partial match)
	@Query("SELECT f FROM FoundItem f WHERE f.status=false and (" +
	       "LOWER(f.foundItemName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	       "LOWER(f.color) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	       "LOWER(f.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	       "LOWER(f.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	       "LOWER(f.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<FoundItem> searchByKeyword(String keyword);
	
	
	// Fuzzy matching using SOUNDEX FoundItem
	@Query(value = "SELECT * FROM found_item WHERE status=false and (" +
	       "SOUNDEX(found_item_name) = SOUNDEX(:keyword) OR " +
	       "SOUNDEX(color) = SOUNDEX(:keyword) OR " +
	       "SOUNDEX(brand) = SOUNDEX(:keyword) OR " +
	       "SOUNDEX(location) = SOUNDEX(:keyword) OR " +
	       "SOUNDEX(category) = SOUNDEX(:keyword))",nativeQuery = true)
	List<FoundItem> fuzzySearchBySoundex(String keyword);
	
//	@Query(value = """
//			SELECT * FROM found_item
//			WHERE status = false
//			AND (
//			    SOUNDEX(found_item_name) = SOUNDEX(:itemName)
//			 OR SOUNDEX(color) = SOUNDEX(:color)
//			 OR SOUNDEX(brand) = SOUNDEX(:brand)
//			 OR SOUNDEX(category) = SOUNDEX(:category)
//			 OR LOWER(found_item_name) LIKE LOWER(CONCAT('%', :itemName, '%'))
//			 OR LOWER(color) LIKE LOWER(CONCAT('%', :color, '%'))
//			 OR LOWER(brand) LIKE LOWER(CONCAT('%', :brand, '%'))
//			 OR LOWER(category) LIKE LOWER(CONCAT('%', :category, '%'))
//			)
//			""", nativeQuery = true)
//			List<FoundItem> matchFoundItems(
//			    String itemName,
//			    String category,
//			    String color,
//			    String brand
//			);
	
	
	@Query(value = """
			SELECT * FROM found_item
			WHERE status = false
			AND (
			    SOUNDEX(found_item_name) = SOUNDEX(:itemName)
			 OR LOWER(found_item_name) LIKE LOWER(CONCAT('%', :itemName, '%'))
			 OR SOUNDEX(category) = SOUNDEX(:category)
			 OR SOUNDEX(color) = SOUNDEX(:color)
			 OR SOUNDEX(brand) = SOUNDEX(:brand)
			 OR SOUNDEX(location) = SOUNDEX(:location)
			)
			""", nativeQuery = true)
			List<FoundItem> findPossibleMatches(
			    String itemName,
			    String category,
			    String color,
			    String brand,
			    String location
			);


	
	
}

package com.campusTrack.entity;

public class MatchItemDTO {
	
	
	private String lostItemId;
	private String foundItemId;
    private String itemName;
    private String category;
    private String lostUserName;
    private String foundUserName;
    
	public MatchItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchItemDTO(String lostItemId, String foundItemId, String itemName, String category, String lostUserName,
			String foundUserName) {
		super();
		this.lostItemId = lostItemId;
		this.foundItemId = foundItemId;
		this.itemName = itemName;
		this.category = category;
		this.lostUserName = lostUserName;
		this.foundUserName = foundUserName;
	}
	
	public MatchItemDTO(MatchItem matchItem) {
		super();
		this.lostItemId= matchItem.getMatchItemId().getLostItemId();
		this.foundItemId= matchItem.getMatchItemId().getFoundItemId();
		this.itemName = matchItem.getItemName();
		this.category = matchItem.getCategory();
		this.lostUserName = matchItem.getLostUsername();
		this.foundUserName = matchItem.getFoundUsername();
	}

	public String getLostItemId() {
		return lostItemId;
	}

	public void setLostItemId(String lostItemId) {
		this.lostItemId = lostItemId;
	}

	public String getFoundItemId() {
		return foundItemId;
	}

	public void setFoundItemId(String foundItemId) {
		this.foundItemId = foundItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLostUserName() {
		return lostUserName;
	}

	public void setLostUserName(String lostUserName) {
		this.lostUserName = lostUserName;
	}

	public String getFoundUserName() {
		return foundUserName;
	}

	public void setFoundUserName(String foundUserName) {
		this.foundUserName = foundUserName;
	}
    
    
    
    
}

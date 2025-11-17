package com.campusTrack.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class LostFounditems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_Id;
	
	@Column(name = "item_type", nullable = false)
	private String type;
	
	@Column(name = "item_title", nullable = false)
	private String title;
	
	@Column(name = "item_description", nullable = false , columnDefinition = "TEXT")
	private String description;
	
	//the @Temporal annotation is used to specify the exact precision of temporal data (dates and times) when mapping java.util.Date or java.util.Calendar fields to database columns.
	//TemporalType.TIMESTAMP specifically means that the mapped field will include both date and time information, down to the precision supported by the underlying database's timestamp data type
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "item_reportedDate", nullable = false , updatable = false)
	private Date date_reported;
	
	@Column(name = "item_location", nullable = false)
	private String location;
	
	@Column(name = "item_image", nullable = false)
	private String image_url;
	
	@Column(name = "item_category", nullable = false)
	private String category;
	
	@Column(name = "item_tags", nullable = false)
	private String tags;
	
	@Column(name = "item_status", nullable = false)
	private String status;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime created_at;
	
//	@Column(name = "Users_id", nullable = false)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	@OneToMany(mappedBy = "lost_Item")
	private List<Matches> lostMatches;
	
	@OneToMany(mappedBy = "found_Item")
	private List<Matches> foundMatches;
	
	@OneToMany(mappedBy = "items")
	private List<Flags> flags;
	

	public LostFounditems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LostFounditems(int item_Id, String type, String title, String description, Date date_reported,
			String location, String image_url, String category, String tags, String status
			) {

		super();
		this.item_Id = item_Id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.date_reported = date_reported;
		this.location = location;
		this.image_url = image_url;
		this.category = category;
		this.tags = tags;
		this.status = status;
		this.created_at = LocalDateTime.now();

	}



	public int getItem_Id() {
		return item_Id;
	}

	public void setItem_Id(int item_Id) {
		this.item_Id = item_Id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_reported() {
		return date_reported;
	}

	public void setDate_reported(Date date_reported) {
		this.date_reported = date_reported;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	

	public List<Matches> getLostMatches() {
		return lostMatches;
	}

	public void setLostMatches(List<Matches> lostMatches) {
		this.lostMatches = lostMatches;
	}

	public List<Matches> getFoundMatches() {
		return foundMatches;
	}

	public void setFoundMatches(List<Matches> foundMatches) {
		this.foundMatches = foundMatches;
	}

	public List<Flags> getFlags() {
		return flags;
	}

	public void setFlags(List<Flags> flags) {
		this.flags = flags;
	}

	@Override
	public String toString() {
		return "Lost_Found_items [item_Id=" + item_Id + ", type=" + type + ", title=" + title + ", description="
				+ description + ", date_reported=" + date_reported + ", location=" + location + ", image_url="
				+ image_url + ", category=" + category + ", tags=" + tags + ", status=" + status + ", created_at="
				+ created_at + "]";
	}
	
	
	
	

}

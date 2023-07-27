package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Required!!")
	@Size(min = 2,max = 20,message = "Minimum 2 characters and maximum 20 characters!!")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Required!!")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;

	@Column(length = 500)
	private String about;

	private boolean enabled;
	
	@NotBlank(message="Required!!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$",message = "Password must be of Minimum lenght 8. It should contain atleast one uppercase,one lowercase character,one digit and one special character(!,@,#,$,%,^,&,*)")
	private String password;
	private String imageUrl;

	private String role;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Contact> contacts = new ArrayList<Contact>();

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public User(int id, String name, String email, String about, boolean enabled, String password, String imageUrl,
			String role, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.about = about;

		this.enabled = enabled;
		this.password = password;
		this.imageUrl = imageUrl;
		this.role = role;
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", about=" + about + ", enabled=" + enabled
				+ ", password=" + password + ", imageUrl=" + imageUrl + ", role=" + role + ", contacts=" + contacts
				+ "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

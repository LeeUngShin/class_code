package com.example.demo;

public class MemberInfoClass {
	
	private String id;
	private String password;
	private String name;
	private String address;
	private int age;
	private String email;
	
	public MemberInfoClass() {	}

	public MemberInfoClass(String id, String password, String name, String address, int age, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.age = age;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberInfoClass [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", age=" + age + ", email=" + email + "]";
	}
	
	
	
	
	

}

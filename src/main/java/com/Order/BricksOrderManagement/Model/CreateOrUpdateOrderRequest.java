package com.Order.BricksOrderManagement.Model;

public class CreateOrUpdateOrderRequest {

	private Integer bricks;

	public Integer getBricks() {
		return bricks;
	}

	public void setBricks(Integer bricks) {
		this.bricks = bricks;
	}

	public CreateOrUpdateOrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateOrUpdateOrderRequest(Integer bricks) {
		super();
		this.bricks = bricks;
	}
	
	
}

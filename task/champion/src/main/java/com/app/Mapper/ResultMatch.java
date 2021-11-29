package com.app.Mapper;

public class ResultMatch { 
	
	private Integer id ; 
	
	private Double homeResult; 
	
	private Double wayResult ; 
	
	private Integer isWin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getHomeResult() {
		return homeResult;
	}

	public void setHomeResult(Double homeResult) {
		this.homeResult = homeResult;
	}

	public Double getWayResult() {
		return wayResult;
	}

	public void setWayResult(Double wayResult) {
		this.wayResult = wayResult;
	}

	public Integer getIsWin() {
		return isWin;
	}

	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}
	

}

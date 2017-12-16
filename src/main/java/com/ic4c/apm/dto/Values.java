/**
 * 
 */
package com.ic4c.apm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10620506
 *
 */
public class Values {
	
	
/*	private List<InnerValues> innerValues = new ArrayList<>();
	
	*//**
	 * @return the eccCustomAssetList
	 *//*
	public List<InnerValues> getInnerValues() {
		return innerValues;
	}
	*//**
	 * @param eccCustomAssetList the eccCustomAssetList to set
	 *//*
	public void setInnerValues(List<InnerValues> innerValues) {
		this.innerValues = innerValues;
	}*/
	
	private long datapoints[]=new long[3];
	
	
	public long[] getDatapoints(){
		 
		 return datapoints;
	 }
	
	public void  setDatapoints(long datapoints[]){
		 
		this.datapoints = datapoints;
	 }

	
	 public long getTimestamp(){
		 
		 return datapoints[0];
	 }
	 
	 public long getValue(){
		 
		 return datapoints[1];
	 }
	 
	 public long getQuality(){
		 
		 return datapoints[2];
	 }

}

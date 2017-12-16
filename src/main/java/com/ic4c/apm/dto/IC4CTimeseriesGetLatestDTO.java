/**
 * 
 */
package com.ic4c.apm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 10620506
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IC4CTimeseriesGetLatestDTO {
	
	private List<TagNamesDTO> tags;

	public List<TagNamesDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagNamesDTO> tags) {
		this.tags = tags;
	}

	
}

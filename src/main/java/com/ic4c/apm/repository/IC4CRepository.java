/**
 * 
 */
package com.ic4c.apm.repository;

import java.util.List;

import com.ic4c.apm.dto.IC4CAssetDTO;

/**
 * @author vasudev007
 *
 */
public interface IC4CRepository {

	public List<IC4CAssetDTO> getAllAssetFromCache(String authorizationToken);

}

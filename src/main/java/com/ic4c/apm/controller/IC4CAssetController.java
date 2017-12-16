
package com.ic4c.apm.controller;

import java.io.IOException;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ic4c.apm.service.IC4CAssetService;



@RestController
@RequestMapping(value = "/api")
public class IC4CAssetController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IC4CAssetService assetService;

	@RequestMapping(value = "/assets", method = RequestMethod.GET)
	private Object getAssetsDetailsByURI(@RequestHeader(value = "authorization") String authorizationToken)
			throws ParseException, IOException {
		// List<EccCustomAssetDetails> allAssets = null;

		logger.debug("IC4CAssetController: getAssetDetails");

		return assetService.getAssetDetails(authorizationToken);
	}

}

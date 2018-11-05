
package com.communityratesgames.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlatformController {

	@Autowired
	private final PlatformService service;

	public PlatformController(PlatformService service) {
		this.service = service;
	}

	@GetMapping("/platform")
	public ResponseEntity<List<PlatformModel>> getPlatforms() {
		List<PlatformModel> platforms = service.getPlatforms();
		return new ResponseEntity(platforms, HttpStatus.OK);
	}

	@PostMapping("/platform")
	public ResponseEntity<List<PlatformModel>> postPlatform(@RequestBody PlatformModel payload) {
		service.insertPlatform(payload);
		return getPlatforms();
	}

}

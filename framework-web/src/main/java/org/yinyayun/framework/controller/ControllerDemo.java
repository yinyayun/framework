/**
 * 
 */
package org.yinyayun.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.yinyayun.framework.service.ServiceDemo;

/**
 * @author yinyayun
 *
 */
@RestController
public class ControllerDemo {
	@Autowired
	private ServiceDemo service;

	public String hello() {
		return service.hello();
	}
}

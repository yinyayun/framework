/**
 * 
 */
package org.yinyayun.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yinyayun
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/1")
	public String index() {
		return "/statics/index.html";
	}
}

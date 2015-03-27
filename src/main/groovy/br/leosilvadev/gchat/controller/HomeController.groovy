package br.leosilvadev.gchat.controller

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	String home(Model model){
		model.addAttribute("rooms", ["ROOM1", "ROOM2", "ROOM3"])
		return "home"
	}
	
}

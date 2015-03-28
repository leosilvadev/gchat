package br.leosilvadev.gchat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import br.leosilvadev.gchat.manager.RoomsManager

@Controller
@RequestMapping("/")
class HomeController {

	@Autowired RoomsManager roomsManager
	
	@RequestMapping(method=RequestMethod.GET)
	String home(Model model){
		model.addAttribute("rooms", roomsManager.rooms)
		return "home"
	}
	
}

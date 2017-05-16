package com.anz.eventplanner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anz.eventplanner.model.EventManager;
import com.anz.eventplanner.service.EventManagerService;

@Controller
public class AdminControllerForEventManager {
	
	@Autowired
	EventManagerService eventManagerService;

	@Autowired
	MessageSource messageSource;
	
	/**
	 * This method lists all Event Managers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listEventManagers" }, method = RequestMethod.GET)
	public String listAllEventManagers(ModelMap model) {
		List<EventManager> eventManagers = eventManagerService.findAllEventManager();
		model.addAttribute("eventManagers", eventManagers);
		return "listEventManagers";
	}

	/**
	 * This method provide the form to add a new Event Manager
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEventManager" }, method = RequestMethod.GET)
	public String newEventManager(ModelMap model) {
		EventManager eventManager = new EventManager();
		model.addAttribute("eventManager", eventManager);
		model.addAttribute("edit", false);
		return "addEventManager";
	}

	/**
	 * This method will be called on New Event Manager addition form submission,
	 * handling POST request for saving event managers details in database. TO
	 * DO: validate user Input
	 * 
	 * @param eventManager
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEventManager" }, method = RequestMethod.POST)
	public String saveEventManager(@Valid EventManager eventManager, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEventManager";
		}
		eventManagerService.saveEventManager(eventManager);
		redirectAttributes.addFlashAttribute("success", eventManager.getEventManagerName() + " added as event manager");
		return "redirect:/listEventManagers";
	}

	/**
	 * This method will provide a medium to update existing Event Managers
	 * 
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{eventManagerId}-eventManager" }, method = RequestMethod.GET)
	public String editEventManager(@PathVariable(value = "eventManagerId") int eventManagerId, ModelMap model) {
		EventManager eventManager = eventManagerService.findById(eventManagerId);
		model.addAttribute("eventManager", eventManager);
		model.addAttribute("edit", true);
		return "addEventManager";
	}

	/***
	 * This method will be called on form submission, handling POST request for
	 * updating event in database. To do - validation
	 * 
	 * @param event
	 * @param result
	 * @param model
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value = { "/edit-{eventManagerId}-eventManager" }, method = RequestMethod.POST)
	public String updateEventManager(@Valid EventManager eventManager, BindingResult result, ModelMap model,
			@PathVariable(value = "eventManagerId") int eventManagerId, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEventManager";
		}

		eventManagerService.updateEventManager(eventManager);

		redirectAttributes.addFlashAttribute("success", eventManager.getEventManagerName() + " updated successfully");
		return "redirect:/listEventManagers";
	}

	/**
	 * 
	 * @param eventManagerId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/delete-{eventManagerId}-eventManager" }, method = RequestMethod.GET)
	public String deleteEventManager(@PathVariable(value = "eventManagerId") int eventManagerId,
			RedirectAttributes redirectAttributes) {
		EventManager eventManager = eventManagerService.findById(eventManagerId);
		eventManagerService.deleteEventManagerById(eventManagerId);
		redirectAttributes.addFlashAttribute("success", eventManager.getEventManagerName() + "  removed");
		return "redirect:/listEventManagers";
	}

}

package com.ssdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdi.Create;
import com.ssdi.entity.Plan;
import com.ssdi.entity.User;
import com.ssdi.service.PlanService;

@RestController
public class PlanController {

	PlanService planService;
	private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@RequestMapping(value = "/savePlan", method = RequestMethod.POST)
	public Plan AddPlans(@Validated(Create.class) @RequestBody Plan plan) {
		/*if(null != plan.getDateTravel() || plan.getDateTravel().toString() == ""){*/
		
		if(plan.getDateTravel() != null || plan.getDateTravel().toString() != ""){
			System.out.println("Inside If"+plan.getDateTravel());
			plan.setDateTravel(new java.sql.Date(plan.getDateTravel().getTime()));
		//plan.setDateTravel(new java.sql.Date(new Date().getTime()));
		}else{
			System.out.println("Else loop"+plan.getDateTravel());
			plan.setDateTravel(new java.sql.Date(new Date().getTime()));
		}
		plan.setDateAdded(new java.sql.Date(new Date().getTime()));
		return planService.addPlan(plan);
	}
	
	@RequestMapping(value = "/plan", method = RequestMethod.POST)
	public List<Plan> GetPlans(@Validated(Create.class) @RequestBody Plan plan){
		System.out.println("fetch plans************");
		return planService.getPlansById(plan.getUserId());
	}
	
	
	
}

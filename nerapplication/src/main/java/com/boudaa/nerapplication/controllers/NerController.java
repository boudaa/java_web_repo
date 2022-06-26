package com.boudaa.nerapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boudaa.nerapplication.entities.NamedEntity;
import com.boudaa.nerapplication.nlpservices.INerService;

@RestController
@RequestMapping("/api/nlp/")
public class NerController {

	@Autowired
	private INerService nerService;

	@GetMapping("/{text}")
	public List<NamedEntity> getNamedEntities(@PathVariable("text") String text) {
		return nerService.getNamedEntites(text);
	}

}
package com.boudaa.nerapplication.nlpservices;

import java.util.List;

import com.boudaa.nerapplication.entities.NamedEntity;

public interface INerService {
	public List<NamedEntity> getNamedEntites(String textToAnalyze) ;
}

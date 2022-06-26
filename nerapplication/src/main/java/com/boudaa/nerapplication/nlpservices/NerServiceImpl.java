package com.boudaa.nerapplication.nlpservices;

import edu.stanford.nlp.pipeline.*;

import java.util.*;

import org.springframework.stereotype.Service;
import org.wikidata.wdtk.datamodel.helpers.Datamodel;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.wikibaseapi.BasicApiConnection;
import org.wikidata.wdtk.wikibaseapi.WbSearchEntitiesResult;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;

import com.boudaa.nerapplication.entities.NamedEntity;

/**
 * This service, extracts the list of named entities from a given text and adds
 * a description of each one from wikidata
 * 
 * @author T. BOUDAA
 *
 */
@Service
public class NerServiceImpl implements INerService {

	private StanfordCoreNLP pipeline;
	private WikibaseDataFetcher wbdf;

	public NerServiceImpl() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		props.setProperty("ner.applyFineGrained", "false");
		pipeline = new StanfordCoreNLP(props);
		wbdf = new WikibaseDataFetcher(BasicApiConnection.getWikidataApiConnection(), Datamodel.SITE_WIKIDATA);
	}

	/**
	 * extracts all named entities from the text given as a parameter to the method
	 * 
	 * @param textToAnalyze the text to analyze
	 * @return the list containing the extracted named entities
	 */
	public List<NamedEntity> getNamedEntites(String textToAnalyze) {

		CoreDocument doc = new CoreDocument(textToAnalyze);
		// annotate the text
		pipeline.annotate(doc);

		List<NamedEntity> namedEntities = new ArrayList<NamedEntity>();

		for (CoreEntityMention em : doc.entityMentions()) {
			NamedEntity ne = new NamedEntity();
			ne.setText(em.text());
			ne.setType(em.entityType());
			addDescription(ne);
			namedEntities.add(ne);
		}
		
		return namedEntities;

	}

	/**
	 * extract a description of a named entity from wikidata
	 * 
	 * @param ne a named entity object
	 */
	private void addDescription(NamedEntity ne) {
		try {
			List<WbSearchEntitiesResult> searchResults = wbdf.searchEntities(ne.getText());
			if (!searchResults.isEmpty()) {
				WbSearchEntitiesResult firstRes = searchResults.get(0);
				ItemDocument entityDoc = (ItemDocument) wbdf.getEntityDocument(firstRes.getTitle());
				if (entityDoc.getDescriptions().get("en") != null) {
					ne.setDescription(entityDoc.getDescriptions().get("en").getText());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NerException("can't fetch the description from wikidata :" + ne.getText(), ex);
		}
	}

}
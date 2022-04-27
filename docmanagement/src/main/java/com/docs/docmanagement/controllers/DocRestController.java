package com.docs.docmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docs.docmanagement.bo.Document;
import com.docs.docmanagement.services.DocumentMngService;

@RestController
@RequestMapping("/api/documents")
public class DocRestController {

	@Autowired
	private DocumentMngService docServices;

	@GetMapping
	public List<Document> getAllDocuments() {
		return docServices.getAllDocs();
	}

	@PostMapping
	public Document createDoc(@RequestBody Document doc) {
		doc.setIdDoc(null);
		return docServices.saveDoc(doc);
	}

	@PutMapping
	public Document updateDoc(@RequestBody Document doc) {
		docServices.updateDoc(doc);
		return doc;
	}

	@GetMapping("/titles/{title}") 
	public List<Document> findDocByTitle(@PathVariable String title) {
		return docServices.findDocByTitlre(title);
	}

	@GetMapping("/{idDoc}")
	public Document getDocById(@PathVariable Long idDoc) {
		return docServices.findDocById(idDoc).get();
	}

	@DeleteMapping("/{idDoc}")
	public void deleteDocById(@PathVariable Long idDoc) {
		docServices.deleteDoc(idDoc);
	}

}

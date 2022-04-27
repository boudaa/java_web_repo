package com.docs.docmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docs.docmanagement.bo.Document;
import com.docs.docmanagement.dao.DocumentRepository;

@Service
@Transactional
public class DocumentMngServiceImpl implements DocumentMngService {

	@Autowired
	private DocumentRepository docRepo;

	@Override
	public Document saveDoc(Document doc) {
		return docRepo.save(doc);
	}

	@Override
	public void updateDoc(Document doc) {
		docRepo.save(doc);
	}

	@Override
	public void deleteDoc(Long idDoc) {
		docRepo.delete(findDocById(idDoc).get());
	}

	@Override
	public Optional<Document> findDocById(Long idDoc) {
		return docRepo.findById(idDoc);
	}

	@Override
	public List<Document> findDocByTitlre(String title) {
		return docRepo.getDocumentByTitle(title);
	}

	@Override
	public List<Document> getAllDocs() {
		return docRepo.findAll();
	}

}

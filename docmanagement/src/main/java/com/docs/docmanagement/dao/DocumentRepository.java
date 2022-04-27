package com.docs.docmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docs.docmanagement.bo.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	public List<Document> getDocumentByTitle(String title);

}

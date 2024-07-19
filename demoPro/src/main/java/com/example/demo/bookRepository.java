package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface bookRepository extends JpaRepository<BookInfo, Long>{
	
	Optional<BookInfo> findByIsbn(String isbn);

	List<BookInfo> findAllByTitleOrderByPublishedDate(String title);
	List<BookInfo> findAllByOrderByPublishedDateDesc();
	List<BookInfo> findAllByTitleContaining(String keyword);
	List<BookInfo> findAllByTitleContainingOrderByPublishedDateDesc(String keyword);
	
	Page<BookInfo> findAll(Pageable pageable);

	@Transactional
	Long deleteByNum(Long num);
	
	//Page<BookInfo> findByContentContaining(String keyword);
}


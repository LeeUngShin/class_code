package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{  // extends JpaRepository<엔티티클래스, 기본키타입>
	
    Optional<Memo> findByIdAndPw(String Id, String pw);
    
    Optional<Memo> findById(String id);
    
    Long countByIdAndPw(String Id, String pw);
        
    @Transactional
    Long deleteById(String id);

}

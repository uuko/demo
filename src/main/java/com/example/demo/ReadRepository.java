package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//對象類型 跟 id屬性類型
public interface ReadRepository extends JpaRepository<Book,Long> {

    List<Book> findByReader(String reader);
}

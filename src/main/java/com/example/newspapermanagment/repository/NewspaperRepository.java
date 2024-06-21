package com.example.newspapermanagment.repository;

import com.example.newspapermanagment.entity.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper,Long> {
}

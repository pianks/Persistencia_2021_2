package com.example.Trabalho_02.dao;

import com.example.Trabalho_02.entity.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacaoDAO extends JpaRepository<Associacao, Integer> {
}

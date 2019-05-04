package br.com.appProposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appProposta.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

}

package com.mnishida.apinovaxs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mnishida.apinovaxs.model.Pokemon;

/**
  É possível usar metodos do JPARepository sem implementá-los, como save(), 
  findById(), findAll(), delete(), etc.
 */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
  Usuario findByNum(long num);

}

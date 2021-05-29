package com.mnishida.apinovaxs.controller;

import java.util.ArrayList;
import java.util.List;

import com.mnishida.apinovaxs.model.Pokemon;
import com.mnishida.apinovaxs.repository.PokemonRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
		Apenas durante o desenvolvimento, requisições de qualquer origem serão processadas pelo back-end.
		Porém, em produção, é recomendável fazer o mesmo. É possível limitar as origens das requisições
		utilizando @CrossOrigin(origins = "http://localhost:3000"), por exemplo.
 */
@CrossOrigin
@RestController
public class PokemonController {

	@Autowired
	PokemonRepository pokemonRepository;

	@GetMapping("/pokemons")
	public ResponseEntity<List<Pokemon>> getAllPokemon() {
		try {
			List<Pokemon> pokemons = new ArrayList<Pokemon>();

      //Teste se há algum elemento na array. Caso não haja
			if (pokemons.isEmpty()) {
        //Retorna que não há nenhum conteúdo pelo status do HTTP
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
      //Caso haja, retorna o status de OK e a array com os pokemons
			return new ResponseEntity<>(pokemons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pokemons/{num}")
	public ResponseEntity<Pokemon> getPokemonByNum(@PathVariable("num") long num) {
		Pokemon pokemonByCodigo = pokemonRepository.findByNum(num);

    //Se foi retornado algum Pokemon
		if (pokemonByCodigo != null) {
			return new ResponseEntity<>(pokemonByCodigo, HttpStatus.OK);
		} else {
      //Se não, retorna erro
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pokemon")
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
    try {
			Pokemon _pokemon = pokemonRepository.save(new Pokemon(pokemon.getNum(), pokemon.getName(), pokemon.getTypes(), pokemon.getNext_evol()));
			return new ResponseEntity<>(_pokemon, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pokemon/{num}")
	public ResponseEntity<Pokemon> updatePokemon(
    @PathVariable("num") long num, 
    @RequestBody Pokemon pokemon //Pokemon com as alterações a serem feitas
    ) {
		Pokemon pokemonByNum = pokemonRepository.findByNum(num);

    //Se há um pokemon com aquele num
		if (pokemonByNum != null) {
			pokemonByNum.setName(pokemon.getName());
			pokemonByNum.setType(pokemon.getGetTypes());
			pokemonByNum.setNext_evol(usuario.getNext_evol());
			return new ResponseEntity<>(pokemonRepository.save(pokemonByNum), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/pokemon/{num}")
	public ResponseEntity<HttpStatus> deletePokemon(@PathVariable("num") long num) {
    Usuario pokemonByNum = pokemonRepository.findByNum(num);

    //Se existe um pokemon com aquele num para ser deletado
    if(pokemonByNum != null){
      try {
        pokemonRepository.delete(pokemonByNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    //Se não
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	}
}

package br.com.brenonoccioli.pokedex.pokemon.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    //@NotNull
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Tipo.class)
    @Column(nullable = false)
    private Set<Tipo> tipo = new HashSet<Tipo>();

    @OneToOne //Optei pela relação apenas com a evolução imediata
    @ElementCollection(targetClass = Pokemon.class)
    private Pokemon evoluiPara;

    @Deprecated //apenas para uso do framework
    public Pokemon() {
    }

    public Pokemon(String nome, Set<Tipo> tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    //Optei pela sobrecarga pela não obrigatoriedade de uma evolução
    public Pokemon(String nome, Set<Tipo> tipo, Pokemon evoluiPara) {
        this.nome = nome;
        this.tipo = tipo;
        this.evoluiPara = evoluiPara;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<Tipo> getTipo() {
        return tipo;
    }

    public Pokemon getEvoluiPara() {
        return evoluiPara;
    }

    //Para definir uma evolução após o cadastramento
    public void atualizaEvoluiPara(Pokemon evoluiPara) {
        this.evoluiPara = evoluiPara;
    }

    public void atualizaNome(String novoNome) {
        this.nome = novoNome;
    }
}

package br.com.brenonoccioli.pokedex.annotations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdExistsValidator implements ConstraintValidator<IdExists, Object> {
    private String domainField;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(IdExists object) {
        domainField = object.field();
        clazz = object.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("select 1 from "+clazz.getName()+
                " where "+domainField+" =:value");
        query.setParameter("value", value);
        List<?> lista = query.getResultList();
        Assert.state(lista.size()<=1, "O id "+value+" informado não foi encontrado");
        return !lista.isEmpty() || value == null;
    }
}

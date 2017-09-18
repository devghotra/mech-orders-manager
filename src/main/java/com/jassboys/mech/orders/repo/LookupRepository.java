package com.jassboys.mech.orders.repo;

import com.jassboys.mech.orders.model.Lookup;
import org.springframework.data.repository.CrudRepository;

public interface LookupRepository extends CrudRepository<Lookup, Long>{

    Lookup findByTypeAndValue(String type, String val);

}

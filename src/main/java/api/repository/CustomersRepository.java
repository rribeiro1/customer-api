package api.repository;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rx.Observable;

@Repository
public class CustomersRepository {

    private final Mapper<Customer> mapper;

    @Autowired
    public CustomersRepository(MappingManager manager){
        this.mapper = Objects.requireNonNull(manager.mapper(Customer.class));
    }

    public Observable<Customer> findAll() {
        ResultSetFuture rs = mapper.getManager().getSession().executeAsync("select * from clientes");
        return Observable.from(rs).map(mapper::map).map(Result::all).flatMap(Observable::from);
    }
}

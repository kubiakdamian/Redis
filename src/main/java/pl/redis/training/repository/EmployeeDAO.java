package pl.redis.training.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.redis.training.model.Person;

import javax.annotation.Resource;
import java.util.Map;

@Repository
@Transactional
public class EmployeeDAO {
    private static final String KEY = "employees";

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, Person> hashOps;

    public void addEmployee(Person person){
        hashOps.putIfAbsent(KEY, person.getId(), person);
    }

    public Person getEmployee(Long id){
        return hashOps.get(KEY, id);
    }

    public Map<Long, Person> getAllEmployees(){
        return hashOps.entries(KEY);
    }
}

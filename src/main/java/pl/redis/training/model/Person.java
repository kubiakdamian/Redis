package pl.redis.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Person")
@Data
@AllArgsConstructor
public class Person implements Serializable {
    private Long id;
    private String name;
    private String lastName;
}

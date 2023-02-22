package nttdata.bootcamp.quarkus.audit.application;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.string.StringCommands;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PersonTypeService {

    private ReactiveKeyCommands<String> keys;
    private StringCommands<String, Integer> counter;

    public PersonTypeService(RedisDataSource redisDS, ReactiveRedisDataSource reactiveRedisDS) {
        keys = reactiveRedisDS.key();
        counter = redisDS.string(Integer.class);
    }

    public Uni<Void> del(String key) {
        return keys.del(key)
                .replaceWithVoid();
    }

    public int get(String key) {
        return counter.get(key);
    }

    public void set(String key, int value) {
        counter.set(key, value);
    }

    public void personType(String key, int personTypeBy) {
        counter.incrby(key, personTypeBy);
    }

    public Uni<List<String>> keys() {
        return keys
                .keys("*");
    }
}

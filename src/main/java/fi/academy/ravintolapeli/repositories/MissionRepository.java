package fi.academy.ravintolapeli.repositories;

import fi.academy.ravintolapeli.objects.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MissionRepository extends MongoRepository<Mission, String> {

    public List<Mission> findAllByStoryIsContaining(String word);
}


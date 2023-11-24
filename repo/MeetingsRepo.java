package sgsits.cse.dis.administration.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sgsits.cse.dis.administration.models.Meetings;

import java.util.Optional;

@Repository
public interface MeetingsRepo extends JpaRepository<Meetings, String> {
    //Optional<Meetings> save(Meetings meetings);
}

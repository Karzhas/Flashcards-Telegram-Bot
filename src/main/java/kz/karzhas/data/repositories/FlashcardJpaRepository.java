package kz.karzhas.data.repositories;

import kz.karzhas.data.dto.FlashcardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardJpaRepository extends JpaRepository<FlashcardDto, Long> {


}

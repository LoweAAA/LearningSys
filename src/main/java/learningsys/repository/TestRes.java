package learningsys.repository;


import learningsys.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRes extends JpaRepository<Test,Integer> {

}

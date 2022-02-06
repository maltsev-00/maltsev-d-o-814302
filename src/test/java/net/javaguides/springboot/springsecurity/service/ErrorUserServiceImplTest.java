package net.javaguides.springboot.springsecurity.service;


import net.javaguides.springboot.springsecurity.model.entity.InfoFile;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import net.javaguides.springboot.springsecurity.repository.PathFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ErrorUserServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PathFileRepository pathFileRepository;

    @Test
    public void whenFindAll() {
        //given
        PathFile pathFile = new PathFile();
        pathFile.setPath("/path");
        pathFile.setPrivacy(false);
        pathFile.setId(2L);
        pathFile.setInfoFile(new InfoFile());

        //when
        List<PathFile> pathFiles = pathFileRepository.findAll();

        //then
        assertThat(pathFiles.size()).isEqualTo(1);
        assertThat(pathFiles.get(1)).isEqualTo(pathFile);
    }

}
package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Comentario;
import org.iesvdm.videoclub.domain.Tutorial;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoclubApplicationTests {
    @Autowired
    TutorialRepository tutorialRepository;

    @Test
    void contextLoads() {
    }
    @Test
    void PruebaOneToManyTutorial(){
        /*
        Tutorial tutorial2  = Tutorial.builder().titulo("Prueba").publicado(true).descripcion("Prueba").build();
        Comentario comentario1 = Comentario.builder().texto("Comentario1").build();
        Comentario comentario1 = Comentario.builder().texto("Comentario2").build();

        var tutorialList = tutorialRepository.findAll();
        tutorialList.forEach(tutorial -> System.out.println(tutorial));
        System.out.println(tutorial2.toString());
        */
    }

}

package com.b2w.poc;

import com.b2w.poc.api.document.planet;
import com.b2w.poc.api.repository.iplanetRepository;
import com.b2w.poc.api.responses.response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.type.NullType;
import javax.swing.border.EmptyBorder;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class planetControllerTest {

    @MockBean
    private iplanetRepository planetRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void buscarPlanetaPorIdExiste() {
        // given
        given(planetRepository.findById("5d88312a0510913b4409876c"))
                .willReturn(java.util.Optional.of(new planet("5d88312a0510913b4409876c", "Alderaan", "temperate", "grasslands, mountains", 2)));

        // when
        ResponseEntity<planet> planetResponse = restTemplate.getForEntity("/api/v1/planet/5d88312a0510913b4409876c", planet.class);

        // then
         assertThat(planetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planetResponse.getBody().equals(new planet("5d88312a0510913b4409876c", "Alderaan", "temperate", "grasslands, mountains", 2)));
    }

    @Test
    public void buscarPlanetaPorIdNaoExiste() {
        // given
        given(planetRepository.findById("5d88312a05109"))
                .willReturn(java.util.Optional.of(new planet()));

        // when
        ResponseEntity<planet> planetResponse = restTemplate.getForEntity("/api/v1/planet/5d88312a05109", planet.class);

        // then
        assertThat(planetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planetResponse.getBody().toString().equals("{\"data\":null,\"erros\":null}"));
    }

    @Test
    public void buscarPlanetaPorNome() {
        // given
        given(planetRepository.listarPorNome("Tatooine"))
                .willReturn(java.util.Optional.of(new planet("5d88312d0510913b440987a7", "Tatooine", "arid", "desert", 5)));

        // when
        ResponseEntity<planet> planetResponse = restTemplate.getForEntity("/api/v1/planet/name/Tatooine", planet.class);

        // then
        assertThat(planetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(planetResponse.getBody().equals(new planet("5d88312d0510913b440987a7", "Tatooine", "arid", "desert", 5)));
    }

    @Test
    public void CadastrandoNovoPlaneta() {
        // when
        ResponseEntity<planet> planetResponse = restTemplate.postForEntity("/api/v1/planet/",
                new planet("Rogerio", "temperate", "grasslands, mountains", 0), planet.class);

        // then
        assertThat(planetResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}

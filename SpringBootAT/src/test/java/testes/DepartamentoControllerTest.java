package testes;

import com.example.springbootat.controller.DepartamentoController;
import com.example.springbootat.model.Departamento;
import com.example.springbootat.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DepartamentoControllerTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoController departamentoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDepartamentos() {
        Departamento deptoTeste1 = new Departamento("TI", "Belo Horizonte");
        Departamento deptoTeste2 = new Departamento("Recursos Humanos", "Rio de Janeiro");
        List<Departamento> departamentos = Arrays.asList(deptoTeste1, deptoTeste2);

        when(departamentoRepository.findAll()).thenReturn(departamentos);

        ResponseEntity<List<Departamento>> response = departamentoController.getAllDepartamentos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(departamentos);
    }

    @Test
    public void testCreateDepartamento() {
        Departamento novoDepartamento = new Departamento("Logística", "São Paulo");

        when(departamentoRepository.save(any(Departamento.class))).thenReturn(novoDepartamento);

        Departamento departamentoSalvo = departamentoController.addDepartamento(novoDepartamento);
        assertThat(departamentoSalvo).isNotNull();
        assertThat(departamentoSalvo.getNome()).isEqualTo("Logística");
        assertThat(departamentoSalvo.getLocal()).isEqualTo("São Paulo");
    }
}

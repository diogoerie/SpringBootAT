package testes;

import com.example.springbootat.controller.FuncionarioController;
import com.example.springbootat.model.Funcionario;
import com.example.springbootat.repository.FuncionarioRepository;
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

public class FuncionarioControllerTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFuncionarios() {
        Funcionario funcionarioteste1 = new Funcionario("João", "Rua A", "123456789", "joao@example.com");
        Funcionario funcionarioteste2 = new Funcionario("Maria", "Rua B", "987654321", "maria@example.com");
        List<Funcionario> funcionarios = Arrays.asList(funcionarioteste1, funcionarioteste2);

        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        ResponseEntity<List<Funcionario>> response = funcionarioController.getAllFuncionarios();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(funcionarios);
    }
    @Test
    public void testCreateFuncionario() {
        Funcionario novoFuncionario = new Funcionario("Pedro", "Rua C", "111222333", "pedro@example.com");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(novoFuncionario);

        Funcionario funcionarioSalvo = funcionarioController.addFuncionario(novoFuncionario);
        assertThat(funcionarioSalvo).isNotNull();
        assertThat(funcionarioSalvo.getNome()).isEqualTo("Pedro");
        assertThat(funcionarioSalvo.getEndereco()).isEqualTo("Rua C");
        assertThat(funcionarioSalvo.getTelefone()).isEqualTo("111222333");
        assertThat(funcionarioSalvo.getEmail()).isEqualTo("pedro@example.com");
    }

}

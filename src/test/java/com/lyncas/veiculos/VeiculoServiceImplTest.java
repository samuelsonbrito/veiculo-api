package com.lyncas.veiculos;

import com.lyncas.veiculos.application.service.VeiculoServiceImpl;
import com.lyncas.veiculos.application.usecase.command.AdicionarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.command.AtualizarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.query.VeiculoResult;
import com.lyncas.veiculos.domain.entity.Veiculo;
import com.lyncas.veiculos.domain.enumerator.Marca;
import com.lyncas.veiculos.domain.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("VeiculoServiceImpl")
class VeiculoServiceImplTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoServiceImpl service;

    private Veiculo veiculoMock;

    @BeforeEach
    void setUp() {
        veiculoMock = new Veiculo(1L, "Sedan completo", Marca.TOYOTA, "Corolla XEi", "Ar, Direção", new BigDecimal("89900.00"));
    }

    @Test
    @DisplayName("deve adicionar um veículo com sucesso")
    void deveAdicionarVeiculo() {
        var command = new AdicionarVeiculoCommand("Sedan completo", Marca.TOYOTA, "Corolla XEi", "Ar, Direção", new BigDecimal("89900.00"));
        when(veiculoRepository.save(any())).thenReturn(veiculoMock);

        VeiculoResult result = service.adicionar(command);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.descricao()).isEqualTo("Sedan completo");
        assertThat(result.marca()).isEqualTo(Marca.TOYOTA);
        verify(veiculoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("deve atualizar veículo existente com sucesso")
    void deveAtualizarVeiculo() {
        var command = new AtualizarVeiculoCommand(1L, "Sedan atualizado", Marca.HONDA, "Civic EX", null, null);
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoMock));
        when(veiculoRepository.save(any())).thenReturn(new Veiculo(1L, "Sedan atualizado", Marca.HONDA, "Civic EX", null, null));

        VeiculoResult result = service.atualizar(command);

        assertThat(result.descricao()).isEqualTo("Sedan atualizado");
        assertThat(result.marca()).isEqualTo(Marca.HONDA);
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar veículo inexistente")
    void deveLancarExcecaoAoAtualizarVeiculoInexistente() {
        var command = new AtualizarVeiculoCommand(99L, "X", Marca.FORD, "Fiesta", null, null);
        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar(command))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("deve obter veículo por id com sucesso")
    void deveObterVeiculoPorId() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoMock));

        VeiculoResult result = service.obterPorId(1L);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.modelo()).isEqualTo("Corolla XEi");
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar veículo inexistente")
    void deveLancarExcecaoAoBuscarInexistente() {
        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obterPorId(99L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("deve listar veículos paginados")
    void deveListarVeiculos() {
        Page<Veiculo> page = new PageImpl<>(List.of(veiculoMock));
        when(veiculoRepository.findAll(any())).thenReturn(page);

        Page<VeiculoResult> result = service.listar(PageRequest.of(0, 10));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).id()).isEqualTo(1L);
    }

    @Test
    @DisplayName("deve excluir veículo com sucesso")
    void deveExcluirVeiculo() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(veiculoRepository).deleteById(1L);

        assertThatCode(() -> service.excluir(1L)).doesNotThrowAnyException();
        verify(veiculoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("deve lançar exceção ao excluir veículo inexistente")
    void deveLancarExcecaoAoExcluirInexistente() {
        when(veiculoRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> service.excluir(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }
}

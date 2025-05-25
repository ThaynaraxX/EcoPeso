package br.com.ascamaras.Repositories;

import br.com.ascamaras.Model.PesagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PesagemRepository extends JpaRepository<PesagemModel, Long> {

    // Busca por intervalo de datas e nome do material (contém, ignorando maiúsculas/minúsculas)
    List<PesagemModel> findByDataPesagemBetweenAndMaterial_NomeContainingIgnoreCase(
            OffsetDateTime dataInicial,
            OffsetDateTime dataFinal,
            String tipoMaterial);

    // Busca por intervalo de datas
    List<PesagemModel> findByDataPesagemBetween(
            OffsetDateTime dataInicial,
            OffsetDateTime dataFinal);

    // Busca por intervalo de peso
    List<PesagemModel> findByPesoBetween(Float pesoMin, Float pesoMax);

    // Pesagem com peso mínimo
    List<PesagemModel> findByPesoGreaterThanEqual(Float pesoMin);

    // Pesagem com peso máximo
    List<PesagemModel> findByPesoLessThanEqual(Float pesoMax);
}

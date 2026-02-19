package br.com.oficina.orcamento.adapter.in.controller;

import br.com.oficina.orcamento.core.port.in.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.oficina.orcamento.adapter.in.controller.response.IdResponse;
import br.com.oficina.orcamento.adapter.in.controller.mapper.OrcamentoControllerMapper;
import br.com.oficina.orcamento.adapter.in.controller.request.CriarOrcamentoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/orcamentos")
@RequiredArgsConstructor
@Tag(name = "Orçamentos", description = "Gerenciamento dos orçamentos")
public class OrcamentoController {

    private static final Logger logger = LoggerFactory.getLogger(OrcamentoController.class);

    private final CriarOrcamentoUseCase criarOrcamentoUseCase;
    private final AprovarOrcamentoUseCase aprovarOrcamentoUseCase;
    private final RecusarOrcamentoUseCase recusarOrcamentoUseCase;
    private final BuscarOrcamentoPorIdUseCase buscarOrcamentoPorIdUseCase;
    private final EnviarOrcamentoPdfUseCase enviarOrcamentoPdfUseCase;
    private final OrcamentoControllerMapper mapper;

    @Operation(summary = "Criar novo orçamento", description = "Cria um novo orçamento com peças e serviços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orçamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<?> criarOrcamento(
            @Parameter(description = "Dados para criação do orçamento")
            @RequestBody @Valid CriarOrcamentoRequest request) {
        logger.info("Recebida requisição para criar orçamento: {}", request);
        var input = mapper.toInput(request);
        var orcamentoId = criarOrcamentoUseCase.executar(input);
        logger.info("Orçamento ID {} criado com sucesso.", orcamentoId);
        return ResponseEntity.created(URI.create("/orcamentos/" + orcamentoId)).body(new IdResponse(orcamentoId));
    }

    @Operation(summary = "Aceitar orçamento", description = "Marca o orçamento como aceito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Orçamento aceito com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado")
    })
    @PostMapping("/{id}/aceitos")
    public ResponseEntity<Void> aceitarOrcamento(
            @Parameter(description = "ID do orçamento")
            @PathVariable UUID id) {
        logger.info("Recebida requisição para aceitar orçamento ID: {}", id);
        aprovarOrcamentoUseCase.executar(id);
        logger.info("Orçamento ID {} aceito com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Recusar orçamento", description = "Marca o orçamento como recusado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Orçamento recusado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado")
    })
    @PostMapping("/{id}/recusados")
    public ResponseEntity<Void> recusarOrcamento(
            @Parameter(description = "ID do orçamento")
            @PathVariable UUID id) {
        logger.info("Recebida requisição para recusar orçamento ID: {}", id);
        recusarOrcamentoUseCase.executar(id);
        logger.info("Orçamento ID {} recusado com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Buscar orçamento por ID", description = "Retorna os detalhes do orçamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orçamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarOrcamento(
            @Parameter(description = "ID do orçamento")
            @PathVariable UUID id) {
        logger.info("Recebida requisição para buscar orçamento ID: {}", id);
        var orcamento = buscarOrcamentoPorIdUseCase.executar(id);
        logger.info("Orçamento ID {} encontrado e retornado.", id);
        return ResponseEntity.ok(mapper.toResponse(orcamento));
    }
    @Operation(summary = "Enviar orçamento em PDF", description = "Gera e envia o orçamento em formato PDF para download")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF do orçamento gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado")
    })
    @GetMapping("/{id}/envios")
    public ResponseEntity<?> enviarOrcamento(
            @Parameter(description = "ID do orçamento")
            @PathVariable UUID id) {
        logger.info("Recebida requisição para enviar PDF do orçamento ID: {}", id);
        var pdfBytes = enviarOrcamentoPdfUseCase.executar(id);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("orcamento.pdf").build());
        logger.info("PDF do orçamento ID {} gerado e enviado com sucesso.", id);
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }}
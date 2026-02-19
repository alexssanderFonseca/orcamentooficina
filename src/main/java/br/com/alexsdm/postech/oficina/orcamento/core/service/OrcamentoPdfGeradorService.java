package br.com.alexsdm.postech.oficina.orcamento.core.service;

import br.com.alexsdm.postech.oficina.orcamento.core.entity.Orcamento;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.inject.Named;
import java.io.ByteArrayOutputStream;

@Named
public class OrcamentoPdfGeradorService implements PdfGenerator {

    public byte[] generate(Orcamento orcamento) {
        try {
            var baos = new ByteArrayOutputStream();
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            adicionarCabecalho(document);
            document.add(new Paragraph(" "));
            adicionarDadosClienteEVeiculo(orcamento, document);
            document.add(new Paragraph(" "));
            adicionarItens(orcamento, document);
            document.add(new Paragraph(" "));
            adicionarSumario(orcamento, document);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private void adicionarCabecalho(Document document) throws DocumentException {
        var tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        var titulo = new Paragraph("ORÇAMENTO", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
    }

    private void adicionarDadosClienteEVeiculo(Orcamento orcamento, Document document) throws DocumentException {
        var cliente = orcamento.getCliente();
        var veiculo = orcamento.getVeiculo();
        document.add(new Paragraph("Cliente: " + cliente.getNome()));
        document.add(new Paragraph("CPF/CNPJ: " + cliente.getDocumento()));
        document.add(new Paragraph("Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo()));
        document.add(new Paragraph("Placa: " + veiculo.getPlaca()));
    }

    private void adicionarItens(Orcamento orcamento, Document document) throws DocumentException {
        var itens = new PdfPTable(4);
        itens.setWidthPercentage(100);
        itens.addCell("Item");
        itens.addCell("Qtd");
        itens.addCell("Valor Unitário");
        itens.addCell("Subtotal");
        orcamento.getItens().forEach(item -> {
            itens.addCell(item.getNome());
            itens.addCell(String.valueOf(item.getQuantidade()));
            itens.addCell("R$ " + item.getPreco());
            itens.addCell("R$ " + item.getTotal());
        });
        document.add(itens);
    }

    private void adicionarSumario(Orcamento orcamento, Document document) throws DocumentException {
        document.add(new Paragraph("Valor Total: R$ " + orcamento.getValorTotal()));
        document.add(new Paragraph("Status: " + orcamento.getStatus()));
    }
}
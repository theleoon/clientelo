package br.com.alura.clientelo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.clientelo.model.Cliente;
import br.com.alura.clientelo.model.Endereco;

public class ClienteDto {

	private Long id;
	private String nome;
	private String cpf;
	private String telefone;

	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;

	public ClienteDto(Cliente cliente) {
		this.nome = cliente.getNome();
		this.id = cliente.getId();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.rua = cliente.getRua();
		this.numero = cliente.getNumero();
		this.complemento = cliente.getComplemento();
		this.bairro = cliente.getBairro();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
	}

	public ClienteDto(Long id, String nome, String cpf, String telefone, String rua, String numero, String complemento,
			String bairro, String cidade, String estado) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public static Cliente converter(ClienteDto clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getCpf(), clienteDto.getTelefone(),
				new Endereco(clienteDto.getRua(), clienteDto.getNumero(), clienteDto.getComplemento(),
						clienteDto.getBairro(), clienteDto.getCidade(), clienteDto.getEstado()));
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		List<ClienteDto> listaDto = new ArrayList<>();
		clientes.forEach(c -> {
			listaDto.add(new ClienteDto(c));
		});

		return listaDto;
	}

	public static ClienteDto converter(Cliente obj) {
		return new ClienteDto(obj.getId(), obj.getNome(), obj.getCpf(), obj.getTelefone(), obj.getRua(),
				obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCidade(), obj.getEstado());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

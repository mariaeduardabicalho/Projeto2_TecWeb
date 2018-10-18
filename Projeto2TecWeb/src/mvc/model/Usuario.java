package mvc.model;

public class Usuario {
	
	private Integer id;
	private String primeiro_nome;
	private String sobrenome;
	private String email;
	private String tipo;
	private String usuario;
	private String senha;

	public Integer getId() {return this.id;}
	public void setId(Integer id) {this.id = id;}
	
	public String getPrimeiro_nome() {return this.primeiro_nome;}
	public void setPrimeiro_nome(String primeiro_nome) {this.primeiro_nome = primeiro_nome;}
	
	public String getSobrenome() {return this.sobrenome;}
	public void setSobrenome(String sobrenome) {this.sobrenome = sobrenome;}
	
	public String getEmail() {return this.email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getTipo() {return this.tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}
	
	public String getUsuario() {return this.usuario;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
	
	public String getSenha() {return this.senha;}
	public void setSenha(String senha) {this.senha = senha;}

	
}

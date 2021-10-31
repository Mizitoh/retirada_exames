package cadernoazul.modelo;

import java.util.Date;

public class Entrega {

		private int codigo;
		private Date data;
		private String exame;
		private String nome;
		private String recebedor;
		
		
		
		public Entrega() {
			super();
		}

		public Entrega(int codigo, Date data, String exame, String nome, String recebedor) {
			super();
			this.codigo = codigo;
			this.data = data;
			this.exame = exame;
			this.nome = nome;
			this.recebedor = recebedor;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public String getExame() {
			return exame;
		}

		public void setExame(String exame) {
			this.exame = exame;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getRecebedor() {
			return recebedor;
		}

		public void setRecebedor(String recebedor) {
			this.recebedor = recebedor;
		}
		
	}

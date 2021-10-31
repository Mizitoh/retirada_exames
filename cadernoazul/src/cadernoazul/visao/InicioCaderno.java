package cadernoazul.visao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cadernoazul.conexao.DAO;
import cadernoazul.modelo.Entrega;

public class InicioCaderno {

	public static void main(String[] args) throws SQLException, ParseException{
		
		int parametro = -1;
		
		System.out.println("Bem vindo ao cadastro de retirada de exames e consultas!");

		do {
		System.out.println("\nO que você deseja fazer? \n "
					+ "\n1 - Registrar retirada."
					+ "\n2 - Listar retiradas."
					+ "\n0 - para sair do sistema");
		
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		int digitado = teclado.nextInt();		
			
		switch (digitado) {
		case 1:{
			
			System.out.println("Digite o tipo de exame: \n");
			String exm = teclado.next();
			
			System.out.println("Digite o nome do paciente: \n");
			String nome = teclado.nextLine();
			
			System.out.println("Digite nome de quem retirou: \n");
			String retirada = teclado.nextLine();
			
			String sql = "INSERT INTO entrega (data, exame, nome, recebedor) VALUES (?, ?, ?, ?)";
			
			DAO dao = new DAO();
			Date date = new Date();
			String dt = new SimpleDateFormat("yyyy-MM-dd").format(date);
			dao.incluir(sql, dt, exm, nome, retirada);
			
			dao.fecha();
			System.out.println("Cadastro salvo com sucesso!");
			
			parametro = -1;
			break;
		}
		
		case 2: {
			DAO dao = new DAO();
			ResultSet resultado = dao.ExecuteSQL("SELECT * FROM entrega");
			List<Entrega> entregas = new ArrayList<>();
			
			
			while(resultado.next()) {
				int codigo = resultado.getInt("codigo");
				Date data = resultado.getDate("data");
				String exame = resultado.getString("exame");
				String nome = resultado.getString("nome");
				String recebedor = resultado.getString("recebedor");
				entregas.add(new Entrega(codigo, data, exame, nome, recebedor));
			}
			
			for(Entrega e : entregas) {
				System.out.println(e.getCodigo() + " -> "+ new SimpleDateFormat("dd-MM-yyyy").format(e.getData())
				+ " -> "+ e.getExame()+ " -> "+ e.getNome()+ " -> "+ e.getRecebedor());
			}
			
			dao.fecha();

			parametro = -1;
			
			break;
		}
		default:
			parametro = 0;
			System.out.println("Você saiu do sistema");
			break;
		}
	} while (parametro != 0);

}
}

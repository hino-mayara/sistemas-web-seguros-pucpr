package sisrh.soap;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;

import seguranca.Autenticador;
import sisrh.banco.Banco;
import sisrh.dto.Solicitacao;
import sisrh.dto.Solicitacoes;

@WebService
@SOAPBinding(style = Style.RPC)
public class ServicoSolicitacao {
	
	@Resource
	WebServiceContext context;

	@WebMethod(action = "listar")
	public Solicitacoes listar() throws Exception {

		Autenticador.autenticarUsuarioSenha(context);
		
		String usuario = Autenticador.getUsuario(context);

		Solicitacoes solicitacoes = new Solicitacoes();
		List<Solicitacao> lista = Banco.listarSolicitacoes(usuario);
		for (Solicitacao emp : lista) {
			solicitacoes.getSolicitacoes().add(emp);
		}
		return solicitacoes;
	}

}

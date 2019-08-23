package br.com.controller;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.model.HibernateUtil;
import br.com.model.Pessoa;


@Controller
public class ControllerTest {
	
	public ControllerTest() {
		System.out.println("Teste");
	}
	
	@RequestMapping(value="/cliente", method = RequestMethod.GET)
	public String retorno(Model model) {
		List<Pessoa> list = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			list = session.createCriteria(Pessoa.class).list();
			session.getTransaction().commit();
			session.close();
			System.out.println("tamanho "+ list.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pessoas", list);
		return "cliente";
	}
	
	@RequestMapping("/salvaCliente")
    public String salvaCliente(HttpServletRequest request) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Pessoa p = new Pessoa();
			p.setNome(request.getParameter("nome"));
			session.save(p);
			transaction.commit();
			session.close();
		}
        return "redirect:/cliente";
    }
	
	@RequestMapping("delete")
	public String deletar(HttpServletRequest request) {
		if(request.getParameter("nome") == null) {
			System.out.println("vazio");
			return "redirect:/cliente";
		}
			
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println(request.getParameter("nome"));
			session.beginTransaction();
			Query query = session.createQuery("delete from Pessoa where UPPER(nome) = UPPER(:nome) ");
			query.setParameter("nome", request.getParameter("nome").toLowerCase());
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cliente";
	}

}

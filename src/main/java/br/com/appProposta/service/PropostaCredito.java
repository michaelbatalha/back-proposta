package br.com.appProposta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.appProposta.model.Proposta;
import br.com.appProposta.repository.PropostaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class PropostaCredito {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@CrossOrigin
    @ApiOperation(value="", notes="Método que irá retornar as Proposta de Crédito")
    @GetMapping("/proposta")
    public List<Proposta> getAllPropostas(){
		System.out.println("CHAMOU SERVIÇO getAllPropostas");
		return propostaRepository.findAll();
    } 	
	
	@CrossOrigin
    @ApiOperation(value="", notes="Método de cadastro da Proposta de Crédito")
    @RequestMapping(method = RequestMethod.POST, value = "/proposta", produces = "application/json")
    public ResponseEntity<Proposta> createProposta(@RequestBody Proposta proposta) {
		System.out.println("CHAMOU SERVIÇO createProposta");

		proposta = trataDados(proposta);
		if(proposta.getNome() == null || 
			proposta.getCpf() == null ||
			proposta.getIdade() == null ||
			proposta.getSexo() == null ||
			proposta.getEstado_civil() == null ||
			proposta.getEstado() == null ||
			proposta.getDependentes() == null ||
			proposta.getRenda() == null) {
			return null;
		}
		if("VI".equals(proposta.getEstado_civil().toUpperCase()) || "DI".equals(proposta.getEstado_civil().toUpperCase())){
			proposta.setResultado("Negado");
			proposta.setLimite("Reprovado pela política de crédito.");
		}else {

			Double valorNegado = new Double(500);
			
    		if(proposta.getRenda().compareTo(valorNegado) == -1) {
    			proposta.setResultado("Negado");
    			proposta.setLimite("Renda baixa");
    		}else {
    			Double valorCalculoRendaDisp = proposta.getRenda()/3;
    			
    			if((valorCalculoRendaDisp.compareTo(new Double(100)) == 1 || valorCalculoRendaDisp.compareTo(new Double(100)) == 0) && 
    					(valorCalculoRendaDisp.compareTo(new Double(499)) == -1 || valorCalculoRendaDisp.compareTo(new Double(100)) == 0)) {
    				proposta.setLimite("Entre 100 - 500");
    			}else if((valorCalculoRendaDisp.compareTo(new Double(999)) == -1 || valorCalculoRendaDisp.compareTo(new Double(999)) == 0)){
    				proposta.setLimite("Entre 500 - 1.000");
    			}else if((valorCalculoRendaDisp.compareTo(new Double(1499)) == -1 || valorCalculoRendaDisp.compareTo(new Double(1499)) == 0)){
    				proposta.setLimite("Entre 1.000 - 1.500");
    			}else if((valorCalculoRendaDisp.compareTo(new Double(1999)) == -1 || valorCalculoRendaDisp.compareTo(new Double(1999)) == 0)){
    				proposta.setLimite("Entre 1.500 - 2.000");
    			}else if((valorCalculoRendaDisp.compareTo(new Double(2000)) == 1 || valorCalculoRendaDisp.compareTo(new Double(2000)) == 0)){
    				proposta.setLimite("Superior a 2.000");
    			}
    			proposta.setResultado("Aprovado");
    		}			
		}
		
    	propostaRepository.save(proposta);
    	return new ResponseEntity<>(proposta, HttpStatus.CREATED);
    } 
	
	@CrossOrigin
    @ApiOperation(value="", notes="Método de delete da Proposta de Crédito")
    @RequestMapping(method = RequestMethod.DELETE, value = "/proposta/{id}", produces = "application/json")	
    public Map<String, Boolean> deleteProposta(@PathVariable(value="id") Long propostaID){
		System.out.println("CHAMOU SERVIÇO deleteProposta");
        Optional<Proposta> proposta = propostaRepository.findById(propostaID);
        if(proposta == null) return null;
        propostaRepository.delete(proposta.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;		
    } 	

	public Proposta trataDados(Proposta proposta) {
		proposta.setCpf(proposta.getCpf().replaceAll("[.-]", ""));
		return proposta;
	}
}
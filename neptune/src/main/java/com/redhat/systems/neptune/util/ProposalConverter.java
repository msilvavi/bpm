package com.redhat.systems.neptune.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.dto.Variable;
import com.redhat.systems.neptune.model.proposal.Proposal;

public class ProposalConverter {

	private static Logger log = Logger.getLogger(ProposalConverter.class);
	
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.Z:SS");
	
	public static Map<String,Object> convertToParams(Proposal proposal){
		Map<String,Object> params = new HashMap<String,Object>();
		addParameter("monto", proposal.getAmount(),params);
		addParameter("arquitecto", proposal.getArchitect(),params);	
		addParameter("fechaArquitectura", proposal.getArchitecture(),params);	
		addParameter("cliente", proposal.getClient(),params);
		addParameter("noRevisionesCliente", proposal.getClientReviews(),params);	
		addParameter("cvePropuesta", proposal.getCode(),params);
		addParameter("comentariosPropuesta", proposal.getComments(),params);
		addParameter("servicios", proposal.getConsultant(),params);
		addParameter("fechaCreacion", proposal.getCreation(),params);
		addParameter("fechaLiberacion", proposal.getDelivery(),params);
		addParameter("archivo", proposal.getFile(),params);
		addParameter("hatpack", proposal.getHatpack(),params);
		addParameter("noPropuesta", proposal.getId(),params);
		addParameter("manager", proposal.getManager(),params);
		addParameter("nombre", proposal.getName(),params);
		addParameter("prioridad", proposal.getPriority(),params);
		addParameter("fechaRevision", proposal.getReview(),params);
		addParameter("fechaServicios", proposal.getServices(),params);
		addParameter("estatus", proposal.getStatus(),params);
		addParameter("cveEstatus", proposal.getStatusCode(),params);
		addParameter("propuestaCalificada", proposal.isQualified(),params);
		addParameter("comentariosCalificacion", proposal.getReviewComments(),params);
		addParameter("arquitecturaCalificada", proposal.isArchitectureQualified(),params);
		addParameter("propuestaAceptada", proposal.isAccepted(),params);
		addParameter("fechaCierre", proposal.getClose(),params);
		return params;
	}
	
	public static Proposal convertFromParams(Map<String,Object> params){
		Proposal proposal = new Proposal();
		proposal.setAmount((params.get("monto")!=null)?(Float)params.get("monto"):null);
		proposal.setArchitect((params.get("arquitecto")!=null)?(String)params.get("arquitecto"):null);
		proposal.setArchitecture((params.get("fechaArquitectura")!=null)?(Date)params.get("fechaArquitectura"):null);
		proposal.setClient((params.get("cliente")!=null)?(String)params.get("cliente"):null);
		proposal.setClientReviews((params.get("noRevisiones")!=null)?(Integer)params.get("noRevisiones"):null);
		proposal.setCode((params.get("cvePropuesta")!=null)?(String)params.get("cvePropuesta"):null);
		proposal.setComments((params.get("comentariosPropuesta")!=null)?(String)params.get("comentariosPropuesta"):null);
		proposal.setConsultant((params.get("servicios")!=null)?(String)params.get("servicios"):null);
		proposal.setCreation((params.get("fechaCreacion")!=null)?(Date)params.get("fechaCreacion"):null);
		proposal.setDelivery((params.get("fechaLiberacion")!=null)?(Date)params.get("fechaLiberacion"):null);
		proposal.setFile((params.get("archivo")!=null)?(String)params.get("archivo"):null);
		proposal.setHatpack((params.get("hatpack")!=null)?(boolean)params.get("hatpack"):null);
		proposal.setId((params.get("noPropuesta")!=null)?(long)params.get("noPropuesta"):null);
		proposal.setManager((params.get("manager")!=null)?(String)params.get("manager"):null);
		proposal.setName((params.get("nombre")!=null)?(String)params.get("nombre"):null);
		proposal.setPriority((params.get("prioridad")!=null)?(int)params.get("prioridad"):null);
		proposal.setReview((params.get("fechaServicios")!=null)?(Date)params.get("fechaServicios"):null);
		proposal.setServices((params.get("fechaServicios")!=null)?(Date)params.get("fechaServicios"):null);
		proposal.setStatus((params.get("estatus")!=null)?(String)params.get("estatus"):null);
		proposal.setStatusCode((params.get("cveEstatus")!=null)?(String)params.get("cveEstatus"):null);
		proposal.setQualified((params.get("propuestaCalificada")!=null)?(Boolean)params.get("propuestaCalificada"):null);
		proposal.setReviewComments((params.get("comentariosCalificacion")!=null)?(String)params.get("comentariosCalificacion"):null);
		proposal.setArchitectureQualified((params.get("arquitecturaCalificada")!=null)?(Boolean)params.get("arquitecturaCalificada"):null);
		return proposal;
	}
	
	public static Proposal convertFromStringsParams(Map<String,String> params){
		Proposal proposal = new Proposal();
		proposal.setAmount((params.get("monto")!=null)?Float.parseFloat(params.get("monto")):null);
		proposal.setArchitect((params.get("arquitecto")!=null)?(String)params.get("arquitecto"):null);
		proposal.setArchitecture((params.get("fechaArquitectura")!=null)?ProposalConverter.createDate(params.get("fechaArquitectura")):null);
		proposal.setClient((params.get("cliente")!=null)?(String)params.get("cliente"):null);
		proposal.setClientReviews((params.get("noRevisiones")!=null)?Integer.parseInt(params.get("noRevisiones")):null);
		proposal.setCode((params.get("cvePropuesta")!=null)?(String)params.get("cvePropuesta"):null);
		proposal.setComments((params.get("comentariosPropuesta")!=null)?(String)params.get("comentariosPropuesta"):null);
		proposal.setConsultant((params.get("servicios")!=null)?(String)params.get("servicios"):null);
		proposal.setCreation((params.get("fechaCreacion")!=null)?ProposalConverter.createDate(params.get("fechaCreacion")):null);
		proposal.setDelivery((params.get("fechaLiberacion")!=null)?ProposalConverter.createDate(params.get("fechaLiberacion")):null);
		proposal.setFile((params.get("archivo")!=null)?(String)params.get("archivo"):null);
		proposal.setHatpack((params.get("hatpack")!=null)?Boolean.parseBoolean(params.get("hatpack")):null);
		proposal.setId((params.get("noPropuesta")!=null)?Long.parseLong(params.get("noPropuesta")):null);
		proposal.setManager((params.get("manager")!=null)?(String)params.get("manager"):null);
		proposal.setName((params.get("nombre")!=null)?(String)params.get("nombre"):null);
		proposal.setPriority((params.get("prioridad")!=null)?Integer.parseInt(params.get("prioridad")):null);
		proposal.setReview((params.get("fechaServicios")!=null)?ProposalConverter.createDate(params.get("fechaServicios")):null);
		proposal.setServices((params.get("fechaServicios")!=null)?ProposalConverter.createDate(params.get("fechaServicios")):null);
		proposal.setStatus((params.get("estatus")!=null)?(String)params.get("estatus"):null);
		proposal.setStatusCode((params.get("cveEstatus")!=null)?(String)params.get("cveEstatus"):null);
		proposal.setQualified((params.get("propuestaCalificada")!=null)?Boolean.parseBoolean(params.get("propuestaCalificada")):null);
		proposal.setReviewComments((params.get("comentariosCalificacion")!=null)?(String)params.get("comentariosCalificacion"):null);
//		proposal.setArchitectureQualified((params.get("arquitecturaCalificada")!=null)?Boolean.parseBoolean(params.get("arquitecturaCalificada")):null);
		return proposal;
	}
	
	public static List<Variable> convertMapToVariables(Map<String,String> params){
		List<Variable> variables = new ArrayList<Variable>();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String name = it.next();
			String value = params.get(name);
			Variable var = new Variable();
			var.setName(ProposalConverter.convertVariableToReadable(name));
			var.setValue(ProposalConverter.convertValueToReadable(value));
			variables.add(var);
		}
		return variables;
	}
	
	private static String convertValueToReadable(String value){
		if(value.equalsIgnoreCase("false")){return "No";}
		if(value.equalsIgnoreCase("true")){return "Si";}
		return value;
	}
	
	private static String convertVariableToReadable(String variable){
		if(variable.equalsIgnoreCase("manager")){return "Account Manager";}
		if(variable.equalsIgnoreCase("initiator")){return "Creado Por";}
		if(variable.equalsIgnoreCase("comentariosPropuesta")){return "Comentarios";}
		if(variable.equalsIgnoreCase("cvePropuesta")){return "Clave de Propuesta";}
		if(variable.equalsIgnoreCase("nombre")){return "Nombre de Propuesta";}
		if(variable.equalsIgnoreCase("prioridad")){return "Prioridad";}
		if(variable.equalsIgnoreCase("arquitecturaCalificada")){return "Propuesta Calificada por Arquitectura";}
		if(variable.equalsIgnoreCase("propuestaAceptada")){return "Propuesta Aceptada por Cliente";}
		if(variable.equalsIgnoreCase("servicios")){return "Propuesta Aceptada por Servicios";}
		if(variable.equalsIgnoreCase("cliente")){return "Cliente";}
		if(variable.equalsIgnoreCase("monto")){return "Monto";}
		if(variable.equalsIgnoreCase("arquitecto")){return "Arquitecto";}
		if(variable.equalsIgnoreCase("fechaCreacion")){return "Fecha de Creacion";}
		if(variable.equalsIgnoreCase("fechaArquitectura")){return "Fecha de Termino de Arquitectura";}
		if(variable.equalsIgnoreCase("cveEstatus")){return "Clave de Estado";}
		if(variable.equalsIgnoreCase("archivo")){return "Archivo de la Propuesta";}
		if(variable.equalsIgnoreCase("comentariosCalificacion")){return "Comentarios del Proceso";}
		if(variable.equalsIgnoreCase("hatpack")){return "Es Hat Pack";}
		if(variable.equalsIgnoreCase("fechaLiberacion")){return "Fecha de Liberacion";}
		if(variable.equalsIgnoreCase("noRevisionesCliente")){return "Revisiones con Cliente";}
		if(variable.equalsIgnoreCase("estatus")){return "Estatus";}
		if(variable.equalsIgnoreCase("propuestaCalificada")){return "Propuesta Aceptada Por Arquitectura";}
		if(variable.equalsIgnoreCase("noRevisiones")){return "No. de Revisiones Internas";}
		if(variable.equalsIgnoreCase("fechaServicios")){return "Fecha de Termino por Serivcios";}
		else{return variable;}
	}
	
	private static Date createDate(String stringDate){
		Date date = null;
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			log.error("Cannot create Date from string",e);
		}
		return date;
	}
	
	private static void addParameter(String paramName, Object param, Map<String,Object> params){
		if(param!=null){
			params.put(paramName, param);
		}
	}
}

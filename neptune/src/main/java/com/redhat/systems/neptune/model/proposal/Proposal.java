package com.redhat.systems.neptune.model.proposal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "PROPOSAL_SEQ", sequenceName = "PROPOSAL_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "PROPOSALS")
public class Proposal {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPOSAL_SEQ")
	@Column(name = "no_propuesta")
	private Long id;
	
	@Column(name = "archivo")
	private String file;
	
	@NotNull
	@Column(name = "cliente")
	private String client;
	
	@Column(name = "cve_propuesta")
	private String code;
	
	@Column(name = "cve_estatus")
	private String statusCode;
	
	@NotNull
	@Column(name = "estatus")
	private String status;
	
	@Column(name = "hatpack")
	private Boolean hatpack;
	
	@Column(name = "fecha_arquitectura")
	private Date architecture;
	
	@NotNull
	@Column(name = "fecha_creacion")
	private Date creation;
	
	@Column(name = "fecha_liberacion")
	private Date delivery;
	
	@Column(name = "fecha_servicios")
	private Date services;
	
	@Column(name = "fecha_cierre")
	private Date close;
	
	@NotNull
	@Column(name = "monto")
	private float amount;
	
	@Column(name = "fecha_revision")
	private Date review;

	@Column(name = "no_revisiones")
	private Integer reviews;
	
	@Column(name = "no_revisiones_cliente")
	private Integer clientReviews;
	
	@NotNull
	@Column(name = "nombre_propuesta")
	private String name;
	
	@NotNull
	@Column(name = "prioridad")
	private Integer priority;
	
	@NotNull
	@Column(name = "account_manager")
	private String manager;
	
	@NotNull
	@Column(name = "arcquitecto")
	private String architect;
	
	@Column(name = "consultor_servicios")
	private String consultant;
	
	@Column(name = "calificada")
	private boolean qualified;
	
	@Column(name = "arquitectura_calificada")
	private boolean architectureQualified;
	
	@Column(name = "propuesta_aceptada")
	private boolean accepted;
	
	@Column(name = "comentarios")
	private String comments;
	
	@Column(name = "comentarios_revision")
	private String reviewComments;
	
	private int taskId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getHatpack() {
		return hatpack;
	}

	public void setHatpack(Boolean hatpack) {
		this.hatpack = hatpack;
	}

	public Date getArchitecture() {
		return architecture;
	}

	public void setArchitecture(Date architecture) {
		this.architecture = architecture;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public Date getServices() {
		return services;
	}

	public void setServices(Date services) {
		this.services = services;
	}

	public Date getReview() {
		return review;
	}

	public void setReview(Date review) {
		this.review = review;
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		this.reviews = reviews;
	}

	public Integer getClientReviews() {
		return clientReviews;
	}

	public void setClientReviews(Integer clientReviews) {
		this.clientReviews = clientReviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getArchitect() {
		return architect;
	}

	public void setArchitect(String architect) {
		this.architect = architect;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public boolean isQualified() {
		return qualified;
	}

	public void setQualified(boolean qualified) {
		this.qualified = qualified;
	}

	public boolean isArchitectureQualified() {
		return architectureQualified;
	}

	public void setArchitectureQualified(boolean architectureQualified) {
		this.architectureQualified = architectureQualified;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}
	
}

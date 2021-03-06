package com.cognizant.ormlearn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "options")
public class Options {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "op_id")
	private int id;
	@Column(name = "op_score")
	private double score;
	@Column(name = "op_text")
	private String text;
	@ManyToOne
	@JoinColumn(name = "op_qt_id")
	private Question question;

	@OneToMany(mappedBy = "options", fetch = FetchType.EAGER)
	private Set<AttemptOption> attemptOption;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Set<AttemptOption> getAttemptOption() {
		return attemptOption;
	}

	public void setAttemptOption(Set<AttemptOption> attemptOption) {
		this.attemptOption = attemptOption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Options [id=" + id + ", score=" + score + ", text=" + text + "]";
	}

}
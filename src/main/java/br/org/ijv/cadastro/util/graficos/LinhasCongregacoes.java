package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.repository.Congregacoes;

@Named
@ViewScoped
public class LinhasCongregacoes extends PessoaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Congregacoes congregacoes;
	
	private LineChartModel animatedModelLinha;
	
	public LineChartModel getAnimatedModelLinha() {
		return animatedModelLinha;
	}
		
	@PostConstruct
	public void init() {
		createAnimatedModels();
		
	}
		
	private void createAnimatedModels() {

		animatedModelLinha = initLinearModel();
		animatedModelLinha.setTitle("Gráfico de linhas por congregações");
		animatedModelLinha.setAnimate(true);
		animatedModelLinha.setLegendPosition("ne");
		animatedModelLinha.setShowPointLabels(true);
		animatedModelLinha.getAxes().put(AxisType.X, new CategoryAxis("Funções"));
		Axis yAxis = animatedModelLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Pessoas");
		yAxis.setMin(0);
		yAxis.setMax(300);
		
	}
	
	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		consultar();
		Congregacao congregacao = new Congregacao();
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			LineChartSeries congregacoes = new LineChartSeries();
			congregacoes.setLabel(congregacao.getNomeCongregacao());

			congregacoes.set("Membros", pessoas.membrosAtivosCongregacao(congregacao).size());
			congregacoes.set("Colaborador", pessoas.colaboradorCongregacao(congregacao).size());
			congregacoes.set("Intercessor", pessoas.intercessorCongregacao(congregacao).size());
			congregacoes.set("Louvor", pessoas.louvorCongregacao(congregacao).size());
			congregacoes.set("Diáconos", pessoas.diaconoCongregacao(congregacao).size());
			congregacoes.set("Pastores", pessoas.pastorCongregacao(congregacao).size());

			model.addSeries(congregacoes);
		}
		return model;
	}

}

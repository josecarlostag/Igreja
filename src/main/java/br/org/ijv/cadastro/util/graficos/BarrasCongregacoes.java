package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.repository.Congregacoes;

@Named
@ViewScoped
public class BarrasCongregacoes extends PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LineChartModel areaModel;
	
	private BarChartModel barModel;
	
	
	
	public BarChartModel getBarModel() {
		return barModel;
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}
	
	@Inject
	private Congregacoes congregacoes;
	
	@PostConstruct
	public void init() {
		createAnimatedModels();
	}
	
	private BarChartModel animatedModelBarra;
	
	public BarChartModel getAnimatedModelBarra() {
		return animatedModelBarra;
	}
	
	private void createAnimatedModels() {

		animatedModelBarra = initBarModel();
		animatedModelBarra.setTitle("Gráfico de barras por congregações");

		animatedModelBarra.setShowPointLabels(true);
		animatedModelBarra.setAnimate(true);
		animatedModelBarra.setLegendPosition("ne");

		Axis yAxis = animatedModelBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Pessoas");
		yAxis.setMin(0);
		yAxis.setMax(300);
	}
	
	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		consultar();
		Congregacao congregacao = new Congregacao();
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			ChartSeries congregacoes = new ChartSeries();
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
